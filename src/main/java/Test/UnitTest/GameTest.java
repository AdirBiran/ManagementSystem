package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    Game game;
    FootballManagementSystem system;
    User user;
    Fan fan;
    Team team0;
    Team team1;

    @Before
public void init(){
    system = new FootballManagementSystem();
    system.systemInit(true);
    String  leagueId = system.dataReboot();
    LeagueInSeason league = Database.getLeagueInSeason(leagueId);
    Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
    team0=league.getTeams().get(0);
    team1=league.getTeams().get(1);;
    User union = admin.addNewUnionRepresentative("Union", "Rep", "unionRep@gmail.com");
    UnionRepresentative unionRole = ((UnionRepresentative)union.checkUserRole("UnionRepresentative"));
    unionRole.assignGames(league.getId(), system.getDates());
    game = league.getGames().get(0);
    Guest guest = new Guest();
    user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
    fan = (Fan) user.checkUserRole("Fan");
    }

    @Test
    public void addFanForNotifications() {
        assertTrue(game.addFanForNotifications(fan,true));
    }

    @Test
    public void getEventReportString() {
        assertNotNull(game.getEventReportString());
    }

    @Test
    public void setNews() {
    }

    @Test
    public void getId() {
        assertNotNull(game.getId());
    }

    @Test
    public void getDate() {
        assertNotNull(game.getDate());
    }


    @Test
    public void hostScore() {
        game.setHostScore(2);
        assertEquals(game.hostScore(),2);
    }

    @Test
    public void guestScore() {
        game.setGuestScore(3);
        assertEquals(game.guestScore(),3);
    }

    @Test
    public void setHostScore() {
        game.setHostScore(2);
        assertEquals(game.hostScore(),2);
    }

    @Test
    public void setGuestScore() {
        game.setGuestScore(3);
        assertEquals(game.guestScore(),3);
    }

    @Test
    public void getField() {
        assertEquals(game.getField().getName(),"Teddy");
    }

    @Test
    public void getMainReferee() {
        assertNotNull(game.getMainReferee());
    }

    @Test
    public void getSideReferees() {
        assertEquals(game.getSideReferees().size(),2);
    }

    @Test
    public void getHostTeam() {
        assertEquals(game.getHostTeam().getName(),team0.getName());
    }

    @Test
    public void getGuestTeam() {
        assertEquals(game.getGuestTeam().getName(),team1.getName());

    }

    @Test
    public void getName(){
        assertEquals(game.getName(), game.getHostTeam().getName() + " VS "+ game.getGuestTeam().getName());
    }

    @Test
    public void getEventReport() {
        assertEquals(game.getEventReport().getEvents().size(),0);
    }

    @Test
    public void setDate() {
        game.setDate(new Date(2020, 5, 25, 20, 0));
        assertEquals(game.getDate(),new Date(2020, 5, 25, 20, 0));
    }

    @Test
    public void setField() {
        Field field1 = new Field("beer sheva","terner", 10000, 150000);
        game.setField(field1);
        assertEquals(game.getField().getName(),field1.getName());
    }

    @Test
    public void setNewsFromReferee() {
    }

    @Test
    public void getLeague() {

    }

    @Test
    public void getFansForAlerts() {

    }
}