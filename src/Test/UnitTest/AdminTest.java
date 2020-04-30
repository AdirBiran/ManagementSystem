package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

public class AdminTest {

    FootballManagementSystem system;
    Admin admin;
    Team team;
    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        admin = (Admin) system.getAdmin().checkUserRole("Admin");
    }
    @Test
    public void closeTeamPermanently() {
    }

    @Test
    public void addNewPlayer() {
    }

    @Test
    public void addNewCoach() {
    }

    @Test
    public void addNewTeamOwner() {
    }

    @Test
    public void addNewTeamManager() {
    }

    @Test
    public void addNewUnionRepresentative() {
    }

    @Test
    public void addNewAdmin() {
    }

    @Test
    public void removeUser() {
    }

    @Test
    public void removeField() {
    }

    @Test
    public void addField() {
    }

    @Test
    public void responseToComplaint() {
    }

    @Test
    public void viewLog() {
    }

    @Test
    public void trainModel() {
    }

    @Test
    public void myRole() {
    }
}