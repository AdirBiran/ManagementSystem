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

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        team = system.getDatabase().getTeams().get(0);
    }

    @Test
    public void addTeamOwner() {
        TeamOwner teamOwner = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        Player player = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        assertTrue(team.addTeamOwner(teamOwner));
        assertTrue(team.addTeamOwner(player));
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
        assertTrue(team.addCoach(coach));
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
        assertFalse(team.removeTeamManager(teamManager));
        assertTrue(team.removeTeamManager(team.getTeamManagers().get(0)));
    }

    @Test
    public void removePlayer() {
        Player player1 = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        Player player2 = new Player("Doron" , "Shamai" , "Mail",null, player1.getBirthDate() , player1.getRole() , 100);
        assertTrue(team.removePlayer(player1));
        assertFalse(team.removePlayer(player2));
    }

    @Test
    public void removeCoach() {
    }

    @Test
    public void isActive() {
    }

    @Test
    public void isPermanentlyClosed() {
    }
}