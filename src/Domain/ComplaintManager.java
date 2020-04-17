package Domain;

import Data.Database;

import java.util.Date;

public class ComplaintManager {

    private Database database;

    public ComplaintManager(Database database) {
        this.database = database;
    }

    /*
    user adds a complaint to the system
     */
    public void addComplaintToSystem(Fan fan, String description) {
        Complaint complaint = new Complaint(new Date(), description, fan);
        database.addComplaint(complaint);
    }

    public void responseToComplaint(Admin admin, Complaint complaint) {

    }
}
