package Domain;

import Data.Database;
import Service.RefereeSystem;


public class EventReportManagement {

    private Database database;
    private RefereeSystem refereeSystem;

    public EventReportManagement(Database database, RefereeSystem refereeSystem) {
        this.database = database;
        this.refereeSystem = refereeSystem;
    }
}
