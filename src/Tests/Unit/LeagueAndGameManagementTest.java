package Tests.Unit;

import Data.Database;
import Domain.Game;
import Domain.GameAssignmentPolicy;
import Domain.LeagueAndGameManagement;
import Domain.ReceiveAlerts;
import Presentation.Fan;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeagueAndGameManagementTest {

    @Test
    public void registrationForGamesAlerts() {
        Database database = new Database();
        LeagueAndGameManagement leagueAndGameManagement = new LeagueAndGameManagement(database);
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        List<Game> games = database.getAllFutureGames();
        ReceiveAlerts receive = new ReceiveAlerts(false, false);
        assertTrue(leagueAndGameManagement.registrationForGamesAlerts(fan, games, receive));
        assertFalse(leagueAndGameManagement.registrationForGamesAlerts(fan, games, receive));
    }

    @Test
    public void configureNewLeague() {
        Database database = new Database();
        LeagueAndGameManagement leagueAndGameManagement = new LeagueAndGameManagement(database);
        assertTrue(leagueAndGameManagement.configureNewLeague("Alofot", "2"));
        assertFalse(leagueAndGameManagement.configureNewLeague("Alofot", "2"));
    }

    @Test
    public void configureNewSeason() {
        Database database = new Database();
        LeagueAndGameManagement leagueAndGameManagement = new LeagueAndGameManagement(database);
        assertTrue(leagueAndGameManagement.configureNewSeason(2021));
        assertFalse(leagueAndGameManagement.configureNewSeason(2021));
    }

    @Test
    public void configureLeagueInSeason() {
        Database database = new Database();
        LeagueAndGameManagement leagueAndGameManagement = new LeagueAndGameManagement(database);
        //GameAssignmentPolicy assignmentPolicy
        //leagueAndGameManagement.configureLeagueInSeason("Haal", "2020", )

    }

    @Test
    public void assignRefToLeague() {
    }

    @Test
    public void changeScorePolicy() {
    }

    @Test
    public void changeAssignmentPolicy() {
    }

    @Test
    public void assignGames() {
    }

    @Test
    public void closeTeam() {
    }

    @Test
    public void reopeningTeam() {
    }

    @Test
    public void permanentlyCloseTeam() {
    }

    @Test
    public void addTeamToLeague() {
    }

    @Test
    public void calculateLeagueScore() {
    }

    @Test
    public void calculateGameScore() {
    }

    @Test
    public void changeRegistrationFee() {
    }
}