package Service;

import Domain.Asset;
import Domain.LeagueAndGameManagement;
import Domain.Team;
import Domain.UserManagement;
import Presentation.TeamOwner;
import Presentation.User;

public class TeamManagementSystem {
    private LeagueAndGameManagement leagueAndGameManagement;
    private UserManagement userManagement;
    private NotificationSystem notificationSystem; //??

    public TeamManagementSystem(LeagueAndGameManagement leagueAndGameManagement, UserManagement userManagement,
                                NotificationSystem notificationSystem) {
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.userManagement = userManagement;
        this.notificationSystem = notificationSystem;
    }
    /*
    this function adds a new asset to the system
     */
    public void addAsset(Asset asset , Team team){

        userManagement.addAsset(asset , team);
    }
    /*
    Remove user by an administrator
    */
    public void removeUser(String userId){
        String userMail = userManagement.removeUser(userId);
        notificationSystem.UserRemovalNotification(userMail);
    }


    /*
    Remove Asset
     */
    public void removeAsset(Asset asset , Team team){
        userManagement.removeAsset(asset ,team);
    }

    public void appointmentTeamOwner(TeamOwner teamOwner , User user, Team team){
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
    Closing a team by the team owner
     */
    public boolean closeTeam(TeamOwner teamOwner, Team team){
        if(leagueAndGameManagement.closeTeam(teamOwner, team)){
            notificationSystem.openORCloseTeam("closed", team, false);
            return true;
        }
        return false;
    }
    /*
    Re-opening a team by the team owner that it closed
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
