package Domain;

import Data.Database;
import Presentation.User;

public class UserManagement//for admins
{
    private Database database;

    public UserManagement(Database database) {
        this.database = database;
    }

    /*
       this function adds a new user to the system
    */
    public void addUser(String userId, String password, User user) {
        database.addUser(userId, password, user);
    }

    public void removeUser(String userId){
        database.removeUser(userId);
    }
}
