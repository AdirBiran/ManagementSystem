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
    Admin admin;
    Team team;
    User userTeamOwner;
    User user;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        String  leagueId = system.dataReboot();
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        team = league.getTeams().get(0);
        admin = (Admin) system.getAdmin();
        userTeamOwner= admin.addNewTeamOwner("team", "owner", "teamOwner@gmail.com");
        teamOwner = (TeamOwner) userTeamOwner.checkUserRole("TeamOwner");
        user=admin.addNewTeamManager("team", "management", "manage@gmail.com", 1200, false, false);
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
        assertFalse(teamOwner.appointTeamOwner(user,team.getID()));
        teamOwner.addTeam(team);
        assertTrue(teamOwner.appointTeamOwner(user,team.getID()));

    }

    @Test
    public void appointTeamManager() {
        assertFalse(teamOwner.appointTeamManager(user,team.getID(),30,false,false));
        teamOwner.addTeam(team);
        assertTrue(teamOwner.appointTeamManager(user,team.getID(),30,false,false));
    }

    @Test
    public void removeAppointTeamOwner() {
        team.addTeamOwner(userTeamOwner,true);
        User user1 = admin.addNewCoach("new", "user", "coach@gamil.com", Coach.TrainingCoach.UEFA_B, Coach.RoleCoach.main, 1100);
        teamOwner.appointTeamOwner(user1,team.getID());
        TeamOwner teamOwner1 = (TeamOwner) user1.checkUserRole("TeamOwner");
        teamOwner1.appointTeamOwner(user, team.getID());
        assertTrue(teamOwner.removeAppointTeamOwner(user1,team.getID()));
        assertNull(user1.checkUserRole("TeamOwner"));
        assertNull(user.checkUserRole("TeamOwner"));
    }

    @Test
    public void removeAppointTeamManager() {
        assertFalse(teamOwner.removeAppointTeamManager(user,team.getID()));
        team.addTeamOwner(userTeamOwner,true);
        teamOwner.appointTeamManager(user,team.getID(),30,false,false);
        assertTrue(teamOwner.removeAppointTeamManager(user,team.getID()));
        assertNull(user.checkUserRole("TeamManager"));
    }

    @Test
    public void closeTeam() {
        assertFalse(teamOwner.closeTeam(team.getID()));
        teamOwner.addTeam(team);
        assertTrue(teamOwner.closeTeam(team.getID()));
    }

    @Test
    public void reopenTeam() {
        assertFalse(teamOwner.reopenTeam(team.getID()));
        teamOwner.addTeam(team);
        assertFalse(teamOwner.reopenTeam(team.getID()));
        teamOwner.closeTeam(team.getID());
        assertTrue(teamOwner.reopenTeam(team.getID()));
    }

    @Test
    public void myRole() {
        assertEquals(teamOwner.myRole(),"TeamOwner");
    }

    @Test
    public void createTeam() {

    }
}