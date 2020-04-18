package Tests.Integration;

import Domain.*;
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

    }

    @Test
    public void integration_AdminSystem()
    {

        system.getDatabase().GetSystemAdmins().get(0).addUser("Ab1234", new Referee("First", "Last", "a@b.com", "Training"));
        User user = system.getDatabase().getUserByMail("a@b.com");
        assertNotNull(user);

        representativeSystem.configureNewSeason(2020,new Date(121, 4, 1));
        representativeSystem.configureNewLeague("Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 15000000);
        Team team = new Team("team",page,owners,players,coaches, field);
        team.setActive(true);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        boolean flag = adminSystem.permanentlyCloseTeam(system.getDatabase().GetSystemAdmins().get(0) ,team);
        assertTrue(flag);

    }

    @Test
    public void integration_FinanceSystem()
    {

        FinanceTransactionsSystem finSystem = system.getFinanceTransactionsSystem();

        representativeSystem.configureNewSeason(2020,new Date(121, 4, 1));
        representativeSystem.configureNewLeague("Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        representativeSystem.addTeamToLeague(leagueInSeason, team);
        boolean failExpense = finSystem.reportNewExpanse(owner, team, 100);
        assertFalse(failExpense);

        double amount = finSystem.getBalance(team);
        finSystem.reportNewIncome(owner, team, 100);
        finSystem.reportNewExpanse(owner, team, 100);
        double afterChange = finSystem.getBalance(team);

        assertEquals(amount, afterChange, 0);

    }

    @Test
    public void integration_GuestSystem()
    {

        boolean registered = guestSystem.registrationToSystem("a@b.com", "ABb123", "First", "Last", "0123456789", "Israel");
        assertTrue(registered);

        User user = guestSystem.logIn("a@b.com", "ABb123");
        assertNotNull(user);

        Guest g = userSystem.logOut();
        assertNotNull(g);

        List<Object> searchResults = guestSystem.search(g, "TestNotFoundWord");
        assertNotNull(searchResults);
        assertEquals(0, searchResults.size());

    }

    @Test
    public void integration_TeamManagementSystem()
    {

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        representativeSystem.configureNewSeason(2020,new Date(120, 4, 1));
        representativeSystem.configureNewLeague("Haal", "3");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        List<Player> teamPlayers = teamSystem.getTeamPlayers(team);
        assertEquals(players.size(), teamPlayers.size());

        List<Field> fields = teamSystem.getTeamFields(team);
        assertEquals(1, fields.size());

        List<Coach> teamCoaches = teamSystem.getTeamCoaches(team);
        assertEquals(1, teamCoaches.size());

        List<User> teamManagers = teamSystem.getTeamManagers(team);
        assertEquals(0, teamManagers.size());

        int numOfPlayers = teamSystem.getTeamPlayers(team).size();
        teamSystem.removeAsset(teamPlayers.get(0), team);
        int afterChange = teamSystem.getTeamPlayers(team).size();
        assertEquals(numOfPlayers-1, afterChange);

        User user = teamPlayers.get(0);
        teamSystem.appointmentTeamManager(owner, user, team);
        teamManagers = teamSystem.getTeamManagers(team);
        assertEquals(1, teamManagers.size());

        boolean isActive = teamSystem.isActiveTeam(team);
        assertTrue(isActive);

        teamSystem.closeTeam(owner, team);
        isActive = teamSystem.isActiveTeam(team);
        assertFalse(isActive);
    }

    @Test
    public void integration_UnionRepresentativeSystem()
    {

        representativeSystem = system.getUnionRepresentativeSystem();
        boolean success;

        success = representativeSystem.configureNewSeason(2021,new Date(121, 4, 1));
        assertTrue(success);

        success = representativeSystem.configureNewLeague("Alufot", "3");
        assertTrue(success);

        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertNotNull(leagueInSeason);

        Referee ref = representativeSystem.appointReferee("Ref", "Test", "abc@d.com", "3");
        assertNotNull(ref);

        success = representativeSystem.assignRefToLeague(leagueInSeason, ref);
        assertTrue(success);

        success = representativeSystem.changeAssignmentPolicy(leagueInSeason, new PlayOnceWithEachTeamPolicy());
        assertTrue(success);

        success = representativeSystem.changeScorePolicy(leagueInSeason, new StandardScorePolicy());
        assertTrue(success);

        representativeSystem.changeRegistrationFee(leagueInSeason, 75.5);
        double fee = representativeSystem.getRegistrationFee(leagueInSeason);
        assertEquals(fee, 75.5, 0);


        Referee ref2 = representativeSystem.appointReferee("Ref2", "Test2", "abc2@d.com", "3");
        Referee ref3 = representativeSystem.appointReferee("Ref3", "Test3", "abc3@d.com", "3");
        representativeSystem.assignRefToLeague(leagueInSeason, ref2);
        representativeSystem.assignRefToLeague(leagueInSeason, ref3);

        success = representativeSystem.assignGames(leagueInSeason, FootballManagementSystem.getDates());
        assertTrue(success);

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        boolean found = false;
        List<Team> teams = leagueInSeason.getTeams();
        if (teams.contains(team))
            found = true;

        assertTrue(found);

    }

    @Test
    public void integration_UserSystem()
    {

        List<Player> players = FootballManagementSystem.createPlayers();

        guestSystem.registrationToSystem("a@b.com", "123456", "first", "last", "123456789", "Israel");
        User user = system.getDatabase().getUserByMail("a@b.com");
        Fan fan = new Fan("ab@c.com", "fan1", "fan12", "0123456789", "Israel");
        Player player = players.get(0);
        PersonalPage page = new PersonalPage("Personal Page", player);

        List<PersonalPage> pages = userSystem.getFanPages(fan);

        userSystem.registrationToFollowUp(fan, page);
        int pagesNum = userSystem.getFanPages(fan).size();
        assertEquals(1, pagesNum);

        Guest g = userSystem.logOut();
        assertNotNull(g);

        String keyword = "test check";
        FootballManagementSystem.getSearcher().searchInfo(fan, keyword);
        List<String> history = userSystem.viewSearchHistory(fan);
        assertEquals(1, history.size());


    }
}
