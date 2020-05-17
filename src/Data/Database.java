package Data;
import Domain.*;

import java.security.MessageDigest;
import java.util.*;


public class Database //maybe generalize with interface? //for now red layer
{
    private static HashMap<String,String> mailsAndPasswords; //- <mail, encryptedPassword??>
    private static HashMap<String,String> mailsAndUserID; //- <mail, userId>
    private static HashMap<String,User> usersInDatabase; // - <userId,User>
    private static HashMap<String, User> admins;
    private static HashMap<String, PartOfATeam> assetsInDatabase;// - <asset.name, PartOfATeam>
    private static HashMap<String, Game> gamesInDatabase; // - <game.id, Game>
    private static HashMap<String, PersonalPage> pagesInDatabase;//-<userId, PersonalPage>
    private static HashSet<League> leagues;
    private static HashSet<Season> seasons;
    private static HashMap<String, LeagueInSeason> leaguesInSeasons; //-<id, LeagueInSeason>
    private static HashMap<String , Complaint> complaints; //-<complaintId, Complaint>
    private static HashMap<String, Team> teams;
    private static HashMap<User, Referee> referees;

    public Database() {
        mailsAndPasswords = new HashMap<>();
        mailsAndUserID = new HashMap<>();
        usersInDatabase = new HashMap<>();
        assetsInDatabase = new HashMap<>();
        gamesInDatabase = new HashMap<>();
        pagesInDatabase = new HashMap<>();
        leaguesInSeasons = new HashMap<>();
        leagues = new HashSet<>();
        seasons = new HashSet<>();
        complaints = new HashMap<>();
        teams = new HashMap<>();
        admins = new HashMap<>();
        referees = new HashMap<>();
    }

    public static boolean addReferee(User user, Referee referee){
        if(referees.containsKey(user))return false;
        referees.put(user, referee);
        return true;
    }

    public static List<Referee> getReferees() {
        return new LinkedList<>(referees.values());
    }

    public static boolean addLeague(League league) {
        if(!leagues.contains(league)){
            leagues.add(league);
            return true;
        }
        return false;
    }
    public static boolean addSeason(Season season) {
        if(!seasons.contains(season)){
            seasons.add(season);
            return true;
        }
        return false;
    }

    public static boolean addTeam(Team team){
        if(!teams.containsKey(team.getID())){
            teams.put(team.getID(), team);
            return true;
        }

        return false;
    }

    public static boolean addLeagueInSeason(LeagueInSeason leagueInSeason){
        String id = leagueInSeason.getId();
        if(!leaguesInSeasons.containsKey(id)){
            leaguesInSeasons.put(id, leagueInSeason);
            return true;
        }
        return false;
    }

    public static LeagueInSeason getLeagueInSeason(String leagueInSeasonId){
        return leaguesInSeasons.get(leagueInSeasonId);
    }

    public static Team getTeam(String teamId){
        return teams.get(teamId);
    }

    public static List<Team> getTeams() {
        return new LinkedList<>(teams.values());
    }

    public static List<League> getLeagues(){ return new LinkedList<>(leagues);}

    public static List<Season> getSeasons() {
        return new LinkedList<>(seasons);
    }

    /*
            this function gets a name of an asset and returns a pointer to the object of this asset
            for example input: "Blumfield stadium" - the output will be a pointer to Blumfield stadium object or Null if it doesn't exists
            return null if cant find asset
             */
    public static PartOfATeam getAsset(String name){
        return (PartOfATeam)search("PartOfATeam", name);
    }
    /*
    this function gets a user id and returns a pointer to the object of this user
    for example input: "Leonardo Messi" - the output will be a pointer to messi's user or Null if it doesn't exists
    return null if cant find user
     */
    public static User getUser(String userId){
        return (User)search("User", userId);
    }

