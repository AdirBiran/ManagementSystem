package Domain;

import Data.Database;
import Presentation.Fan;
import java.util.Date;

public class ComplaintManager {

    private Database database;

    public ComplaintManager(Database database) {
        this.database = database;
    }

    public void addComplaintToSystem(Fan fan, String description) {
        Complaint complaint = new Complaint(new Date(), description, fan);
        database.addComplaint(complaint);
    }
}
