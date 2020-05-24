package Domain;

import Data.Database;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Referee extends Role implements Observer {

    public enum TrainingReferee{
        referees,
        linesman,
        var
    }

    private TrainingReferee training;
    private HashSet<Game> games;

    public Referee(User user, TrainingReferee training) {
        this.user = user;
        this.training = training;
        games = new HashSet<>();
        myRole = "Referee";
    }

    public Referee(User user, String training, HashSet<Game> games)
    {
        this.user = user;
        this.training = TrainingReferee.valueOf(training);
        this.games = games;
    }

// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    public String getTraining() {
        return training.toString();
    }

    public void setTraining(TrainingReferee training) {
        this.training = training;
        Database.updateObject(this);
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public HashSet<Game> viewGames(){return games;}


    public void addEventToGame(String gameID, Event.EventType type, String playerId, String teamId)
    {
        Game game= Database.getGame(gameID);
        Team team = Database.getTeam(teamId);
        Player player = Database.getPlayer(playerId);
        if(game!=null && team!=null && player!=null) {
            String description = player.getUser().getName() + " from team "+ team.getName();
            Event event = new Event(type, game, description);
            game.getEventReport().addEvent(event);
            game.setNewsFromReferee(event.createMessage());
            Database.updateObject(game);
        }
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

    public boolean changeEvent(String gameID, String eventID, String change){
        Game game= Database.getGame(gameID);
        Event event=game.getEventReport().gerEventById(eventID);
        if (event!=null && this.equals(game.getMainReferee())) {
            long time = new Date().getTime();
            if(TimeUnit.DAYS.convert(Math.abs(time - game.getDate().getTime()), TimeUnit.MILLISECONDS)<=300) {
                for (Event event1 : game.getEventReport().getEvents()) {
                    if (event1.getId().equals(event.getId())) {
                        event1.setDescription(change);
                        Database.updateObject(game);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean setScoreInGame(String gameID,int hostScore, int guestScore)
    {
        Game game= Database.getGame(gameID);
        if(this.equals(game.getMainReferee())){
            game.setGuestScore(guestScore);
            game.setHostScore(hostScore);
            String message = game.getName()+": "+ game.getHostTeam()+": "+hostScore+"-"+game.getGuestTeam()+": "+guestScore;
            game.setNewsFromReferee(message);
            Database.updateObject(game);
            return true;
        }
        return false;
    }

    public List<String> getGameReport(String gameID) {
        List<String> gameReport = new LinkedList<>();
        Game game= Database.getGame(gameID);
        if(game.getMainReferee().equals(this)) {
            gameReport.addAll(game.getEventReportString());
        }
        return gameReport;
    }

    public String getGamesId(){
        String listOfId = "";
        for (Game game: games) {
            if(listOfId.equals("")){
                listOfId = listOfId+game.getId();
            }
            else {
                listOfId = listOfId + ","+game.getId();
            }
        }
        return listOfId;
    }

    public static LinkedList<String> getAllPastGames(){
        Date today = new Date();
        LinkedList<String> pastGames = new LinkedList<>();
        for(Game game : Database.getAllGames()){
            if(today.after(game.getDate()))
                pastGames.add(game.toString());
        }
        return pastGames;
    }

    public String getAllOccurringGame() {
        long time = new Date().getTime();
        for(Game game : Database.getAllGames()){
            if(TimeUnit.DAYS.convert(Math.abs(time - game.getDate().getTime()), TimeUnit.MILLISECONDS)<=120)
                return game.toString();
        }
        return null;
    }

    @Override
    public String myRole() {
        return "Referee";
    }

    @Override
    public String toString() {
        return "Referee" +
                ", id="+user.getID()+
                ": name="+user.getName()+
                ", training=" + training;
    }
    @Override
    public void update(Observable o, Object arg) {
        if(!(arg instanceof Event))
            user.addMessage((String)arg);
    }
}
