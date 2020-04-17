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
    private double minuteInGame;
    private String description;
    private EventReport eventReport;

    public Event(EventType type, Date date, double minuteInGame, String description, EventReport eventReport) {
        this.type = type;
        this.date = date;
        this.minuteInGame = minuteInGame;
        this.description = description;
        this.eventReport = eventReport;
        eventReport.addEvent(this);
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


}
