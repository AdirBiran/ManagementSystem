package Tests.Unit;

import Data.Database;
import Domain.Budget;
import Domain.FinanceTransactionsManagement;
import Domain.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FinanceTransactionsManagementTest {

    Database database;
    FinanceTransactionsManagement finance;

    @Before
    public void initDataBase(){
        database = new Database();
        finance = new FinanceTransactionsManagement(database);
    }


    @Test
    public void reportNewIncome() {
        Budget budget = new Budget(null);
        finance.reportNewIncome(budget,200);
        assertEquals(200.0, budget.getIncome(), 0);
    }

    @Test
    public void reportNewExpanse() {
        Budget budget = new Budget(null);
        finance.reportNewExpanse(budget, 100);
        assertEquals(0, budget.getExpanses(), 0);
        finance.reportNewIncome(budget, 200);
        finance.reportNewExpanse(budget, 150);
        assertEquals(150, budget.getExpanses(), 0);
    }

    @Test
    public void getBalance() {
        Team team = (Team) database.searchObject("team0");
        Budget budget = new Budget(team);
        finance.reportNewIncome(budget, 250);
        finance.reportNewExpanse(budget, 150);
        assertEquals(100, finance.getBalance(team), 0);
    }

    @Test
    public void addTUTUPayment() {
        Team team = (Team) database.searchObject("team0");
        finance.addTUTUPayment(team, 100);
        assertEquals(100, finance.getBalance(team), 0);
    }
}