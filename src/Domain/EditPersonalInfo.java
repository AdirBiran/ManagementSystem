package Domain;

import Data.Database;
import Presentation.Fan;
import Presentation.User;
import Service.UserSystem;

public class EditPersonalInfo {
    private Database database;

    public EditPersonalInfo(Database database) {
        this.database = database;
    }

    /*
Edit fan personal information
 */
    public void editPersonalDetails(Fan fan, String firstName, String lastName, String phone,
                                    String address, String password) {
        if(!password.equals(""))
            database.changePassword(fan.getID(), password);
        fan.editDetails(firstName, lastName, address, phone);
    }
    /*
    Edit user personal information
     */
    public void editPersonalDetails(User user, String firstName, String lastName, String password){
        if(!password.equals(""))
            database.changePassword(user.getID(), password);
        user.editDetails(firstName, lastName);
    }



}
