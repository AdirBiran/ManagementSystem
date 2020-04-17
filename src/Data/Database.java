package Data;
import Domain.*;
import Presentation.*;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;


public class Database //maybe generalize with interface? //for now red layer
{
    private HashMap<String, String> userNamesAndPasswords; //- <mail, encryptedPassword??>
    private HashMap<String,User> usersInDatabase; // - <userId,User>
    private HashMap<String,Asset> assetsInDatabase;// - <asset.name, Asset>
    private HashMap<String, Game> gamesInDatabase; // - <game.id, Game>
    private HashMap<String, PersonalPage> pagesInDatabase;//-<userId, PersonalPage>
    private HashSet<League> leagues;
    private HashSet<Season> seasons;
    private HashSet<Complaint> complaints;
    private HashMap<String, Team> teams;

    public Database() {
        userNamesAndPasswords = new HashMap<>();
        usersInDatabase = new HashMap<>();
        assetsInDatabase = new HashMap<>();
        gamesInDatabase = new HashMap<>();
        pagesInDatabase = new HashMap<>();
        leagues = new HashSet<>();
        seasons = new HashSet<>();
        complaints = new HashSet<>();
        teams = new HashMap<>();
    }

    public boolean addLeague(League league) {
        if(!leagues.contains(league)){
            leagues.add(league);
            return true;
        }
        return false;
    }
    public boolean addSeason(Season season) {
        if(!seasons.contains(season)){
            seasons.add(season);
            return true;
        }
        return false;
    }

