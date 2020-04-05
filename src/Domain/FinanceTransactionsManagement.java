package Domain;

import Data.Database;
import Service.FinanceTransactionsSystem;


public class FinanceTransactionsManagement {

    private Database database;
    private FinanceTransactionsSystem financeTransactionsSystem;

    public FinanceTransactionsManagement(Database database, FinanceTransactionsSystem financeTransactionsSystem) {
        this.database = database;
        this.financeTransactionsSystem = financeTransactionsSystem;
    }
}
