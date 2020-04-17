package Tests.Acceptance;

import Service.FootballManagementSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemInitiationTest {

    @Test
    public void systemInitFail_1()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        boolean init = system.systemInit(false);

        assertFalse(init);

    }

    @Test
    public void systemInitSuccess_2()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        boolean init = system.systemInit(true);

        assertTrue(init);

    }

}
