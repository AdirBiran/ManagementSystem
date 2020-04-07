package Domain;

import Data.Database;
import Presentation.Fan;

import java.util.List;

public class GameManagement {

    private Database database;

    public GameManagement(Database database) {
        this.database = database;
    }

    public void registrationForGameAlerts(Fan fan, List<Game> games, Notice notice) {
        for(Game game : games){
            game.addFanForNotifications(fan, notice);
        }
    }
}
