package Service;

import Domain.*;
import Presentation.*;
import java.util.List;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;
    private PersonalPageManagement personalPageManagement;
    private LeagueAndGameManagement leagueAndGameManagement;
    private NotificationSystem notificationSystem;

    public UserSystem(Searcher searcher, ComplaintManager complaintManger, EditPersonalInfo editPersonalInfo,
                      PersonalPageManagement personalPageManagement,UserManagement userManagement,
                      LeagueAndGameManagement leagueAndGameManagement,
                      NotificationSystem notificationSystem) {
        super(searcher, userManagement);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;
        this.personalPageManagement = personalPageManagement;
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.notificationSystem = notificationSystem;

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
    this function adds a new user to the system
     */
    public void addUser(String id, String password, User user) {
        userManagement.addUser(id, password, user);
    }
    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(Fan fan, List<Game> games, ReceiveAlerts receive){
        return leagueAndGameManagement.registrationForGamesAlerts(fan, games, receive);
        
    }
    /*
    Remove user by an administrator
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
    /*

     */
    public boolean closeTeam(TeamOwner teamOwner, Team team){
        if(leagueAndGameManagement.closeTeam(teamOwner, team)){
            notificationSystem.openORCloseTeam("closed", team, false);
            return true;
        }
        return false;
    }
    /*

     */
    public boolean reopeningTeam(TeamOwner teamOwner, Team team){
        if(leagueAndGameManagement.reopeningTeam(teamOwner, team)){
            notificationSystem.openORCloseTeam("open", team, false);
            return true;
        }
        return false;
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
}
