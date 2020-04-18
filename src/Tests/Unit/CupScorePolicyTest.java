package Tests.Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CupScorePolicyTest {

    FootballManagementSystem system;
    LeagueInSeason league;
    CupScorePolicy score;
    Team team0;
    Team team1;
    Game game;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020, new Date(120,4,1)),200);
        score=new CupScorePolicy();
        team0 = system.getDatabase().getTeams().get(0);
        team1 = system.getDatabase().getTeams().get(1);
        Field field = new Field("Tel-Aviv", 10000, 150000);
        Referee mainReferee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(2));
        game = new Game(new Date(120, 4, 25, 20, 0), field, mainReferee, sideReferees ,team0, team1,league);
        league.addATeam(team0);
        league.addATeam(team1);
    }
    @Test
    public void calculateScore() {
        game.setGuestScore(3);
        game.setHostScore(1);
        score.calculateScore(game);
        assertEquals(1,game.getGuestTeam().getWins(),0);
        assertEquals(0,game.getHostTeam().getWins(),0);
    }

    @Test
    public void calculateLeagueScore() {
        score.calculateLeagueScore(league);
        assertEquals(2,league.getScoreTable().size(),0);
    }
    }