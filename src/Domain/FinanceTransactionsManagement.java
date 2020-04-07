package Domain;

import Data.Database;

import java.util.List;

public class FinanceTransactionsManagement {

    private Database database;

    public FinanceTransactionsManagement(Database database) {
        this.database = database;
    }


    public boolean reportNewIncome(Budget budget, double income) {
        return budget.addIncome(income);
    }

    public boolean reportNewExpanse(Budget budget, double expanse) {
        return budget.addExpanse(expanse);
    }

    public double getBalance(Team team) {
        return team.getBudget().getBalance();
    }
}