    public boolean addTeam(Team team){
        if(!teams.containsKey(team.getID())){
            teams.put(team.getID(), team);
            for(Player player:team.getPlayers()){
                if(!usersInDatabase.containsKey(player.getID())){
                    addUser("Aa1234",player);
                }
            }
            for(Coach coach:team.getCoaches()){
                if(!usersInDatabase.containsKey(coach.getID())){

                    addUser("Aa1234",coach);
                }
            }
            for(TeamOwner teamOwner:team.getTeamOwners()){
                if(!usersInDatabase.containsKey(teamOwner.getID())){

                    addUser("Aa1234",teamOwner);
                }
            }
            for(TeamManager teamManager:team.getTeamManagers()){
                if(!usersInDatabase.containsKey(teamManager.getID())){

                    addUser("Aa1234",teamManager);
                }
            }

            return true;
        }

        return false;
    }
    public Team getTeam(String teamId){
        return (Team)search("Team", teamId);
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
    public PersonalPage getPage(String userId){
        return (PersonalPage)search("Page", userId);
    }
    /*
    this function returns all games in database
     */
    public LinkedList<Game> getAllGames(){
        return new LinkedList<>(gamesInDatabase.values());
    }
    /*
    this function returns a list of all future games
     */
    public LinkedList<Game> getAllFutureGames(){
        Date today = new Date();// Calendar.getInstance().getTime();
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
        String assetID = asset.getID();
        if(assetsInDatabase.containsKey(assetID)){
            return false;
        }
        assetsInDatabase.put(assetID, asset);

        return true;
    }
    /*
    adds a user to the database
    returns false if the user already exists
     */
    public boolean addUser(String password, User user){
        if(!usersInDatabase.containsKey(user.getID())&& user.isActive()){
            String encryptedPassword = encrypt(password);
            userNamesAndPasswords.put(user.getMail(), encryptedPassword);
            usersInDatabase.put(user.getID(), user);
            if(user instanceof Asset && !assetsInDatabase.containsKey(user.getID())){
                assetsInDatabase.put(user.getID(), (Asset)user);
            }
            return true;
        }
        return false;
    }

    private String encrypt(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageDigest.update(password.getBytes());
        return new String(messageDigest.digest());
    }

    /*
    this function adds a game to the database according to its toString() value
    returns false if the game already exists
    */
    public boolean addGame(Game game){
        if(gamesInDatabase.containsKey(game.getId()))
            return false;
        gamesInDatabase.put(game.getId(), game);
        addUser("Aa1234", game.getMainReferee());
        for(Referee ref : game.getSideReferees()){
                addUser("Aa1234", ref);
        }
        addTeam(game.getGuestTeam());
        addTeam(game.getHostTeam());
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
    public boolean authenticationCheck(String mail, String password){
        if(usersInDatabase.containsKey(mail)&& usersInDatabase.get(mail).isActive()){
            String encryptedPassword = encrypt(password);
            String passwordInSystem = userNamesAndPasswords.get(mail);
            return passwordInSystem.equals(encryptedPassword);
        }
        return false;
    }
    public void changePassword(String mail, String newPassword){
        if(userNamesAndPasswords.containsKey(mail)){
            String encryptedPassword = encrypt(newPassword);
            userNamesAndPasswords.replace(mail, encryptedPassword);
        }

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
                    if(user instanceof Admin &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;

            }
            case("Coach"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Coach &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;

            }
            case("Fan"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Fan &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;

            }
            case("Player"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Player &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("Referee"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof Referee &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("TeamManager"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof TeamManager &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("TeamOwner"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof TeamOwner &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
            case("UnionRepresentative"):{
                for(User user : usersInDatabase.values()){
                    if(user instanceof UnionRepresentative &&user.isActive())
                        listOfUsers.add(user);
                }
                return listOfUsers;
            }
        }
        return null;
    }

    public List<Admin>GetSystemAdmins(){
        List<Admin> listOfUsers = new LinkedList<>();
        for(User user : usersInDatabase.values()){
            if(user instanceof Admin &&user.isActive())
                listOfUsers.add((Admin)user);
        }
        return listOfUsers;
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
                            if(usersInDatabase.get(searchWord).isActive())
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
                case("League"):{
                    for(League league:leagues){
                        if(searchWord.equals(league.getName()))
                            return league;
                    }
                }
                case("Season"):{
                    String year="";
                    for(Season season:seasons){
                        year =""+season.getYear();
                        if(searchWord.equals(year))
                            return season;
                    }
                }
                case("Team"):{
                    for(Team team:teams.values()){
                        if(searchWord.equals(team.getID())&&team.isActive())
                            return team;
                    }
                }
                case("Password"):{
                    //think about it
                    break;
                }

            }
            return null;

    }

    public String removeUser(String userId) {
        User user = usersInDatabase.get(userId);
        String userMail="";
        if(user!=null){
            user.deactivate();
            userMail= user.getMail();
        }
        return userMail;
    }

    /*
    *
    * */
    public void removeAsset(String assetId) {
        Asset asset = assetsInDatabase.get(assetId);
        if(asset!=null){
            if(asset instanceof User &&((User)asset).getAmountOfTeams()==0){
                asset.deactivate();
            }
            else if(asset instanceof Field)
                asset.deactivate();
        }

    }

    public League getLeague(String nameOfLeague) {
        return (League)search("League", nameOfLeague);
    }

    public Season getSeason(String yearOfSeason) {
        return (Season)search("Season", yearOfSeason);
    }

    public HashSet<Complaint> getComplaints() {
        return complaints;
    }

    public void addComplaint(Complaint complaint){complaints.add(complaint);}

    public void writeToDatabaseDisk(){
        //*
    }
    public void loadDatabaseFromDisk(String path){
        //*
    }

    public List<Object> searchObject(String searchWord){
        List<Object> result = new LinkedList<>();
        for(User user : usersInDatabase.values()){
            if(searchWord.equals(user.getName())||searchWord.equals(user.getMail()))
                result.add(user);
        }
        for(Team team : teams.values()){
            if(searchWord.equals(team.getName()))
                result.add(team);
        }
        for(Asset asset : assetsInDatabase.values()){
            if(asset instanceof Field && ((Field)asset).getLocation().equals(searchWord)){
                result.add(asset);
            }
        }
        return result;

    }

}



