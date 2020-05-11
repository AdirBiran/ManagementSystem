package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TeamTest {
    FootballManagementSystem system;
    Team team;
    Admin admin;
    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        admin = (Admin) system.getAdmin().checkUserRole("Admin");

    }
    @Test
    public void addLeague() {
        User union = admin.addNewUnionRepresentative("Union", "Rep", "unionRep@gmail.com");
        UnionRepresentative unionRole = ((UnionRepresentative)union.checkUserRole("UnionRepresentative"));
        unionRole.configureNewSeason(2021, new Date(120, 4, 1));
        LeagueInSeason leagueInSeason = unionRole.configureLeagueInSeason("Haal", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertTrue(team.addLeague(leagueInSeason));
    }

    @Test
    public void addAWin() {
    }

    @Test
    public void addALoss() {
    }

    @Test
    public void addADraw() {

    }

    @Test
    public void addTeamOwner() {
        User userTeamOwner = admin.addNewTeamOwner("Team", "Owner", "teamOwner@gmail.com");
        team.addTeamOwner(userTeamOwner);
        assertNotNull(userTeamOwner.checkUserRole("TeamOwner"));
    }

    @Test
    public void addTeamManager() {
        User userTeamManager = admin.addNewTeamManager("Team", "Manager", "teamManager@gmail.com", 10000, true, false);
        team.addTeamManager(userTeamManager, 10000, true, false);
        assertNotNull(userTeamManager.checkUserRole("TeamManager"));
    }

    @Test
    public void addPlayer() {
        User user = admin.addNewPlayer("p", "Player", "p@gmail.com" ,new Date(10,10,1999), Player.RolePlayer.playerBack, 1000);
        assertTrue(team.addPlayer(user));

    }

    @Test
    public void addCoach() {
        User user = admin.addNewPlayer("c", "Coach", "c@gmail.com" ,new Date(10,10,1999), Player.RolePlayer.playerBack, 1000);
        assertTrue(team.addCoach(user));
    }

    @Test
    public void addGame() {
    }

    @Test
    public void removeTeamOwner() {
        User userTeamOwner = admin.addNewTeamOwner("Team", "Owner", "teamOwner@gmail.com");
        team.addTeamOwner(userTeamOwner);
        assertTrue(team.removeTeamOwner(userTeamOwner));
        /*for notification*/
        assertEquals(userTeamOwner.getMessageBox().size(), 1);
    }

    @Test
    public void removeTeamManager() {
        User userTeamManager = admin.addNewTeamManager("Team", "Manager", "teamManager@gmail.com", 10000, true, false);
        team.addTeamManager(userTeamManager, 10000, true, false);
        assertTrue(team.removeTeamManager(userTeamManager));
        /*for notification*/
        assertEquals(userTeamManager.getMessageBox().size(), 1);
    }

    @Test
    public void removePlayer() {
    }

    @Test
    public void removeCoach() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getWins() {
    }

    @Test
    public void getLosses() {
    }

    @Test
    public void getDraws() {
    }

    @Test
    public void getPage() {
    }

    @Test
    public void getTeamOwners() {
    }

    @Test
    public void getTeamManagers() {
    }

    @Test
    public void getPlayers() {
    }

    @Test
    public void getCoaches() {
    }

    @Test
    public void getBudget() {
    }

    @Test
    public void getGames() {
    }

    @Test
    public void getFields() {
    }

    @Test
    public void getField() {
    }

    @Test
    public void isActive() {
    }

    @Test
    public void setActive() {
    }

    @Test
    public void isPermanentlyClosed() {
    }

    @Test
    public void setPermanentlyClosed() {
    }

    @Test
    public void getID() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void addField() {
    }

    @Test
    public void removeField() {
    }
}