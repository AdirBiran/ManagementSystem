package Service;

import Domain.*;

import java.util.Date;

public class RefereeSystem {



    public RefereeSystem() {

    }

    public void addEventToGame(Referee referee, Game game, Event.EventType type, Date date, double minuteInGame, String description){
        referee.createEvent(game,type,date,minuteInGame,description);

    }

    public void makeGameReport(Referee referee,Game game){
        referee.generateGameReport(game.getEventReport());
    }

}
