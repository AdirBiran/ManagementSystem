package Data;
import Domain.*;
import Presentation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;


public class Database //maybe generalize with interface? //for now red layer
{
    private HashMap<String, String> userNamesAndPasswords; //- <userId, encryptedPassword??>
    private HashMap<String,User> usersInDatabase; // - <userId,User>
    private HashMap<String,Asset> assetsInDatabase;// - <asset.name, Asset>
    private HashMap<String, Game> gamesInDatabase; // - <game.id, Game>
    private HashMap<String, PersonalPage> pagesInDatabase;

    public Database() {
        userNamesAndPasswords = new HashMap<>();
        usersInDatabase = new HashMap<>();
        assetsInDatabase = new HashMap<>();
        gamesInDatabase = new HashMap<>();
        pagesInDatabase = new HashMap<>();
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
    this function gets a gameId - Game.toString (its address in memory) and returns a pointer to the object of this game
    return null if cant find game
    */
    public Game getGame(String gameId){
        return (Game)search("Game", gameId);
    }
    /*
    this function gets a userId and return its personalPage if exists
    if page not exists the function returns null
     */
    public PersonalPage getPge(String userId){
        return (PersonalPage)search("Page", userId);
    }
    /*
    this function returns all games in database
     */
    public LinkedList<Game> getAllGames(){
        return new LinkedList(gamesInDatabase.values());
    }
    /*
    this function returns a list of all future games
     */
    public LinkedList<Game> getAllFutureGames(){
        Date today = Calendar.getInstance().getTime();
        LinkedList<Game> futureGames = new LinkedList<>();
        for(Game game : gamesInDatabase.values()){
            if(today.before(game.getDate()))
                futureGames.add(game);
        }
        return futureGames;
    }
    /*
    adds an asset to the database
    returns false if the asset already exists
     */
    public boolean addAsset(Asset asset){
        String assetName = asset.getID();
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
    this function adds a game to the database according to its toString() value
    returns false if the game already exists
    */
    public boolean addGame(Game game){
        if(gamesInDatabase.containsKey(game.toString()))
            return false;
        gamesInDatabase.put(game.toString(), game);
        return true;
    }
    /*
    this function adds a new personal page to the database according to the user id
     */
    public boolean addPage(PersonalPage page){
        String userId = page.getId();
        if(pagesInDatabase.containsKey(userId))
            return false;
        pagesInDatabase.put(userId, page);
        return true;
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
    /*
    this function returns a list of users of a specific type. for example all admins, all players ext.
    the input is a string of the type "Admin", "Player"
    if there aren't any users of this type - the list will be empty
    if the string type is wrong the function will return null
     */
    public List<User> getListOfAllSpecificUsers(String userType){
        LinkedList<User> listOfUsers = new LinkedList<>();
        switch(userType){
            case("Admin"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Admin)
                        listOfUsers.add(user);
                }
                return listOfUsers;

            }
            case("Coach"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Coach)
                        listOfUsers.add(user);
                }
                return listOfUsers;

            }
            case("Fan"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Fan)
                        listOfUsers.add(user);
                }
                return listOfUsers;

            }
            case("Player"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Player)
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("Referee"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Referee)
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("TeamManager"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof TeamManager)
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("TeamOwner"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof TeamOwner)
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("UnionRepresentative"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof UnionRepresentative)
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
        }
        return null;
    }


    private Object search(String whatType, String searchWord){
            switch(whatType){
                case("Asset"):{
                    for(String nameOfAsset : assetsInDatabase.keySet()) {
                        if (searchWord.equals(nameOfAsset))
                            return assetsInDatabase.get(searchWord);
                    }
                    break;
                }
                case("User"):{
                    for(String userId : usersInDatabase.keySet()) {
                        if (searchWord.equals(userId)) {
                            return usersInDatabase.get(searchWord);
                        }
                    }
                    break;
                }
                case("Game"):{
                    for(String gameId:gamesInDatabase.keySet()){
                        if(searchWord.equals(gameId))
                            return gamesInDatabase.get(gameId);
                    }
                    break;
                }
                case("Page"):{
                    for(String userId:pagesInDatabase.keySet()){
                        if(searchWord.equals(userId))
                            return pagesInDatabase.get(userId);
                    }
                    break;
                }
                case("Password"):{
                    //think about it
                    break;
                }

            }
            return null;

    }

    public HashMap<String, String> getUserNamesAndPasswords() {
        return userNamesAndPasswords;
    }

}
