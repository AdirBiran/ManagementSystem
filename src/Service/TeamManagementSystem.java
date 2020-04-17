package Service;

import Domain.*;
import Domain.TeamOwner;
import Domain.User;

import java.util.List;

public class TeamManagementSystem {
    private LeagueAndGameManagement leagueAndGameManagement;
    private UserManagement userManagement;
    private NotificationSystem notificationSystem;

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

    public List<Coach> getTeamCoaches(Team team)
    {
        return team.getCoaches();
    }

    public List<Player> getTeamPlayers(Team team)
    {
        return team.getPlayers();
    }

    public List<Field> getTeamFields(Team team)
    {
        return team.getFields();
    }

    public List<TeamManager> getTeamManagers(Team team)
    {
        return team.getTeamManagers();
    }

    /*
    Remove Asset
     */
    public void removeAsset(Asset asset , Team team){
        userManagement.removeAsset(asset ,team);
    }

    public void appointmentTeamOwner(TeamOwner teamOwner , User user, Team team){
        if(userManagement.appointmentTeamOwner(teamOwner, user, team)){
            notificationSystem.notificationForAppointment(user, true);
        }

    }
    public void appointmentTeamManager(TeamOwner teamOwner, User user, Team team){
        if(userManagement.appointmentTeamManager(teamOwner, user, team)){
            notificationSystem.notificationForAppointment(user, true);
        }
    }
    public void removeAppointmentTeamOwner(TeamOwner teamOwner, User user, Team team){
        userManagement.removeAppointmentTeamOwner(teamOwner, user, team);
    }
    public void removeAppointmentTeamManager(TeamOwner teamOwner,User user, Team team){
        if(userManagement.removeAppointmentTeamManager(teamOwner, user, team)){
            notificationSystem.notificationForAppointment(user, false);
        }
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


    public boolean updateRole(User user,String role){
       return userManagement.updateRole(user, role);
    }

    public String getRole(User user){
        return userManagement.getRole(user);
    }
    public boolean updateTraining(User user,String training){
        return userManagement.updateTraining(user, training);
    }
    public void deactivateField(Field field){
        userManagement.deactivateField(field);
    }

    public boolean isActiveTeam(Team team)
    {
        return team.isActive();
    }
}
