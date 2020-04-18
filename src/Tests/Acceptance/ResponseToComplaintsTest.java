package Tests.Acceptance;

import Domain.Complaint;
import Service.FootballManagementSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class ResponseToComplaintsTest {


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
    public void responseToComplaint_48()
    {
        HashSet<Complaint> complaints = system.getDatabase().getComplaints();

        assertEquals(0, complaints.size());

    }

    @Test
    public void responseToComplaint_49()
    {

        // cant be implemented yet

    }

    @Test
    public void responseToComplaint_50()
    {

        // cant be implemented yet

    }

    @Test
    public void responseToComplaint_51()
    {

        // cant be implemented yet

    }
}
