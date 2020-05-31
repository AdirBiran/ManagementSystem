package Domain;

import Data.Database;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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


    public Event(EventType type,Game game, String description) {
        this.id = "E"+IdGenerator.getNewId();
        this.type = type;
        this.date = new Date();
        this.minuteInGame = calculateMinutes(game.getDate(), date);
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

    @Override
    public String toString() {
        return "Event{" +
                ", id=" + id +
                ", type=" + type +
                ", date=" + date +
                ", minuteInGame=" + minuteInGame +
                ", description='" + description + '\'' +
                '}';
    }

    public String createMessage(){
        return date + ": " + minuteInGame + ", " + type + "- " + description;
    }


    private double calculateMinutes(Date gameDate, Date nowDate) {
        long now = (nowDate.getTime()/1000)/60;
        long gameTime = (gameDate.getTime()/1000)/60;
        long minutes = now-gameTime;
        return minutes;
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
}
