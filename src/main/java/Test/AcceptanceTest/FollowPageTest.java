package AcceptanceTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class FollowPageTest {

    FootballManagementSystem system;
    User user;
    User mesi;
    PersonalPage mesiPage;
    Fan fan;


    @Before
    public void init() {
        system = new FootballManagementSystem();
        system.systemInit(true);
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        Guest guest = new Guest();
        user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        mesi = admin.addNewPlayer("mesi", "mesi", "mesi@mail.com", new Date(30 / 5 / 93), Player.RolePlayer.goalkeeper, 200000);
        Role pageRole = mesi.checkUserRole("HasPage");
        mesiPage = ((HasPage) pageRole).getPage();
        fan = (Fan) user.checkUserRole("Fan");
    }

    @Test
    public void followPageSuccess_9()
    {
        assertTrue(fan.addPageToFollow(mesiPage.getId()));

    }

    @Test
    public void followPageFail_10()
    {
        fan.addPageToFollow(mesiPage.getId());
        assertFalse(fan.addPageToFollow(mesiPage.getId()));

    }
}
