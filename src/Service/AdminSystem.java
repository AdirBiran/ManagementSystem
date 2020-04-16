package Service;

import Domain.*;
import Presentation.Admin;
import Presentation.User;

public class AdminSystem {

    private LeagueAndGameManagement leagueAndGameManagement;
    private UserManagement userManagement;
    private NotificationSystem notificationSystem;
    private ComplaintManager complaintManager;

    public AdminSystem(LeagueAndGameManagement leagueAndGameManagement, UserManagement userManagement, NotificationSystem notificationSystem, ComplaintManager complaintManager) {
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.userManagement = userManagement;
        this.notificationSystem = notificationSystem;
        this.complaintManager = complaintManager;
    }

    /*
        Remove user by an administrator
        */
    public void removeUser(String userId){
        String userMail = userManagement.removeUser(userId);
        notificationSystem.UserRemovalNotification(userMail);
    }

    /*
    this function adds a new user to the system
    */
    public void addUser(String password, User user) {
        userManagement.addUser(password, user);
    }

    /*
Permanently close a group only by an administrator
 */
    public boolean permanentlyCloseTeam(Team team){
        if(leagueAndGameManagement.permanentlyCloseTeam(team)){
            notificationSystem.openORCloseTeam("closed", team, true);
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void responseToComplaint(Admin admin, Complaint complaint)
    {
        complaintManager.responseToComplaint(admin,complaint);
    }
    /**
     *
     */
    public void viewLog()
    {

    }

    /**
     *
     */
    public void trainModel()
    {

    }
}
