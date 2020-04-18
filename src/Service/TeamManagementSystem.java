package Service;

import Domain.*;
import Domain.TeamOwner;
import Domain.User;

import java.util.List;

public class TeamManagementSystem {
    private LeagueAndGameManagement leagueAndGameManagement;
    private UserManagement userManagement;
    private NotificationSystem notificationSystem;
    private AssetManagement assetManagement;

    public TeamManagementSystem(LeagueAndGameManagement leagueAndGameManagement, UserManagement userManagement,
                                NotificationSystem notificationSystem, AssetManagement assetManagement) {
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.userManagement = userManagement;
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

    public List<TeamManager> getTeamManagers(Team team)
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

    public void deactivateField(Field field){
        field.deactivate();
    }

    public boolean isActiveTeam(Team team)
    {
        return team.isActive();
    }

    public boolean closeTeam(TeamOwner teamOwner, Team team) {
        if(team.isActive()){
            teamOwner.setClosedTeam(team, true);
            team.setActive(false);
            //Removing permissions for team members
            notificationSystem.openORCloseTeam("closed", team, false);
            return true;
        }
        return false;
    }

    public boolean reopeningTeam(TeamOwner teamOwner, Team team) {
        if(!team.isActive() && !team.isPermanentlyClosed() && teamOwner.isClosedTeam(team)){
            teamOwner.setClosedTeam(team, false);
            team.setActive(true);
            notificationSystem.openORCloseTeam("open", team, false);
            //Re-configure permissions for team members
            return true;
        }
        return false;
    }
}
