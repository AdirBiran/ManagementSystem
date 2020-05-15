package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventReportTest {

    FootballManagementSystem system;
    EventReport eventReport;
    Event event;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        eventReport = new EventReport();
        event = new Event(Event.EventType.Foul , 30 ,"data" );

    }


    @Test
    public void addEvent() {
        assertEquals(0,eventReport.getEvents().size());
        eventReport.addEvent(event);
        assertEquals(1,eventReport.getEvents().size());
    }

    @Test
    public void getEvents() {
        assertNotNull(eventReport.getEvents());
    }

    @Test
    public void getId(){
        assertNotNull(eventReport.getId());
    }
}