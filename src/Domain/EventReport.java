package Domain;

import java.util.List;
import java.util.LinkedList;

public class EventReport {

    private List<Event> events;

    public EventReport() {
        this.events = new LinkedList<>();
    }


    public void addEvent(Event event){
        if(!events.contains(event))
            events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

}
