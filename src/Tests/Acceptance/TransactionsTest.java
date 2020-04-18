package Tests.Acceptance;

import Data.Database;
import Domain.*;
import Service.AdminSystem;
import Service.FinanceTransactionsSystem;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionsTest {

    private FootballManagementSystem system;
    private AdminSystem adminSystem;
    private Database database;
    private FinanceTransactionsSystem transSystem;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        adminSystem = system.getAdminSystem();
        database = system.getDatabase();
        transSystem = system.getFinanceTransactionsSystem();
    }

    @Test
    public void transactionFail_39()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        transSystem.reportNewExpanse(owner, team, 50);
        double afterChange = transSystem.getBalance(team);

        assertEquals(0, afterChange, 0);

    }

    @Test
    public void transactionSuccess_40()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        double total = transSystem.getBalance(team);
        transSystem.reportNewIncome(owner, team, 50);
        double afterChange = transSystem.getBalance(team);

        assertEquals(total+50, afterChange, 0);

        total = transSystem.getBalance(team);
        transSystem.reportNewExpanse(owner, team, 50);
        afterChange = transSystem.getBalance(team);

        assertEquals(total-50, afterChange, 0);

    }
}
