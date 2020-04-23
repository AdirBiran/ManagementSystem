package Unit;

import Data.Database;
import Domain.*;
import Domain.Authorization.UnionAuthorization;
import Domain.Authorization.UserAuthorization;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UnionAuthorizationTest {

    FootballManagementSystem system;
    UnionAuthorization authorization;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        User unionRep = UserFactory.getNewUnionRepresentative("union", "ush", "AviLevi3322@gmail.com");
        assertNotNull(unionRep);
        authorization = (UnionAuthorization) unionRep.getRoles().get(0);
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
    public void addTeamToDatabase() {
    }

    /*@Test
    public void reportNewIncome() {
        Budget budget = new Budget(null);
        system.getFinanceTransactionsManagement().reportNewIncome(budget,200);
        assertEquals(200.0, budget.getIncome(), 0);
    }

    @Test
    public void reportNewExpanse() {
        Budget budget = new Budget(null);
        system.getFinanceTransactionsManagement().reportNewExpanse(budget, 100);
        assertEquals(0, budget.getExpanses(), 0);
        system.getFinanceTransactionsManagement().reportNewIncome(budget, 200);
        system.getFinanceTransactionsManagement().reportNewExpanse(budget, 150);
        assertEquals(150, budget.getExpanses(), 0);
    }

    @Test
    public void getBalance() {
        Team team = (Team) system.getDatabase().searchObject("team0").get(0);
        system.getFinanceTransactionsManagement().reportNewIncome(team.getBudget(), 250);
        system.getFinanceTransactionsManagement().reportNewExpanse(team.getBudget(), 150);
        assertEquals(100, system.getFinanceTransactionsManagement().getBalance(team), 0);
    }*/

    @Test
    public void addTUTUPayment() {
        Team team = (Team) Database.searchObject("team0").get(0);
        authorization.addTUTUPayment(team, 100);
        assertEquals(100, team.getBudget().getBalance(), 0);
    }


    @Test
    public void configureNewLeague() {
        assertTrue(authorization.configureNewLeague("Alofot", "2"));
        assertFalse(authorization.configureNewLeague("Alofot", "2"));
    }

    @Test
    public void configureNewSeason() {
        assertTrue(authorization.configureNewSeason(2021, new Date(120,4,1)));
        assertFalse(authorization.configureNewSeason(2021, new Date(120,4,1)));
        assertFalse(authorization.configureNewSeason(1900, new Date(00,4,1)));
    }

    @Test
    public void configureLeagueInSeason() {
        LeagueInSeason leagueInSeason =authorization.configureLeagueInSeason("Leomit", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertNull(leagueInSeason);
        LeagueInSeason leagueInSeason2 = authorization.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        assertEquals("Haal", leagueInSeason2.getLeague().getName());
        assertEquals(2020, leagueInSeason2.getSeason().getYear());
    }

    @Test
    public void assignGames() {
        LeagueInSeason haal = authorization.configureLeagueInSeason("Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        for(Referee r : Database.getReferees().values())
            haal.addReferee(r);
        for (Team team: Database.getTeams())
            haal.addATeam(team);
        assertTrue(authorization.assignGames(haal, system.getDates()));
    }


    @Test
    public void appointReferee() {
        int amountOfReferee = Database.getReferees().size();
        assertNotNull(authorization.appointReferee("Alon", "Yefet", "alonY@gmail.com", "talented"));
        assertEquals(amountOfReferee+1,Database.getReferees().size(), 0);
    }
}