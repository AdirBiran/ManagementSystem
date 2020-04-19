package Service;

import Domain.*;
import Presentation.Guest;

import java.util.List;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;


    public UserSystem(Searcher searcher, ComplaintManager complaintManger, EditPersonalInfo editPersonalInfo,UserManagement userManagement) {
        super(searcher, userManagement);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;

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
        return fan.addPageToFollow(page);
    }

    public List<PersonalPage> getFanPages(Fan fan)
    {
        return fan.getFollowPages();
    }
    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(Fan fan, List<Game> games, ReceiveAlerts receive){
        return userManagement.registrationForGamesAlerts(fan, games, receive);
        
    }
    public boolean updateTraining(User user,String training){
        if(user instanceof Coach){
            ((Coach)user).setTraining(training);
            return true;
        }
        else if(user instanceof Referee){
            ((Referee)user).setTraining(training);
            return true;
        }
        return false;
    }

    public boolean updateRole(User user,String role){

        if(user instanceof Player){
            ((Player)user).setRole(role);
            return true;
        }
        if(user instanceof Coach){
            ((Coach)user).setRole(role);
            return true;
        }
        return false;
    }
    public String getRole(User user) {

        if (user instanceof Player) {
            return ((Player)user).getRole();
        }
        if (user instanceof Coach) {
            return ((Coach)user).getRole();
        }

        return "";
    }
}
