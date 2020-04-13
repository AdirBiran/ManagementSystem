package Domain;

import Data.Database;
import Presentation.Fan;
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
    /*
    Remove user
     */
    public void removeUser(String userId){
        database.removeUser(userId);
    }

    /*
     this function update a user in the system
     */
    public void updateUser(String userId){
    User user = database.getUser(userId);

}
    /*
    *this function adds a new asset to the system
    * */
    public void addAsset(Asset asset){
        database.addAsset(asset);
    }
    /*
       Remove asset
        */
    public void removeAsset(String assetId){
        database.removeAsset(assetId);
    }

    /*
    this function update a asset in the system
    */
    public void updateAsset(String assetId, String action) {
        Asset asset = database.getAsset(assetId);
        if(action.equals("Some_Action")){
            //do the action
        }
    }

    /*
     * User login to system
     */
    public User logInUserToSystem(String mail, String password) {
        if(database.authenticationCheck(mail, password))
            return database.getUser(mail);
        return null;
    }
    /*
    Guest registration for the system
     */
    public boolean registrationToSystem(String mail, String password, String firstName, String lastName, String phone,
                                        String address) {
        if(!database.authenticationCheck(mail, password) && database.getUser(mail)==null){
            Fan fan = new Fan(mail, firstName, lastName, phone,address);
            database.addUser(fan.getID(), password, fan);
            return true;
        }
        return false;
    }
    /*
    Edit user personal information
     */
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
