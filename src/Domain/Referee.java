package Domain;

import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Referee extends User {


    private String training;
    private List<Game> games;

    public Referee(String firstName,String lastName, String mail, String training) {
        super(firstName,lastName, "R", mail);
        this.training = training;
        this.games = new LinkedList<>();
    }

// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    public void addGame(Game game) {
        if(!games.contains(game))
            this.games.add(game);
    }

    /**
     *
     * @param game
     *
     */
    public void createEvent(Game game, Event.EventType type, Date date, double minuteInGame, String description)
    {
        Event newEvent = new Event(type, date, minuteInGame, description, game.getEventReport());


    }

    /**
     *
     * @param eventReport
     */
    public void generateGameReport(EventReport eventReport)
    {
        for (Event event: eventReport.getEvents()) {

        }

    }

    /**
     *
     * @return
     */
    public String getTraining() {
        return training;
    }


    public void setTraining(String training) {
        this.training = training;
    }
}
