package Unit;

import Domain.*;
import Service.FootballManagementSystem;
import Domain.Referee;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LeagueAndGameManagementTest {

    FootballManagementSystem system;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
    }

    @Test
    public void configureNewLeague() {
        assertTrue(system.getLeagueAndGameManagement().configureNewLeague("Alofot", "2"));
        assertFalse(system.getLeagueAndGameManagement().configureNewLeague("Alofot", "2"));
    }

    @Test
    public void configureNewSeason() {
        assertTrue(system.getLeagueAndGameManagement().configureNewSeason(2021, new Date(120,4,1)));
        assertFalse(system.getLeagueAndGameManagement().configureNewSeason(2021, new Date(120,4,1)));
    }

    @Test
    public void configureLeagueInSeason() {
        LeagueInSeason leagueInSeason = system.getLeagueAndGameManagement().configureLeagueInSeason("Leomit", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertNull(leagueInSeason);
        LeagueInSeason leagueInSeason2 = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertEquals("Haal", leagueInSeason2.getLeague().getName());
        assertEquals(2020, leagueInSeason2.getSeason().getYear());
    }

    @Test
    public void assignGames() {
        LeagueInSeason haal = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for(Object r : system.getDatabase().getListOfAllSpecificUsers("Referee"))
            haal.addReferee((Referee) r);
        for (Team team: system.getDatabase().getTeams())
            haal.addATeam(team);
        assertTrue(system.getLeagueAndGameManagement().assignGames(haal, system.getDates()));
    }
}