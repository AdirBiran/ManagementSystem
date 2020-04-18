package Tests.Unit;

import Data.Database;
import Domain.Game;
import Domain.ReceiveAlerts;

import Domain.Fan;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void addFanForNotifications() {
        Database  database = new Database();
        Game game = (Game) database.searchObject("game");
        Fan fan = new Fan("bendor.shir@gmail.com","Shir","Ben David","0525379547","Beer Sheva");
        ReceiveAlerts ra=new ReceiveAlerts(true,false);
        assertTrue(game.addFanForNotifications(fan,ra));
    }

}