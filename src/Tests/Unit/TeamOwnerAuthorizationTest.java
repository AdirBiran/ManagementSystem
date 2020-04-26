package Unit;
import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class TeamOwnerAuthorizationTest {

    FootballManagementSystem system;
    TeamOwnerAuthorization authorization;
    Team team;
    User playerUser , teamOwner2, TMuser;
    Player playerObject;




    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        User teamOwner = new User("", "", "TO", "teamowner@mail.com");
        authorization = new TeamOwnerAuthorization(teamOwner);
        authorization.giveAll(true);
        team = Database.getTeams().get(0);
        authorization.addTeam(team);
        Object[] playerobj = UserFactory.getNewPlayer("player100", "", "p100@gmail.com",new Date(90,10,10), "goalkeeper",10000);
        assertNotNull(playerobj);
        playerUser= (User)playerobj[0];
        playerObject = (Player)playerobj[1];
        teamOwner2 = UserFactory.getNewTeamOwner("teamOwner", "ush", "ttttoooooo@mail.com");
        assertNotNull(teamOwner2);
        Object[] teamManager = UserFactory.getNewTeamManager("teamManager", "ush", "ttmmmm@mail.com", 12.50);
        assertNotNull(teamManager);
        TMuser = (User)teamManager[0];

    }
/*
    @Test
    public void alertBudgetException() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Team team = (Team)Database.searchObject("team0").get(0);
        assertFalse(authorization.alertBudgetException("closed", team, false));
        assertTrue(authorization.alertBudgetException("open", team, false));
        team.setActive(false);
        assertTrue(authorization.alertBudgetException("closed", team, false));
        assertFalse(authorization.alertBudgetException("open", team, false));
        team.setPermanentlyClosed(true);
        assertTrue(authorization.alertBudgetException("closed", team, true));
    }

    @Test
    public void alertBudgetException1() {
        UnionRepresentative union = new UnionRepresentative("union", "rep", "unionrep@gmail.com");
        system.getDatabase().addUser("AAA123", union);
        int sizeOfMessageBox = union.getMessageBox().size();
        authorization.alertBudgetException("test message");
        assertEquals(sizeOfMessageBox+1, union.getMessageBox().size(), 0);
    }*/

    @Test
    public void addAsset(){
        int sizeOfAssets = Database.getAssetsInDatabase().size();
        Object[] playerToAdd = UserFactory.getNewPlayer("ssdd", "ddff", "mail@maiull.com", new Date(99, 1, 1), "", 1205);
        assertNotNull(playerToAdd);
        authorization.addAssetToTeam((Player)playerToAdd[1], team);
        assertEquals(sizeOfAssets+1, Database.getAssetsInDatabase().size(), 0);
    }

    @Test
    public void removeAsset(){
        authorization.getTeamsToManage().get(0).getPlayers().get(0);
        Player player1 = authorization.getTeamsToManage().get(0).getPlayers().get(0);;
        assertTrue(authorization.removeAssetFromTeam(player1, authorization.getTeamsToManage().get(0)));
        Coach coach = authorization.getTeamsToManage().get(0).getCoaches().get(0);
        assertFalse(authorization.removeAssetFromTeam(coach, authorization.getTeamsToManage().get(0)));
    }

    @Test
    public void updateAsset(){
        Player player = authorization.getTeamsToManage().get(0).getPlayers().get(0);
        authorization.updateAsset(player.getID(), "Price", "10000");
        assertEquals(10000, player.getPrice(), 0);
    }

    @Test
    public void getTeamById() {
    }

    @Test
    public void removeTeam() {
    }


    @Test
    public void appointTeamOwner() {
        assertNotNull(teamOwner2);
        assertTrue(authorization.appointTeamOwner(teamOwner2,team));
        assertFalse(authorization.appointTeamOwner(teamOwner2,team));
        assertTrue(authorization.appointTeamOwner(playerUser,team));
    }

    @Test
    public void appointTeamManager() {

        assertTrue(authorization.appointTeamManager(TMuser,team));
        assertFalse(authorization.appointTeamManager(TMuser,team));

    }

    @Test
    public void removeAppointTeamOwner() {
        assertFalse(((TeamOwnerAuthorization)(teamOwner2.getRoles().get(0))).removeAppointTeamOwner(authorization.getUser(),team));
        assertTrue(authorization.appointTeamOwner(teamOwner2,team));
        assertTrue(authorization.removeAppointTeamOwner(teamOwner2,team));
    }

    @Test
    public void removeAppointTeamManager() {
        assertTrue(authorization.appointTeamManager(TMuser, team));
        assertFalse(((TeamOwnerAuthorization)(teamOwner2.getRoles().get(0))).removeAppointTeamManager(TMuser, team));
        assertTrue(authorization.removeAppointTeamManager(TMuser, team));
    }

    @Test
    public void closeTeam() {
        assertTrue(authorization.closeTeam(team));
        assertFalse(authorization.closeTeam(team));
    }

    @Test
    public void reopenTeam() {
        authorization.closeTeam(team);
        assertTrue(authorization.reopenTeam(team));
        assertFalse(authorization.reopenTeam(team));
    }

    @Test
    public void reportIncome() {
        assertTrue(authorization.reportIncome(team, 250));
        //test other team??
    }

    @Test
    public void reportExpanse() {
        assertTrue(authorization.reportIncome(team, 250));
        assertFalse(authorization.reportExpanse(team, 15000));

    }
}
