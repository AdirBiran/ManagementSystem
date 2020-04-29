package Domain;

public class UnionBudget {

    private Budget budget;

    public UnionBudget() {
        //budget = new Budget(null);
    }

    public boolean addPayment(double income){
        return budget.addIncome(income);
    }
}
