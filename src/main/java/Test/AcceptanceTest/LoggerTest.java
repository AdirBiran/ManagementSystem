package AcceptanceTest;
import Data.Database;
import Domain.Admin;
import Logger.Logger;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;
import Service.AdminSystem;


import static org.junit.Assert.*;

public class LoggerTest {
    AdminSystem adminSystem;
    FootballManagementSystem system;
    Admin admin;

    @Before
    public void init() {
        adminSystem = new AdminSystem();
        system = new FootballManagementSystem();
        system.systemInit(true);
        admin = (Admin) system.getAdmin();

    }

    @Test
    public void emptyLogFile_52()
    {
        FootballManagementSystem system = new FootballManagementSystem();
        boolean init = system.systemInit(true);
        assertEquals(Logger.getEventsLog().size(),0);
    }
    @Test
    public void errorsLogFile_53()
    {
        adminSystem.addNewPlayer(admin.getID(),"Lionel","Mesi","mesi@mail.com", Database.getCurrentDate(),"Player",300000);
        assertNull(adminSystem.addNewPlayer(admin.getID(),"Lionel","Mesi","mesi@mail.com", Database.getCurrentDate(),"Player",300000));
        assertEquals(Logger.getErrorsLog().size(),1);
}
    @Test
    public void eventsLogFile_54()
    {
        adminSystem.addNewPlayer(admin.getID(),"Lionel","Mesi","mesi@mail.com", Database.getCurrentDate(),"Player",300000);
        assertEquals(Logger.getEventsLog().size(),1);
    }
}
