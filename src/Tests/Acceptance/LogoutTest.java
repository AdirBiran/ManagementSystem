package Tests.Acceptance;

import Service.FootballManagementSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;


public class LogoutTest {

    private FootballManagementSystem system;
    private UserSystem userSystem;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        userSystem = system.getUserSystem();

        system.dataReboot();
    }

    @Test
    public void logoutSuccess_18()
    {

        // can't be implemented yet

    }

    @Test
    public void logoutFail_19()
    {

        // can't be implemented yet

    }
}
