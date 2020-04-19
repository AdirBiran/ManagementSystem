package Acceptance;

import Domain.Complaint;
import Domain.Fan;
import Service.FootballManagementSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class ComplaintTest {

    private FootballManagementSystem system;
    private UserSystem userSystem;


    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        userSystem = system.getUserSystem();
    }

    @Test
    public void invalidComplaint_13()
    {
        Fan fan = new Fan("a@b.com", "AAA", "BBB", "0123456789", "Israel");
        String invalidDescription = "";
        userSystem.addComplaint(fan, invalidDescription);

        boolean found = false;

        HashSet<Complaint> complaints = system.getDatabase().getComplaints();

        for (Complaint comp : complaints)
            if (comp.getDescription().equals(invalidDescription))
                found = true;

        assertFalse(found);

    }

    @Test
    public void validComplaint_14()
    {
        Fan fan = new Fan("a@b.com", "AAA", "BBB", "0123456789", "Israel");
        String validDescription = "valid complaint";
        userSystem.addComplaint(fan, validDescription);

        boolean found = false;

        HashSet<Complaint> complaints = system.getDatabase().getComplaints();

        for (Complaint comp : complaints)
            if (comp.getDescription().equals(validDescription))
                found = true;

        assertTrue(found);
    }
}
