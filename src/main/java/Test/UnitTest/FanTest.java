package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FanTest {

    FootballManagementSystem system;
    User user;
    User mesi;
    PersonalPage mesiPage;
    Fan fan;
    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        Guest guest = new Guest();
        user= guest.register("fan@gmail.com", "Aa1234", "fan", "fan","0500001234", "yosef23");
        mesi=new User("mesi","mesi","123456789","mesi@gmail.com");
        mesiPage=new PersonalPage("",mesi);
        fan = (Fan) user.checkUserRole("fan");
    }

    @Test
    public void addPageToFollow() {
        assertTrue(fan.addPageToFollow(mesiPage));
    }

    @Test
    public void editPersonalInfo() {
        fan.editPersonalInfo(user,"shir","shir","ff","052555654");
        assertEquals(fan.getAddress(),"ff");
    }

    @Test
    public void followPage() {
    }

    @Test
    public void followGames() {
    }

    @Test
    public void submitComplaint() {
    }

    @Test
    public void getFollowedPages() {
    }

    @Test
    public void setAddress() {
    }

    @Test
    public void setPhone() {
    }

    @Test
    public void getAddress() {
    }

    @Test
    public void getPhone() {
    }

    @Test
    public void getComplaints() {
    }

    @Test
    public void getFollowPages() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void myRole() {
    }

    @Test
    public void testToString() {
    }
}