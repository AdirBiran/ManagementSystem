package Service;

import Domain.*;
import Presentation.Admin;
import Presentation.FootballManagementSystem;
import Presentation.User;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;
    private PersonalPageManagement personalPageManagement;
    private UserManagement userManagement;

    public UserSystem(Searcher searcher, ComplaintManager complaintManger,
                      EditPersonalInfo editPersonalInfo, PersonalPageManagement personalPageManagement,UserManagement userManagement) {
        super(searcher);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;
        this.personalPageManagement = personalPageManagement;
        this.userManagement = userManagement;
    }

    public void viewSearchHistory(){

    }
    public void logOut(){

    }
    public void viewPersonalDetails(){

    }
    public void editPersonalDetails(){

    }
    /*
    this function adds a new user to the system
     */
    public void addUser(String id, String password, User user) {
        userManagement.addUser(id, password, user);
    }
}
