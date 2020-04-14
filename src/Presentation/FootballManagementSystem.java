package Presentation;

import Data.Database;
import Domain.*;
import Service.*;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class FootballManagementSystem {

    public static void main(String[] args) {
        //***data***//
        Database database = new Database();
        //***domain***//
        AssetManagement assetManagement = new AssetManagement(database);
        ComplaintManager complaintManager = new ComplaintManager(database);
        EditPersonalInfo editPersonalInfo = new EditPersonalInfo(database);
        EventReportManagement eventReportManagement = new EventReportManagement(database);
        FinanceTransactionsManagement financeTransactionsManagement = new FinanceTransactionsManagement(database);
        LeagueAndGameManagement leagueAndGameManagement = new LeagueAndGameManagement(database);
        PersonalPageManagement personalPageManagement = new PersonalPageManagement(database);
        RefereeManagement refereeManagement = new RefereeManagement(database);
        Searcher searcher = new Searcher(database);
        UserManagement userManagement = new UserManagement(database);
        //***service***//
        AssetSystem assetSystem = new AssetSystem(assetManagement);
        NotificationSystem notificationSystem = new NotificationSystem(leagueAndGameManagement, refereeManagement, assetManagement);
        FinanceTransactionsSystem financeTransactionsSystem = new FinanceTransactionsSystem(financeTransactionsManagement, notificationSystem);
        GuestSystem guestSystem = new GuestSystem(searcher, userManagement);
        PersonalPageSystem personalPageSystem = new PersonalPageSystem(personalPageManagement);
        RefereeSystem refereeSystem = new RefereeSystem(leagueAndGameManagement, refereeManagement, eventReportManagement);
        SearchSystem searchSystem = new SearchSystem(searcher);
        UnionRepresentativeSystem unionRepresentativeSystem = new UnionRepresentativeSystem(financeTransactionsManagement, leagueAndGameManagement, refereeManagement);
        UserSystem userSystem = new UserSystem(searcher, complaintManager, editPersonalInfo, personalPageManagement, userManagement);
        //***presentation***//
        int id = IdGenerator.getNewId();
        Admin systemAdmin = new Admin("adminush","", "example@gmail.com");
        userSystem.addUser(systemAdmin.getID(), "Adminush1", systemAdmin);



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
        teamOwner.appointmentTeamManager(teamManager ,team);

        userSystem.appointmentTeamManager(teamOwner,teamManager,team);
        userSystem.appointmentTeamOwner(teamOwner,teamOwner2,team);
        userSystem.appointmentTeamOwner(teamOwner2,teamOwner3,team);

        /**TEAM OWNER TRY TO REMOVE APPOINTMENT
         * THOSE APPOINTMENTS IS NOT HIS APPOINTMENTS
         * */
        userSystem.removeAppointmentTeamOwner(teamOwner2,teamOwner,team);
        userSystem.removeAppointmentTeamManager(teamOwner2,teamManager,team);

        /**TEAM OWNER TRY TO REMOVE APPOINTMENT
         * THOSE APPOINTMENTS IS HIS APPOINTMENTS
         * */
        userSystem.removeAppointmentTeamOwner(teamOwner , teamOwner2,team);


        /**-----------DORON TESTS--------------*/


        //**UnitTests!-NotificationSystem**//
        TeamOwner owner = new TeamOwner("Team","Owner", "a@gmail.com");
        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        Field field = new Field( "jerusalem", 550);
        Team hapoel = new Team("Hapoel", null, owners, database.createPlayers(), database.createCoaches(),field);

        database.addAsset(field);
        database.addTeam(hapoel);
        PersonalPage hapoelsPage = new PersonalPage("",hapoel.getPlayers().get(0));
        database.addPage(hapoelsPage);//expected : true
        Field field2 = new Field( "TelAviv", 550);
        Team macabi = new Team ("Macabi", null,owners, database.createPlayers(),database.createCoaches(),field2);
        database.addTeam(macabi);
        database.addAsset(field2);
        Game game = new Game("G"+IdGenerator.getNewId(),new Date(120,4,25, 20,0,0), field, database.mainReferee(), database.sideReferees(),hapoel, macabi);
        Game game2 = new Game("G"+IdGenerator.getNewId(),new Date(120,4,25, 20,0,0), field, database.mainReferee(), database.sideReferees(),hapoel, macabi);
        database.addGame(game);
        database.addGame(game2);

        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        ReceiveAlerts receiveAlerts = new ReceiveAlerts(true, true);
        List<Game> games = new LinkedList<>();
        games.add(game);
        games.add(game2);
        System.out.println(notificationSystem.registrationForGameAlerts(fan, games, receiveAlerts)); //expected : true
        System.out.println(notificationSystem.openORCloseTeam("closed", hapoel, false)); //expected : false
        System.out.println(notificationSystem.openORCloseTeam("open", hapoel, false)); //expected : true
        hapoel.setActive(false);
        System.out.println(notificationSystem.openORCloseTeam("closed", hapoel, true)); //expected : true
        notificationSystem.refereeAlertsChangeDate(game, game.getDate());
        //notificationSystem.refereeAlertsChangeTime(game, game.getTime());
        notificationSystem.refereeAlertsChangeGameLocation(game, game.getField());
        System.out.println(notificationSystem.UserRemovalNotification(fan));//expected : false
        fan.deactivate();
        System.out.println(notificationSystem.UserRemovalNotification(fan));//expected : true
        System.out.println();
        System.out.println(financeTransactionsSystem.reportNewIncome(macabi.getTeamOwners().get(0), macabi, 200));//expected : true
        System.out.println(financeTransactionsSystem.reportNewExpanse(macabi.getTeamOwners().get(0), macabi, 300));//expected : false
    }
}
