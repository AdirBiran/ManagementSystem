package Service;

import Domain.Authorization.AuthorizationRole;
import Domain.Authorization.RefereeAuthorization;
import Domain.*;

public class RefereeSystem {



    public RefereeSystem() {

    }

    public boolean addEventToGame(User user, Game game, Event.EventType type, double minuteInGame, String description){
        RefereeAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.addEventToGame(game, type, minuteInGame, description);
            return true;
        }
        return false;
    }

    public void setScoreInGame(User user,Game game, int hostScore, int guestScore){
        RefereeAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.setScoreInGame(game, hostScore, guestScore);
        }
    }

    private RefereeAuthorization getAuthorization(User user) {

        for(AuthorizationRole role : user.getRoles()){
            if(role instanceof RefereeAuthorization)
                return (RefereeAuthorization)role;
        }
        return null;
    }
}
