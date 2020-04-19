package Acceptance;

import Domain.*;
import Service.FootballManagementSystem;
import Service.TeamManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

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

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021,new Date(121,4,1));
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee();
        Referee ref2 = FootballManagementSystem.mainReferee();
        Referee ref3 = FootballManagementSystem.mainReferee();

        representativeSystem.assignRefToLeague(leagueInSeason, ref);
        representativeSystem.assignRefToLeague(leagueInSeason, ref2);
        representativeSystem.assignRefToLeague(leagueInSeason, ref3);


        League league = system.getDatabase().getLeague("Alufot");
        assertNotNull(league);

        boolean success = representativeSystem.assignGames(leagueInSeason, FootballManagementSystem.getDates());
        assertFalse(success);
    }

    @Test
    public void defineLeagueTest_56()
    {

        system.dataReboot();

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021,new Date(121,4,1));
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", null, null, 250);

        assertNull(leagueInSeason);
    }

    @Test
    public void defineLeagueTest_57()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(1800, new Date(18,4,1));
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "1800", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        assertNull(leagueInSeason);
    }

    @Test
    public void defineLeagueTest_58()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021,new Date(121,4,1));
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team1","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("Team1",page,owners,players,coaches, field);
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        List<Player> players2 = FootballManagementSystem.createPlayers();
        List<Coach> coaches2 = FootballManagementSystem.createCoaches();
        TeamOwner owner2 = new TeamOwner("Team1","Owner", "a"+"@gmail.com");
        List<User> owners2 = new LinkedList<>();
        owners2.add(owner2);
        PersonalPage page2 = new PersonalPage("", players2.get(0));
        Field field2 = new Field( "tel-aviv", 550, 15000);
        Team team2 = new Team("Team2",page2,owners2,players2,coaches2, field2);
        representativeSystem.addTeamToLeague(leagueInSeason, team2);

        Referee ref = FootballManagementSystem.mainReferee();
        Referee ref2 = FootballManagementSystem.mainReferee();
        Referee ref3 = FootballManagementSystem.mainReferee();

        representativeSystem.assignRefToLeague(leagueInSeason, ref);
        representativeSystem.assignRefToLeague(leagueInSeason, ref2);
        representativeSystem.assignRefToLeague(leagueInSeason, ref3);


        League league = system.getDatabase().getLeague("Alufot");
        assertNotNull(league);

        boolean success = representativeSystem.assignGames(leagueInSeason, FootballManagementSystem.getDates());
        assertTrue(success);


    }

    @Test
    public void setReferees_65()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021 ,new Date(121,4,1));
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee();
        system.getDatabase().GetSystemAdmins().get(0).addUser("Aa123", ref);
        representativeSystem.assignRefToLeague(leagueInSeason, ref);

        List<Referee> referees = leagueInSeason.getReferees();
        Referee resReferee = null;

        for (Referee r : referees)
            if (r == ref)
                resReferee = r;

        assertEquals(ref, resReferee);

    }

    @Test
    public void setReferees_66()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021, new Date(121,4,1));
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee();
        system.getDatabase().GetSystemAdmins().get(0).addUser("Aa123", ref);

        representativeSystem.assignRefToLeague(leagueInSeason, ref);

        boolean success = representativeSystem.assignGames(leagueInSeason, FootballManagementSystem.getDates());

        assertFalse(success);

    }

}
