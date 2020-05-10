package Domain;

import java.util.*;

public class Referee extends Role implements Observer {

    public enum TrainingReferee{
        referees,
        linesman,
        var
    }

    private TrainingReferee training;
    private HashSet<Game> games;

    public Referee(TrainingReferee training) {
        this.training = training;
        games = new HashSet<>();
        messageBox = new LinkedList<>();
        myRole = "Referee";
    }

// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    public String getTraining() {
        return training.toString();
    }

    public void setTraining(TrainingReferee training) {
        this.training = training;
    }

    public void addGame(Game game) {

        this.games.add(game);
    }

    public HashSet<Game> viewGames(){return games;}


    public void addEventToGame(Game game, Event.EventType type, double minuteInGame, String description)
    {
        Event event = new Event(type, minuteInGame, description, game.getEventReport());
        game.getEventReport().addEvent(event);
        game.setNewsFromReferee(event);
    }
    /*
    to edit get event report and edit it only main referee can
     */
    /*
    public EventReport getEventReport(Game game){
        return game.getEventReport();
    }
*/
    public boolean changeEvent(Game game, Event event, String change){
        for (Event event1: game.getEventReport().getEvents()) {
            if (event1.getId().equals(event.getId())) {
                event1.setDescription(change);
                return true;
            }
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

    @Override
    public String toString() {
        return "Referee{" +
                "training='" + training + '\'' +
                ", games=" + games +
                '}';
    }
    @Override
    public void update(Observable o, Object arg) {
        if(!(arg instanceof Event))
            messageBox.add(new Notice((String)arg));
    }
}
