package Tests.Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayTwiceWithEachTeamPolicyTest {

    @Test
    public void assignGames() {
        FootballManagementSystem system;
        PlayOnceWithEachTeamPolicy one = new PlayOnceWithEachTeamPolicy();
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        List <Team> teams = system.getDatabase().getTeams();
        List<Date> dates = system.getDates();
        LeagueInSeason haal = system.getLeagueAndGameManagement().configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for(Object r : system.getDatabase().getListOfAllSpecificUsers("Referee"))
            haal.addReferee((Referee) r);
        assertEquals(20, one.assignGames(teams, dates, haal).size(), 0);
    }
}