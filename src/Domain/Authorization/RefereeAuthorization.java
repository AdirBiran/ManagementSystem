package Domain.Authorization;

import Domain.*;

import java.util.HashSet;

public class RefereeAuthorization extends UserAuthorization {

    private Referee referee;
    private HashSet<Game> games;

    public RefereeAuthorization(Referee referee, User user) {
        super( user);
        this.referee = referee;
        games = new HashSet<>();
    }

    public void addGame(Game game) {

        this.games.add(game);
    }

    public HashSet<Game> viewGames(){return games;}


    public void addEventToGame(Game game, Event.EventType type, double minuteInGame, String description)
    {
        new Event(type, minuteInGame, description, game.getEventReport());
    }
    /*
    to edit get event report and edit it only main referee can
     */
    public EventReport getEventReport(Game game){
        if(referee.equals(game.getMainReferee())){
            return game.getEventReport();
        }
        return null;
    }

    public boolean changeEvent(Game game, Event event, String change){
        if(referee.equals(game.getMainReferee())){
            event.setDescription(change);
            return true;
        }
        return false;
    }

    public boolean changeEvent(Game game, Event event, int minInGame){
        if(referee.equals(game.getMainReferee())){
            event.setMinuteInGame(minInGame);
            return true;
        }
        return false;
    }

    public boolean setScoreInGame(Game game,int hostScore, int guestScore)
    {
        if(referee.equals(game.getMainReferee())){
            game.setGuestScore(guestScore);
            game.setHostScore(hostScore);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RefereeAuthorization;
    }


}
