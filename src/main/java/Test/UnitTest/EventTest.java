package UnitTest;

import Domain.Event;
import Domain.EventReport;
import Domain.LeagueInSeason;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventTest {


    FootballManagementSystem system;
    EventReport eventReport;
    Event event;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        eventReport = new EventReport();
        event = new Event(Event.EventType.Foul , 30 ,"data");

    }
    @Test
    public void getType() {
        assertNotNull(event.getType());
        assertEquals(Event.EventType.Foul,event.getType());
    }

    @Test
    public void getDate() {
        assertNotNull(event.getDate());
    }

    @Test
    public void getMinuteInGame() {
        assertNotNull(event.getMinuteInGame());
        assertEquals(30,event.getMinuteInGame(),1);
    }

    @Test
    public void getDescription() {
        assertNotNull(event.getDescription());
        assertEquals("data",event.getDescription());
    }

    @Test
    public void setMinuteInGame() {
        event.setMinuteInGame(40);
        assertEquals(40,event.getMinuteInGame(),1);
    }

    @Test
    public void setDescription() {

        event.setDescription("Description");
        assertEquals("Description",event.getDescription());
    }

    @Test
    public void getId() {
        assertNotNull(event.getId());
    }
}