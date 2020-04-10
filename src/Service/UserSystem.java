package Service;

import Domain.*;
import Presentation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;
    private PersonalPageManagement personalPageManagement;
    private UserManagement userManagement;

    public UserSystem(Searcher searcher, ComplaintManager complaintManger, EditPersonalInfo editPersonalInfo,
                      PersonalPageManagement personalPageManagement,UserManagement userManagement) {
        super(searcher, userManagement);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;
        this.personalPageManagement = personalPageManagement;
    }

    public List<String> viewSearchHistory(Fan fan){
        return searcher.viewSearchHistory(fan);
    }
    public Guest logOut(){
        return new Guest();

    }
    public void viewPersonalDetails(User user){
        user.toString(); // toString by user!?!?

    }
    public void editPersonalDetails(User user, String password, String firstName, String lastName, String phone,
                                    String address){
        userManagement.editPersonalDetails(user, firstName, lastName, phone, address,  password);
    }
    public void addComplaint(Fan fan, String description){
        complaintManger.addComplaintToSystem(fan, description);
    }
    /*
    this function adds a new user to the system
     */
    public void addUser(String id, String password, User user) {
        userManagement.addUser(id, password, user);
    }
}
