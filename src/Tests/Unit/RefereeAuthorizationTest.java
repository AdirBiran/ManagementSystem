package Unit;

import Domain.Authorization.RefereeAuthorization;
import Domain.Referee;
import Domain.User;
import Domain.UserFactory;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefereeAuthorizationTest {


    FootballManagementSystem system;
    RefereeAuthorization authorization;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Object[] referee = UserFactory.getNewReferee("referee", "ush", "AviLevireferee@gmail.com", "main");
        assertNotNull(referee);
        authorization = (RefereeAuthorization)((User)(referee[0])).getRoles().get(0);
    }

    @Test
    public void viewGames() {
        assertEquals(0,authorization.viewGames().size());

    }

    @Test
    public void addEventToGame() {


    }

    @Test
    public void getEventReport() {
    }

    @Test
    public void changeEvent() {
    }

    @Test
    public void changeEvent1() {
    }

    @Test
    public void setScoreInGame() {
    }
}