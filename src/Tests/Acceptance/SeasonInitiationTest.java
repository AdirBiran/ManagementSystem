package Tests.Acceptance;

import Domain.*;
import Service.FootballManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import org.junit.Test;

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

        system.dataReboot();

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        // cant be implemented yet
    }

    @Test
    public void defineLeagueTest_56()
    {

        system.dataReboot();

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", null, null, 250);

        assertNull(leagueInSeason);
    }

    @Test
    public void defineLeagueTest_57()
    {

        UnionRepresentativeSystem representativeSystem = system.getUnionRepresentativeSystem();
        representativeSystem.configureNewSeason(1800);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "1800", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);


        // need to change configureLeagueInSeason

        assertNull(leagueInSeason);
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
        representativeSystem.addTeamToLeague(leagueInSeason, team);

        League league = system.getDatabase().getLeague("Alufot");

        assertNotNull(league);


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
        representativeSystem.configureNewSeason(2021);
        representativeSystem.configureNewLeague("Alufot", "1");
        LeagueInSeason leagueInSeason = representativeSystem.configureLeagueInSeason("Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        Referee ref = FootballManagementSystem.mainReferee();
        Referee ref2 = FootballManagementSystem.mainReferee();
        system.getAdminSystem().addUser("Aa123", ref);
        system.getAdminSystem().addUser("Bb123", ref2);

        representativeSystem.assignRefToLeague(leagueInSeason, ref);
        representativeSystem.assignRefToLeague(leagueInSeason, ref2);

        List<Referee> referees = leagueInSeason.getReferees();

        boolean flagRef1 = false;
        boolean flagRef2 = false;

        for (Referee r: referees)
            if (r == ref)
                flagRef1 = true;
            else if (r == ref2)
                flagRef2 = true;

            assertTrue(flagRef1);
        assertFalse(flagRef2);

    }
}
