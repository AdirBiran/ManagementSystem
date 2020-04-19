package Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {
    FootballManagementSystem system;
    Admin admin;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        admin = new Admin("admin", "system","adminsystem@gmail.com", system.getDatabase());
    }
    @Test
    public void addUser() {
        int amountOfUsers = system.getDatabase().getListOfAllSpecificUsers("UnionRepresentative").size();
        UnionRepresentative union = new UnionRepresentative("union", "rep", "unionrep@gmail.com");
        admin.addUser("AAA123", union);
        assertEquals(amountOfUsers+1,system.getDatabase().getListOfAllSpecificUsers("UnionRepresentative").size(), 0);
    }

    @Test
    public void removeUser() {
        Player player = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        assertEquals(player.getMail(),admin.removeUser(player.getID()));
        TeamOwner teamOwner = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        assertEquals("", admin.removeUser(teamOwner.getID()));
    }

    @Test
    public void permanentlyCloseTeam() {
        Team team = system.getDatabase().getTeams().get(0);
        admin.permanentlyCloseTeam(team);
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