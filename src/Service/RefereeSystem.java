package Service;

import Domain.*;

import java.util.Date;

public class RefereeSystem {


    private LeagueAndGameManagement leagueAndGameManagement;
    private RefereeManagement refereeManagement;


    public RefereeSystem(LeagueAndGameManagement leagueAndGameManagement, RefereeManagement refereeManagement) {
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.refereeManagement = refereeManagement;


    }

    public void addEventToGame(Referee referee, Game game, Event.EventType type, Date date, double minuteInGame, String description){
        referee.createEvent(game,type,date,minuteInGame,description);

    }

    public void makeGameReport(Referee referee,Game game){
        referee.generateGameReport(game.getEventReport());
    }

}
