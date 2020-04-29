package Domain;

public class UnionBudget {

    private Budget budget;

    public UnionBudget() {
<<<<<<< HEAD
        //budget = new Budget(null);
=======
>>>>>>> 25ba5ad50fed7a4e3e12c9af8ef5b93754ea5daa
        budget = new Budget();
    }

    public boolean addPayment(double income){
        return budget.addIncome(income);
    }
}
