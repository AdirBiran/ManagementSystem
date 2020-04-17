package Service;

import Data.Database;
import Domain.*;
import Presentation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class FootballManagementSystem {
    public static Database database;
    //***domain***//
    public static AssetManagement assetManagement;
    public static ComplaintManager complaintManager;
    public static EditPersonalInfo editPersonalInfo;
    public static EventReportManagement eventReportManagement;
    public static FinanceTransactionsManagement financeTransactionsManagement;
    public static LeagueAndGameManagement leagueAndGameManagement;
    public static PersonalPageManagement personalPageManagement;
    public static RefereeManagement refereeManagement;
    public static Searcher searcher;
    public static UserManagement userManagement;
    public static MailSender mailSender;
    //***service***//
    private static AssetSystem assetSystem ;
    private static NotificationSystem notificationSystem;
    private static FinanceTransactionsSystem financeTransactionsSystem;
    private static GuestSystem guestSystem;
    private static PersonalPageSystem personalPageSystem ;
    private static RefereeSystem refereeSystem;
    private static UnionRepresentativeSystem unionRepresentativeSystem;
    private static UserSystem userSystem;
    private static AdminSystem adminSystem;
    private static TeamManagementSystem teamManagementSystem;
     //***presentation***//
    private static List<Admin> systemAdmins;
     //***External systems***//
    private static StubAccountingSystem accountingSystem;
    private static StubIsraeliTaxLawsSystem taxLawsSystem;

    public TeamManagementSystem getTeamManagementSystem() {
        return teamManagementSystem;
    }

    public AdminSystem getAdminSystem() {
        return adminSystem;
    }

    public static UserSystem getUserSystem() {
        return userSystem;
    }

    public UnionRepresentativeSystem getUnionRepresentativeSystem() {
        return unionRepresentativeSystem;
    }

    public GuestSystem getGuestSystem() {
        return guestSystem;
    }

    public PersonalPageSystem getPersonalPageSystem() {
        return personalPageSystem;
    }

    public RefereeSystem getRefereeSystem() {
        return refereeSystem;
    }

    public SearchSystem getSearchSystem() {
        return searchSystem;
    }

    public AssetSystem getAssetSystem() {
        return assetSystem;
    }

    public NotificationSystem getNotificationSystem() {
        return notificationSystem;
    }

    public FinanceTransactionsSystem getFinanceTransactionsSystem() {
        return financeTransactionsSystem;
    }

    public boolean systemInit(boolean firsTime){
        //***data***//
        database = new Database();
        if(!firsTime)
            database.loadDatabaseFromDisk("");
        //***domain***//
        assetManagement = new AssetManagement(database);
        complaintManager = new ComplaintManager(database);
        editPersonalInfo = new EditPersonalInfo(database);
        eventReportManagement = new EventReportManagement(database);
        financeTransactionsManagement = new FinanceTransactionsManagement(database);
        leagueAndGameManagement = new LeagueAndGameManagement(database);
        personalPageManagement = new PersonalPageManagement(database);
        refereeManagement = new RefereeManagement(database);
        searcher = new Searcher(database);
        userManagement = new UserManagement(database);
        mailSender = new MailSender();
        //***service***//
        adminSystem = new AdminSystem(leagueAndGameManagement, userManagement, notificationSystem, complaintManager);
        assetSystem = new AssetSystem(assetManagement);
        notificationSystem = new NotificationSystem(leagueAndGameManagement, refereeManagement, assetManagement, mailSender, userManagement);
        financeTransactionsSystem = new FinanceTransactionsSystem(financeTransactionsManagement, notificationSystem);
        guestSystem = new GuestSystem(searcher, userManagement);
        personalPageSystem = new PersonalPageSystem(personalPageManagement);
        refereeSystem = new RefereeSystem(leagueAndGameManagement, refereeManagement, eventReportManagement);
        unionRepresentativeSystem = new UnionRepresentativeSystem(financeTransactionsManagement, leagueAndGameManagement, refereeManagement);
        userSystem = new UserSystem(searcher, complaintManager, editPersonalInfo, userManagement, leagueAndGameManagement);
        teamManagementSystem = new TeamManagementSystem(leagueAndGameManagement, userManagement, notificationSystem);
        //***presentation***//
        systemAdmins = new LinkedList<>();
        if(firsTime){
            Admin systemAdmin = new Admin("adminush","", "example@gmail.com");
            systemAdmins.add(systemAdmin);
            adminSystem.addUser("Adminush1", systemAdmin);
        }
        else{
            systemAdmins.addAll(database.GetSystemAdmins());
        }

        //***External systems***//
        accountingSystem = new StubAccountingSystem();
        taxLawsSystem = new StubIsraeliTaxLawsSystem();
        accountingSystem.connect();
        taxLawsSystem.connect();

        return true;
    }

    public void dataReboot(){
        unionRepresentativeSystem.configureNewSeason(2020);
        unionRepresentativeSystem.configureNewLeague("Haal", "3");
        LeagueInSeason leagueInSeason = unionRepresentativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for (int i = 0; i < 5; i++) {
            List<Player> players = createPlayers();
            List<Coach> coaches = createCoaches();
            TeamOwner owner = new TeamOwner("Team","Owner", "a"+i+"@gmail.com");
            List<TeamOwner> owners = new LinkedList<>();
            owners.add(owner);
            PersonalPage page = new PersonalPage("", players.get(0));
            Field field = new Field( "jerusalem", 550);
            Team team = new Team("team"+i,page,owners,players,coaches, field);
            database.addTeam(team);
            unionRepresentativeSystem.addTeamToLeague(leagueInSeason, team);
        }
        for (int i = 0; i <10 ; i++) {
            Referee ref = mainReferee();
            adminSystem.addUser("Aa123", ref);
            unionRepresentativeSystem.assignRefToLeague(leagueInSeason, ref);
        }
    }


    public static List<Referee> sideReferees() {
        List<Referee> refs = new LinkedList<>();
        Referee referee;
        for (int i = 0; i <3 ; i++) {
            referee = new Referee("ref"+i,"", "referee"+IdGenerator.getNewId()+"@gmail.com", "side");
            refs.add(referee);
        }
        return refs;
    }

    public static Referee mainReferee() {
        return new Referee("referee", "", "referee"+IdGenerator.getNewId()+"@gmail.com", "talented");
    }
    public static List<Coach> createCoaches() {
        Coach coach = new Coach("coach1", "", "coach"+IdGenerator.getNewId()+"@gmail.com", null, "", "main");
        List<Coach> coaches = new LinkedList<>();
        coaches.add(coach);
        return coaches;
    }
    public static List<Player> createPlayers() {
        List<Player> players = new LinkedList<>();
        for (int i = 0; i <12 ; i++) {
            players.add(new Player("player"+i, "", "player"+IdGenerator.getNewId()+"@gmail.com", null, new Date (99,1,1),"role"+i));
        }
        return players;
    }
    private static void printList(List<Game> allGames) {
        for(Game game: allGames){
            System.out.println(game.toString());
        }
    }

    private static List<Date> getDates() {
        LinkedList<Date> dates = new LinkedList<>();
        for (int i = 1; i < 29; i++) {
            dates.add(new Date (120, 6, i, 20, 0));
        }
        return dates;
    }

    public static void main(String[] args) {

        /**-----------DORON TESTS--------------*/

        TeamOwner teamOwner = new TeamOwner("Doron" , "Shamai" , "shamai.doron@gmail.com");
        TeamOwner teamOwner2 = new TeamOwner("Saly" , "Shamai" , "shamai.doron@gmail.com");
        TeamOwner teamOwner3 = new TeamOwner("Saly" , "Shamai" , "shamai.doron@gmail.com");

        TeamManager teamManager = new TeamManager("Yotam" , "Oren" , "yotamoren@gmail.com",null);

        List<TeamOwner> teamOwnerList = new LinkedList<>();
        teamOwnerList.add(teamOwner);

        Date date = new Date();
        Player player1 = new Player("1","1","1",null, date,"1");
        Player player2 = new Player("2","2","1",null, date,"1");
        Player player3 = new Player("3","3","1",null, date,"1");
        Player player4 = new Player("4","4","1",null, date,"1");
        Player player5 = new Player("5","5","1",null, date,"1");
        Player player6 = new Player("6","6","1",null, date,"1");
        Player player7 = new Player("7","7","1",null, date,"1");
        Player player8 = new Player("8","8","1",null, date,"1");
        Player player9 = new Player("9","9","1",null, date,"1");
        Player player10 = new Player("10","10","1",null, date,"1");
        Player player11 = new Player("11","11","1",null, date,"1");

        List<Player> playerList = new LinkedList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(player5);
        playerList.add(player6);
        playerList.add(player7);
        playerList.add(player8);
        playerList.add(player9);
        playerList.add(player10);
        playerList.add(player11);

        Coach coach = new Coach("coach", "1" ,"1", null,"master" , "1");

        List<Coach> coachList = new LinkedList<>();
        coachList.add(coach);

        Field field1 = new Field("Tel Aviv" , 10);

        Team team = new Team("Lidoy" , null ,teamOwnerList,playerList,coachList,field1 );
        //teamOwner.appointmentTeamManager(teamManager ,team);

        teamManagementSystem.appointmentTeamManager(teamOwner,teamManager,team);
        teamManagementSystem.appointmentTeamOwner(teamOwner,teamOwner2,team);
        teamManagementSystem.appointmentTeamOwner(teamOwner2,teamOwner3,team);

        /**TEAM OWNER TRY TO REMOVE APPOINTMENT
         * THOSE APPOINTMENTS IS NOT HIS APPOINTMENTS
         * */
        teamManagementSystem.removeAppointmentTeamOwner(teamOwner2,teamOwner,team);
        teamManagementSystem.removeAppointmentTeamManager(teamOwner2,teamManager,team);

        /**TEAM OWNER TRY TO REMOVE APPOINTMENT
         * THOSE APPOINTMENTS IS HIS APPOINTMENTS
         * */
        teamManagementSystem.removeAppointmentTeamOwner(teamOwner , teamOwner2,team);


        /**-----------DORON TESTS--------------*/

        //**UnitTests!-NotificationSystem**//
        LeagueInSeason league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020),200);
        TeamOwner owner = new TeamOwner("Team","Owner", "a@gmail.com");
        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        Field field = new Field( "jerusalem", 550);
        Team hapoel = new Team("Hapoel", null, owners, createPlayers(), createCoaches(),field);

        database.addAsset(field);
        database.addTeam(hapoel);
        PersonalPage hapoelsPage = new PersonalPage("",hapoel.getPlayers().get(0));
        database.addPage(hapoelsPage);//expected : true
        Field field2 = new Field( "TelAviv", 550);
        Team macabi = new Team ("Macabi", null,owners, createPlayers(),createCoaches(),field2);
        database.addTeam(macabi);
        database.addAsset(field2);
        Game game = new Game(new Date(120,4,25, 20,0,0), field, mainReferee(), sideReferees(),hapoel, macabi, league);
        Game game2 = new Game(new Date(120,4,25, 20,0,0), field, mainReferee(), sideReferees(),hapoel, macabi, league);
        database.addGame(game);
        database.addGame(game2);

        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        ReceiveAlerts receiveAlerts = new ReceiveAlerts(true, true);
        List<Game> games = new LinkedList<>();
        games.add(game);
        games.add(game2);
        System.out.println(notificationSystem.openORCloseTeam("closed", hapoel, false)); //expected : false
        System.out.println(notificationSystem.openORCloseTeam("open", hapoel, false)); //expected : true
        hapoel.setActive(false);
        System.out.println(notificationSystem.openORCloseTeam("closed", hapoel, true)); //expected : true
        notificationSystem.refereeAlertsChangeDate(game, game.getDate());
        notificationSystem.refereeAlertsChangeGameLocation(game, game.getField());
        System.out.println(notificationSystem.UserRemovalNotification(fan.getMail()));//expected : false
        fan.deactivate();
        System.out.println(notificationSystem.UserRemovalNotification(fan.getMail()));//expected : true
        System.out.println();
        System.out.println(financeTransactionsSystem.reportNewIncome(macabi.getTeamOwners().get(0), macabi, 200));//expected : true
        System.out.println(financeTransactionsSystem.reportNewExpanse(macabi.getTeamOwners().get(0), macabi, 300));//expected : false

        //-----------FinanceTransactionsSystem test--------------//
        System.out.println();
        System.out.println(financeTransactionsSystem.reportNewIncome(macabi.getTeamOwners().get(0), macabi, 200));//expected : true
        System.out.println(financeTransactionsSystem.reportNewIncome(macabi.getTeamOwners().get(0), macabi, 200));//expected : true
        System.out.println(financeTransactionsSystem.getBalance(macabi));//expected : 600
        System.out.println(financeTransactionsSystem.reportNewExpanse(macabi.getTeamOwners().get(0), macabi, 500));//expected : true
        System.out.println(financeTransactionsSystem.getBalance(macabi));//expected : 100
        System.out.println(financeTransactionsSystem.reportNewExpanse(macabi.getTeamOwners().get(0), macabi, 500));//expected : false
        System.out.println(financeTransactionsSystem.getBalance(macabi));//expected : 100

        //--------unionRepresentativeSystem test--------------//

        hapoel.setActive(true);
        unionRepresentativeSystem.configureNewLeague("leumit", "2");
        unionRepresentativeSystem.configureNewSeason(2020);
        GameAssignmentPolicy gameAssignmentPolicy = new PlayOnceWithEachTeamPolicy();
        LeagueInSeason leumit2020 = unionRepresentativeSystem.configureLeagueInSeason("leumit", "2020",gameAssignmentPolicy, new StandardScorePolicy(), 250);
        Referee ref1 = unionRepresentativeSystem.appointReferee("ref", "1", "a1@gmail.com", "the best one");
        Referee ref2 = unionRepresentativeSystem.appointReferee("ref", "2", "a2@gmail.com", "the best one");
        Referee ref3 = unionRepresentativeSystem.appointReferee("ref", "3", "a3@gmail.com", "the best one");
        unionRepresentativeSystem.assignRefToLeague(leumit2020, ref1);
        unionRepresentativeSystem.assignRefToLeague(leumit2020, ref2);
        unionRepresentativeSystem.assignRefToLeague(leumit2020, ref3);
        unionRepresentativeSystem.addTeamToLeague(leumit2020, macabi);
        unionRepresentativeSystem.addTeamToLeague(leumit2020, hapoel);
        unionRepresentativeSystem.addTeamToLeague(leumit2020, team);
        unionRepresentativeSystem.assignGames(leumit2020, getDates());
        printList(leumit2020.getAllGames());//expected :each team play once with each other team
        unionRepresentativeSystem.changeAssignmentPolicy(leumit2020, new PlayTwiceWithEachTeamPolicy());
        unionRepresentativeSystem.assignGames(leumit2020, getDates());
        printList(leumit2020.getAllGames());//expected :each team play twice with each other team

        //------------DATABASE UnitTests-------------//
        League league1 = new League("Alufot", "3");
        Season season = new Season(2019);
        System.out.println(database.addLeague(league1));// expected : true
        System.out.println(database.addSeason(season));// expected : true
        System.out.println(database.addLeague(league1));// expected : false
        System.out.println(database.addSeason(season));// expected : false
        User admin = new Admin("Admin","Ush", "example@gmail.com");
        User unionRep = new UnionRepresentative("Natzig", "Ush", "");
        System.out.println(database.addUser("aA1aA1", admin));//expected : true
        System.out.println(database.addUser("aA1aA1", unionRep));//expected : true
        System.out.println(database.addUser("aA1aA1", admin));//expected : false
        System.out.println(database.authenticationCheck(admin.getID(), "aA1aA1"));//expected : true
        System.out.println(database.authenticationCheck(admin.getID(), "aA1bA1"));//expected : false

        TeamOwner tOwner = new TeamOwner("Team","Owner", "");
        List<TeamOwner> owners1 = new LinkedList<>();
        owners1.add(tOwner);
        Field field11 = new Field("jerusalem", 550);
        Team hapoelTA = new Team("Hapoel", null, owners1, createPlayers(), createCoaches(),field11);

        System.out.println(database.addAsset(field11));//expected : true
        System.out.println(database.addTeam(hapoelTA));//expected : true
        PersonalPage hapoelsPage2 = new PersonalPage("",hapoelTA.getPlayers().get(0));
        System.out.println(database.addPage(hapoelsPage2));//expected : true
        Field field22 = new Field( "TelAviv", 550);
        Team macabi2 = new Team ( "Macabi", null,owners1, createPlayers(),createCoaches(),field22);
        System.out.println(database.addTeam(macabi2));//expected : true
        System.out.println(database.addAsset(field22));//expected : true
        Game game3 = new Game(new Date(120,4,25, 20,0,0), field1, mainReferee(), sideReferees(),hapoelTA, macabi2, league);
        Game game23 = new Game(new Date(120,4,25, 20,0,0), field1, mainReferee(), sideReferees(),hapoelTA, macabi2, league);
        System.out.println(database.addGame(game3));//expected : true
        System.out.println(database.addGame(game23));//expected : true
        System.out.println(database.getUser(admin.getID()));//expected : admin
        System.out.println(database.getUser(unionRep.getID()));//expected : union rep
        System.out.println(database.getUser(""));//expected : null
        System.out.println(database.getAsset(macabi2.getID()));//expected : macabi2
        System.out.println(database.getAsset(field11.getID()));//expected : field11
        System.out.println(database.getAsset(""));//expected : null
        System.out.println(database.getGame(game.getId()));//expected : game
        System.out.println(database.getGame(""));//expected : null
        System.out.println(database.getPage(hapoelsPage.getId()));//expected : hapoelsPage
        System.out.println(database.getPage(""));//expected : null
        System.out.println(database.getSeason("2019"));//expected : season 2019
        System.out.println(database.getLeague("Alufot"));//expected : Alufot
        System.out.println(database.getSeason("2020"));//expected : season 2020
        System.out.println(database.getLeague("leumit"));//expected : leomit
        database.removeUser("2");
        System.out.println(database.getUser("2"));//expected : null
        System.out.println(database.getAllGames().size());//expected : 2 games
        System.out.println(database.getAllFutureGames().size());//expected : 1 game
        System.out.println(database.getListOfAllSpecificUsers("Admin").size());//expected : 2
        System.out.println(database.getListOfAllSpecificUsers("Player").size());//expected : 48
        System.out.println(database.getListOfAllSpecificUsers("Referee").size());//expected : 19

    }

}

