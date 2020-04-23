package Unit;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class LeagueInSeasonTest {
    FootballManagementSystem system;
    LeagueInSeason league;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        league =system.dataReboot();
    }


    @Test
    public void changeRegistrationFee() {
        double fee=20;
        league.changeRegistrationFee(fee);
        assertEquals(fee,league.getRegistrationFee(),0);
    }

    @Test
    public void addATeam() {
        List<User> owners = new LinkedList<>();
        User owner = UserFactory.getNewTeamOwner("eee", "ggg", "444@mail.com");
        assertNotNull(owner);
        owners.add(owner);
        Team team0 = new Team("ww", new PersonalPage("", owner), owners, FootballManagementSystem.createPlayers(), FootballManagementSystem.createCoaches(),new Field("here", 30001, 20000));
        league.addATeam(team0);
        assertEquals(15,league.getTeams().size(),0);
        league.addATeam(team0);
        assertEquals(15,league.getTeams().size(),0);
    }

    @Test
    public void addGame() {
        Team team0 = (Team) Database.searchObject("team0").get(0);
        Team team1 = (Team) Database.searchObject("team1").get(0);
        Field field = new Field("Tel-Aviv", 10000, 150000);
        Referee mainReferee= league.getReferees().get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        Game game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees ,team0, team1,league);
        league.addGame(game);
        assertEquals(1,league.getAllGames().size(),0);
        league.addGame(game);
        assertEquals(1,league.getAllGames().size(),0);
    }

    @Test
    public void getGameById() {
        assertNull(league.getGameById("111"));
    }

    @Test
    public void addScoreTableRecord() {
        Team team = (Team) Database.searchObject("team0").get(0);
        int score = team.getWins()*3 + team.getDraws();
        ScoreTableRecord record = new ScoreTableRecord(team, score);
        league.addScoreTableRecord(record);
        assertEquals(1,league.getScoreTable().size(),0);
    }

    @Test
    public void addReferee() {
        Referee referee = league.getReferees().get(0);
        assertFalse(league.addReferee(referee));

        Object[] ref = UserFactory.getNewReferee("test", "ref", "testRef@gmail.com", "best");
        assertNotNull(ref);
        assertTrue(league.addReferee((Referee)ref[1]));

    }

    @Test
    public void changeScorePolicy() {
        ScorePolicy policy=league.getScorePolicy();
        assertFalse(league.changeScorePolicy(policy));
    }

    @Test
    public void changeAssignmentPolicy() {
        GameAssignmentPolicy policy=league.getAssignmentPolicy();
        assertFalse(league.changeAssignmentPolicy(policy));
    }
}