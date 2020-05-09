package Domain;

import java.util.List;

public class UnionBudget {

    private Budget budget;

    public UnionBudget() {
        //budget = new Budget(null);
        budget = new Budget();
    }

    public boolean addPayment(double income){
        return budget.addIncome(income);
    }

}
