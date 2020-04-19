package Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TeamTest {

    FootballManagementSystem system;
    Team team;
    Team team1;
    TeamOwner teamOwner1;
    Admin admin;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        team = system.getDatabase().getTeams().get(0);
        team1 = system.getDatabase().getTeams().get(1);
        teamOwner1 = (TeamOwner) team.getTeamOwners().get(0);
        admin = (Admin) system.getDatabase().getListOfAllSpecificUsers("Admin").get(0);
    }

    @Test
    public void addTeamOwner() {
        TeamOwner teamOwner = new TeamOwner("Doron" , "Shamai" , "shamaid@gmail.com" );
        system.getDatabase().addUser("AAa123", teamOwner);
        Player player1 = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        Player player2 = new Player("Doron" , "Shamai" , "shamaid@gmail.com",null, player1.getBirthDate() , player1.getRole() , 100);

        system.getDatabase().addUser("AAa123", player2);

        assertTrue(team.addTeamOwner(teamOwner));
        assertTrue(team.addTeamOwner(player2));

        assertFalse(team.addTeamOwner(teamOwner));
    }

    @Test
    public void addTeamManager() {
        TeamManager teamManager = new TeamManager("Doron" , "Shamai" , "shamaid@gmail.com" , team1 ,100);
        system.getDatabase().addUser("AAa123", teamManager);

        assertTrue(team.addTeamManager(teamManager));
        assertFalse(team.addTeamManager(teamManager));

    }

    @Test
    public void addPlayer() {
        Player player1 = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        Player player2 = new Player("Doron" , "Shamai" , "shamaid@gmail.com",null, player1.getBirthDate() , player1.getRole() , 100);
        system.getDatabase().addUser("AAa123", player2);

        assertTrue(team.addPlayer(player2));
        assertFalse(team.addPlayer(player2));
        //assertFalse(team.addPlayer(team.getPlayers().get(0)));
    }

    @Test
    public void addCoach() {
        Coach coach = (Coach) system.getDatabase().getListOfAllSpecificUsers("Coach").get(0);
        Coach coach1 = new Coach("Doron" , "Shamai" , "shamaid@gmail.com",null,coach.getTraining(),coach.getRole(),100);
        system.getDatabase().addUser("AAa123", coach1);

        assertTrue(team.addCoach(coach1));
        assertFalse(team.addCoach(coach1));
        
    }

    @Test
    public void removeTeamOwner() {
        TeamOwner teamOwner = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        assertFalse(teamOwner.getTeam().get(0).removeTeamOwner(teamOwner));

        TeamOwner to = (TeamOwner)system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(1);
        system.getDatabase().addUser("AAa123", to);
        teamOwner.appointmentTeamOwner(to, teamOwner.getTeam().get(0));
        assertTrue(teamOwner.getTeam().get(0).removeTeamOwner(to));
    }

    @Test
    public void removeTeamManager() {

        TeamManager teamManager = new TeamManager("Doron" , "Shamai" , "shamaid@gmail.com" , team1 ,100);
        system.getDatabase().addUser("AAa123", teamManager);

        assertFalse(team.removeTeamManager(teamManager));
        teamOwner1.appointmentTeamManager(teamManager,team);
        assertTrue(team.removeTeamManager(teamManager));

    }

    @Test
    public void removePlayer() {
        Player player1 = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        Player player2 = new Player("Doron" , "Shamai" , "shamaid@gmail.com",null, player1.getBirthDate() , player1.getRole() , 100);

        system.getDatabase().addUser("AAa123", player2);

        assertFalse(team.removePlayer(player2));
        team.addPlayer(player2);
        assertTrue(team.removePlayer(player2));

    }

    @Test
    public void removeCoach() {
        Coach coach = (Coach) system.getDatabase().getListOfAllSpecificUsers("Coach").get(0);
        Coach coach1 = new Coach("Doron" , "Shamai" , "shamaid@gmail.com",null,coach.getTraining(),coach.getRole(),100);
        system.getDatabase().addUser("AAa123", coach1);

        assertFalse(team.removeCoach(coach1));
        team.addCoach(coach1);
        assertTrue(team.removeCoach(coach1));
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