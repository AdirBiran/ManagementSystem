package Service;

import Domain.*;
import Logger.Logger;

public class RefereeSystem {

    public RefereeSystem() {
    }

    public boolean addEventToGame(User user, Game game, Event.EventType type, double minuteInGame, String description){
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee){
            ((Referee)role).addEventToGame(game, type, minuteInGame, description);
            Logger.logEvent( user.getID()+ " (Referee)","Added Event to Game");
            return true;
        }
        return false;
    }

    public void setScoreInGame(User user,Game game, int hostScore, int guestScore){
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee){
            ((Referee)role).setScoreInGame(game, hostScore, guestScore);
            Logger.logEvent( user.getID()+ " (Referee)","Set game's score");

        }
    }
/*
    public List<String> getGameReport(Game game){

    }*/
}
