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

        String  leagueId = system.dataReboot();
        //LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        //LeagueInSeason league =system.getDatabase().getAllLeaguesInSeasons().get(0);

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

        int sizeLogger = admin.viewLog("Errors").size();
        adminSystem.addNewPlayer(admin.getID(),"Lionel","Mesi","mesiLionel@mail.com", Database.getCurrentDate(),"midfielderPlayer",300000);
        assertNull(adminSystem.addNewPlayer(admin.getID(),"Lionel","Mesi","mesiLionel@mail.com", Database.getCurrentDate(),"midfielderPlayer",300000));
        assertEquals(admin.viewLog("Errors").size(),sizeLogger+1);

}
    @Test
    public void eventsLogFile_54()
    {

        int sizeLogger = admin.viewLog("Events").size();
        adminSystem.addNewPlayer(admin.getID(),"Lionel","Mesi","mesiLionel1@mail.com", Database.getCurrentDate(),"midfielderPlayer",300000);
        assertEquals(admin.viewLog("Events").size(),sizeLogger+1);

    }
}
