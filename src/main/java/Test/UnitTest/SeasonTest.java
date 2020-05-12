package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SeasonTest {
    FootballManagementSystem system;
    LeagueInSeason leagueInSeason;

    @Before
    public void init() {
        system = new FootballManagementSystem();
        system.systemInit(true);
        leagueInSeason = system.dataReboot();
    }


    @Test
    public void getStartDate() {
       assertEquals( leagueInSeason.getSeason().getStartDate(),new Date(120, 4, 1));
    }

    @Test
    public void getYear() {
        assertEquals(leagueInSeason.getSeason().getYear(),2020);
    }

    @Test
    public void getLeagueInSeasons() {
        assertEquals(leagueInSeason.getSeason().getLeagueInSeasons().size(),1);
    }

    @Test
    public void addLeagueInSeason() {
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        User union = admin.addNewUnionRepresentative("Union", "Rep", "unionRep@gmail.com");
        UnionRepresentative unionRole = ((UnionRepresentative)union.checkUserRole("UnionRepresentative"));
        unionRole.configureNewSeason(2021, new Date(120, 4, 1));
        Season season1 = Database.getSeason("2021");
        LeagueInSeason leagueInSeason = unionRole.configureLeagueInSeason("Haal", "2021", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        season1.addLeagueInSeason(leagueInSeason);
        assertEquals(season1.getLeagueInSeasons().size(), 2);
    }

    @Test
    public void getId(){
        assertNotNull(leagueInSeason.getSeason().getId());
    }
}