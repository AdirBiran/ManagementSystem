package Domain;

import Data.Database;
import Service.GuestSystem;
import Service.UserSystem;

public class Searcher {

    private Database database;
    private GuestSystem guestSystem;
    private UserSystem userSystem;

    public Searcher(Database database, GuestSystem guestSystem, UserSystem userSystem) {
        this.database = database;
        this.guestSystem = guestSystem;
        this.userSystem = userSystem;
    }
}
