package Domain;

public class UnionBudget {

    private Budget budget;

    public UnionBudget() {
<<<<<<< HEAD
        //budget = new Budget(null);
=======
        budget = new Budget();
>>>>>>> a3dabb2a87d72bd10ba1205f11d32143ebb45d37
    }

    public boolean addPayment(double income){
        return budget.addIncome(income);
    }
}
