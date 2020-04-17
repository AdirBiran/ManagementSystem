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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        Team team0 = (Team) system.getDatabase().searchObject("team0").get(0);
        Team team1 = (Team) system.getDatabase().searchObject("team1").get(0);
        Field field = new Field("Tel-Aviv", 10000);
        Referee mainReferee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(2));
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
        Database database = new Database();
        LeagueAndGameManagement leagueAndGameManagement = new LeagueAndGameManagement(database);
        //GameAssignmentPolicy assignmentPolicy
        //leagueAndGameManagement.configureLeagueInSeason("Haal", "2020", )

    }

    @Test
    public void assignRefToLeague() {
    }

    @Test
    public void changeScorePolicy() {
    }

    @Test
    public void changeAssignmentPolicy() {
    }

    @Test
    public void assignGames() {
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