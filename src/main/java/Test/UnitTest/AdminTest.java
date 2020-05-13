package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

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
        assertTrue(admin.closeTeamPermanently(team.getID()));
        assertFalse(admin.closeTeamPermanently(team.getID()));
    }

    @Test
    public void addNewPlayer() {
    assertNotNull(admin.addNewPlayer("lionel","mesi","lmesi@gmail.com",new Date(99, 1, 1), Player.RolePlayer.attackingPlayer,300000));
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
        //admin.addNewTeamManager("team", "manager", "teamManager@gmail.com", 20000, false, false);
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
       User user= admin.addNewAdmin("123456","","","admin@mail.com");
         assertEquals(admin.removeUser(user.getID()),user.getMail());
    }

    @Test
    public void removeField() {
     Field  field = new Field("Tel-Aviv","Bloomfield", 150000, 125000);
     admin.addField(field);
     admin.removeField(field);
     assertFalse(field.isActive());
    }

    @Test
    public void addField() {
        Field  field = new Field("Tel-Aviv","Bloomfield", 150000, 125000);
        admin.addField(field);
        assertTrue(field.isActive());
    }

    @Test
    public void responseToComplaint() {
        Guest guest = new Guest();
        User user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        Fan fan = (Fan) user.checkUserRole("Fan");
        Complaint complaint = new Complaint("complaint system", fan);
        assertTrue(admin.responseToComplaint(complaint.getId(), "answer"));
    }

    @Test
    public void viewLog() {
    }

    @Test
    public void trainModel() {
    }

    @Test
    public void myRole() {
        assertEquals(admin.myRole(),"Admin");

    }
}