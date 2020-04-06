package Domain;

import Data.Database;
import Service.PersonalPageSystem;

public class PersonalPageManagement {
    private Database database;
    private PersonalPageSystem personalPageSystem;

    public PersonalPageManagement(Database database, PersonalPageSystem personalPageSystem) {
        this.database = database;
        this.personalPageSystem = personalPageSystem;
    }
}