    public static User getUserByMail(String mail , String password){
        if(authenticationCheck(mail, password)) {
            return (User) search("Mail", mail);
        }
        return null;
    }
    /*
    this function gets a gameId - Game.toString (its address in memory) and returns a pointer to the object of this game
    return null if cant find game
    */
    public static Game getGame(String gameId){
        return (Game)search("Game", gameId);
    }
    /*
    this function gets a userId and return its personalPage if exists
    if page not exists the function returns null
     */
    public static PersonalPage getPage(String pageId){
        return (PersonalPage)search("Page", pageId);
    }
    /*
    this function returns all games in database
     */
    public static LinkedList<Game> getAllGames(){
        return new LinkedList<>(gamesInDatabase.values());
    }
    /*
    this function returns a list of all future games
     */
    public static LinkedList<Game> getAllFutureGames(){
        Date today = new Date();// Calendar.getInstance().getTime();
        LinkedList<Game> futureGames = new LinkedList<>();
        for(Game game : gamesInDatabase.values()){
            if(today.before(game.getDate()))
                futureGames.add(game);
        }
        return futureGames;
    }

    public static HashMap<String, PartOfATeam> getAssetsInDatabase() {
        return assetsInDatabase;
    }

    /*
        adds an asset to the database
        returns false if the asset already exists
         */
    public static boolean addAsset(PartOfATeam asset){
        String assetID = asset.getID();
        if(assetsInDatabase.containsKey(assetID)){
            return false;
        }
        assetsInDatabase.put(assetID, asset);

        return true;
    }

    public static PartOfATeam getAssetById(String assetId){
        return assetsInDatabase.get(assetId);
    }
    /*
    adds a user to the database
    returns false if the user already exists
     */
    public static boolean addUser(String password, User user){
        if(!mailsAndUserID.containsKey(user.getMail())&& user.isActive()){
            String encryptedPassword = encrypt(password);
            mailsAndPasswords.put(user.getMail(), encryptedPassword);
            mailsAndUserID.put(user.getMail(), user.getID());
            usersInDatabase.put(user.getID(), user);
            return true;
        }
        return false;
    }

