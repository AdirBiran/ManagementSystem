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
    private League league;

    public Event()
    {

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

    /**
     *
     * @return
     */
    public League getLeague() {
        return league;
    }

}
