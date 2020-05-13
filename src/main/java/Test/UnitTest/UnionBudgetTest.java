package UnitTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnionBudgetTest {

    @Test
    public void addPayment() {
        UnionBudget unionBudget=new UnionBudget();
        assertTrue(unionBudget.addPayment(30));
        assertFalse(unionBudget.addPayment(0));
    }
}