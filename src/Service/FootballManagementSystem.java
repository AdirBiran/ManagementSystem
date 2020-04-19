package Service;

import Data.Database;
import Domain.*;
import Presentation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class FootballManagementSystem {
    private static Database database;
    //***domain***//
    private static AssetManagement assetManagement;
    private static ComplaintManager complaintManager;
    private static EditPersonalInfo editPersonalInfo;

    private static FinanceTransactionsManagement financeTransactionsManagement;
    private static LeagueAndGameManagement leagueAndGameManagement;
    private static PersonalPageManagement personalPageManagement;
    private static RefereeManagement refereeManagement;
    private static Searcher searcher;
    private static UserManagement userManagement;
    private static MailSender mailSender;
    //***service***//

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

    public Database getDatabase()
    {
        return database;
    }

    public TeamManagementSystem getTeamManagementSystem() {
        return teamManagementSystem;
    }

    public AdminSystem getAdminSystem() {
        return adminSystem;
    }

    public UserSystem getUserSystem() {
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




    public NotificationSystem getNotificationSystem() {
        return notificationSystem;
    }

    public FinanceTransactionsSystem getFinanceTransactionsSystem() {
        return financeTransactionsSystem;
    }

    public static AssetManagement getAssetManagement() {
        return assetManagement;
    }

    public static ComplaintManager getComplaintManager() {
        return complaintManager;
    }

    public static EditPersonalInfo getEditPersonalInfo() {
        return editPersonalInfo;
    }



    public static FinanceTransactionsManagement getFinanceTransactionsManagement() {
        return financeTransactionsManagement;
    }

    public static LeagueAndGameManagement getLeagueAndGameManagement() {
        return leagueAndGameManagement;
    }

    public static PersonalPageManagement getPersonalPageManagement() {
        return personalPageManagement;
    }

    public static RefereeManagement getRefereeManagement() {
        return refereeManagement;
    }

    public static Searcher getSearcher() {
        return searcher;
    }

    public static UserManagement getUserManagement() {
        return userManagement;
    }

    public static MailSender getMailSender() {
        return mailSender;
    }

    public static List<Admin> getSystemAdmins() {
        return systemAdmins;
    }

    public static StubAccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    public static StubIsraeliTaxLawsSystem getTaxLawsSystem() {
        return taxLawsSystem;
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
        financeTransactionsManagement = new FinanceTransactionsManagement(database);
        leagueAndGameManagement = new LeagueAndGameManagement(database);
        personalPageManagement = new PersonalPageManagement(database);
        refereeManagement = new RefereeManagement(database);
        searcher = new Searcher(database);
        userManagement = new UserManagement(database);
        mailSender = new MailSender();
        //***service***//
        notificationSystem = new NotificationSystem(refereeManagement, assetManagement);
        adminSystem = new AdminSystem(notificationSystem, complaintManager);
        financeTransactionsSystem = new FinanceTransactionsSystem(financeTransactionsManagement, notificationSystem);
        guestSystem = new GuestSystem(searcher, userManagement);
        personalPageSystem = new PersonalPageSystem(personalPageManagement);
        refereeSystem = new RefereeSystem();
        unionRepresentativeSystem = new UnionRepresentativeSystem(financeTransactionsManagement, leagueAndGameManagement, refereeManagement);
        userSystem = new UserSystem(searcher, complaintManager, editPersonalInfo, userManagement);
        teamManagementSystem = new TeamManagementSystem(notificationSystem, assetManagement);
        //***presentation***//
        systemAdmins = new LinkedList<>();
        if(firsTime){
            Admin systemAdmin = new Admin("adminush","", "example@gmail.com", database);
            systemAdmins.add(systemAdmin);
            systemAdmin.addUser("Adminush1", systemAdmin);
        }
        else{
            systemAdmins.addAll(database.GetSystemAdmins());
        }


        accountingSystem = new StubAccountingSystem();
        taxLawsSystem = new StubIsraeliTaxLawsSystem();

        return true;
    }

    public boolean connectToOuterSystems(boolean flag)
    {

        boolean con1 = accountingSystem.connect(flag);
        boolean con2 = taxLawsSystem.connect(flag);

        if (con1 && con2)
            return true;

        return false;

    }

    public void dataReboot(){
        unionRepresentativeSystem.configureNewSeason(2020, new Date(120, 4, 1));
        unionRepresentativeSystem.configureNewLeague("Haal", "3");
        LeagueInSeason leagueInSeason = unionRepresentativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for (int i = 0; i < 5; i++) {
            List<Player> players = createPlayers();
            List<Coach> coaches = createCoaches();
            TeamOwner owner = new TeamOwner("Team","Owner", "a"+i+"@gmail.com");
            List<User> owners = new LinkedList<>();
            owners.add(owner);
            PersonalPage page = new PersonalPage("", players.get(0));
            Field field = new Field( "jerusalem", 550, 150000);
            Team team = new Team("team"+i,page,owners,players,coaches, field);
            database.addTeam(team);
            unionRepresentativeSystem.addTeamToLeague(leagueInSeason, team);
        }
        for (int i = 0; i <10 ; i++) {
            Referee ref = mainReferee();
            systemAdmins.get(0).addUser("Aa123", ref);
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
        Coach coach = new Coach("coach1", "", "coach"+IdGenerator.getNewId()+"@gmail.com", null, "", "main", 1500);
        List<Coach> coaches = new LinkedList<>();
        coaches.add(coach);
        return coaches;
    }
    public static List<Player> createPlayers() {
        List<Player> players = new LinkedList<>();
        for (int i = 0; i <12 ; i++) {
            players.add(new Player("player"+i, "", "player"+IdGenerator.getNewId()+"@gmail.com", null, new Date (99,1,1),"role"+i, 1500));
        }
        return players;
    }
    private static void printList(List<Game> allGames) {
        for(Game game: allGames){
            System.out.println(game.toString());
        }
    }

    public static List<Date> getDates() {
        LinkedList<Date> dates = new LinkedList<>();
        for (int i = 1; i < 29; i++) {
            dates.add(new Date (120, 6, i, 20, 0));
        }
        return dates;
    }

}

