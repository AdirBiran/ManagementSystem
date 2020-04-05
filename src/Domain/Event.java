package Domain;

import java.util.Date;
import java.sql.Time;

public class Event {

    private enum EventType {
        Undefined,
        Goal,
        Offside,
        Foul,
        RedCard,
        YellowCard,
        Injury,
        Replacement
    }

    private EventType type;
    private Date date;
    private Time time;
    private double minuteInGame;
    private String description;
    private Game game;

    public Event(EventType type, Date date, Time time, double minuteInGame, String description, Game game) {
        this.type = type;
        this.date = date;
        this.time = time;
        this.minuteInGame = minuteInGame;
        this.description = description;
        this.game = game;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public EventType getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public Time getTime() {
        return time;
    }

    /**
     *
     * @return
     */
    public double getMinuteInGame() {
        return minuteInGame;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public Game getGame() {
        return game;
    }


}
