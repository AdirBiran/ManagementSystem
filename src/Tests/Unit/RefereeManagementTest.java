package Tests.Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RefereeManagementTest {

    FootballManagementSystem system;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
    }

    @Test
    public void sendNotification() {
        LeagueInSeason league = new LeagueInSeason(new PlayOnceWithEachTeamPolicy(), new StandardScorePolicy(), new League("alofot", "3"), new Season(2020, new Date(120,4,1)),200);
        Team team2 = (Team) system.getDatabase().searchObject("team2").get(0);
        Team team3 = (Team) system.getDatabase().searchObject("team3").get(0);
        Field field = new Field("Jerusalem", 20000);
        Referee mainReferee= (Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(0);
        List<Referee> sideReferees = new LinkedList<>();
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(1));
        sideReferees.add((Referee) system.getDatabase().getListOfAllSpecificUsers("Referee").get(2));
        Game game = new Game(new Date(120, 4, 20, 20, 0), field, mainReferee, sideReferees ,team2, team3,league);
        int amountOfMessage = system.getDatabase().getListOfAllSpecificUsers("Referee").get(0).getMessageBox().size();
        system.getRefereeManagement().sendNotification(game, "The game in "+game.getDate());
        assertEquals(amountOfMessage+1,system.getDatabase().getListOfAllSpecificUsers("Referee").get(0).getMessageBox().size(), 0 );
    }

    @Test
    public void appointReferee() {
        int amountOfReferee = system.getDatabase().getListOfAllSpecificUsers("Referee").size();
        assertNotNull(system.getRefereeManagement().appointReferee("Alon", "Yefet", "alonY@gmail.com", "talented"));
        assertEquals(amountOfReferee+1,system.getDatabase().getListOfAllSpecificUsers("Referee").size(), 0);
    }
}