package Tests.Acceptance;

import Domain.User;
import Service.FootballManagementSystem;
import Service.GuestSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    private FootballManagementSystem system;
    private GuestSystem guestSystem;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        guestSystem = system.getGuestSystem();

        system.dataReboot();
    }

    @Test
    public void loginSuccess_15()
    {
        guestSystem.registrationToSystem("a@b.com", "Abb123", "Moshe", "Cohen", "0987654321", "Israel");
        User user = guestSystem.logIn("a@b.com", "Abb123");

        assertNotNull(user);

    }

    @Test
    public void loginFail_16()
    {
        guestSystem.registrationToSystem("a@b.com", "123456", "Moshe", "Cohen", "0987654321", "Israel");
        User user = guestSystem.logIn("a@b.com", "147852");

        assertNull(user);
    }

    @Test
    public void loginFail_17()
    {
        guestSystem.registrationToSystem("a@b.com", "123456", "Moshe", "Cohen", "0987654321", "Israel");
        User user = guestSystem.logIn("blablablatest@yahoo.com", "123456");

        assertNull(user);
    }
}
