package Tests.Unit;

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
        assertTrue(system.getUserManagement().registrationForGamesAlerts(fan, games, receive));
        assertFalse(system.getUserManagement().registrationForGamesAlerts(fan, games, receive));
    }

    @Test
    public void logInUserToSystem() {
    }

    @Test
    public void registrationToSystem() {
    }

    @Test
    public void registrationToFollowUp() {
    }

    @Test
    public void appointmentTeamOwner() {
    }

    @Test
    public void appointmentTeamManager() {
    }

    @Test
    public void removeAppointmentTeamOwner() {
    }

    @Test
    public void removeAppointmentTeamManager() {
    }

    @Test
    public void updateRole() {
    }

    @Test
    public void updateTraining() {
    }

    @Test
    public void deactivateField() {
    }

    @Test
    public void notificationForAppointment() {
    }
}