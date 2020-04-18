package Tests.Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TeamTest {

    FootballManagementSystem system;
    Team team;
    TeamOwner teamOwner1;
    Admin admin;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        team = system.getDatabase().getTeams().get(0);
        teamOwner1 = (TeamOwner) team.getTeamOwners().get(0);
        admin = (Admin) system.getDatabase().getListOfAllSpecificUsers("Admin").get(0);
    }

    @Test
    public void addTeamOwner() {
        TeamOwner teamOwner = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        Player player = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        if(!team.getTeamOwners().contains(teamOwner)) {
            assertTrue(team.addTeamOwner(teamOwner));
        }
        if(!team.getTeamOwners().contains(player)) {
            assertTrue(team.addTeamOwner(player));
        }
        assertFalse(team.addTeamOwner(team.getTeamOwners().get(0)));
    }

    @Test
    public void addTeamManager() {
        TeamManager teamManager = new TeamManager("Doron" , "Shamai" , "Mail" , team ,100);
        assertTrue(team.addTeamManager(teamManager));
        assertFalse(team.addTeamManager(team.getTeamManagers().get(0)));

    }

    @Test
    public void addPlayer() {
        Player player1 = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        Player player2 = new Player("Doron" , "Shamai" , "Mail",null, player1.getBirthDate() , player1.getRole() , 100);
        assertTrue(team.addPlayer(player2));
        assertFalse(team.addPlayer(team.getPlayers().get(0)));
    }

    @Test
    public void addCoach() {
        Coach coach = (Coach) system.getDatabase().getListOfAllSpecificUsers("Coach").get(0);
        if(!team.getCoaches().contains(coach)) {
            assertTrue(team.addCoach(coach));
        }
        assertFalse(team.addCoach(team.getCoaches().get(0)));
    }

    @Test
    public void removeTeamOwner() {
        TeamOwner teamOwner = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        assertFalse(team.removeTeamOwner(teamOwner));
        assertTrue(team.removeTeamOwner(team.getTeamOwners().get(0)));
    }

    @Test
    public void removeTeamManager() {
        TeamManager teamManager = new TeamManager("Doron" , "Shamai" , "Mail" , team ,100);
        if(team.getCoaches().size()>1) {
            assertFalse(team.removeTeamManager(teamManager));
            assertTrue(team.removeTeamManager(team.getTeamManagers().get(0)));
        }
    }

    @Test
    public void removePlayer() {
        Player player1 = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        Player player2 = new Player("Doron" , "Shamai" , "Mail",null, player1.getBirthDate() , player1.getRole() , 100);
        if(team.getPlayers().size()>11 && team.getPlayers().contains(player1)) {
            assertTrue(team.removePlayer(player1));
        }
        assertFalse(team.removePlayer(player2));
    }

    @Test
    public void removeCoach() {
        Coach coach = (Coach) system.getDatabase().getListOfAllSpecificUsers("Coach").get(0);
        if(team.getCoaches().size()>1) {
            assertTrue(team.removeCoach(coach));
            assertFalse(team.removeCoach(team.getCoaches().get(0)));
        }
    }

    @Test
    public void isActive() {
        assertTrue(team.isActive());

        teamOwner1.closeTeam(team);
        assertFalse(team.isActive());
    }

    @Test
    public void isPermanentlyClosed() {
        assertFalse(team.isPermanentlyClosed());

        admin.permanentlyCloseTeam(team);
        assertTrue(team.isPermanentlyClosed());

    }
}