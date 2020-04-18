package Tests.Unit;

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
        system.financeTransactionsManagement.reportNewIncome(budget,200);
        assertEquals(200.0, budget.getIncome(), 0);
    }

    @Test
    public void reportNewExpanse() {
        Budget budget = new Budget(null);
        system.financeTransactionsManagement.reportNewExpanse(budget, 100);
        assertEquals(0, budget.getExpanses(), 0);
        system.financeTransactionsManagement.reportNewIncome(budget, 200);
        system.financeTransactionsManagement.reportNewExpanse(budget, 150);
        assertEquals(150, budget.getExpanses(), 0);
    }

    @Test
    public void getBalance() {
        Team team = (Team) system.database.searchObject("team0").get(0);
        system.financeTransactionsManagement.reportNewIncome(team.getBudget(), 250);
        system.financeTransactionsManagement.reportNewExpanse(team.getBudget(), 150);
        assertEquals(100, system.financeTransactionsManagement.getBalance(team), 0);
    }

    @Test
    public void addTUTUPayment() {
        Team team = (Team) system.database.searchObject("team0").get(0);
        system.financeTransactionsManagement.addTUTUPayment(team, 100);
        assertEquals(100, system.financeTransactionsManagement.getBalance(team), 0);
    }
}