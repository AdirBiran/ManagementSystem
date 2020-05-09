package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FanTest {

    FootballManagementSystem system;
    User user;
    User mesi;
    PersonalPage mesiPage;
    Fan fan;
    ReceiveAlerts receiveAlerts;
    Game game;

    @Before
    public void init() {
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        Guest guest = new Guest();
        user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        mesi = admin.addNewPlayer("mesi", "mesi", "mesi@mail.com", new Date(30 / 5 / 93), Player.RolePlayer.goalkeeper, 200000);
        Role pageRole = mesi.checkUserRole("HasPage");
        mesiPage = ((HasPage) pageRole).getPage();
        fan = (Fan) user.checkUserRole("Fan");
        receiveAlerts = new ReceiveAlerts(true, false);
        Team team0 = league.getTeams().get(0);
        Team team1 = league.getTeams().get(1);
        Field field = new Field("Tel-Aviv","Bloomfield", 10000, 150000);
        Referee mainReferee = league.getReferees().get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees, team0, team1, league);
    }

    @Test
    public void addPageToFollow() {

        assertTrue(fan.addPageToFollow(mesiPage));
        List<Game> games = new LinkedList<>();
        games.add(game);
        assertTrue(fan.followGames(games, receiveAlerts));
        /***/
        Field newField = new Field("Jerusalem","Teddy", 10000, 200000);
        game.setField(newField);
        assertEquals(1, fan.getMessageBox().size(),0);
        Guest guest1 = new Guest();

        User user1 = guest1.register("fan1@gmail.com", "Aa1234", "fan1", "fan1", "0500001234", "yosef23");
        Fan fan1 = (Fan) user1.checkUserRole("Fan");
        assertEquals(0, fan1.getMessageBox().size(), 0);
        Referee mainReferee = game.getMainReferee();
        mainReferee.addEventToGame(game, Event.EventType.RedCard, 70, "");
        assertEquals(2, fan.getMessageBox().size(), 0);
        assertEquals(1, mainReferee.getMessageBox().size(), 0);
    }

    @Test
    public void editPersonalInfo() {
        fan.editPersonalInfo(user, "shir", "shir", "ff", "052555654");
        assertEquals(fan.getAddress(), "ff");
    }

    @Test
    public void submitComplaint() {
        assertNotNull(fan.submitComplaint("bla bla"));
        assertFalse(fan.submitComplaint(""));
    }

    @Test
    public void getFollowedPages() {
        fan.addPageToFollow(mesiPage);
        assertEquals(fan.getFollowedPages().size(), 1);
    }

    @Test
    public void setAddress() {
        fan.setAddress("beer");
        assertEquals(fan.getAddress(), "beer");
    }

    @Test
    public void setPhone() {
        fan.setPhone("052-5468972");
        assertEquals(fan.getPhone(), "052-5468972");
    }

    @Test
    public void getAddress() {
        assertNotNull(fan.getAddress());
    }

    @Test
    public void getPhone() {
        assertNotNull(fan.getPhone());
    }

    @Test
    public void getComplaints() {
        assertEquals(fan.getComplaints().size(), 0);
    }

    @Test
    public void getFollowPages() {
        assertEquals(fan.getFollowedPages().size(), 0);
    }


    @Test
    public void myRole() {
        assertEquals(fan.myRole(),"Fan");
    }
}