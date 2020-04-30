package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamOwnerTest {
    TeamOwner teamOwner;
    FootballManagementSystem system;
    Team team;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        User userTeamOwner= admin.addNewTeamOwner("team", "owner", "teamOwner@gmail.com");
        teamOwner = (TeamOwner) userTeamOwner.checkUserRole("TeamOwner");
    }

    @Test
    public void getAppointedTeamOwners() {
        assertNotNull(teamOwner.getAppointedTeamOwners());
    }

    @Test
    public void getAppointedTeamManagers() {
        assertNotNull(teamOwner.getAppointedTeamManagers());
    }

    @Test
    public void addTeam() {
        assertEquals(teamOwner.getTeamsToManage().size(),0);
        teamOwner.addTeam(team);
        assertEquals(teamOwner.getTeamsToManage().size(),1);
        teamOwner.addTeam(team);
        assertEquals(teamOwner.getTeamsToManage().size(),1);
    }

    @Test
    public void addTeamPersonalPage() {
    }

    @Test
    public void getTeamsToManage() {
        assertNotNull(teamOwner.getTeamsToManage());
    }


    @Test
    public void getTeamById() {
    }

    @Test
    public void removeTeam() {
    }

    @Test
    public void appointTeamOwner() {
    }

    @Test
    public void appointTeamManager() {
    }

    @Test
    public void removeAppointTeamOwner() {
    }

    @Test
    public void removeAppointTeamManager() {
    }

    @Test
    public void closeTeam() {
    }

    @Test
    public void reopenTeam() {
    }

    @Test
    public void myRole() {
    }
}