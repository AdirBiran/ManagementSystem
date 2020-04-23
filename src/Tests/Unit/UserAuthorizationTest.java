package Unit;

import Data.Database;
import Domain.Authorization.UserAuthorization;
import Domain.Player;
import Domain.User;
import Domain.UserFactory;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserAuthorizationTest {


    UserAuthorization authorization;
    FootballManagementSystem system;
    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        User unionRep = UserFactory.getNewUnionRepresentative("union", "ush", "AviLevi3322@gmail.com");
        assertNotNull(unionRep);
        authorization = new UserAuthorization(unionRep);
        unionRep.addAuthorization(authorization);
    }


    @Test
    public void editPersonalInfo() {

        authorization.editPersonalInfo("leo", "mesi");
        assertEquals("leo mesi", authorization.getUser().getName()); ///
        //assertTrue(authorization.changePassword())
        //assertTrue(system.getDatabase().authenticationCheck(player.getMail(), "AAA123"));
    }

    @Test
    public void search() {

        assertNotNull(authorization.search("team0"));
    }

    @Test
    public void viewSearchHistory() {
        authorization.search("team0");
        assertNotEquals(0,authorization.viewSearchHistory().size());
    }
}