package Unit;

import Data.Database;
import Domain.*;
import Domain.Authorization.AdminAuthorization;
import Service.FootballManagementSystem;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminAuthorizationTest {
    FootballManagementSystem system;
    AdminAuthorization admin;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        User userAdmin = UserFactory.getNewAdmin("Aaa112233","ad", "min", "adminTest@gmail.com");
        assertNotNull(userAdmin);
        admin = (AdminAuthorization) userAdmin.getRoles().get(0);
    }
    @Test
    public void addNewPlayer() {

        int amountOfUsers = Database.getListOfAllSpecificAssets("Player").size();
        admin.addNewPlayer("best", "player","bestPlayer@mail.com", new Date(), "best one", 200);
        assertEquals(amountOfUsers+1,Database.getListOfAllSpecificAssets("Player").size(), 0);
    }

    @Test
    public void removeUser() {
        Player player = (Player) Database.getListOfAllSpecificAssets("Player").get(0);
        assertEquals(player.getUser().getMail(),admin.removeUser(player.getID()));
        Coach coach = (Coach) Database.getListOfAllSpecificAssets("Coach").get(0);
        int i;
        assertEquals(coach.getUser().getMail(), admin.removeUser(coach.getUser().getID()));
    }

    @Test
    public void permanentlyCloseTeam() {
        Team team = Database.getTeams().get(0);
        admin.closeTeamPermanently(team);
        assertTrue(team.isPermanentlyClosed());
        assertFalse(team.isActive());
    }

    @Test
    public void responseToComplaint() {

    }

    @Test
    public void viewLog() {
    }

    @Test
    public void trainModel() {
    }
}