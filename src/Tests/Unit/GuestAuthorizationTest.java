package Unit;


import Domain.Authorization.GuestAuthorization;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuestAuthorizationTest {

    FootballManagementSystem system;
    GuestAuthorization authorization;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        authorization = new GuestAuthorization();
    }

    @Test
    public void search() {
        assertNotEquals(0,authorization.search("team0").size(),0);
    }

    @Test
    public void register() {
        assertNull(authorization.register("","","","","",""));
        assertNotNull(authorization.register("Fanfan1@gmail.com","fan1234","fan","1","0502233695","somewhere12"));
    }

    @Test
    public void login() {
        assertNotNull(authorization.login("example@gmail.com", "Aa1234"));
        assertNull(authorization.login("example@gmail.com", "wrongOne"));
    }
}