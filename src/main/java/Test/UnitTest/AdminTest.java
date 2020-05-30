package UnitTest;

import Data.Database;
import Data.initTest;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;


import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AdminTest {

    FootballManagementSystem system;
    Admin admin;
    Team team;
    User user;
    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(false);
        //String  leagueId = system.dataReboot();
        LeagueInSeason league =system.getDatabase().getAllLeaguesInSeasons().get(0);

        team = league.getTeams().get(0);
        admin = system.getAdmin();
        Guest guest = new Guest();
        user = guest.register("fan@gmail.com", "Aa1234","test","testFan","0502055454","aa");
        //user = guest.login("fan@gmail.com", "Aa1234");
    }

    @Test
    public void closeTeamPermanently() {
        String teamId = team.getID();
        assertNotNull(admin.closeTeamPermanently(teamId));
        assertNull(admin.closeTeamPermanently(team.getID()));
    }

    @Test
    public void addNewPlayer() {
        User newUser = admin.addNewPlayer("lionel","mesi","lmesi@gmail.com",Database.getDate(1992, 1, 1), Player.RolePlayer.attackingPlayer,300000);
        assertNotNull(newUser);

    }

    @Test
    public void addNewCoach() {
        assertNotNull(admin.addNewCoach("coach1", "coach",+IdGenerator.getNewId()+"@gmail.com", Coach.TrainingCoach.UEFA_A, Coach.RoleCoach.main, 1500));
    }

    @Test
    public void addNewTeamOwner() {
        assertNotNull(admin.addNewTeamOwner("Team","Owner","to"+30+"@gmail.com" ));
    }

    @Test
    public void addNewTeamManager() {
        assertNotNull(admin.addNewTeamManager("team", "manager", "teamManager@gmail.com", 20000, false, false));
    }

    @Test
    public void addNewUnionRepresentative() {
        assertNotNull(admin.addNewUnionRepresentative("", "","mail1@mail.com"));
    }

    @Test
    public void addNewAdmin() {
        assertNotNull(admin.addNewAdmin("123456","","","admin@mail.com"));
    }

    @Test
    public void removeUser() {
         assertEquals(admin.removeUser(user.getID()),user.getMail());
    }

    @Test
    public void removeField() {
        UnionRepresentative unionRepresentative = system.getDatabase().getAllUnions().get(0);
        unionRepresentative.addFieldToSystem("Tel-Aviv","Bloomfield", 150000, 125000);
        admin.removeField(system.getDatabase().getAllActiveFields().get(1));
        assertFalse(system.getDatabase().getAllFields().get(1).isActive());
    }

    @Test
    public void addField() {
        Field  field = new Field("Tel-Aviv","Bloomfield", 150000, 125000);
        admin.addField(field);
        assertTrue(field.isActive());
    }

    @Test
    public void responseToComplaint() {
        Fan fan = (Fan) user.checkUserRole("Fan");
        fan.submitComplaint("complaint to system");
        String complaint = fan.getComplaintsId().get(0);
        assertTrue(admin.responseToComplaint(complaint, "answer"));
    }

    @Test
    public void viewLog() {
        assertNotNull(admin.viewLog("Errors"));
    }

    @Test
    public void myRole() {
        assertEquals(admin.myRole(),"Admin");

    }
    @Test
    public void getAllDetailsAboutCloseTeams(){
        assertNotNull(admin.getAllDetailsAboutCloseTeams());
    }
    @Test
    public void getAllCloseTeams() {
        assertNotNull(admin.getAllCloseTeams());
    }

    @Test
    public void getAllActiveComplaints(){
        assertNotNull(admin.getAllActiveComplaints());
    }
    }
