package Service;
import Domain.*;

import java.util.HashMap;


public class Database //maybe generalize with interface?
{
    private HashMap<String, String> userNamesAndPasswords; //- <userId, encryptedPassword??>
    private HashMap<String,User> usersInDatabase; // - <userId,User>
    private HashMap<String,Asset> assetsInDatabase;// - <asset.name, Asset>

    public Database() {
        userNamesAndPasswords = new HashMap<>();
        usersInDatabase = new HashMap<>();
        assetsInDatabase = new HashMap<>();
    }
    /*
    this function gets a name of an asset and returns a pointer to the object of this asset
    for example input: "Blumfield stadium" - the output will be a pointer to Blumfield stadium object or Null if it doesn't exists
    return null if cant find asset
     */
    public Asset getAsset(String name){
       return (Asset)search("Asset", name);
    }
    /*
    this function gets a user id and returns a pointer to the object of this user
    for example input: "Leonardo Messi" - the output will be a pointer to messi's user or Null if it doesn't exists
    return null if cant find user
     */
    public User getUser(String userId){
        return (User)search("User", userId);
    }
    /*
    adds an asset to the database
    returns false if the asset already exists
     */
    public boolean addAsset(Asset asset){
        String assetName = asset.getName();
        if(assetsInDatabase.containsKey(assetName)){
            return false;
        }
        assetsInDatabase.put(assetName, asset);
        return true;
    }
    /*
    adds a user to the database
    returns false if the user already exists
     */
    public boolean addUser(String userId, String password, User user){
        if(!usersInDatabase.containsKey(userId)){
            //use some hash to encrypt the password?
            userNamesAndPasswords.put(userId, password);
            usersInDatabase.put(userId, user);
            return true;
        }
        return false;
    }
    /*
    this function perform a authentication check for username an password
    returns true if this is the correct credentials and false otherwise
     */
    public boolean authenticationCheck(String userId, String password){
        if(usersInDatabase.containsKey(userId)){
            //use some hash to encrypt the the given password to compare it with the one we have
            String passwordInSystem = userNamesAndPasswords.get(userId);
            return passwordInSystem.equals(password);
        }
        return false;
    }


    private Object search(String whatType, String searchWord){
        if(Checker.isValid(searchWord)){
            Object result = null;
            switch(whatType){
                case("Asset"):{
                    for(String nameOfAsset : assetsInDatabase.keySet()) {
                        if (searchWord.equals(nameOfAsset))
                            result = assetsInDatabase.get(searchWord);
                    }
                    break;
                }
                case("User"):{
                    for(String userId : usersInDatabase.keySet()) {
                        if (searchWord.equals(userId))
                            result = usersInDatabase.get(searchWord);
                    }
                    break;
                }
                case("Password"):{
                    //think about it
                    break;
                }

            }
            return result;
        }
        return null;
    }

}
