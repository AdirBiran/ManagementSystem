package Domain;

import Data.Database;
import Service.UserSystem;

public class EditPersonalInfo {
    private Database database;
    private UserSystem userSystem;

    public EditPersonalInfo(Database database, UserSystem userSystem) {
        this.database = database;
        this.userSystem = userSystem;
    }
}
