package Tests.Acceptance;

import Service.FootballManagementSystem;
import org.junit.Test;

public class SystemInitiationTest {

    @Test
    public void systemInitFail_1()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        boolean init = system.systemInit(false);

    }

    @Test
    public void systemInitSuccess_2()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        boolean init = system.systemInit(true);
    }

}
