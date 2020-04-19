package Unit;

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
        system.dataReboot();
        league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020, new Date(120,4,1)),200);

    }


    @Test
    public void changeRegistrationFee() {
        double fee=20;
        league.changeRegistrationFee(fee);
        assertEquals(fee,league.getRegistrationFee(),0);
    }

    @Test
    public void addATeam() {
        Team team0 = (Team) system.getDatabase().searchObject("team0").get(0);
        league.addATeam(team0);
        assertEquals(1,league.getTeams().size(),0);
        league.addATeam(team0);
        assertEquals(1,league.getTeams().size(),0);
    }

    @Test
    public void addGame() {
        Team team0 = (Team) system.getDatabase().searchObject("team0").get(0);
        Team team1 = (Team) system.getDatabase().searchObject("team1").get(0);
        Field field = new Field("Tel-Aviv", 10000, 150000);
        Referee mainReferee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(2));
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
        Team team = (Team) system.getDatabase().searchObject("team0").get(0);
        int score = team.getWins()*3 + team.getDraws();
        ScoreTableRecord record = new ScoreTableRecord(team, score);
        league.addScoreTableRecord(record);
        assertEquals(1,league.getScoreTable().size(),0);
    }

    @Test
    public void addReferee() {
        Referee referee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        assertTrue(league.addReferee(referee));
        assertFalse(league.addReferee(referee));
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