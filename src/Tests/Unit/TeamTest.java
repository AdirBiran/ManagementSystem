package Unit;

import Data.Database;
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
    User teamOwner1, teamOwner2,admin;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        team1 = league.getTeams().get(1);
        teamOwner1 = team.getTeamOwners().get(0);
        teamOwner2 = UserFactory.getNewTeamOwner("first", "last", "mcndhffd@gmail.com");
        assertNotNull(teamOwner2);
        admin = Database.getSystemAdmins().get(0);
    }

    @Test
    public void addTeamOwner() {

        Player player1 = (Player) Database.getListOfAllSpecificAssets("Player").get(0);
        assertNotNull(player1);
        Object[] player2 = UserFactory.getNewPlayer("Doron" , "Shamai" , "shamaid@gmail.com", player1.getBirthDate() , player1.getRole() , 100);
        assertNotNull(player2);
        assertTrue(team.addTeamOwner(teamOwner2));
        assertTrue(team.addTeamOwner((User)player2[0]));
        assertFalse(team.addTeamOwner(teamOwner2));
    }

    @Test
    public void addTeamManager() {
        Object[] teamManager =UserFactory.getNewTeamManager("Doron" , "Shamai" , "shamaid@gfffmail.com" ,100);
        assertNotNull(teamManager);

        assertTrue(team.addTeamManager((User)teamManager[0]));
        assertFalse(team.addTeamManager((User)teamManager[0]));

    }

    @Test
    public void addPlayer() {
        Player player1 = (Player) Database.getListOfAllSpecificAssets("Player").get(0);
        assertNotNull(player1);
        Object[] player2 = UserFactory.getNewPlayer("Doron" , "Shamai" , "shafffmaid@gmail.com", player1.getBirthDate() , player1.getRole() , 100);
        assertNotNull(player2);

        assertTrue(team.addPlayer((Player) player2[1]));
        assertFalse(team.addPlayer((Player) player2[1]));
    }

    @Test
    public void addCoach() {
        Coach coach = (Coach) Database.getListOfAllSpecificAssets("Coach").get(0);
        assertNotNull(coach);
        Object[] coach1 = UserFactory.getNewCoach("Doron" , "Shamai" , "shadddmdsaid@gmail.com",coach.getTraining(),coach.getRole(),100);
        assertNotNull(coach1);
        assertTrue(team.addCoach((Coach) coach1[1]));
        assertFalse(team.addCoach((Coach) coach1[1]));
        
    }

    @Test
    public void removeTeamOwner() {

        User teamOwner = team.getTeamOwners().get(0);
        assertNotNull(teamOwner);
        assertFalse(team.removeTeamOwner(teamOwner));
        assertTrue(team.addTeamOwner(teamOwner2));
        assertTrue(team.removeTeamOwner(teamOwner));
    }

    @Test
    public void removeTeamManager() {

        Object[] teamManager = UserFactory.getNewTeamManager("Doron" , "Shamai" , "shamaid@gmailaa.com"  ,100);
        assertNotNull(teamManager);
        assertFalse(team.removeTeamManager((User)teamManager[0]));
        team.addTeamManager((User)teamManager[0]);
        assertTrue(team.removeTeamManager((User)teamManager[0]));

    }

    @Test
    public void removePlayer() {

        Object[] player2 = UserFactory.getNewPlayer("Doron" , "Shamai" , "shamaid@gmail.com", new Date(98, 1, 4) , "" , 100);
        assertNotNull(player2);

        assertFalse(team.removePlayer((Player) player2[1]));
        team.addPlayer((Player) player2[1]);
        assertTrue(team.removePlayer((Player) player2[1]));

    }

    @Test
    public void removeCoach() {

        Object[] coach1 = UserFactory.getNewCoach("Doron" , "Shamai" , "shamaid@gmail.com","","",100);
        assertNotNull(coach1);
        assertFalse(team.removeCoach((Coach) coach1[1]));
        team.addCoach((Coach) coach1[1]);
        assertTrue(team.removeCoach((Coach) coach1[1]));
    }


    @Test
    public void isPermanentlyClosed() {
        assertFalse(team.isPermanentlyClosed());

        ((AdminAuthorization)(admin.getRoles().get(0))).closeTeamPermanently(team);
        assertTrue(team.isPermanentlyClosed());

    }

}