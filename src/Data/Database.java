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

    private static DataAccess dataAccess;

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

        dataAccess = new DataAccess();
    }


    public static boolean updateObject(Object object){

        //אדמין מיותר כי הID לא משתנה רק דוגמא
       /* if(object instanceof Admin){
            return dataAccess.updateCellValue("Admins" ,"ID" ,((Admin) object).getUser().getID() , ((Admin) object).getUser().getID());
        }*/
        if(object instanceof Coach){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             * [Training] [varchar](50) NOT NULL,
             * 	[Teams] [varchar](255) NOT NULL,
             * 	[isActive] [bit] NOT NULL,
             * 	[Price] [real] NOT NULL,
             * 	*/
            ans1 = dataAccess.updateCellValue("Coaches" ,"Training" ,((Coach) object).getID() ,((Coach) object).getTraining() );
            //not sure what to do with the list of teams
            ans2 = dataAccess.updateCellValue("Coaches" ,"Teams" ,((Coach) object).getID() ,listToString( ((Coach) object).getTeams() ));
            ans3 = dataAccess.updateCellValue("Coaches" ,"isActive" ,((Coach) object).getID() ,""+((Coach) object).isActive() );
            ans4 = dataAccess.updateCellValue("Coaches" ,"Price" ,((Coach) object).getID() ,""+((Coach) object).getPrice() );

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof Complaint){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             * [ComplaintDate] [date] NOT NULL,
             * 	[isActive] [bit] NOT NULL,
             * 	[Description] [varchar] (1000)NOT NULL ,
             * 	[ComplainedFanID] [char](50) NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Complaints" ,"ComplaintDate" ,((Complaint) object).getId() ,""+((Complaint) object).getDate() );
            ans2 = dataAccess.updateCellValue("Complaints" ,"isActive" ,((Complaint) object).getId() ,""+((Complaint) object).getIsActive());
            ans3 = dataAccess.updateCellValue("Complaints" ,"Description" ,((Complaint) object).getId() ,((Complaint) object).getDescription() );
            ans4 = dataAccess.updateCellValue("Complaints" ,"ComplainedFanID" ,((Complaint) object).getId() ,((Complaint) object).getFanComplained().getUser().getID() );

            return ans1 && ans2 && ans3 && ans4 ;
        }

        else if(object instanceof Event){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             *
             * [EventType] [char](50) NOT NULL,
             * 	[EventDate] [date] NOT NULL,
             * 	[MinutesInGame] [real] NOT NULL,
             * 	[Description] [varchar](max) NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Events","EventType",((Event) object).getId() ,""+((Event) object).getType());
            ans2 = dataAccess.updateCellValue("Events","EventDate", ((Event) object).getId(),""+((Event) object).getDate());
            ans3 = dataAccess.updateCellValue("Events","MinutesInGame",((Event) object).getId(),""+((Event) object).getMinuteInGame());
            ans4 = dataAccess.updateCellValue("Events","Description" ,((Event) object).getId(),((Event) object).getDescription());


            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof EventReport){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             *
             * [GameID] [char](30) NOT NULL,
             * 	[EventsIDs] [varchar](max) NOT NULL,
             * */

            // ans1 = dataAccess.updateCellValue("EventReports","GameID",((EventReport) object).getId() ,);
            ans2 = dataAccess.updateCellValue("EventReports","EventsIDs",((EventReport) object).getId() , ((EventReport) object).getEventsId());


            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof Fan){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             * [Address] [varchar](255) NOT NULL,
             * 	[Phone] [varchar](50) NOT NULL unique,
             * 	[FollowedPagesIDs] [varchar](255) ,
             * */

            ans2 = dataAccess.updateCellValue("Fans" ,"Address" , ((Fan)object).getUser().getID(),((Fan) object).getAddress() );
            ans3 = dataAccess.updateCellValue("Fans" ,"Phone" ,((Fan)object).getUser().getID() , ((Fan) object).getPhone());
            ans4 = dataAccess.updateCellValue("Fans" ,"FollowedPagesIDs" , ((Fan)object).getUser().getID(), ((Fan) object).getfollowPagesId());

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof Field){
            boolean ans1=true,ans2=true,ans3=true,ans4=true,ans5=true;
            /**
             *
             * [Location] [char](50) NOT NULL,
             * 	[Capacity] [int] NOT NULL,
             * 	[Teams] [varchar](255) NOT NULL,
             * 	[isActive] [bit] NOT NULL,
             * 	[Price] [real] NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Fields","Location",((Field) object).getID() ,((Field) object).getLocation());
            ans2 = dataAccess.updateCellValue("Fields","Capacity", ((Field) object).getID(),""+((Field) object).getCapacity());
            ans3 = dataAccess.updateCellValue("Fields","Teams", ((Field) object).getID(),listToString(((Field) object).getTeams()) );
            ans4 = dataAccess.updateCellValue("Fields","isActive" ,((Field) object).getID(),""+((Field) object).isActive());
            ans5 = dataAccess.updateCellValue("Fields","Price" ,((Field) object).getID(),""+((Field) object).getPrice());


            return ans1 && ans2 && ans3 && ans4  && ans5;
        }
        else if(object instanceof Game){
            boolean ans1=true,ans2=true,ans3=true,ans4=true,
                    ans5=true,ans6=true,ans7=true,ans8=true,
                    ans9=true,ans10=true ,ans11=true;
            /**
             *
             [GameDate] [date] NOT NULL,
             [HostScore] [int] NOT NULL,
             [GuestScore] [int] NOT NULL,
             [FieldID] [char] (50) NOT NULL,
             [MainRefereeID] [char] (50) NOT NULL,
             [SideRefereesIDs] [char] (50) NOT NULL,
             [HostTeamID] [char] (50) NOT NULL,
             [GuestTeamID] [char] (50) NOT NULL,
             [AlertsFansIDs] [varchar](max) NOT NULL,
             [EventReportID] [char] (50) NOT NULL,
             [LeagueInSeasonID] [char] (50) NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Games","GameDate",((Game) object).getId() ,""+((Game) object).getDate());
            ans2 = dataAccess.updateCellValue("Games","HostScore", ((Game) object).getId(),""+((Game) object).hostScore());
            ans3 = dataAccess.updateCellValue("Games","GuestScore", ((Game) object).getId(),""+((Game) object).guestScore());
            ans4 = dataAccess.updateCellValue("Games","FieldID" ,((Game) object).getId(),((Game) object).getField().getID());
            ans5 = dataAccess.updateCellValue("Games","MainRefereeID" ,((Game) object).getId(),((Game) object).getMainReferee().getUser().getID());
            ans6 = dataAccess.updateCellValue("Games","SideRefereesIDs" ,((Game) object).getId(),((Game) object).getSideRefereesId() );
            ans7 = dataAccess.updateCellValue("Games","HostTeamID" ,((Game) object).getId(),((Game) object).getHostTeam().getID());
            ans8 = dataAccess.updateCellValue("Games","GuestTeamID" ,((Game) object).getId(),((Game) object).getGuestTeam().getID());
            ans9 = dataAccess.updateCellValue("Games","AlertsFansIDs" ,((Game) object).getId(),((Game) object).getAlertsFansId());
            ans10 = dataAccess.updateCellValue("Games","EventReportID" ,((Game) object).getId(),((Game) object).getEventReport().getId());
            ans11 = dataAccess.updateCellValue("Games","LeagueInSeasonID" ,((Game) object).getId(),((Game) object).getLeague().getId());


            return ans1 && ans2 && ans3 && ans4  && ans5 &&
                    ans6 && ans7 && ans8 && ans9  && ans10 && ans11;
        }
        else if(object instanceof League){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             [Name] [varchar](50) NOT NULL,
             [LeagueLevel] [varchar](50) NOT NULL,
             [SeasonsIDs] [varchar](255) NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Leagues" ,"Name" , ((League) object).getId() , ((League) object).getName());
            ans2 = dataAccess.updateCellValue("Leagues" ,"LeagueLevel" ,((League) object).getId() ,((League) object).getLevel() );
            //ans4 = dataAccess.updateCellValue("Leagues" ,"SeasonsIDs" , ((League) object).getId(),object. );

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof LeagueInSeason){
            boolean ans1=true,ans2=true,ans3=true,ans4=true,ans5=true ,ans6=true,ans7=true;
            /**
             *
             [AssignmentPolicy] [char](255) NOT NULL,
             [ScorePolicy] [char](255) NOT NULL,
             [GamesIDs] [varchar](255) NOT NULL,
             [RefereesIDs] [varchar](255) NOT NULL,
             [TeamsIDs] [varchar](255) NOT NULL,
             [RegistrationFee] [real] NOT NULL,
             [Records] [varchar](1000) NOT NULL,
             * */

            //ans1 = dataAccess.updateCellValue("LeaguesInSeasons","AssignmentPolicy", ((LeagueInSeason) object).getId() ,((LeagueInSeason) object).getAssignmentPolicy());
            // ans2 = dataAccess.updateCellValue("LeaguesInSeasons","ScorePolicy", ((LeagueInSeason) object).getId(),((LeagueInSeason) object).getScorePolicy());
            ans3 = dataAccess.updateCellValue("LeaguesInSeasons","GamesIDs", ((LeagueInSeason) object).getId(), ((LeagueInSeason) object).getGamesId());
            ans4 = dataAccess.updateCellValue("LeaguesInSeasons","RefereesIDs" ,((LeagueInSeason) object).getId(),((LeagueInSeason) object).getRefereesId());
            ans5 = dataAccess.updateCellValue("LeaguesInSeasons","TeamsIDs" ,((LeagueInSeason) object).getId(),((LeagueInSeason) object).getTeamsId());
            ans6 = dataAccess.updateCellValue("LeaguesInSeasons","RegistrationFee" ,((LeagueInSeason) object).getId(),""+((LeagueInSeason) object).getRegistrationFee());
            //ans7 = dataAccess.updateCellValue("LeaguesInSeasons","Records" ,((LeagueInSeason) object).getId(),);


            return ans1 && ans2 && ans3 && ans4  && ans5 && ans6  && ans7;
        }
        else if(object instanceof PersonalPage){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             [OwnerID] [char](30) NOT NULL unique,
             [PageData] [varchar](max) ,
             [Followers] [varchar](max) ,
             * */

            ans1 = dataAccess.updateCellValue("PersonalPages" ,"OwnerID" , ((PersonalPage) object).getId() ,((PersonalPage) object).getUser().getID() );
            ans2 = dataAccess.updateCellValue("PersonalPages" ,"PageData" ,((PersonalPage) object).getId() , ((PersonalPage) object).getData() );
            ans3 = dataAccess.updateCellValue("PersonalPages" ,"Followers" , ((PersonalPage) object).getId(), listToString(((PersonalPage) object).getFollowers()));

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else  if(object instanceof Player){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             [Birthdate] [date] NOT NULL,
             [Teams] [varchar](255) NOT NULL,
             [isActive] [bit] NOT NULL,
             [Price] [real] NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Players" ,"Birthdate" ,((Player) object).getID() , ""+((Player) object).getBirthDate());
            ans2 = dataAccess.updateCellValue("Players" ,"Teams" , ((Player) object).getID(), listToString(((Player) object).getTeams()));
            ans3 = dataAccess.updateCellValue("Players" ,"isActive" , ((Player) object).getID(), ""+((Player) object).isActive());
            ans4 = dataAccess.updateCellValue("Players" ,"Price" , ((Player) object).getID(), ""+((Player) object).getPrice());

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof Referee){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             [Training] [varchar](50) NOT NULL,
             [Games] [varchar](255) NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Referees" ,"Training" , ((Referee) object).getUser().getID() ,((Referee) object).getTraining() );
            ans2 = dataAccess.updateCellValue("Referees" ,"Games" , ((Referee) object).getUser().getID(), listToString(((Referee) object).viewGames()) );

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof Season){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             [SeasonYear] [int] NOT NULL,
             [StartDate] [date] NOT NULL,
             [LeaguesIDs] [varchar](255) NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Seasons" ,"SeasonYear" , ((Season) object).getId() ,""+((Season) object).getYear() );
            ans2 = dataAccess.updateCellValue("Seasons" ,"StartDate" , ((Season) object).getId(), ""+((Season) object).getStartDate() );
            ans3 = dataAccess.updateCellValue("Seasons" ,"LeaguesIDs" , ((Season) object).getId(), ((Season) object).getLeaguesId() );

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof Team){
            boolean ans1=true,ans2=true,ans3=true,ans4=true,
                    ans5=true,ans6=true,ans7=true,ans8=true,
                    ans9=true,ans10=true ,ans11=true, ans12=true,
                    ans13=true,ans14=true ,ans15=true;
            /**
             *
             [Name] [varchar](50) NOT NULL,
             [Wins] [int] NOT NULL,
             [Losses] [int] NOT NULL,
             [Draws] [int] NOT NULL,
             [PersonalPageID] [char] (30) NOT NULL,
             [TeamOwners] [varchar](255) NOT NULL ,
             [TeamManagers] [varchar](255) NOT NULL,
             [Players] [varchar](255) NOT NULL,
             [Coaches] [varchar](255) NOT NULL,
             [Budget] [real] NOT NULL,
             [GamesIDs] [varchar] (255) NOT NULL,
             [Fields] [varchar] (255) NOT NULL,
             [LeaguesInSeasons] [varchar] (255) NOT NULL,
             [isActive] [bit] NOT NULL,
             [isPermanentlyClosed] [bit] NOT NULL,
             * */

            ans1 = dataAccess.updateCellValue("Teams","Name", ((Team) object).getID(), ((Team) object).getName());
            ans2 = dataAccess.updateCellValue("Teams","Wins", ((Team) object).getID(), ""+((Team) object).getWins());
            ans3 = dataAccess.updateCellValue("Teams","Losses", ((Team) object).getID(), ""+((Team) object).getLosses());
            ans4 = dataAccess.updateCellValue("Teams","Draws", ((Team) object).getID(), ""+((Team) object).getDraws());
            ans5 = dataAccess.updateCellValue("Teams","PersonalPageID", ((Team) object).getID(),((Team) object).getPage().getId() );
            ans6 = dataAccess.updateCellValue("Teams","TeamOwners", ((Team) object).getID(), listToString(((Team) object).getTeamOwners()));
            ans7 = dataAccess.updateCellValue("Teams","TeamManagers", ((Team) object).getID(), listToString(((Team) object).getTeamManagers()));
            ans8 = dataAccess.updateCellValue("Teams","Players", ((Team) object).getID(), listToString(((Team) object).getPlayers())  );
            ans9 = dataAccess.updateCellValue("Teams","Coaches",((Team) object).getID() , listToString(((Team) object).getCoaches()));
           //מה לשים בטבלה מבחינת ה buget
            // ans10 = dataAccess.updateCellValue("Teams","Budget" , ((Team) object).getID(), ""+((Team) object).getBudget().getBalance());
            ans11 = dataAccess.updateCellValue("Teams","GamesIDs" , ((Team) object).getID(), ((Team) object).getGamesId() );
            ans12 = dataAccess.updateCellValue("Teams","Fields" , ((Team) object).getID(), listToString(((Team) object).getFields()) );
            //לא הבנתי מה הכוונה
            //ans13 = dataAccess.updateCellValue("Teams","LeaguesInSeasons" , ((Team) object).getID(), );
            ans14 = dataAccess.updateCellValue("Teams","isActive" ,((Team) object).getID() , ""+((Team) object).isActive());
            ans15 = dataAccess.updateCellValue("Teams","isPermanentlyClosed" , ((Team) object).getID(), ""+((Team) object).isPermanentlyClosed());


            return ans1 && ans2 && ans3 && ans4  && ans5 &&
                    ans6 && ans7 && ans8 && ans9  && ans10 && ans11
                    && ans12 && ans13  && ans14 && ans15;
        }
        else if(object instanceof TeamManager){
            boolean ans1=true,ans2=true,ans3=true,ans4=true,ans5=true;
            /**
             [Teams] [varchar](255) NOT NULL,
             [isActive] [bit] NOT NULL,
             [Price] [real] NOT NULL,
             [ManageAssets] [bit] NOT NULL,
             [Finance] [bit] ,
             * */

            ans1 = dataAccess.updateCellValue("TeamManagers" ,"Teams" , ((TeamManager) object).getID() , listToString(((TeamManager) object).getTeams()));
            ans2 = dataAccess.updateCellValue("TeamManagers" ,"isActive" ,((TeamManager) object).getID() , ""+((TeamManager) object).isActive());
            ans3 = dataAccess.updateCellValue("TeamManagers" ,"Price" , ((TeamManager) object).getID(), ""+((TeamManager) object).getPrice());
           //ManageAssets?
            // ans4 = dataAccess.updateCellValue("TeamManagers" ,"ManageAssets" , ((TeamManager) object).getID(), );
            //Finance?
            //ans5 = dataAccess.updateCellValue("TeamManagers" ,"Finance" , ((TeamManager) object).getID(), );

            return ans1 && ans2 && ans3 && ans4 && ans5 ;
        }
        else if(object instanceof TeamOwner){
            boolean ans1=true,ans2=true,ans3=true,ans4=true;
            /**
             [Teams] [varchar](255) NOT NULL,
             [ClosedTeams] [varchar](255) ,
             [AppointedTeamOwners] [varchar] ,
             [AppointedTeamManagers] [varchar](255) ,
             * */

            ans1 = dataAccess.updateCellValue("TeamOwners" ,"Teams" , ((TeamOwner) object).getUser().getID(), listToString(((TeamOwner) object).getTeamsToManage()));
            ans2 = dataAccess.updateCellValue("TeamOwners" ,"ClosedTeams" , ((TeamOwner) object).getUser().getID(), listToString(((TeamOwner) object).getClosedTeams()) );

            //HashMap for user and team, need to save them together
            //ans3 = dataAccess.updateCellValue("TeamOwners" ,"AppointedTeamOwners" , ((TeamOwner) object).getUser().getID(), listToString(((TeamOwner) object).getAppointedTeamOwners().keySet()));
            //ans4 = dataAccess.updateCellValue("TeamOwners" ,"AppointedTeamManagers" , ((TeamOwner) object).getUser().getID(), );

            return ans1 && ans2 && ans3 && ans4 ;
        }
        else if(object instanceof User){
            boolean ans1=true,ans2=true,ans3=true,ans4=true,ans5=true ,ans6=true;
            /**
             *
             [FirstName] [varchar](255) NOT NULL,
             [LastName] [varchar](255) NOT NULL,
             [Mail] [varchar](255) NOT NULL unique,
             [isActive] [bit] NOT NULL,
             [Roles] [varchar](255) ,
             [searchHistories] [varchar](1000) ,
             * */

            ans1 = dataAccess.updateCellValue("Users","FirstName",((User) object).getID() , ((User) object).getFirstName());
            ans2 = dataAccess.updateCellValue("Users","LastName", ((User) object).getID(),((User) object).getLastName());
            ans3 = dataAccess.updateCellValue("Users","Mail", ((User) object).getID(), ((User) object).getMail());
            ans4 = dataAccess.updateCellValue("Users","isActive" ,((User) object).getID(), ""+((User) object).isActive());
            ans5 = dataAccess.updateCellValue("Users","Roles" ,((User) object).getID(), listToString(((User) object).getRoles()));
            ans6 = dataAccess.updateCellValue("Users","searchHistories" ,((User) object).getID(), listToString(((User) object).getSearchHistory()));


            return ans1 && ans2 && ans3 && ans4  && ans5 && ans6  ;
        }
        return false;

    }



    private static String listToString(Collection objects){
        String listOfStrings="";
        for (Object object:objects) {
            if(listOfStrings.equals("")){
                listOfStrings= listOfStrings +object;
            }else {
                listOfStrings = listOfStrings + "," + object;
            }
        }
        return listOfStrings;

    }


    private static String listToStringForEventsID(List<Event> events){
        String listOfStrings="";
        for (Event event:events) {
            if(listOfStrings.equals("")){
                listOfStrings= listOfStrings +event.getId();
            }else {
                listOfStrings = listOfStrings + "," + event.getId();
            }
        }
        return listOfStrings;

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
            case ("Field"):{
                for(PartOfATeam asset : assetsInDatabase.values()){
                    if(asset instanceof Field && asset.isActive())
                        listOfAssets.add(asset);
                }
                return listOfAssets;
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
                        if (searchWord.contains(nameOfAsset))
                            return assetsInDatabase.get(searchWord);
                    }
                    break;
                }
                case("User"): {
                    for (String userId : usersInDatabase.keySet()) {
                        if (searchWord.equals(userId)) {
                            if (usersInDatabase.get(searchWord).isActive())
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

    public static List<User> getAllUsers(){
        return null;
    }

    public static List<Game> getAllPastGames() {
        return null;
    }

    public static List<PersonalPage> getAllPages() {
        return null;
    }

    public static List<Complaint> getAllActiveComplaints() {
        return null;
    }
}



