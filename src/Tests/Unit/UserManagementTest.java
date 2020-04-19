package Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserManagementTest {

    FootballManagementSystem system;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
    }

    @Test
    public void logInUserToSystem() {
        system.getUserManagement().registrationToSystem("liron@gmail.com", "liron123", "liron", "Ben Yakov", "0504874960", "yosef2");
        assertNotNull(system.getUserManagement().logInUserToSystem("liron@gmail.com","liron123"));

    }

    @Test
    public void registrationToSystem() {
        assertTrue(system.getUserManagement().registrationToSystem("liron@gmail.com", "liron123", "liron", "Ben Yakov", "0504874960", "yosef2"));
        assertFalse(system.getUserManagement().registrationToSystem("liron@gmail.com", "liron123", "liron", "Ben Yakov", "0504874960", "yosef2"));
    }

    @Test
    public void registrationForGamesAlerts() {
        LeagueInSeason league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020, new Date(120,4,1)),200);
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        Team team0 = system.getDatabase().getTeams().get(0);
        Team team1 = system.getDatabase().getTeams().get(0);
        Field field = new Field("Tel-Aviv", 10000, 1500);
        Referee mainReferee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(2));
        Game game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees ,team0, team1,league);
        List<Game> games = new LinkedList<>();
        games.add(game);
        ReceiveAlerts receive = new ReceiveAlerts(false, false);
        assertTrue(system.getUserManagement().registrationForGamesAlerts(fan, games, receive));
        assertFalse(system.getUserManagement().registrationForGamesAlerts(fan, games, receive));
    }
}