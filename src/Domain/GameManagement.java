package Domain;

import Data.Database;
import Service.RefereeSystem;
import Service.UserSystem;

public class GameManagement {

    private Database database;
    private UserSystem userSystem;
    private RefereeSystem refereeSystem;

    public GameManagement(Database database, UserSystem userSystem, RefereeSystem refereeSystem) {
        this.database = database;
        this.userSystem = userSystem;
        this.refereeSystem = refereeSystem;
    }
}
