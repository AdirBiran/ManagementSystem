package Presentation;

import Data.Database;
import Domain.*;
import Service.*;


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
        FinanceTransactionsSystem financeTransactionsSystem = new FinanceTransactionsSystem(financeTransactionsManagement);
        GuestSystem guestSystem = new GuestSystem(searcher, userManagement);
        NotificationSystem notificationSystem = new NotificationSystem(leagueAndGameManagement, refereeManagement, assetManagement);
        PersonalPageSystem personalPageSystem = new PersonalPageSystem(personalPageManagement);
        RefereeSystem refereeSystem = new RefereeSystem(leagueAndGameManagement, refereeManagement, eventReportManagement);
        SearchSystem searchSystem = new SearchSystem(searcher);
        UnionRepresentativeSystem unionRepresentativeSystem = new UnionRepresentativeSystem(financeTransactionsManagement, leagueAndGameManagement, refereeManagement);
        UserSystem userSystem = new UserSystem(searcher, complaintManager, editPersonalInfo, personalPageManagement, userManagement);
        //***presentation***//
        int id = IdGenerator.getNewId();
        Admin systemAdmin = new Admin("adminush","", "ua" + id, "example@gmail.com");
        userSystem.addUser("ua" + id, "Adminush1", systemAdmin);

    }
}
