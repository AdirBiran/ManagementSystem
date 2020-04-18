package Tests.Acceptance;

import Domain.*;
import Service.FootballManagementSystem;
import Service.GuestSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SearchHistoryTest {


    private FootballManagementSystem system;
    private UserSystem userSystem;
    private GuestSystem guestSystem;
    private Fan fan;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        userSystem = system.getUserSystem();
        guestSystem = system.getGuestSystem();

        system.dataReboot();

        fan = new Fan("a@b.com", "AAA", "BBB", "0123456789", "Israel");

    }

    @Test
    public void viewSearchHistory_20()
    {

        String searchWord = "Search Test";
        guestSystem.search(fan, searchWord);
        List<String> searchHistory = userSystem.viewSearchHistory(fan);

        boolean flag = false;

        if (searchHistory.contains(searchWord))
            flag = true;

        assertTrue(flag);

    }

    public void viewSearchHistory_21()
    {

        String searchWord = "Search Test";
        List<String> searchHistory = userSystem.viewSearchHistory(fan);

        boolean flag = false;

        if (searchHistory.contains(searchWord))
            flag = true;

        assertTrue(flag);
        assertTrue(false);

        // cant be implemented, need to check 6 months before

    }
}
