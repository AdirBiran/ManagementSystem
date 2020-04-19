package Unit;

import Domain.Budget;
import Domain.Team;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FinanceTransactionsManagementTest {

    FootballManagementSystem system;

    @Before
    public void initDataBase(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
    }


    @Test
    public void reportNewIncome() {
        Budget budget = new Budget(null);
        system.getFinanceTransactionsManagement().reportNewIncome(budget,200);
        assertEquals(200.0, budget.getIncome(), 0);
    }

    @Test
    public void reportNewExpanse() {
        Budget budget = new Budget(null);
        system.getFinanceTransactionsManagement().reportNewExpanse(budget, 100);
        assertEquals(0, budget.getExpanses(), 0);
        system.getFinanceTransactionsManagement().reportNewIncome(budget, 200);
        system.getFinanceTransactionsManagement().reportNewExpanse(budget, 150);
        assertEquals(150, budget.getExpanses(), 0);
    }

    @Test
    public void getBalance() {
        Team team = (Team) system.getDatabase().searchObject("team0").get(0);
        system.getFinanceTransactionsManagement().reportNewIncome(team.getBudget(), 250);
        system.getFinanceTransactionsManagement().reportNewExpanse(team.getBudget(), 150);
        assertEquals(100, system.getFinanceTransactionsManagement().getBalance(team), 0);
    }

    @Test
    public void addTUTUPayment() {
        Team team = (Team) system.getDatabase().searchObject("team0").get(0);
        system.getFinanceTransactionsManagement().addTUTUPayment(team, 100);
        assertEquals(100, system.getFinanceTransactionsManagement().getBalance(team), 0);
    }
}