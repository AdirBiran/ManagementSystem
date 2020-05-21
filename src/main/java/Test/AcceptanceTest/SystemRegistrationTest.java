package AcceptanceTest;

import Data.Database;
import Domain.Admin;
import Domain.Guest;
import Domain.Team;
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
    }

    @Test
    public void registrationSuccess_6() {
        Guest guest = new Guest();
        User user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        assertNotNull(user);

    }


    @Test
    public void registrationFail_7()
    {
        Guest guest = new Guest();
        User user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        User userFail= guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");

       assertNull(userFail);
    }

    @Test
    public void registrationFail_8()
    {
        Guest guest = new Guest();
        User userFail= guest.register("fan@gmail.com", "1234", "fan", "fan", "0500001234", "yosef23");

        assertNull(userFail);
    }
}
