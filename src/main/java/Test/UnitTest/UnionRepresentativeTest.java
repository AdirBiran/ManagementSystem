package UnitTest;

import Domain.Admin;
import Domain.Guest;
import Domain.LeagueInSeason;
import Domain.User;
import Service.FootballManagementSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnionRepresentativeTest {
    FootballManagementSystem system;
    User user;

    public void init() {
        system = new FootballManagementSystem();
        system.systemInit(true);
        Guest guest = new Guest();
        user = guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23");
    }

    @Test
    public void configureNewLeague() {
    }

    @Test
    public void configureNewSeason() {
    }

    @Test
    public void configureLeagueInSeason() {
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
    }

    @Test
    public void addTeamToDatabase() {
    }
}