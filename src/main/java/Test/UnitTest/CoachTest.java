package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoachTest {

    FootballManagementSystem system;
    Coach coach;
    Team team;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        User userTeamOwner= admin.addNewTeamOwner("team", "owner", "teamOwner@gmail.com");
        coach = (Coach) userTeamOwner.checkUserRole("Coach");
    }
    @Test
    public void getTraining() {
    }

    @Test
    public void getRole() {
        assertEquals(coach.getRoleInTeam(),"Coach");
    }

    @Test
    public void setTraining() {
    }

    @Test
    public void setRole() {
    }

    @Test
    public void getID() {
    }

    @Test
    public void deactivate() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void setPrice() {
    }

    @Test
    public void getTeams() {
    }

    @Test
    public void addTeam() {
    }

    @Test
    public void removeTeam() {
    }

    @Test
    public void isActive() {
    }

    @Test
    public void reactivate() {
    }

    @Test
    public void myRole() {
    }

    @Test
    public void testToString() {
    }
}