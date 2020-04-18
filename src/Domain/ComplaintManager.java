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
    public boolean addComplaintToSystem(Fan fan, String description) {
        if(!description.equals("") ) {
            Complaint complaint = new Complaint(new Date(), description, fan);
            database.addComplaint(complaint);
            return true;
        }
        return false;
    }

    public void responseToComplaint(Admin admin, Complaint complaint) {

    }
}
