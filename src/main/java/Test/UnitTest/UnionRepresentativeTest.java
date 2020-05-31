package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UnionRepresentativeTest {
    FootballManagementSystem system;
    UnionRepresentative unionRepresentative;

    @Before
    public void init() {
        system = new FootballManagementSystem();
        system.systemInit(false);
        unionRepresentative = system.getDatabase().getAllUnions().get(0);
    }

    @Test
    public void configureNewLeague() {

    }

    @Test
    public void configureNewSeason() {
        //assertNull(unionRepresentative.configureNewSeason(2021, new Date()));
        //assertNotNull(unionRepresentative.configureNewSeason(2022, new Date()));
        assertNotNull(unionRepresentative.configureNewSeason(2023, new Date()));
    }

    @Test
    public void configureLeagueInSeason() {
        assertNull(unionRepresentative.configureLeagueInSeason(system.getDatabase().getLeagues().get(0).getId(),system.getDatabase().getSeasons().get(0).getId(),"PlayOnceWithEachTeamPolicy", "StandardScorePolicy", 200));
        assertNotNull(unionRepresentative.configureLeagueInSeason(system.getDatabase().getLeagues().get(0).getId(),system.getDatabase().getSeasons().get(0).getId(),"PlayOnceWithEachTeamPolicy", "StandardScorePolicy", 200));
    }

    @Test
    public void assignGames() {
    }

    @Test
    public void appointReferee() {
    }

    @Test
    public void removeReferee() {
    }

    @Test
    public void addRefereeToLeague() {
    }

    @Test
    public void changeScorePolicy() {
    }

    @Test
    public void changeAssignmentPolicy() {
    }

    @Test
    public void addTUTUPayment() {
    }

    @Test
    public void allLeaguesInSeasons() {
        assertNotNull(unionRepresentative.allLeaguesInSeasons());
    }

    @Test
    public void addTeamToDatabase() {
    }
}