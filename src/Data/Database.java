package Data;
import Domain.*;
import Presentation.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;


public class Database //maybe generalize with interface? //for now red layer
{
    private HashMap<String, String> userNamesAndPasswords; //- <userId, encryptedPassword??>
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
                    //addUser(player.getID(),PasswordGenerator.generateRandPassword(6),player);
                    addUser(player.getID(),"Aa1234",player);
                }
            }
            for(Coach coach:team.getCoaches()){
                if(!usersInDatabase.containsKey(coach.getID())){
                    //addUser(player.getID(),PasswordGenerator.generateRandPassword(6),player);
                    addUser(coach.getID(),"Aa1234",coach);
                }
            }
            for(TeamOwner teamOwner:team.getTeamOwners()){
                if(!usersInDatabase.containsKey(teamOwner.getID())){
                    //addUser(player.getID(),PasswordGenerator.generateRandPassword(6),player);
                    addUser(teamOwner.getID(),"Aa1234",teamOwner);
                }
            }
            for(TeamManager teamManager:team.getTeamManagers()){
                if(!usersInDatabase.containsKey(teamManager.getID())){
                    //addUser(player.getID(),PasswordGenerator.generateRandPassword(6),player);
                    addUser(teamManager.getID(),"Aa1234",teamManager);
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
        if(!usersInDatabase.containsKey(userId)&& user.isActive()){
            //use some hash to encrypt the password?
            userNamesAndPasswords.put(userId, password);
            usersInDatabase.put(userId, user);
            if(user instanceof Asset && !assetsInDatabase.containsKey(user.getID())){
                assetsInDatabase.put(user.getID(), (Asset)user);
            }
            return true;
        }
        return false;
    }
    /*
    this function adds a game to the database according to its toString() value
    returns false if the game already exists
    */
    public boolean addGame(Game game){
        if(gamesInDatabase.containsKey(game.getId()))
            return false;
        gamesInDatabase.put(game.getId(), game);
        addUser(game.getMainReferee().getID(), "Aa1234", game.getMainReferee());
        for(Referee ref : game.getSideReferees()){
                addUser(ref.getID(), "Aa1234", ref);
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
    public boolean authenticationCheck(String userId, String password){
        if(usersInDatabase.containsKey(userId)&& usersInDatabase.get(userId).isActive()){
            //use some hash to encrypt the the given password to compare it with the one we have
            String passwordInSystem = userNamesAndPasswords.get(userId);
            return passwordInSystem.equals(password);
        }
        return false;
    }
    public void changePassword(String userId, String newPassword){
        if(userNamesAndPasswords.containsKey(userId))
            userNamesAndPasswords.replace(userId, newPassword);
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
                        if(searchWord.equals(team.getID()))
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

    public void removeUser(String userId) {
        User user = usersInDatabase.get(userId);
        if(user!=null){
            user.deactivate();
        }
    }

    public League getLeague(String nameOfLeague) {
        return (League)search("League", nameOfLeague);
    }

    public Season getSeason(String yearOfSeason) {
        return (Season)search("Season", yearOfSeason);
    }

    public void addComplaint(Complaint complaint){complaints.add(complaint);}


    //**UnitTests!**//
    public static void main(String[] args) {
        Database database = new Database();
        League league = new League("Alufot", "3");
        Season season = new Season(2019);
        System.out.println(database.addLeague(league));// expected : true
        System.out.println(database.addSeason(season));// expected : true
        System.out.println(database.addLeague(league));// expected : false
        System.out.println(database.addSeason(season));// expected : false
        User admin = new Admin("Admin","Ush", "example@gmail.com");
        User unionRep = new UnionRepresentative("Natzig", "Ush", "");
        System.out.println(database.addUser(admin.getID(), "aA1aA1", admin));//expected : true
        System.out.println(database.addUser(unionRep.getID(), "aA1aA1", unionRep));//expected : true maybe false
        System.out.println(database.addUser(admin.getID(), "aA1aA1", admin));//expected : false
        System.out.println(database.authenticationCheck(admin.getID(), "aA1aA1"));//expected : true
        System.out.println(database.authenticationCheck(admin.getID(), "aA1bA1"));//expected : false
        TeamOwner owner = new TeamOwner("Team","Owner", "");
        List<TeamOwner> owners1 = new LinkedList<>();
        owners1.add(owner);
        Field field1 = new Field("jerusalem", 550);
        Team hapoel = new Team("Hapoel", null, owners1, createPlayers(), createCoaches(),field1);

        System.out.println(database.addAsset(field1));//expected : true
        System.out.println(database.addTeam(hapoel));//expected : true
        PersonalPage hapoelsPage = new PersonalPage("",hapoel.getPlayers().get(0));
        System.out.println(database.addPage(hapoelsPage));//expected : true
        Field field2 = new Field( "TelAviv", 550);
        Team macabi = new Team ( "Macabi", null,owners1, createPlayers(),createCoaches(),field2);
        System.out.println(database.addTeam(macabi));//expected : true
        System.out.println(database.addAsset(field2));//expected : true
        Game game = new Game("G"+IdGenerator.getNewId(),new Date(120,4,25, 20,0,0), field1, mainReferee(), sideReferees(),hapoel, macabi);
        Game game2 = new Game("G"+IdGenerator.getNewId(),new Date(120,4,25, 20,0,0), field1, mainReferee(), sideReferees(),hapoel, macabi);
        System.out.println(database.addGame(game));//expected : true
        System.out.println(database.addGame(game2));//expected : true
        System.out.println(database.getUser("1"));//expected : admin
        System.out.println(database.getUser("2"));//expected : union rep
        System.out.println(database.getUser(""));//expected : null
        System.out.println(database.getAsset(macabi.getID()));//expected : macabi
        System.out.println(database.getAsset(field1.getID()));//expected : field1
        System.out.println(database.getAsset(""));//expected : null
        System.out.println(database.getGame(game.getId()));//expected : game
        System.out.println(database.getGame(""));//expected : null
        System.out.println(database.getPage(hapoelsPage.getId()));//expected : hapoelsPage
        System.out.println(database.getPage(""));//expected : null
        System.out.println(database.getSeason("2019"));//expected : season 2019
        System.out.println(database.getLeague("Alufot"));//expected : Alufot
        System.out.println(database.getSeason("2020"));//expected : null
        System.out.println(database.getLeague("Leomit"));//expected : null
        database.removeUser("2");
        System.out.println(database.getUser("2"));//expected : null
        System.out.println(database.getAllGames().size());//expected : 2 games
        System.out.println(database.getAllFutureGames().size());//expected : 1 game
        System.out.println(database.getListOfAllSpecificUsers("Admin").size());//expected : 1
        System.out.println(database.getListOfAllSpecificUsers("Player").size());//expected : 24
        System.out.println(database.getListOfAllSpecificUsers("Referee").size());//expected : 0

    }

    public static List<Referee> sideReferees() {
        List<Referee> refs = new LinkedList<>();
        Referee referee;
        for (int i = 0; i <3 ; i++) {
            referee = new Referee("ref"+i,"", "", "side");
            refs.add(referee);
        }
        return refs;
    }

    public static Referee mainReferee() {
        return new Referee("referee", "", "", "talented");
    }

    public static List<Coach> createCoaches() {
        Coach coach = new Coach("coach1", "", "", null, "", "main");
        List<Coach> coaches = new LinkedList<>();
        coaches.add(coach);
        return coaches;
    }


    public static List<Player> createPlayers() {
        List<Player> players = new LinkedList<>();
        for (int i = 0; i <12 ; i++) {
            players.add(new Player("player"+i, "", "", null, new Date (1999,1,1),"role"+i));
        }
        return players;
    }


}



