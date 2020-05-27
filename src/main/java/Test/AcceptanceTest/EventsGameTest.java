package AcceptanceTest;

import Data.Database;
import Domain.*;

import Service.FootballManagementSystem;
import Service.RefereeSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.*;


public class EventsGameTest {
    RefereeSystem refereeSystem;
    FootballManagementSystem system;
    Admin admin;
    Referee referee;
    Game game;
    User mesi;
    @Before
    public void init() {
        refereeSystem=new RefereeSystem();
        system = new FootballManagementSystem();
        system.systemInit(true);
        String  leagueId = system.dataReboot();
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        admin = (Admin) system.getAdmin().getUser().checkUserRole("Admin");
        game=league.getGames().get(0);
        referee=game.getMainReferee();
        mesi = admin.addNewPlayer("mesi", "mesi", "mesi@mail.com", new Date(30 / 5 / 93), Player.RolePlayer.goalkeeper, 200000);

    }

    @Test
    public void eventsGameSuccess_72()
    {
       assertTrue(refereeSystem.addEventToGame(referee.getUser().getID(),game.getId(), "RedCard",game.getHostTeam().getPlayers().get(0).getID(),game.getHostTeam().getID()));
    }
    @Test
    public void eventsGameFail_73()
    {
        assertFalse(refereeSystem.addEventToGame(referee.getUser().getID(),game.getId(), "RedCard",game.getHostTeam().getPlayers().get(0).getID(),null));
    }
}
