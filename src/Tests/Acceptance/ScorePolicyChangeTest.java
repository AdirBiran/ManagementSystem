package Acceptance;

import Domain.*;
import Service.FootballManagementSystem;
import Service.UnionRepresentativeSystem;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

public class ScorePolicyChangeTest {


    private FootballManagementSystem system;
    private UnionRepresentativeSystem repSystem;
    private User unionRep;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        unionRep = UserFactory.getNewUnionRepresentative("union", "rep", "someLie@a.com");

        repSystem = system.getUnionRepresentativeSystem();
    }


    @Test
    public void changeScorePolicy_68()
    {

        system.dataReboot();

        repSystem.configureNewSeason(unionRep,2021,new Date(121, 4, 11));
        repSystem.configureNewLeague(unionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = repSystem.configureLeagueInSeason(unionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        boolean success = repSystem.changeScorePolicy(unionRep,leagueInSeason, new StandardScorePolicy());
        assertFalse(success);

    }

    @Test
    public void changeScorePolicy_69()
    {

        system.dataReboot();

        repSystem.configureNewSeason(unionRep,2021,new Date(121, 4, 11));
        repSystem.configureNewLeague(unionRep,"Alufot", "1");
        LeagueInSeason leagueInSeason = repSystem.configureLeagueInSeason(unionRep,"Alufot", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 250);

        boolean success = repSystem.changeScorePolicy(unionRep,leagueInSeason, new CupScorePolicy());
        assertTrue(success);

    }
}
