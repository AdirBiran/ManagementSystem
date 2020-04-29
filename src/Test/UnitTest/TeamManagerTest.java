package UnitTest;

import Domain.LeagueInSeason;
import Domain.Team;
import Domain.TeamManager;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

 public class TeamManagerTest {

    TeamManager teamManager;
    FootballManagementSystem system;
    Team team;
    @Before
    public void init(){
        teamManager=new TeamManager("123456789",20000,false,false);
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
    }

    @Test
    public void getID() {
        assertEquals(teamManager.getID(),"123456789");
    }

    @Test
    public void getPrice() {
        assertEquals(teamManager.getPrice(),20000,1);
    }

    @Test
    public void setPrice() {
        teamManager.setPrice(30);
        assertEquals(teamManager.getPrice(),30,1);

    }

    @Test
    public void getTeams() {
        assertEquals(teamManager.getTeams().size(),0);
    }

    @Test
    public void isPermissionManageAssets() {
    }

    @Test
    public void isPermissionFinance() {
    }

    @Test
    public void addTeam() {
        teamManager.addTeam(team);
        assertEquals(teamManager.getTeams().size(),1);
    }

    @Test
    public void removeTeam() {
    }

    @Test
    public void isActive() {
    }

    @Test
    public void reactivate() {
    }

    @Test
    public void myRole() {
    }
}