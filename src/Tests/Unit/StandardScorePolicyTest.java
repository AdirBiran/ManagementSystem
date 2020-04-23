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

public class StandardScorePolicyTest {

    FootballManagementSystem system;
    LeagueInSeason league;
    StandardScorePolicy score;
    Team team0;
    Team team1;
    Game game;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        league = system.dataReboot();
        //league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020, new Date(120,4,1)),200);
        score = new StandardScorePolicy();
        team0 = (Team) Database.searchObject("team0").get(0);
        team1 = (Team) Database.searchObject("team1").get(0);
        Field field = new Field("Tel-Aviv", 10000, 150000);
        Referee mainReferee= league.getReferees().get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add(league.getReferees().get(1));
        sideReferees.add(league.getReferees().get(2));
        game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees ,team0, team1,league);
        league.addATeam(team0);
        league.addATeam(team1);
    }
    @Test
    public void calculateScore() {
        score.calculateScore(game);
        assertEquals(1,team0.getDraws(),0);
        assertEquals(1,team1.getDraws(),0);
    }

    @Test
    public void calculateLeagueScore() {
        score.calculateLeagueScore(league);
        assertEquals(league.getTeams().size(),league.getScoreTable().size(),0);
    }
}