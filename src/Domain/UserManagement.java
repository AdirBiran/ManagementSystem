package Domain;

import Data.Database;
import Presentation.Fan;
import Presentation.Player;
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

    public User logInUserToSystem(String mail, String password) {
        if(database.authenticationCheck(mail, password))
            return database.getUser(mail);
        return null;
    }

    public boolean registrationToSystem(String mail, String password, String firstName, String lastName, String phone,
                                        String address) {
        if(!database.authenticationCheck(mail, password) && database.getUser(mail)==null){
            String userID = "F"+IdGenerator.getNewId();
            Fan fan = new Fan(mail, password, firstName, lastName, phone,address, userID);
            database.addUser(userID, password, fan);
            return true;
        }
        return false;
    }

    public void editPersonalDetails(User user, String firstName, String lastName, String phone,
                                    String address, String password) {
        if(password!=null)
            //change password ?!!?
            if(user instanceof Fan)
                ((Fan)user).editDetails(firstName, lastName, phone, address, lastName);
            else // player? coach? what change !?!?!
                user.editDetails(firstName, lastName);

    }
}
