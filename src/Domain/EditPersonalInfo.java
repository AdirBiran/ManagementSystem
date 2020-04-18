package Domain;

import Data.Database;

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
            database.changePassword(fan.getMail(), password);
        fan.editDetails(firstName, lastName, address, phone);
    }
    /*
    Edit user personal information
     */
    public void editPersonalDetails(User user, String firstName, String lastName, String password){
        if(!password.equals(""))
            database.changePassword(user.getMail(), password);
        user.editDetails(firstName, lastName);
    }



}
