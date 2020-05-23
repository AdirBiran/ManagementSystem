package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ManagerTest {

    TeamOwner teamOwner;
    FootballManagementSystem system;
    Team team;
    User mesi;
    User coachU;
    User userTeamManager;
    UnionRepresentative unionRole;
    Game game;


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
        mesi = admin.addNewPlayer("mesi", "mesi", "mesi@mail.com", new Date(30 / 5 / 93), Player.RolePlayer.goalkeeper, 200000);
        coachU= admin.addNewCoach("dor","dor","dor@mail.com", Coach.TrainingCoach.UEFA_B, Coach.RoleCoach.main,50000);
        teamOwner.addTeam(team);
        userTeamManager= admin.addNewTeamManager("team", "manager", "teamManager@gmail.com", 20000, false, false);

        User union = admin.addNewUnionRepresentative("Union", "Rep", "unionRep@gmail.com");
        unionRole = ((UnionRepresentative)union.checkUserRole("UnionRepresentative"));
    }

    @Test
    public void addPlayerToTeam() {
        assertTrue(teamOwner.addPlayerToTeam(mesi.getID(),team.getID()));
    }


    @Test
    public void addCoachToTeam() {
        assertTrue(teamOwner.addCoachToTeam(coachU.getID(),team.getID()));
    }

    @Test
    public void addTeamManagerToTeam() {
        assertTrue(teamOwner.addTeamManagerToTeam(userTeamManager.getID(),team.getID(),5,true,true));
    }

    @Test
    public void addFieldToTeam() {
        unionRole.addFieldToSystem("Tel-Aviv","Bloomfield", 10000, 150000);
        Field field = (Field) Database.getAllFields().get(1);
        assertTrue(teamOwner.addFieldToTeam(field.getID(),team.getID()));
    }

    @Test
    public void removeFieldFromTeam() {
        unionRole.addFieldToSystem("Tel-Aviv","Bloomfield", 10000, 150000);
        Field field = (Field) Database.getAllFields().get(1);
        teamOwner.addFieldToTeam(field.getID(), team.getID());
        assertTrue(teamOwner.removeFieldFromTeam(field.getID(),team.getID()));
    }

    @Test
    public void removePlayerFormTeam() {
        teamOwner.addPlayerToTeam(mesi.getID(),team.getID());
        assertTrue(teamOwner.removePlayerFormTeam(mesi.getID(),team.getID()));
    }

    @Test
    public void removeCoachFormTeam() {
        teamOwner.addCoachToTeam(coachU.getID(),team.getID());
        assertTrue(teamOwner.removeCoachFormTeam(coachU.getID(),team.getID()));

    }

    @Test
    public void removeTeamManagerFormTeam() {
        teamOwner.addTeamManagerToTeam(userTeamManager.getID(),team.getID(),3000,true,true);
        assertTrue(teamOwner.removeTeamManagerFormTeam(userTeamManager.getID(),team.getID()));

    }

    @Test
    public void updateAsset() {
        assertTrue(teamOwner.updateAsset(mesi.getID(),"Price","300000"));
    }

    @Test
    public void reportIncome() {
        assertTrue(teamOwner.reportIncome(team.getID(),30));
    }

    @Test
    public void reportExpanse() {
        assertTrue(teamOwner.reportExpanse(team.getID(),30));
    }

    @Test
    public void getBalance() {
        assertNotEquals(teamOwner.getBalance(team.getID()),-1);
    }

    @Test
    public void getTeamsToManage(){
     assertNotNull(teamOwner.getStringTeams());
    }
}