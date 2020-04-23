package Acceptance;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class SeasonInitiationTest {

    private FootballManagementSystem system;
    private User UnionRep;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        UnionRep = UserFactory.getNewUnionRepresentative("aa", "bb", "aa@bb.com");

    }

    @Test
    public void defineLeagueTest_55()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(UnionRep,2021,new Date(121,4,1));
        representativeSystem.configureNewLeague(UnionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee(UnionRep);
        Referee ref2 = FootballManagementSystem.mainReferee(UnionRep);
        Referee ref3 = FootballManagementSystem.mainReferee(UnionRep);

        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref);
        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref2);
        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref3);


        League league = Database.getLeague("Alufot");
        assertNotNull(league);

        boolean success = representativeSystem.assignGames(UnionRep,leagueInSeason, FootballManagementSystem.getDates());
        assertFalse(success);
    }

    @Test
    public void defineLeagueTest_56()
    {

        system.dataReboot();

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(UnionRep,2021,new Date(121,4,1));
        representativeSystem.configureNewLeague(UnionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Alufot", "2021", null, null, 250);

        assertNull(leagueInSeason);
    }

    @Test
    public void defineLeagueTest_57()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(UnionRep,1800, new Date(18,4,1));
        representativeSystem.configureNewLeague(UnionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Alufot", "1800", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        assertNull(leagueInSeason);
    }

    @Test
    public void defineLeagueTest_58()//needs 14 teams to run
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(UnionRep,2021,new Date(121,4,1));
        representativeSystem.configureNewLeague(UnionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);
        //LeagueInSeason leagueInSeason = FootballManagementSystem.dataReboot();
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        User owner = UserFactory.getNewTeamOwner("Team1","Owner", "a112"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("Team1",page,owners,players,coaches, field);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        List<Player> players2 = FootballManagementSystem.createPlayers();
        List<Coach> coaches2 = FootballManagementSystem.createCoaches();
        User owner2 = UserFactory.getNewTeamOwner("Team1","Owner", "a"+"@gmail.com");
        List<User> owners2 = new LinkedList<>();
        owners2.add(owner2);
        PersonalPage page2 = new PersonalPage("", owner2);
        Field field2 = new Field( "tel-aviv", 550, 15000);
        Team team2 = new Team("Team2",page2,owners2,players2,coaches2, field2);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team2);

        Referee ref = FootballManagementSystem.mainReferee(UnionRep);
        Referee ref2 = FootballManagementSystem.mainReferee(UnionRep);
        Referee ref3 = FootballManagementSystem.mainReferee(UnionRep);

        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref);
        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref2);
        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref3);


        League league = Database.getLeague("Alufot");
        assertNotNull(league);

        boolean success = representativeSystem.assignGames(UnionRep,leagueInSeason, FootballManagementSystem.getDates());
        assertTrue(success);


    }

    @Test
    public void setReferees_65()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(UnionRep,2021 ,new Date(121,4,1));
        representativeSystem.configureNewLeague(UnionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee(UnionRep);

        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref);

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
        representativeSystem.configureNewSeason(UnionRep,2021, new Date(121,4,1));
        representativeSystem.configureNewLeague(UnionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee(UnionRep);


        representativeSystem.assignRefToLeague(UnionRep,leagueInSeason, ref);

        boolean success = representativeSystem.assignGames(UnionRep,leagueInSeason, FootballManagementSystem.getDates());

        assertFalse(success);

    }

}
