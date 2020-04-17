package Tests.Acceptance;

import Presentation.FootballManagementSystem;
import org.junit.Test;

public class SystemInitiationTest {

    @Test
    public void systemInitFail_1()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(false);

    }

    @Test
    public void systemInitSuccess_2()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
    }

}
