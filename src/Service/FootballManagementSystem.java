package Service;

import Domain.Authorization.AdminAuthorization;
import Domain.Authorization.HasPageAuthorization;
import Domain.Authorization.RefereeAuthorization;
import Domain.Authorization.UserAuthorization;
import Data.Database;
import Domain.*;
import Presentation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class FootballManagementSystem {
    private static Database database;
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
    private static List<User> systemAdmins;
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

    public static List<User> getSystemAdmins() {
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
        //***service***//
        notificationSystem = new NotificationSystem();
        adminSystem = new AdminSystem(notificationSystem);
        financeTransactionsSystem = new FinanceTransactionsSystem(notificationSystem);
        guestSystem = new GuestSystem();
        personalPageSystem = new PersonalPageSystem();
        refereeSystem = new RefereeSystem();
        unionRepresentativeSystem = new UnionRepresentativeSystem();
        userSystem = new UserSystem();
        teamManagementSystem = new TeamManagementSystem(notificationSystem);
        //***presentation***//
        systemAdmins = new LinkedList<>();
        if(firsTime){
            User systemAdmin = UserFactory.getNewAdmin("Aa1234","adminush","","example@gmail.com");
            systemAdmins.add(systemAdmin);
        }
        else{
            systemAdmins.addAll(database.getSystemAdmins());
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

    public static LeagueInSeason dataReboot(){
        User unionRep = UserFactory.getNewUnionRepresentative("", "","mail@mail.com");

        unionRepresentativeSystem.configureNewSeason(unionRep,2020, new Date(120, 4, 1));
        unionRepresentativeSystem.configureNewLeague(unionRep,"Haal", "3");
        LeagueInSeason leagueInSeason = unionRepresentativeSystem.configureLeagueInSeason(unionRep,"Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        Team team;
        for (int i = 0; i < 14; i++) {
            List<Player> players = createPlayers();
            List<Coach> coaches = createCoaches();
            List<User> owners = new LinkedList<>();
            User owner = adminSystem.addNewTeamOwner(systemAdmins.get(0),"Team","Owner","to"+i+"@gmail.com" );
            if(owner!=null){
                owners.add(owner);
                PersonalPage page = new PersonalPage("", owner);
                owner.addAuthorization(new HasPageAuthorization(page, owner));
                Field field = new Field( "jerusalem", 550, 150000);
                team = new Team("team"+i,page,owners,players,coaches, field);
                unionRepresentativeSystem.addTeamToLeague(unionRep,leagueInSeason, team);
            }

        }
        for (int i = 0; i <10 ; i++) {
            Referee ref = mainReferee(unionRep);
            unionRepresentativeSystem.assignRefToLeague(unionRep,leagueInSeason, ref);
        }
        return leagueInSeason;
    }


    public static Referee mainReferee(User unionRep) {
        Object[] obj =  unionRepresentativeSystem.appointReferee(unionRep,"referee", "",+IdGenerator.getNewId()+"@gmail.com", "talented");
        if(obj!=null)
            return (Referee)obj[1];
        return null;
    }
    public static List<Coach> createCoaches() {
        Object[] objects;
        objects = adminSystem.addNewCoach(systemAdmins.get(0),"coach1", "coach",+IdGenerator.getNewId()+"@gmail.com","firstOne", "main", 1500);
        List<Coach> coaches = new LinkedList<>();
        coaches.add((Coach) objects[1]);
        return coaches;
    }
    public static List<Player> createPlayers() {
        List<Player> players = new LinkedList<>();
        Object[] objects;
        for (int i = 0; i <12 ; i++) {
            objects = adminSystem.addNewPlayer(systemAdmins.get(0), "player"+i, "...", "mail"+IdGenerator.getNewId()+"@gmail.com", new Date(99, 1, 1),"role"+i, 3500);
            if(objects!=null){
                players.add((Player) objects[1]);
            }
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
        for (int i = 1; i < 30; i++) {
            dates.add(new Date (120, 5, i, 20, 0));
        }
        for (int i = 1; i < 31; i++) {
            dates.add(new Date (120, 6, i, 20, 0));
        }
        for (int i = 1; i <31 ; i++) {
            dates.add(new Date (120, 7, i, 20, 0));
        }
        for (int i = 1; i <30 ; i++) {
            dates.add(new Date (120, 8, i, 20, 0));
        }
        for (int i = 1; i <31 ; i++) {
            dates.add(new Date (120, 9, i, 20, 0));
        }
        for (int i = 1; i <30 ; i++) {
            dates.add(new Date (120, 10, i, 20, 0));
        }
        for (int i = 1; i <31 ; i++) {
            dates.add(new Date (120, 11, i, 20, 0));
        }
        return dates;
    }

}

