package Tests.Acceptance;

import Data.Database;
import Domain.*;
import Domain.Coach;
import Domain.Player;
import Domain.TeamOwner;
import Service.FootballManagementSystem;
import Service.TeamManagementSystem;
import Service.UnionRepresentativeSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import static org.junit.Assert.*;

public class TeamManagementTest {

    private UnionRepresentativeSystem representativeSystem;
    private FootballManagementSystem system;
    private LeagueInSeason leagueInSeason;
    private List<Player> players;
    private List<Coach> coaches;
    private TeamOwner owner;
    private Database database;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        representativeSystem = system.getUnionRepresentativeSystem();

        representativeSystem.configureNewSeason(2020, new Date(120,4,1 ));
        representativeSystem.configureNewLeague("Haal", "3");
        leagueInSeason = representativeSystem.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        players = FootballManagementSystem.createPlayers();
        coaches = FootballManagementSystem.createCoaches();
        owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");

        database = system.getDatabase();
    }

    @Test
    public void manageAssets_26()
    {

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
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


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
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


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        UserSystem userSystem = system.getUserSystem();
        Player player = players.get(0);

        userSystem.updateRole(player, "Goalkeeper");
        String role = userSystem.getRole(player);

        assertEquals(role, "Goalkeeper");
    }

    @Test
    public void manageAssets_31()
    {

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("team",page,owners,players,coaches, field);

        Field fieldAdded = new Field( "tel-aviv", 700, 150000);

        database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        teamSystem.addAsset(fieldAdded, team);
        List<Field> assets = teamSystem.getTeamFields(team);


        boolean flag = false;

        for (Field f : assets)
            if (f == fieldAdded)
                flag = true;

        assertTrue(flag);

    }

    @Test
    public void appointManager_32()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player manager = teamSystem.getTeamPlayers(team).get(1);
        team.setActive(true);
        teamSystem.appointmentTeamManager(owner, manager, team);

        List<User> managers = teamSystem.getTeamManagers(team);

        boolean flag = false;

        assertTrue(flag);



    }

    @Test
    public void appointManager_33()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.appointmentTeamOwner(owner, players.get(0), team);

        // cant be implemented due to permissions problem
        assertTrue(false);

    }

    @Test
    public void closeTeam_34()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
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


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        boolean flag = teamSystem.closeTeam(owner, team);
        assertFalse(flag);

    }

    @Test
    public void openTeam_37()
    {



        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
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

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        boolean active = teamSystem.isActiveTeam(team);
        assertFalse(active);

        teamSystem.reopeningTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertTrue(active);
    }


}
