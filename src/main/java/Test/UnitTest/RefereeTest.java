package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RefereeTest {
    FootballManagementSystem system;
    User user;
    User mesi;
    PersonalPage mesiPage;
    Fan fan;
    ReceiveAlerts receiveAlerts;
    Game game;
    Referee referee;

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
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        User union = admin.addNewUnionRepresentative("Union", "Rep", "unionRep@gmail.com");
        UnionRepresentative unionRole = ((UnionRepresentative)union.checkUserRole("UnionRepresentative"));
        unionRole.assignGames(league.getId(),system.getDates());
        game=league.getGames().get(0);
        referee=game.getMainReferee();
    }

    @Test
    public void getTraining() {
        assertEquals(referee.getTraining(),"referees");
    }

    @Test
    public void setTraining() {
        referee.setTraining(Referee.TrainingReferee.linesman);
        assertEquals(referee.getTraining(),"linesman");

    }

    @Test
    public void addGame() {
        referee.addGame(game);
        assertEquals(referee.viewGames().size(),1);
    }

    @Test
    public void viewGames() {
        assertEquals(referee.viewGames().size(),0);

    }

    @Test
    public void addEventToGame() {

        referee.addEventToGame(game.getId(),Event.EventType.RedCard,50,"");
        assertEquals(1,game.getEventReport().getEvents().size());

    }

    @Test
    public void changeEvent() {
        referee.addEventToGame(game.getId(),Event.EventType.RedCard,50,"");
       assertTrue( referee.changeEvent(game.getId(),game.getEventReport().getEvents().get(0).getId(),"yes"));
       assertEquals(game.getEventReport().getEvents().get(0).getDescription(),"yes");
    }

    @Test
    public void setScoreInGame() {
        assertTrue(referee.setScoreInGame(game.getId(),3,2));
    }
}