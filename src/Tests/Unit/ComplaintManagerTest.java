package Tests.Unit;

import Data.Database;
import Domain.ComplaintManager;
import Domain.Fan;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplaintManagerTest {

    @Test
    public void addComplaintToSystem() {
        Database database = new Database();
        ComplaintManager complaintManager = new ComplaintManager(database);
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        int sizeOfComplaints = database.getComplaints().size();
        complaintManager.addComplaintToSystem(fan, "testMessage");
        assertEquals(sizeOfComplaints+1, database.getComplaints().size(), 0);
    }

    @Test
    public void responseToComplaint() {
    }
}