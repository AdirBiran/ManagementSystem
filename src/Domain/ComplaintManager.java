package Domain;

import Data.Database;
import Service.UserSystem;

public class ComplaintManager {

    private Database database;
    private UserSystem userSystem;

    public ComplaintManager(Database database, UserSystem userSystem) {
        this.database = database;
        this.userSystem = userSystem;
    }
}
