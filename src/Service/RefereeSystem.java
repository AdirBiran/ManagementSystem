package Service;

import Domain.*;
import java.util.LinkedList;
import java.util.List;
import Logger.Logger;

public class RefereeSystem {

    public RefereeSystem() {
    }

    public boolean addEventToGame(String userID, String gameID, String event, double minuteInGame, String description){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee){
            ((Referee)role).addEventToGame(gameID, Event.EventType.valueOf(event), minuteInGame, description);
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

    public List<String> getGameReport(User user, Game game){
        Role role = (Referee)user.checkUserRole("Referee");
        if(role instanceof  Referee && game.getMainReferee().equals(role)) {
            List<String> gameReport = new LinkedList<>();
            gameReport.addAll(game.getEventReportString());
            return gameReport;
        }
        return null;
    }

    public boolean changeEvent(User user, Game game, Event event, String newDescription){
        Referee role = (Referee) user.checkUserRole("Referee");
        if(role instanceof Referee && game.getMainReferee().equals(role)){
            role.changeEvent(game, event, newDescription);
            return true;
        }
        return false;
    }
}
