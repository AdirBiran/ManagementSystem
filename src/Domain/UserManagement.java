package Domain;

import Data.Database;

import java.util.List;

public class UserManagement//for admins
{
    private Database database;

    public UserManagement(Database database) {
        this.database = database;
    }

    /*
     * User login to system
     */
    public User logInUserToSystem(String mail, String password) {
        if(database.authenticationCheck(mail, password))
            return database.getUserByMail(mail);
        return null;
    }
    /*
    Guest registration for the system
     */
    public boolean registrationToSystem(String mail, String password, String firstName, String lastName, String phone,
                                        String address) {
        Fan fan = new Fan(mail, firstName, lastName, phone,address);
        return database.addUser(password, fan);
    }

    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(Fan fan, List<Game> games, ReceiveAlerts receiveAlerts) {
        for(Game game : games){
            if(!game.addFanForNotifications(fan, receiveAlerts))
                return false;
        }
        return true;
    }









}
