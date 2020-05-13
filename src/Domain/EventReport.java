package Domain;

import java.util.List;
import java.util.LinkedList;

public class EventReport {

    private String id;
    private List<Event> events;

    public EventReport() {
        this.id = "ER"+IdGenerator.getNewId();
        this.events = new LinkedList<>();
    }


    public void addEvent(Event event){
        if(!events.contains(event))
            events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getId() {
        return id;
    }
}
