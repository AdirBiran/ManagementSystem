package Tests.Acceptance;

import Domain.*;
import Presentation.*;
import Service.FootballManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import Service.FootballManagementSystem;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SeasonInitiationTest {

    private FootballManagementSystem system;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
    }

    @Test
    public void defineLeagueTest_55()
    {

        system.dataReboot();

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);


    }

    @Test
    public void defineLeagueTest_56()
    {

        system.dataReboot();

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", null, null, 250);

    }

    @Test
    public void defineLeagueTest_57()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(1800);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "1800", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

    }

    @Test
    public void defineLeagueTest_58()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team1","Owner", "a"+"@gmail.com");
        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        Team team = new Team("Team1",page,owners,players,coaches, field);
        FootballManagementSystem.database.addTeam(team);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        TeamOwner owner2 = new TeamOwner("Team2","Owner", "b"+"@gmail.com");
        List<TeamOwner> owners2 = new LinkedList<>();
        owners.add(owner2);
        PersonalPage page2 = new PersonalPage("", players.get(0));
        Field field2 = new Field( "tel-aviv", 600);
        Team team2 = new Team("Team2",page2,owners2,players,coaches, field2);
        FootballManagementSystem.database.addTeam(team2);
        representativeSystem.addTeamToLeague(leagueInSeason, team2);

    }

    @Test
    public void setReferees_65()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee();
        system.getAdminSystem().addUser("Aa123", ref);
        representativeSystem.assignRefToLeague(leagueInSeason, ref);

    }

    @Test
    public void setReferees_66()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee();
        Referee ref2 = FootballManagementSystem.mainReferee();
        system.getAdminSystem().addUser("Aa123", ref);
        system.getAdminSystem().addUser("Bb123", ref2);

        representativeSystem.assignRefToLeague(leagueInSeason, ref);
        representativeSystem.assignRefToLeague(leagueInSeason, ref2);


    }
}
