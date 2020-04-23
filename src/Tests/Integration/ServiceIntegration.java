package Integration;

import Data.Database;
import Domain.*;
import Domain.Authorization.HasPageAuthorization;
import Presentation.Guest;
import Service.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceIntegration {

    private FootballManagementSystem system;
    private AdminSystem adminSystem;
    private UnionRepresentativeSystem representativeSystem;
    private GuestSystem guestSystem;
    private UserSystem userSystem;
    private NotificationSystem notSystem;
    private User admin, unionRep;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        adminSystem = system.getAdminSystem();
        system.dataReboot();
        guestSystem = system.getGuestSystem();
        userSystem = system.getUserSystem();
        representativeSystem = system.getUnionRepresentativeSystem();
        notSystem = system.getNotificationSystem();
        admin = Database.getSystemAdmins().get(0);
        assertNotNull(admin);
        unionRep = adminSystem.addNewUnionRepresentative(admin, "", "", "mmawwwwa@gmail.com");
        assertNotNull(unionRep);
    }

    @Test
    public void integration_AdminSystem()
    {

        Object[] user = adminSystem.addNewCoach(admin,"First", "Last", "a@b.com", "Training", "", 50);
        assertNotNull(user);

        representativeSystem.configureNewSeason(unionRep,2020,new Date(121, 4, 1));
        representativeSystem.configureNewLeague(unionRep,"Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(unionRep,"Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        User owner = adminSystem.addNewTeamOwner(admin,"Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 15000000);
        Team team = new Team("team",page,owners,players,coaches, field);
        team.setActive(true);
        representativeSystem.addTeamToLeague(unionRep, leagueInSeason, team);

        boolean flag = adminSystem.permanentlyCloseTeam(admin ,team);
        assertTrue(flag);

    }

    @Test
    public void integration_FinanceSystem()
    {

        FinanceTransactionsSystem finSystem = system.getFinanceTransactionsSystem();

        representativeSystem.configureNewSeason(unionRep,2020,new Date(121, 4, 1));
        representativeSystem.configureNewLeague(unionRep,"Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(unionRep,"Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        User owner = adminSystem.addNewTeamOwner(admin, "Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        representativeSystem.addTeamToLeague(unionRep, leagueInSeason, team);
        boolean failExpense = finSystem.reportNewExpanse(owner, team, 100);
        assertFalse(failExpense);

        double amount = finSystem.getBalance(owner, team);
        finSystem.reportNewIncome(owner, team, 100);
        finSystem.reportNewExpanse(owner, team, 100);
        double afterChange = finSystem.getBalance(owner, team);

        assertEquals(amount, afterChange, 0);

    }

    @Test
    public void integration_GuestSystem()
    {

        boolean registered = guestSystem.registrationToSystem("a@b.com", "ABb123", "First", "Last", "0123456789", "Israel")!=null;
        assertTrue(registered);

        User user = guestSystem.logIn("a@b.com", "ABb123");
        assertNotNull(user);

        //userSystem.logOut();//how to implement log out? how to test it?
        //assertNotNull();

        List<Object> searchResults = guestSystem.search( "TestNotFoundWord");
        assertNotNull(searchResults);
        assertEquals(0, searchResults.size());

    }

    @Test
    public void integration_TeamManagementSystem()
    {

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        representativeSystem.configureNewSeason(unionRep,2020,new Date(120, 4, 1));
        representativeSystem.configureNewLeague(unionRep,"Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(unionRep,"Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        User owner = adminSystem.addNewTeamOwner(admin,"Team","Owner", "a"+"@gmail.com");

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        List<Player> teamPlayers = teamSystem.getTeamPlayers(owner,team);
        assertEquals(players.size(), teamPlayers.size());

        List<Field> fields = teamSystem.getTeamFields(owner,team);
        assertEquals(1, fields.size());

        List<Coach> teamCoaches = teamSystem.getTeamCoaches(owner,team);
        assertEquals(1, teamCoaches.size());

        List<User> teamManagers = teamSystem.getTeamManagers(owner,team);
        assertEquals(0, teamManagers.size());

        int numOfPlayers = teamSystem.getTeamPlayers(owner,team).size();
        teamSystem.removeAsset(owner,teamPlayers.get(0), team);
        int afterChange = teamSystem.getTeamPlayers(owner,team).size();
        assertEquals(numOfPlayers-1, afterChange);

        User user = teamPlayers.get(0).getUser();
        teamSystem.appointmentTeamManager(owner, user, team);
        teamManagers = teamSystem.getTeamManagers(owner,team);
        assertEquals(1, teamManagers.size());

        boolean isActive = teamSystem.isActiveTeam(team);
        assertTrue(isActive);

        teamSystem.closeTeam(owner, team);
        isActive = teamSystem.isActiveTeam(team);
        assertFalse(isActive);
    }

    @Test
    public void integration_UnionRepresentativeSystem()//we need 14 teams to assign games
    {

        representativeSystem = system.getUnionRepresentativeSystem();
        boolean success;

        success = representativeSystem.configureNewSeason(unionRep,2021,new Date(121, 4, 1));
        assertTrue(success);

        success = representativeSystem.configureNewLeague(unionRep,"Alufot", "3");
        assertTrue(success);

        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(unionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertNotNull(leagueInSeason);

        Object[] ref = representativeSystem.appointReferee(unionRep,"Ref", "Test", "abc@d.com", "3");
        assertNotNull(ref);

        success = representativeSystem.assignRefToLeague(unionRep,leagueInSeason, (Referee) ref[1]);
        assertTrue(success);

        success = representativeSystem.changeAssignmentPolicy(unionRep,leagueInSeason, new PlayOnceWithEachTeamPolicy());
        assertTrue(success);

        success = representativeSystem.changeScorePolicy(unionRep,leagueInSeason, new CupScorePolicy());
        assertTrue(success);

        representativeSystem.changeRegistrationFee(unionRep,leagueInSeason, 75.5);
        double fee = representativeSystem.getRegistrationFee(unionRep,leagueInSeason);
        assertEquals(fee, 75.5, 0);


        Object[] ref2 = representativeSystem.appointReferee(unionRep,"Ref2", "Test2", "abc2@d.com", "3");
        Object[] ref3 = representativeSystem.appointReferee(unionRep,"Ref3", "Test3", "abc3@d.com", "3");
        assertNotNull(ref2);
        assertNotNull(ref3);
        representativeSystem.assignRefToLeague(unionRep,leagueInSeason, (Referee) ref2[1]);
        representativeSystem.assignRefToLeague(unionRep,leagueInSeason, (Referee)ref3[1]);

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        List<Player> players2 = FootballManagementSystem.createPlayers();
        List<Coach> coaches2 = FootballManagementSystem.createCoaches();
        User owner = adminSystem.addNewTeamOwner(admin,"Team1","Owner", "a"+"@gmail.com");
        User owner2 = adminSystem.addNewTeamOwner(admin,"Team2","Owner2", "a2"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        List<User> owners2 = new LinkedList<>();
        owners.add(owner);
        owners2.add(owner2);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 15000);
        Field field2 = new Field( "tel-aviv", 1000, 20000);

        Team team = new Team("Team1",page,owners,players,coaches, field);
        Team team2 = new Team("Team1",page,owners2,players2,coaches2, field2);

        representativeSystem.addTeamToLeague(unionRep,leagueInSeason, team);
        representativeSystem.addTeamToLeague(unionRep,leagueInSeason, team2);

        boolean found = false;
        List<Team> teams = leagueInSeason.getTeams();
        if (teams.contains(team))
            found = true;

        assertTrue(found);

        success = representativeSystem.assignGames(unionRep,leagueInSeason, FootballManagementSystem.getDates());
        assertTrue(success);

    }

    @Test
    public void integration_UserSystem()
    {

        List<Player> players = FootballManagementSystem.createPlayers();

        User user = guestSystem.registrationToSystem("a@b.com", "123456", "first", "last", "123456789", "Israel");
        assertNotNull(user);
        PersonalPage page = ((HasPageAuthorization)players.get(0).getUser().getRoles().get(0)).getPage();
        assertNotNull(page);

        List<PersonalPage> pages = userSystem.getFanPages(user);

        userSystem.registrationToFollowUp(user, page);
        int pagesNum = userSystem.getFanPages(user).size();
        assertEquals(1, pagesNum);

        //Guest g = userSystem.logOut();
        //assertNotNull(g);

        String keyword = "test check";
        userSystem.search(user, keyword);
        List<String> history = userSystem.viewSearchHistory(user);
        assertEquals(1, history.size());


    }
/*
    @Test
    public void integration_NotificationSystem()
    {
        representativeSystem.configureNewSeason(2020, new Date(120,4,1 ));
        representativeSystem.configureNewLeague("Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();

        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        Database database = system.getDatabase();
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        team.setActive(false);
        representativeSystem.addTeamToLeague(leagueInSeason, team);


        boolean success = notSystem.openORCloseTeam("closed", team, false);
        assertTrue(success);

        team.setActive(true);

        success = notSystem.openORCloseTeam("open", team, false);
        assertTrue(success);


    }*/
}
