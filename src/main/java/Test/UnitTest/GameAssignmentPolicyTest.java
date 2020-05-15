package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameAssignmentPolicyTest {
    Game game;
    FootballManagementSystem system;
    User user;
    Fan fan;
    ReceiveAlerts receiveAlerts;
    Team team0;
    Team team1;
    GameAssignmentPolicy gameAssignmentPolicy;
    private List<Date> Null;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        String  leagueId = system.dataReboot();
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        team0 = league.getTeams().get(0);
        team1 = league.getTeams().get(1);
        Field field = new Field("Tel-Aviv","Bloomfield", 10000, 150000);
        Referee mainReferee = league.getReferees().get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees, team0, team1, league);
        Guest guest = new Guest();
        user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
        fan = (Fan) user.checkUserRole("Fan");
        receiveAlerts=new ReceiveAlerts(true,false);
    }

    @Test
    public void checkForDuplicates() {

    }

    @Test
    public void getSideReferees() {
    }

    @Test
    public void getMainReferee() {
    }

    @Test
    public void getDateFromList() {
    }

    @Test
    public void checkConstrains() {
    }

    @Test
    public void makeGame() {
    }
}