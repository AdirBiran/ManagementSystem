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

    }

    @Test
    public void addPlayerToTeam() {
        assertTrue(teamOwner.addPlayerToTeam(mesi,team));
    }


    @Test
    public void addCoachToTeam() {
        assertTrue(teamOwner.addCoachToTeam(coachU,team));
    }

    @Test
    public void addTeamManagerToTeam() {
        assertTrue(teamOwner.addTeamManagerToTeam(userTeamManager,team,5,true,true));
    }

    @Test
    public void addFieldToTeam() {
        Field field = new Field("Tel-Aviv","Bloomfield", 10000, 150000);
        assertTrue(teamOwner.addFieldToTeam(field,team));
    }

    @Test
    public void removeFieldFromTeam() {
        Field field = new Field("Tel-Aviv","Bloomfield", 10000, 150000);
        teamOwner.addFieldToTeam(field,team);
        assertTrue(teamOwner.removeFieldFromTeam(field,team));
    }

    @Test
    public void removePlayerFormTeam() {
        teamOwner.addPlayerToTeam(mesi,team);
        assertTrue(teamOwner.removePlayerFormTeam(mesi,team));
    }

    @Test
    public void removeCoachFormTeam() {
        teamOwner.addCoachToTeam(coachU,team);
        assertTrue(teamOwner.removeCoachFormTeam(coachU,team));

    }

    @Test
    public void removeTeamManagerFormTeam() {
        teamOwner.addTeamManagerToTeam(userTeamManager,team,3000,true,true);
        assertTrue(teamOwner.removeTeamManagerFormTeam(userTeamManager,team));

    }

    @Test
    public void updateAsset() {
        assertTrue(teamOwner.updateAsset(mesi.getID(),"Price","300000"));
    }

    @Test
    public void reportIncome() {
        assertTrue(teamOwner.reportIncome(team,30));
    }

    @Test
    public void reportExpanse() {
        assertTrue(teamOwner.reportExpanse(team,30));
    }

    @Test
    public void getBalance() {
        assertNotEquals(teamOwner.getBalance(team),-1);
    }
}