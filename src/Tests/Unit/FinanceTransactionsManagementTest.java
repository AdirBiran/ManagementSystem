package Tests.Unit;

import Data.Database;
import Domain.Budget;
import Domain.FinanceTransactionsManagement;
import org.junit.Test;

import static org.junit.Assert.*;

public class FinanceTransactionsManagementTest {

    @Test
    public void reportNewIncome() {
        Database database = new Database();
        FinanceTransactionsManagement finance = new FinanceTransactionsManagement(database);
        Budget budget = new Budget(null);
        finance.reportNewIncome(budget,200);
        assertEquals(200.0, budget.getIncome(), 0);

    }

    @Test
    public void reportNewExpanse() {
    }

    @Test
    public void getBalance() {
    }

    @Test
    public void addTUTUPayment() {
    }
}