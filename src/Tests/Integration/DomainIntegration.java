package Integration;

import Data.Database;
import Domain.*;
import Service.*;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class DomainIntegration {
/*
    private FootballManagementSystem system;
    private AssetManagement assetManagement;
    private ComplaintManager complaintManager;
    private EditPersonalInfo editInfo;
    private FinanceTransactionsManagement financeManager;
    private LeagueAndGameManagement leagueManager;
    private RefereeManagement refereeManager;
    private UserManagement userManager;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        Database database = system.getDatabase();
        assetManagement = new AssetManagement(database);
        complaintManager = new ComplaintManager(database);
        editInfo = new EditPersonalInfo(database);
        financeManager = new FinanceTransactionsManagement(database);
        leagueManager = new LeagueAndGameManagement(database);
        refereeManager = new RefereeManagement(database);
        userManager = new UserManagement(database);

    }

    @Test
    public void AssetManagement_Test()
    {

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        Database database = system.getDatabase();

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        Field field2 = new Field("tel-aviv", 300, 1000);

        assetManagement.addAsset(field2, team);
        TeamManagementSystem sys = system.getTeamManagementSystem();
        team.setActive(true);

        assertEquals(2, sys.getTeamFields(team).size());

        assetManagement.updateAsset(field2.getID(), "Price", "15000");
        assertEquals(15000, assetManagement.getAssetPrice(field2), 0);

        assetManagement.removeAsset(field2, team);
        assertEquals(1, sys.getTeamFields(team).size());

    }

    @Test
    public void ComplaintManager_Test()
    {
        Fan fan = new Fan("a@b.com", "first", "last", "012345789", "israel");
        complaintManager.addComplaintToSystem(fan, "Complaint");

        HashSet<Complaint> comps = system.getDatabase().getComplaints();
        boolean found = false;

        for (Complaint c: comps)
            if (c.getDescription().equals("Complaint"))
                found = true;

        assertTrue(found);
    }

    @Test
    public void EditPersonalInfo_Test()
    {
        Fan fan = new Fan("a@b.com", "first", "last", "012345789", "israel");
        system.getDatabase().addUser("00000", fan);

        editInfo.editPersonalDetails(fan, "changed1", "changed1", "9876543210","israel", "12345");
        boolean found1 = system.getDatabase().authenticationCheck("a@b.com", "12345");
        assertTrue(found1);

        Player p = FootballManagementSystem.createPlayers().get(0);
        system.getDatabase().addUser("11111", p);
        String mail = p.getMail();

        editInfo.editPersonalDetails(p, "changed2", "changed2", "00000");
        boolean found2 = system.getDatabase().authenticationCheck(mail, "00000");
        assertTrue(found2);

    }

    @Test
    public void FinanceTransactionsManagement_Test()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);

        double firstBalance = financeManager.getBalance(team);
        financeManager.reportNewIncome(team.getBudget(), 100);

        assertEquals(firstBalance+100, team.getBudget().getBalance(), 0);

        financeManager.reportNewExpanse(team.getBudget(), 50);
        assertEquals(firstBalance+50, team.getBudget().getBalance(), 0);

        double balance = financeManager.getBalance(team);
        assertEquals(firstBalance+50, balance, 0);


    }

    @Test
    public void LeagueAndGameManagement_Test()
    {

        leagueManager.configureNewLeague("TestLeague", "5");
        Date date = new Date(2021, 5, 5);
        leagueManager.configureNewSeason(2021, date);
        Database database = system.getDatabase();
        League l1 = database.getLeague("TestLeagueFAILED");
        League l2 = database.getLeague("TestLeague");

        assertNull(l1);
        assertNotNull(l2);

        Season s1 = database.getSeason("2021");
        Season s2 = database.getSeason("3000");

        assertNotNull(s1);
        assertNull(s2);

        LeagueInSeason lis1 = leagueManager.configureLeagueInSeason("TestLeague", "5000", new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), 50);
        LeagueInSeason lis2 = leagueManager.configureLeagueInSeason("TestLeague", "2021", new PlayOnceWithEachTeamPolicy(), null, 50);
        LeagueInSeason lis3 = leagueManager.configureLeagueInSeason("TestLeague", "2021", null, new StandardScorePolicy(), 50);
        LeagueInSeason lis4 = leagueManager.configureLeagueInSeason("TestLeague", "2021", new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), 50);

        assertNull(lis1);
        assertNull(lis2);
        assertNull(lis3);
        assertNotNull(lis4);

        system.dataReboot();

        LeagueInSeason lis5 = leagueManager.configureLeagueInSeason("Haal", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for(Object r : database.getListOfAllSpecificUsers("Referee"))
            lis5.addReferee((Referee) r);

        UnionRepresentativeSystem repSystem = system.getUnionRepresentativeSystem();

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        List<Player> players2 = FootballManagementSystem.createPlayers();
        List<Coach> coaches2 = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team1","Owner", "a"+"@gmail.com");
        TeamOwner owner2 = new TeamOwner("Team2","Owner2", "a2"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        List<User> owners2 = new LinkedList<>();
        owners.add(owner);
        owners2.add(owner2);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 15000);
        Field field2 = new Field( "tel-aviv", 1000, 20000);

        Team team = new Team("Team1",page,owners,players,coaches, field);
        Team team2 = new Team("Team1",page,owners2,players2,coaches2, field2);

        repSystem.addTeamToLeague(lis5, team);
        repSystem.addTeamToLeague(lis5, team2);


        assertTrue(leagueManager.assignGames(lis5, system.getDates()));

    }

    @Test
    public void PersonalPageManagement_Test()
    {
        // Not functional yet
    }

    @Test
    public void RefereeManagement_Test()
    {
        Referee ref = refereeManager.appointReferee("Ref1", "Last1", "ref1@gm.com", "Main");
        Referee ref2 = refereeManager.appointReferee("Ref2", "Last2", "ref1@gm.com", "Side");

        assertNotNull(ref);
        assertNull(ref2);
    }

    @Test
    public void UserManagement_Test()
    {

        Fan fan = new Fan("a@b.com", "first", "last", "012345789", "israel");
        system.getDatabase().addUser("00000", fan);
        User user = userManager.logInUserToSystem("a@b.com", "00000");
        assertNotNull(user);

        User user2 = userManager.logInUserToSystem("testFail@Gmail.com", "12345");
        assertNull(user2);

        boolean success = userManager.registrationToSystem("a@b.com", "11111", "first", "last", "012345789", "israel");
        assertFalse(success);

        success = userManager.registrationToSystem("abc@b.com", "11111", "first", "last", "012345789", "israel");
        assertTrue(success);

    }


*/
}
