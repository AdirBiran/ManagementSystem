package Unit;

import Domain.Fan;
import Domain.Searcher;
import Service.FootballManagementSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearcherTest {

    @Test
    public void searchInfo() {
        FootballManagementSystem system;
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        Searcher searcher= new Searcher(system.getDatabase());
        assertNotEquals(0,searcher.searchInfo(fan,"team0").size(),0);
    }

}