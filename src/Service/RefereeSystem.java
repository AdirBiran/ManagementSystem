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
        return false;
    }

    public void setScoreInGame(String userID,String gameID, int hostScore, int guestScore){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee){
            ((Referee)role).setScoreInGame(gameID, hostScore, guestScore);
            Logger.logEvent( user.getID()+ " (Referee)","Set game's score");

        }
    }

    public List<String> getGameReport(String userID, String gameID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee ) {
           return  ((Referee)role).getGameReport(gameID);
        }
        return null;
    }
    public List<String> getAllPastGames(String userID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee ) {
            return  ((Referee)role).getAllPastGames();
        }
        return null;
    }

    public boolean changeEvent(String userID, String gameID, String eventID, String newDescription){
        User user= UserFactory.getUser(userID);
        Referee role = (Referee) user.checkUserRole("Referee");
        if(role instanceof Referee){
            role.changeEvent(gameID, eventID, newDescription);
            return true;
        }
        return false;
    }

    public String getAllOccurringGame(String userID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("Referee");
        if(role instanceof  Referee ) {
            return ((Referee)role).getAllOccurringGame();
        }
        return null;
    }
}
