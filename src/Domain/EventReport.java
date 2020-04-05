package Domain;

import java.util.List;
import java.util.LinkedList;

public class EventReport {

    private Game game;
    private List<Event> events;

    public EventReport(Game game) {
        this.game = game;
        this.events = new LinkedList<>();
    }
    public void addEvent(Event event){
        if(!events.contains(event))
            events.add(event);
    }
}
