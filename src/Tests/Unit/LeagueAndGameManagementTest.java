package Tests.Unit;

import Data.Database;
import Domain.*;
import Presentation.Fan;
import Service.FootballManagementSystem;
import Presentation.Referee;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class LeagueAndGameManagementTest {

    FootballManagementSystem system;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
    }

    @Test
    public void registrationForGamesAlerts() {
        LeagueInSeason league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020),200);
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        Team team0 = (Team) system.database.searchObject("team0").get(0);
        Team team1 = (Team) system.database.searchObject("team1").get(0);
        Field field = new Field("Tel-Aviv", 10000);
        Referee mainReferee= (Referee) system.database.getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.database.getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.database.getListOfAllSpecificUsers("Referee").get(2));
        Game game = new Game(new Date(10, 10, 2020), field, mainReferee, sideReferees ,team0, team1,league);
        List<Game> games = new LinkedList<>();
        games.add(game);
        ReceiveAlerts receive = new ReceiveAlerts(false, false);
        assertTrue(system.leagueAndGameManagement.registrationForGamesAlerts(fan, games, receive));
        assertFalse(system.leagueAndGameManagement.registrationForGamesAlerts(fan, games, receive));
    }

    @Test
    public void configureNewLeague() {
        assertTrue(system.leagueAndGameManagement.configureNewLeague("Alofot", "2"));
        assertFalse(system.leagueAndGameManagement.configureNewLeague("Alofot", "2"));
    }

    @Test
    public void configureNewSeason() {
        assertTrue(system.leagueAndGameManagement.configureNewSeason(2021));
        assertFalse(system.leagueAndGameManagement.configureNewSeason(2021));
    }

    @Test
    public void configureLeagueInSeason() {
        LeagueInSeason leagueInSeason = system.leagueAndGameManagement.configureLeagueInSeason("Leomit", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertNull(leagueInSeason);
        LeagueInSeason leagueInSeason2 = system.leagueAndGameManagement.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertEquals("Haal", leagueInSeason2.getLeague().getName());
        assertEquals(2020, leagueInSeason2.getSeason().getYear());
    }

    @Test
    public void assignRefToLeague() {
        LeagueInSeason leagueInSeason = system.leagueAndGameManagement.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        Referee referee= (Referee) system.database.getListOfAllSpecificUsers("Referee").get(0);
        assertTrue(system.leagueAndGameManagement.assignRefToLeague(leagueInSeason, referee));
        assertFalse(system.leagueAndGameManagement.assignRefToLeague(leagueInSeason, referee));
    }

    @Test
    public void changeScorePolicy() {

    }

    @Test
    public void changeAssignmentPolicy() {
        LeagueInSeason haal = system.leagueAndGameManagement.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertTrue(system.leagueAndGameManagement.changeAssignmentPolicy(haal, new PlayOnceWithEachTeamPolicy()));
        assertTrue(system.leagueAndGameManagement.changeAssignmentPolicy(haal, new PlayTwiceWithEachTeamPolicy()));
    }

    @Test
    public void assignGames() {
        LeagueInSeason haal = system.leagueAndGameManagement.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for(Object r : system.database.getListOfAllSpecificUsers("Referee"))
            system.leagueAndGameManagement.assignRefToLeague(haal, (Referee) r);
        assertTrue(system.leagueAndGameManagement.assignGames(haal, system.getDates()));
    }

    @Test
    public void closeTeam() {
    }

    @Test
    public void reopeningTeam() {
    }

    @Test
    public void permanentlyCloseTeam() {
    }

    @Test
    public void addTeamToLeague() {
    }

    @Test
    public void calculateLeagueScore() {
    }

    @Test
    public void calculateGameScore() {
    }

    @Test
    public void changeRegistrationFee() {
    }
}