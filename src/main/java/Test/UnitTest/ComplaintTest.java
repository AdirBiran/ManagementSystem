package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ComplaintTest {
    Fan fan;
    FootballManagementSystem system;
    User user;
    Complaint complaint;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        Guest guest = new Guest();
        user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        fan = (Fan) user.checkUserRole("Fan");
        complaint=new Complaint("",fan);
    }

    @Test
    public void getDate() {
        assertNotNull(complaint.getDate());
    }

    @Test
    public void getDescription() {
        assertEquals("",complaint.getDescription());
    }

    @Test
    public void getIsActive() {
        assertTrue(complaint.getIsActive());
    }

    @Test
    public void getFanComplained() {
        assertEquals(complaint.getFanComplained().getPhone(),fan.getPhone());
    }

    @Test
    public void deactivate() {
        complaint.deactivate();
        assertFalse(complaint.getIsActive());
    }
   /* @Test
    public void setComplaint(Complaint complaint) {

    }*/
}