package Acceptance;

import Domain.*;
import Service.FootballManagementSystem;
import Service.GuestSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SearchHistoryTest {


    private FootballManagementSystem system;
    private UserSystem userSystem;
    private GuestSystem guestSystem;

    private User fanUser;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        userSystem = system.getUserSystem();
        guestSystem = system.getGuestSystem();

        system.dataReboot();

        Object[] fanObj = UserFactory.getNewFan("aA12345", "AAA", "BBB", "a@b.com", "0123456789", "Israel");
        assertNotNull(fanObj);
        fanUser = (User)fanObj[0];



    }

    @Test
    public void viewSearchHistory_20()
    {

        String searchWord = "Search Test";
        userSystem.search(fanUser, searchWord);
        List<String> searchHistory = userSystem.viewSearchHistory(fanUser);

        boolean flag = false;

        if (searchHistory.contains(searchWord))
            flag = true;

        assertTrue(flag);

    }

    public void viewSearchHistory_21()
    {

        String searchWord = "Search Test";
        List<String> searchHistory = userSystem.viewSearchHistory(fanUser);

        boolean flag = false;

        if (searchHistory.contains(searchWord))
            flag = true;

        assertTrue(flag);
        assertTrue(false);

        // cant be implemented, need to check 6 months before

    }
}
