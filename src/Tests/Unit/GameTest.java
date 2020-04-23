package Unit;

import Data.Database;
import Domain.*;

import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    FootballManagementSystem system;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);

    }
    @Test
    public void addFanForNotifications() {
        LeagueInSeason league = system.dataReboot();
        Object[] fan = UserFactory.getNewFan("Aa1234", "Avi", "Levi","AviLevi11@gmail.com", "0500004544", "Yuda123");
        assertNotNull(fan);
        Team team0 = league.getTeams().get(0);
        Team team1 = league.getTeams().get(1);
        Field field = new Field("Tel-Aviv", 10000, 150000);
        Referee mainReferee= league.getReferees().get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        Game game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees ,team0, team1,league);
        ReceiveAlerts ra=new ReceiveAlerts(true,false);
        assertTrue(game.addFanForNotifications((Fan)fan[1],ra));
    }

}