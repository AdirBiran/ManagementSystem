package Tests.Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

public class FanTest {

    FootballManagementSystem system;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        Guest guest = new Guest();
        User user= guest.register("fan@gmail.com", "Aa1234", "fan", "fan","0500001234", "yosef23");

    }

    @Test
    public void addPageToFollow() {
    }

    @Test
    public void editPersonalInfo() {
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