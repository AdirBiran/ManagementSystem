package AcceptanceTest;

import Data.Database;
import Domain.*;
import Service.FinanceTransactionsSystem;
import Service.FootballManagementSystem;
import Service.TeamManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransactionsTest {

    TeamManagementSystem teamManagementSystem;
    FootballManagementSystem system;
    FinanceTransactionsSystem transSystem;
    Team team;
    Admin admin;
    User owner;

    @Before
    public void init()
    {

        system = new FootballManagementSystem();
        system.systemInit(true);
        transSystem = system.getFinanceTransactionsSystem();
        String  leagueId = system.dataReboot();
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        team = league.getTeams().get(0);
        admin = (Admin) system.getAdmin().checkUserRole("Admin");
        owner= admin.addNewTeamOwner("team", "owner", "teamOwner@gmail.com");
        owner= team.getTeamOwners().get(0);

    }

    @Test
    public void transactionFail_39()
    {

       /* transSystem.reportNewExpanse(owner.getID(), team.getID(), 50);
        double afterChange = transSystem.getBalance(owner.getID(),team.getID());

        assertEquals(0, afterChange, 0);*/

    }

    @Test
    public void transactionSuccess_40()
    {
        double before=team.getBudget().getBalance();
       transSystem.reportNewIncome(owner.getID(), team.getID(), 50);
        assertEquals(before+50, team.getBudget().getBalance(), 1);

    }
}
