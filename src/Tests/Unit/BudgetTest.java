package Tests.Unit;


import Domain.Budget;
import Domain.Team;
import Presentation.FootballManagementSystem;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class BudgetTest {


    @Test
    public void addIncome() {
        Budget Budget = new Budget(null);
        assertTrue(Budget.addIncome(200));
        assertFalse(Budget.addIncome(-200));
    }

    @Test
    public void addExpanse() {
        Budget Budget = new Budget(null);
        assertFalse(Budget.addExpanse(200));
        Budget.addIncome(100);
        assertTrue(Budget.addExpanse(50));
        assertFalse(Budget.addExpanse(200));
    }
}
