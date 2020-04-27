package Service;

import Domain.*;
import Domain.User;

import java.util.List;

public class TeamManagementSystem {

    private NotificationSystem notificationSystem;

    public TeamManagementSystem(NotificationSystem notificationSystem) {
        this.notificationSystem = notificationSystem;
    }
    /*
    this function adds a new asset to the system
     */
    public boolean addAsset(Manager user, User asset , Team team){
            return user.addAssetToTeam(asset, team);
    }

    public boolean addField(Manager user, Field asset , Team team){
        return user.addFieldToTeam(asset, team);
    }

    public boolean removeField(Manager user, Field asset , Team team){
        return user.removeFieldFromTeam(asset, team);
    }
    /*
    Remove PartOfATeam
     */
    public boolean removeAsset(Manager user, User asset , Team team){
        return user.removeAssetFromTeam(asset, team);
    }

    public boolean appointmentTeamOwner(TeamOwner user, User userToAppoint, Team team){
        if (user.appointTeamOwner(userToAppoint, team)) {
            notificationSystem.notificationForAppointment(userToAppoint, true);
            return true;
        }
        return false;
    }



    public boolean appointmentTeamManager(TeamOwner user,User userToAppoint, Team team){
        if (user.appointTeamManager(userToAppoint, team)) {
            notificationSystem.notificationForAppointment(userToAppoint, true);
            return true;
        }
        return false;
    }

    public boolean removeAppointmentTeamOwner(TeamOwner user,User userToRemove, Team team){
        if(user.removeAppointTeamOwner(userToRemove, team)) {
            notificationSystem.notificationForAppointment(userToRemove, false);
            return true;
        }
        return false;
    }
    public boolean removeAppointmentTeamManager(TeamOwner user,User userToRemove, Team team){
        if(user.removeAppointTeamManager(userToRemove, team)) {
            notificationSystem.notificationForAppointment(userToRemove, false);
            return true;
        }
        return false;
    }

   /* public void deactivateField(TeamOwner user,Field field){
        if(getAuthorization(user)!=null) {
            field.deactivate();
        }
    }*/


    public boolean closeTeam(TeamOwner user, Team team) {
        if(user.closeTeam(team)) {
            notificationSystem.openORCloseTeam("closed", team, false);
            return true;
        }
        return false;
    }

    public boolean reopeningTeam(TeamOwner user, Team team) {
        if(user.reopenTeam(team)){
            notificationSystem.openORCloseTeam("open", team, false);
            return true;
        }
        return false;
    }
    /*
    public List<User> getTeamCoaches(User user,Team team)
    {
        if(getAuthorization(user)!=null)
            return team.getCoaches();
        return null;
    }

    public List<User> getTeamPlayers(User user,Team team)
    {
        if(getAuthorization(user)!=null)
            return team.getPlayers();
        return null;
    }

    public List<Field> getTeamFields(User user,Team team)
    {
        if(getAuthorization(user)!=null)
            return team.getFields();
        return null;
    }

    public List<User> getTeamManagers(User user,Team team)
    {
        if(getAuthorization(user)!=null)
            return team.getTeamManagers();
        return null;
    }


    public boolean isActiveTeam(Team team) {
        return team.isActive();
    }

    private Manager getAuthorization(User user) {
        for(Role role : user.getRoles()){
            if(role instanceof Manager)
                return (Manager)role;
        }
        return null;
    }

    private TeamOwner getTeamOwnerAuthorization(User user) {

        for(Role role : user.getRoles()){
            if(role instanceof TeamOwner)
                return (TeamOwner)role;
        }
        return null;
    }
     */
}
