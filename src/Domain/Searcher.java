package Domain;

import Data.Database;
import Service.GuestSystem;
import Service.UserSystem;

public class Searcher {

    private Database database;

    public Searcher(Database database) {
        this.database = database;
    }
}
