package UnitTest;

import Domain.LeagueInSeason;
import Domain.User;
import Domain.UserFactory;
import Service.FootballManagementSystem;
import Service.UnionRepresentativeSystem;
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
        leagueInSeason = system.dataReboot();}


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

    }
}