package Tests.Unit;

import Data.Database;
import Domain.*;
import Domain.Fan;
import Service.FootballManagementSystem;
import Domain.Referee;
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
        LeagueInSeason league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020, new Date(120,4,1)),200);
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        Team team0 = (Team) system.getDatabase().searchObject("team0").get(0);
        Team team1 = (Team) system.getDatabase().searchObject("team1").get(0);
        Field field = new Field("Tel-Aviv", 10000);
        Referee mainReferee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(2));
        Game game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees ,team0, team1,league);
        List<Game> games = new LinkedList<>();
        games.add(game);
        ReceiveAlerts receive = new ReceiveAlerts(false, false);
        assertTrue(system.getLeagueAndGameManagement().registrationForGamesAlerts(fan, games, receive));
        assertFalse(system.getLeagueAndGameManagement().registrationForGamesAlerts(fan, games, receive));
    }

    @Test
    public void configureNewLeague() {
        assertTrue(system.getLeagueAndGameManagement().configureNewLeague("Alofot", "2"));
        assertFalse(system.getLeagueAndGameManagement().configureNewLeague("Alofot", "2"));
    }

    @Test
    public void configureNewSeason() {
        assertTrue(system.getLeagueAndGameManagement().configureNewSeason(2021, new Date(120,4,1)));
        assertFalse(system.getLeagueAndGameManagement().configureNewSeason(2021, new Date(120,4,1)));
    }

    @Test
    public void configureLeagueInSeason() {
        LeagueInSeason leagueInSeason = system.getLeagueAndGameManagement().configureLeagueInSeason("Leomit", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertNull(leagueInSeason);
        LeagueInSeason leagueInSeason2 = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertEquals("Haal", leagueInSeason2.getLeague().getName());
        assertEquals(2020, leagueInSeason2.getSeason().getYear());
    }

    @Test
    public void assignRefToLeague() {
        LeagueInSeason leagueInSeason = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        Referee referee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        assertTrue(system.getLeagueAndGameManagement().assignRefToLeague(leagueInSeason, referee));
        assertFalse(system.getLeagueAndGameManagement().assignRefToLeague(leagueInSeason, referee));
    }

    @Test
    public void changeScorePolicy() {
        LeagueInSeason leagueInSeason = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertTrue(system.getLeagueAndGameManagement().changeScorePolicy(leagueInSeason, new StandardScorePolicy()));

    }

    @Test
    public void changeAssignmentPolicy() {
        LeagueInSeason haal = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertTrue(system.getLeagueAndGameManagement().changeAssignmentPolicy(haal, new PlayOnceWithEachTeamPolicy()));
        assertTrue(system.getLeagueAndGameManagement().changeAssignmentPolicy(haal, new PlayTwiceWithEachTeamPolicy()));
    }

    @Test
    public void assignGames() {
        LeagueInSeason haal = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for(Object r : system.getDatabase().getListOfAllSpecificUsers("Referee"))
            system.getLeagueAndGameManagement().assignRefToLeague(haal, (Referee) r);
        assertTrue(system.getLeagueAndGameManagement().assignGames(haal, system.getDates()));
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