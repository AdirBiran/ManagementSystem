package Service;

import Domain.*;
import Domain.TeamOwner;
import Domain.User;

import java.util.List;

public class TeamManagementSystem {

    private NotificationSystem notificationSystem;
    private AssetManagement assetManagement;

    public TeamManagementSystem(NotificationSystem notificationSystem, AssetManagement assetManagement) {
        this.notificationSystem = notificationSystem;
        this.assetManagement = assetManagement;
    }
    /*
    this function adds a new asset to the system
     */
    public void addAsset(Asset asset , Team team){

        assetManagement.addAsset(asset , team);
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

    public List<User> getTeamManagers(Team team)
    {
        return team.getTeamManagers();
    }

    /*
    Remove Asset
     */
    public void removeAsset(Asset asset , Team team){
        assetManagement.removeAsset(asset ,team);
    }

    public void appointmentTeamOwner(TeamOwner teamOwner , User user, Team team){
        if(teamOwner.appointmentTeamOwner( user, team)){
            notificationSystem.notificationForAppointment(user, true);
        }

    }
    public void appointmentTeamManager(TeamOwner teamOwner, User user, Team team){
        if(teamOwner.appointmentTeamManager( user, team)){
            notificationSystem.notificationForAppointment(user, true);
        }
    }
    public void removeAppointmentTeamOwner(TeamOwner teamOwner, User user, Team team){
        teamOwner.removeAppointmentTeamOwner( user, team);
    }
    public void removeAppointmentTeamManager(TeamOwner teamOwner,User user, Team team){
        if(teamOwner.removeAppointmentTeamManager(user, team)){
            notificationSystem.notificationForAppointment(user, false);
        }
    }

    public void deactivateField(Field field){
        field.deactivate();
    }

    public boolean isActiveTeam(Team team)
    {
        return team.isActive();
    }

    public boolean closeTeam(TeamOwner teamOwner, Team team) {
        if(teamOwner.closeTeam(team)){
            notificationSystem.openORCloseTeam("closed", team, false);
            return true;
        }
        return false;
    }

    public boolean reopeningTeam(TeamOwner teamOwner, Team team) {
        if(teamOwner.reopeningTeam(team)){
            notificationSystem.openORCloseTeam("open", team, false);

            return true;
        }
        return false;
    }
}
