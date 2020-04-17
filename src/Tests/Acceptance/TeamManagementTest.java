package Tests.Acceptance;

import Domain.*;
import Presentation.Coach;
import Presentation.Player;
import Presentation.TeamOwner;
import Service.FootballManagementSystem;
import Service.TeamManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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
        teamSystem.addAsset(players.get(0), team);


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
        Player player = players.get(0);

        teamSystem.removeAsset(player, team);
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

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.addAsset(players.get(0), team);
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
        teamSystem.appointmentTeamOwner(owner, players.get(0), team);
        teamSystem.appointmentTeamManager(owner, players.get(1), team);

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
        teamSystem.closeTeam(owner, team);


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
        teamSystem.reopeningTeam(owner, team);
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
        teamSystem.reopeningTeam(owner, team);
    }


}
