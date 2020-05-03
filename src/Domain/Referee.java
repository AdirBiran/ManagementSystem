package Domain;

import java.util.HashSet;

public class Referee implements Role{

    private String training;
    private HashSet<Game> games;

    public Referee(String training) {
        this.training = training;
        games = new HashSet<>();
    }

// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
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
    to edit get event report and edit it only Main referee can
     */
    public EventReport getEventReport(Game game){
        if(this.equals(game.getMainReferee())){
            return game.getEventReport();
        }
        return null;
    }

    public boolean changeEvent(Game game, Event event, String change){
        if(this.equals(game.getMainReferee())){
            event.setDescription(change);
            return true;
        }
        return false;
    }

    public boolean changeEvent(Game game, Event event, int minInGame){
        if(this.equals(game.getMainReferee())){
            event.setMinuteInGame(minInGame);
            return true;
        }
        return false;
    }

    public boolean setScoreInGame(Game game,int hostScore, int guestScore)
    {
        if(this.equals(game.getMainReferee())){
            game.setGuestScore(guestScore);
            game.setHostScore(hostScore);
            return true;
        }
        return false;
    }

    @Override
    public String myRole() {
        return "Referee";
    }
}
