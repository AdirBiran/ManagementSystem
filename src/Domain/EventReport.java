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

    public EventReport(String id, List<Event> events)
    {
        this.id = id;
        this.events = events;
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

    public Event gerEventById(String eventID){
        for (Event e:events){
            if(e.getId().equals(eventID))
                return e;
        }
        return null;
    }

    public String getEventsId(){
        String listOfId = "";
        for (Event event: events) {
            if(listOfId.equals("")){
                listOfId = listOfId + event.getId();
            }
            else {
                listOfId = listOfId + ","+event.getId();
            }
        }
        return listOfId;
    }
}
