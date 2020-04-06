package Domain;

import Data.Database;
import Service.RefereeSystem;
import Service.UnionRepresentativeSystem;

public class RefereeManagement {

    private Database database;
    private RefereeSystem refereeSystem;
    private UnionRepresentativeSystem unionRepresentativeSystem;

    public RefereeManagement(Database database, RefereeSystem refereeSystem, UnionRepresentativeSystem unionRepresentativeSystem) {
        this.database = database;
        this.refereeSystem = refereeSystem;
        this.unionRepresentativeSystem = unionRepresentativeSystem;
    }
}
