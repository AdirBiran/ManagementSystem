package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamOwnerTest {
    TeamOwner teamOwner;
    FootballManagementSystem system;
    Team team;
    User user;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        String  leagueId = system.dataReboot();
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        team = league.getTeams().get(0);
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        User userTeamOwner= admin.addNewTeamOwner("team", "owner", "teamOwner@gmail.com");
        teamOwner = (TeamOwner) userTeamOwner.checkUserRole("TeamOwner");
        user=new User("shir","ben dor","123456789","shir@gmail.com");
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
        teamOwner.addTeam(team);
        assertNotNull(teamOwner.getTeamById(team.getID()));

    }

    @Test
    public void removeTeam() {
        teamOwner.addTeam(team);
        assertEquals(teamOwner.getTeamsToManage().size(),1);
        teamOwner.removeTeam(team);
        assertEquals(teamOwner.getTeamsToManage().size(),0);
    }

    @Test
    public void appointTeamOwner() {
        assertFalse(teamOwner.appointTeamOwner(user,team));
        teamOwner.addTeam(team);
        assertTrue(teamOwner.appointTeamOwner(user,team));

    }

    @Test
    public void appointTeamManager() {
        assertFalse(teamOwner.appointTeamManager(user,team,30,false,false));
        teamOwner.addTeam(team);
        assertTrue(teamOwner.appointTeamManager(user,team,30,false,false));
    }

    @Test
    public void removeAppointTeamOwner() {
        assertFalse(teamOwner.removeAppointTeamOwner(user,team));
        teamOwner.addTeam(team);
        teamOwner.appointTeamOwner(user,team);
        TeamOwner teamOwner1 = (TeamOwner) user.checkUserRole("TeamOwner");
        User user1=new User("shir","ben dor","123456789","shir@gmail.com");
        teamOwner1.appointTeamManager(user1,team,30,false,false);
        assertTrue(teamOwner.removeAppointTeamOwner(user,team));
     //   assertEquals(0,teamOwner1.getAppointedTeamManagers().size());
    }

    @Test
    public void removeAppointTeamManager() {
        assertFalse(teamOwner.removeAppointTeamManager(user,team));
        teamOwner.addTeam(team);
        teamOwner.appointTeamManager(user,team,30,false,false);
        assertTrue(teamOwner.removeAppointTeamManager(user,team));
    }

    @Test
    public void closeTeam() {
        assertFalse(teamOwner.closeTeam(team));
        teamOwner.addTeam(team);
        assertTrue(teamOwner.closeTeam(team));
    }

    @Test
    public void reopenTeam() {
        assertFalse(teamOwner.reopenTeam(team));
        teamOwner.addTeam(team);
        assertFalse(teamOwner.reopenTeam(team));
        teamOwner.closeTeam(team);
        assertTrue(teamOwner.reopenTeam(team));
    }

    @Test
    public void myRole() {
        assertEquals(teamOwner.myRole(),"TeamOwner");
    }

    @Test
    public void createTeam() {

    }
}