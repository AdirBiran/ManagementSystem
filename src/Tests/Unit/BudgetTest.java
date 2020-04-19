package Unit;


import Domain.Budget;

import org.junit.Test;


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
