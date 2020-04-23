package Acceptance;

import Data.Database;
import Domain.Complaint;
import Domain.Fan;
import Domain.User;
import Domain.UserFactory;
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
        Object[] fan = UserFactory.getNewFan("Aa1323","AAA", "BBB","a@b.com", "0123456789", "Israel");
        String invalidDescription = "";
        if(fan!=null){
            userSystem.addComplaint((User)fan[0], invalidDescription);
        }

        boolean found = false;

        HashSet<Complaint> complaints = Database.getComplaints();

        for (Complaint comp : complaints)
            if (comp.getDescription().equals(invalidDescription))
                found = true;

        assertFalse(found);

    }

    @Test
    public void validComplaint_14()
    {
        Object[] fan = UserFactory.getNewFan("Aa12234", "AAA", "BBB","a@b.com" ,"0123456789", "Israel");
        String validDescription = "valid complaint";
        userSystem.addComplaint((User)fan[0], validDescription);

        boolean found = false;

        HashSet<Complaint> complaints = Database.getComplaints();

        for (Complaint comp : complaints)
            if (comp.getDescription().equals(validDescription))
                found = true;

        assertTrue(found);
    }
}
