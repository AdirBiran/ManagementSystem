package Domain;

import java.util.Date;

public class Event {

    public enum EventType {
        Undefined,
        Goal,
        Offside,
        Foul,
        RedCard,
        YellowCard,
        Injury,
        Replacement
    }
    private String id;
    private EventType type;
    private Date date;
    private double minuteInGame;
    private String description;


    public Event(EventType type, double minuteInGame, String description, EventReport eventReport) {
        this.id = "E"+IdGenerator.getNewId();
        this.type = type;
        this.date = new Date();
        this.minuteInGame = minuteInGame;
        this.description = description;
        eventReport.addEvent(this);
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++

    public EventType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public double getMinuteInGame() {
        return minuteInGame;
    }

    public String getDescription() {
        return description;
    }

    public void setMinuteInGame(double minuteInGame) {
        this.minuteInGame = minuteInGame;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }
}