    private static String encrypt(String password) {
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
    public static boolean addGame(Game game){
        if(gamesInDatabase.containsKey(game.getId()))
            return false;
        gamesInDatabase.put(game.getId(), game);

        addTeam(game.getGuestTeam());
        addTeam(game.getHostTeam());
        return true;
    }
    /*
    this function adds a new personal page to the database according to the user id
     */
    public static boolean addPage(PersonalPage page){
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
    private static boolean authenticationCheck(String mail, String password){
        if(mailsAndPasswords.containsKey(mail) && usersInDatabase.get(mailsAndUserID.get(mail)).isActive()){
            String encryptedPassword = encrypt(password);
            String passwordInSystem = mailsAndPasswords.get(mail);
            return passwordInSystem.equals(encryptedPassword);
        }
        return false;
    }
    public static boolean changePassword(String mail,String oldPassword , String newPassword){
        if(authenticationCheck(mail , oldPassword)) {
            if (mailsAndPasswords.containsKey(mail)) {
                String encryptedPassword = encrypt(newPassword);
                mailsAndPasswords.replace(mail, encryptedPassword);
                return true;
            }
        }
        return false;

    }
    /*
    this function returns a list of users of a specific type. for example all admins, all players ext.
    the input is a string of the type "Admin", "Player"
    if there aren't any users of this type - the list will be empty
    if the string type is wrong the function will return null
     */
    public static List<PartOfATeam> getListOfAllSpecificAssets(String userType){
        LinkedList<PartOfATeam> listOfAssets = new LinkedList<>();
        switch(userType){
            case("Coach"):{
                for(PartOfATeam asset : assetsInDatabase.values()){
                    if(asset instanceof Coach &&asset.isActive())
                        listOfAssets.add(asset);
                }
                return listOfAssets;

            }
            case("Fan"):{
                //for(User user : usersInDatabase.values()){
                //    if(user instanceof Fan &&user.isActive())
                //        listOfUsers.add(user);
                //}
                //return listOfUsers;
                break;

            }
            case("Player"):{
                for(PartOfATeam user : assetsInDatabase.values()){
                    if(user instanceof Player &&user.isActive())
                        listOfAssets.add(user);
                }
                return listOfAssets;
            }
            case("Referee"):{
                //for(User user : usersInDatabase.values()){
                //    if(user instanceof Referee &&user.isActive())
                //        listOfUsers.add(user);
                //}
                //return listOfUsers;
                break;
            }
            case("TeamManager"):{
                for(PartOfATeam user : assetsInDatabase.values()){
                    if(user instanceof TeamManager &&user.isActive())
                        listOfAssets.add(user);
                }
                return listOfAssets;

            }
            case("TeamOwner"):{
                //for(User user : usersInDatabase.values()){
                //    if(user instanceof TeamOwner &&user.isActive())
                //        listOfUsers.add(user);
                //}
                //return listOfUsers;
                break;
            }
        }
        return null;
    }
    public static List<Role> getListOfAllSpecificRoles(String userType) {
        LinkedList<Role> listOfUsers = new LinkedList<>();
        switch(userType) {
            case ("UnionRepresentative"): {
                for (User user : usersInDatabase.values()) {
                    if (user.isActive()) {
                        UnionRepresentative union = (UnionRepresentative) user.checkUserRole("UnionRepresentative");
                        if(union instanceof UnionRepresentative)
                            listOfUsers.add(union);
                    }
                }
                return listOfUsers;
            }
        }
        return null;
    }

    public static List<User> getSystemAdmins(){
        LinkedList<User> ListOfUsers = new LinkedList(admins.values());

        return ListOfUsers;
    }

    public static boolean addAdmin(String password, User admin){
        admins.put(admin.getID(), admin);
        return addUser(password, admin);
    }

    private static Object search(String whatType, String searchWord){
        switch(whatType){
            case("PartOfATeam"):{
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
            case ("Mail"):{
                if(mailsAndUserID.containsKey(searchWord)){
                    return usersInDatabase.get(mailsAndUserID.get(searchWord));
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

    public static String removeUser(String userId) {
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
    public static void removeAsset(String assetId) {
        PartOfATeam asset = assetsInDatabase.get(assetId);
        if(asset!=null){
            asset.deactivate();
        }

    }

    public static League getLeague(String nameOfLeague) {
        return (League)search("League", nameOfLeague);
    }

    public static Season getSeason(String yearOfSeason) {
        return (Season)search("Season", yearOfSeason);
    }

    public static Complaint getComplaints(String id) {
        return complaints.get(id);
    }



    public static boolean addComplaint(Complaint complaint){
        if(!complaints.containsKey(complaint.getId())){
            complaints.put(complaint.getId(), complaint);
            return true;
        }
        return false;
    }

    public static void writeToDatabaseDisk(){
        //*
    }
    public static void loadDatabaseFromDisk(String path){
        //*
    }

    public static List<Object> searchObject(String searchWord){
        List<Object> result = new LinkedList<>();
        for(User user : usersInDatabase.values()){
            if(searchWord.contains(user.getName())||searchWord.contains(user.getMail()))
                result.add(user);
        }
        for(Team team : teams.values()){
            if(searchWord.contains(team.getName()))
                result.add(team);
        }
        for(PartOfATeam asset : assetsInDatabase.values()){
            if(asset instanceof Field && ((Field)asset).getLocation().contains(searchWord)){
                result.add(asset);
            }
        }
        return result;

    }

    public static List<Team> getOpenTeams() {
        List<Team> openTeams = new LinkedList<>();
        for(Team team : teams.values()){
            if(team.isActive() && !team.isPermanentlyClosed())
                openTeams.add(team);
        }
        return openTeams;
    }

    public static List<Team> getCloseTeams() {
        List<Team> closeTeams = new LinkedList<>();
        for(Team team : teams.values()){
            if(team.isActive() && !team.isPermanentlyClosed())
                closeTeams.add(team);
        }
        return closeTeams;
    }
}



