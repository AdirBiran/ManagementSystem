package Service;

import Domain.*;
import Presentation.*;
import java.util.List;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;
    private PersonalPageManagement personalPageManagement;

    public UserSystem(Searcher searcher, ComplaintManager complaintManger, EditPersonalInfo editPersonalInfo,
                      PersonalPageManagement personalPageManagement,UserManagement userManagement) {
        super(searcher, userManagement);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;
        this.personalPageManagement = personalPageManagement;
    }
    /*
    View fan search history
     */
    public List<String> viewSearchHistory(Fan fan){
        return searcher.viewSearchHistory(fan);
    }
    /*
    log out user from system
     */
    public Guest logOut(){
        return new Guest();
    }
    /*
    View user's personal information
     */
    public void viewPersonalDetails(User user){
        user.toString(); // toString by user!?!?
    }
    /*
    Edit user personal information
     */
    public void editPersonalDetails(User user, String password, String firstName, String lastName, String phone,
                                    String address){
        userManagement.editPersonalDetails(user, firstName, lastName, phone, address,  password);
    }
    /*
    user adds a complaint to the system
     */
    public void addComplaint(Fan fan, String description){
        complaintManger.addComplaintToSystem(fan, description);
    }
    /*
    this function adds a new user to the system
     */
    public void addUser(String id, String password, User user) {
        userManagement.addUser(id, password, user);
    }

    /*
    Remove user
     */
    public void removeUser(String userId){
        userManagement.removeUser(userId);
    }

    /*
    this function adds a new asset to the system
     */
    public void addAsset(Asset asset , Team team){

        userManagement.addAsset(asset , team);
    }

    /*
    Remove Asset
     */
    public void removeAsset(Asset asset , Team team){
        userManagement.removeAsset(asset ,team);
    }

    public void appointmentTeamOwner(TeamOwner teamOwner ,User user, Team team){
        userManagement.appointmentTeamOwner(teamOwner, user, team);
    }
    public void appointmentTeamManager(TeamOwner teamOwner, User user, Team team){
        userManagement.appointmentTeamManager(teamOwner, user, team);
    }
    public void removeAppointmentTeamOwner(TeamOwner teamOwner, User user, Team team){
        userManagement.removeAppointmentTeamOwner(teamOwner, user, team);
    }
    public void removeAppointmentTeamManager(TeamOwner teamOwner,User user, Team team){
        userManagement.removeAppointmentTeamManager(teamOwner, user, team);
    }


}
