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
        Team team0 = league.getTeams().get(0);
        Team team1 = league.getTeams().get(1);
        Field field = new Field("Tel-Aviv", "Bloomfield", 10000, 150000);
        referee = league.getReferees().get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        game = new Game(new Date(120, 4, 25, 20, 0), field, referee, sideReferees, team0, team1, league);
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

        referee.addEventToGame(game,Event.EventType.RedCard,50,"");
    }

    @Test
    public void changeEvent() {
       /*Event event = new Event(Event.EventType.Goal, 50, "");
        game.getEventReport().addEvent(event);*/

    }

    @Test
    public void setScoreInGame() {
        assertTrue(referee.setScoreInGame(game,3,2));
    }
}