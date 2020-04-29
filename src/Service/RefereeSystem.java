package Service;

import Domain.*;

public class RefereeSystem {

    public RefereeSystem() {
    }

    public boolean addEventToGame(User user, Game game, Event.EventType type, double minuteInGame, String description){
        Referee authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.addEventToGame(game, type, minuteInGame, description);
            return true;
        }
        return false;
    }

    public void setScoreInGame(User user,Game game, int hostScore, int guestScore){
        Referee authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.setScoreInGame(game, hostScore, guestScore);
        }
    }

    private Referee getAuthorization(User user) {

        for(Role role : user.getRoles()){
            if(role instanceof Referee)
                return (Referee)role;
        }
        return null;
    }
}
