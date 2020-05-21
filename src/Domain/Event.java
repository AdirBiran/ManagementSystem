package Domain;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Event {

    //public static Object EventType;

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


    public Event(EventType type,Game game, String description) {
        this.id = "E"+IdGenerator.getNewId();
        this.type = type;
        this.date = new Date();
        long diffInMillies = Math.abs(date.getTime() - game.getDate().getTime());
        this.minuteInGame = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        this.description = description;
    }

    public Event(String id, String type, Date date, double minuteInGame, String description)
    {
        this.id = id;
        this.type = EventType.valueOf(type);
        this.date = date;
        this.minuteInGame = minuteInGame;
        this.description = description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", date=" + date +
                ", minuteInGame=" + minuteInGame +
                ", description='" + description + '\'' +
                '}';
    }

    public String createMessage(){
        return date + ": " + minuteInGame + ", " + type + ", " + description;
    }
}
