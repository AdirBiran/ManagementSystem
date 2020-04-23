package Acceptance;

import Domain.*;
import Service.FinanceTransactionsSystem;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionsTest {

    private FootballManagementSystem system;
    private FinanceTransactionsSystem transSystem;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        transSystem = system.getFinanceTransactionsSystem();
    }

    @Test
    public void transactionFail_39()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        User owner = UserFactory.getNewTeamOwner("Team","Owner", "a233655"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        transSystem.reportNewExpanse(owner, team, 50);
        double afterChange = transSystem.getBalance(owner,team);

        assertEquals(0, afterChange, 0);

    }

    @Test
    public void transactionSuccess_40()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        User owner = UserFactory.getNewTeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        double total = transSystem.getBalance(owner,team);
        transSystem.reportNewIncome(owner, team, 50);
        double afterChange = transSystem.getBalance(owner,team);

        assertEquals(total+50, afterChange, 0);

        total = transSystem.getBalance(owner,team);
        transSystem.reportNewExpanse(owner, team, 50);
        afterChange = transSystem.getBalance(owner,team);

        assertEquals(total-50, afterChange, 0);

    }
}
