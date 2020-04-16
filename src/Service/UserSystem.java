package Service;

import Domain.*;
import Presentation.*;
import java.util.List;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;
    private PersonalPageManagement personalPageManagement;
    private LeagueAndGameManagement leagueAndGameManagement;

    public UserSystem(Searcher searcher, ComplaintManager complaintManger, EditPersonalInfo editPersonalInfo,
                      PersonalPageManagement personalPageManagement,UserManagement userManagement,
                      LeagueAndGameManagement leagueAndGameManagement,
                      NotificationSystem notificationSystem) {
        super(searcher, userManagement);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;
        this.personalPageManagement = personalPageManagement;
        this.leagueAndGameManagement = leagueAndGameManagement;
    }
    /*
    this function adds a new user to the system
    */
    public void addUser(String id, String password, User user) {
        userManagement.addUser(id, password, user);
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
    Edit fan personal information
     */
    public void editPersonalDetails(Fan fan, String password, String firstName, String lastName, String phone,
                                    String address){
        editPersonalInfo.editPersonalDetails(fan, firstName, lastName, phone, address,  password);
    }
    /*
    Edit user personal information
     */
    public void editPersonalInfo(User user, String firstName, String lastName, String password){
        editPersonalInfo.editPersonalDetails(user, firstName, lastName, password);
    }
    /*
    user adds a complaint to the system
     */
    public void addComplaint(Fan fan, String description){
        complaintManger.addComplaintToSystem(fan, description);
    }

    public boolean registrationToFollowUp(Fan fan, PersonalPage page){
        return userManagement.registrationToFollowUp(fan, page);
    }
    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(Fan fan, List<Game> games, ReceiveAlerts receive){
        return leagueAndGameManagement.registrationForGamesAlerts(fan, games, receive);
        
    }
}
