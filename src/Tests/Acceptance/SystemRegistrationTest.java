package Acceptance;

import Data.Database;
import Domain.User;
import Service.FootballManagementSystem;
import Service.GuestSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemRegistrationTest {

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
    public void registrationSuccess_6()
    {
        User user = Database.getUserByMail("a@b.com");
        assertNull(user);

        guestSystem.registrationToSystem("a@b.com", "Ab1234", "Moshe", "Cohen", "0987654321", "Israel");

        user = Database.getUserByMail("a@b.com");
        assertNotNull(user);

    }

    @Test
    public void registrationFail_7()
    {

        boolean reg1 = (guestSystem.registrationToSystem("a@b.com", "Ab2123", "Moshe", "Cohen", "0987654321", "Israel"))!=null;
        boolean reg2 = (guestSystem.registrationToSystem("a@b.com", "Cc1234", "Dani", "Levi", "9876543210", "Israel"))!=null;

        assertTrue(reg1);
        assertFalse(reg2);
    }

    @Test
    public void registrationFail_8()
    {
        boolean reg = (guestSystem.registrationToSystem("as ds & @b.com", "567890", "Dani", "Levi", "9876543210", "Israel"))!=null;

        assertFalse(reg);
    }
}
