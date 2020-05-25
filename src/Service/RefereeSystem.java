package Service;

import Domain.*;
import java.util.LinkedList;
import java.util.List;
import Logger.Logger;

public class RefereeSystem {

    public RefereeSystem() {
    }

    public boolean addEventToGame(String userID, String gameID, String event, String playerId, String teamId){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee){
            ((Referee)role).addEventToGame(gameID, Event.EventType.valueOf(event), playerId, teamId);
            Logger.logEvent( user.getID()+ " (Referee)","Added Event to Game");
            return true;
        }
        Logger.logError("Adding event to game failed");
        return false;
    }

    public boolean setScoreInGame(String userID,String gameID, int hostScore, int guestScore){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee){
            boolean success = ((Referee)role).setScoreInGame(gameID, hostScore, guestScore);
            if(success)
                Logger.logEvent( user.getID()+ " (Referee)","Set game's score");
            return success;
        }
        return false;
    }

    public List<String> getGameReport(String userID, String gameID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee ) {
            Logger.logEvent(userID, "Got Game Report");
            return  ((Referee)role).getGameReport(gameID);
        }
        Logger.logError("Getting Game Report failed");
        return null;
    }

    public boolean changeEvent(String userID, String gameID, String eventID, String newDescription){
        User user= UserFactory.getUser(userID);
        Referee role = (Referee) user.checkUserRole("Referee");
        if(role instanceof Referee){
            role.changeEvent(gameID, eventID, newDescription);
            Logger.logEvent(userID, "Changed event " + eventID);
            return true;
        }
        Logger.logError("Chaning event failed");
        return false;
    }

    public List<String> getAllPastGames(String userID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee ) {
            Logger.logEvent(userID, "Got all Past Games");
            return  ((Referee)role).getAllPastGames();
        }
        Logger.logError("Getting all Past Games failed");
        return null;
    }

    public String getAllOccurringGame(String userID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee ) {
            Logger.logEvent(userID, "Got All Occurring Games");
            return ((Referee)role).getAllOccurringGame();
        }
        Logger.logError("Getting All Occurring games failed");
        return null;
    }
}
