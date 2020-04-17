package Tests.Acceptance;

import Domain.*;
import Domain.Coach;
import Domain.Player;
import Domain.TeamOwner;
import Service.FootballManagementSystem;
import Service.TeamManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class TeamManagementTest {

    private UnionRepresentativeSystem representativeSystem;
    private FootballManagementSystem system;
    private LeagueInSeason leagueInSeason;
    private List<Player> players;
    private List<Coach> coaches;
    private TeamOwner owner;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        representativeSystem = system.getUnionRepresentativeSystem();

        representativeSystem.configureNewSeason(2020);
        representativeSystem.configureNewLeague("Haal", "3");
        leagueInSeason = representativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        players = FootballManagementSystem.createPlayers();
        coaches = FootballManagementSystem.createCoaches();
        owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
    }

    @Test
    public void manageAssets_26()
    {

        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);
        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player playerAdded = teamSystem.getTeamPlayers(team).get(0);

        teamSystem.addAsset(playerAdded, team);


        boolean existsOnce = false;
        boolean existsTwice = false;

        List<Player> assets = teamSystem.getTeamPlayers(team);

        for (Player p : assets)
            if (p == playerAdded)
                if (existsOnce)
                    existsTwice = true;
                else
                    existsOnce = true;

        assertFalse(existsTwice);


    }

    @Test
    public void manageAssets_27()
    {


        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player playerAdded = teamSystem.getTeamPlayers(team).get(0);
        List<Player> assets = teamSystem.getTeamPlayers(team);

        teamSystem.removeAsset(playerAdded, team);

        boolean flag = false;

        for (Player p: assets)
            if (p == playerAdded)
                flag = true;

        assertFalse(flag);
    }

    @Test
    public void manageAssets_28()
    {


        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player player = players.get(0);

        teamSystem.updateRole(player, "Goalkeeper");
        String role = teamSystem.getRole(player);

        assertEquals(player.getRole(), "Goalkeeper");
    }

    @Test
    public void manageAssets_29()
    {
        // cant be implemented
    }

    @Test
    public void manageAssets_30()
    {
        // cant be implemented
    }

    @Test
    public void manageAssets_31()
    {

        List<Player> emptyPlayers = new LinkedList<>();

        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,emptyPlayers,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);
        Player player = players.get(0);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        List<Player> assets = teamSystem.getTeamPlayers(team);

        teamSystem.addAsset(player, team);

        boolean flag = false;

        for (Player p : assets)
            if (p == player)
                flag = true;

        assertTrue(flag);

    }

    @Test
    public void appointManager_32()
    {


        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player manager = teamSystem.getTeamPlayers(team).get(1);
        teamSystem.appointmentTeamManager(owner, manager, team);

        List<TeamManager> managers = teamSystem.getTeamManagers(team);

        boolean flag = false;

        for (TeamManager mng : managers)
            if (mng.getID() == manager.getID())
                flag = true;

        assertTrue(flag);



    }

    @Test
    public void appointManager_33()
    {


        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.appointmentTeamOwner(owner, players.get(0), team);

        // cant be implemented due to permissions problem

    }

    @Test
    public void closeTeam_34()
    {


        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        boolean active = teamSystem.isActiveTeam(team);

        assertTrue(active);

        teamSystem.closeTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertFalse(active);

    }

    @Test
    public void closeTeam_35()
    {


        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        teamSystem.closeTeam(owner, team);

        // cant be implemented, what happens when 2 tries?

    }

    @Test
    public void openTeam_36()
    {



        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);
        teamSystem.reopeningTeam(owner, team);

        //cant be implemented, need at least 24 hours

    }

    @Test
    public void openTeam_37()
    {



        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        boolean active = teamSystem.isActiveTeam(team);
        assertTrue(active);

        teamSystem.reopeningTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertTrue(active);
    }

    @Test
    public void openTeam_38()
    {

        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("team",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        boolean active = teamSystem.isActiveTeam(team);
        assertFalse(active);

        teamSystem.reopeningTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertFalse(active);
    }


}
