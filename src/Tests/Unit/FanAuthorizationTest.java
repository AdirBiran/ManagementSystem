package Unit;

import Data.Database;
import Domain.Fan;
import Domain.PersonalPage;
import Domain.User;
import Domain.UserFactory;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FanAuthorizationTest {

    FootballManagementSystem system;
    FanAuthorization authorization;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Object[] fan = UserFactory.getNewFan("Aa1234","fan", "ush", "AviLevi@gmail.com", "0500004544", "");
        assertNotNull(fan);
        authorization = (FanAuthorization)((User)fan[0]).getRoles().get(0);;
    }
    @Test
    public void editPersonalInfo() {

        authorization.editPersonalInfo("Avi", "Levi", "Yuda123", "0544884666");
        Fan checkFan = authorization.getFan();
        assertEquals(checkFan.getPhone(), "0544884666"); ///
        authorization.changePassword("Aa1234", "ABC123");
        assertTrue(Database.authenticationCheck("AviLevi@gmail.com", "ABC123"));
    }

    @Test
    public void search() {

        assertNotEquals(0,authorization.search("team0").size(),0);
        assertNotEquals(0,authorization.viewSearchHistory().size(),0);
    }

    @Test
    public void followPage() {
        PersonalPage page = new PersonalPage("", null);
        assertTrue(authorization.followPage(page));
        assertEquals(1,authorization.getFollowedPages().size());
    }

    @Test
    public void followGame() {

    }

    @Test
    public void submitComplaint() {
    }

    @Test
    public void getFollowedPages() {
    }
}