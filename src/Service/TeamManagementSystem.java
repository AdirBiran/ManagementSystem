package Service;

import Domain.Authorization.AuthorizationRole;
import Domain.Authorization.TeamOwnerAuthorization;
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
    public boolean addAsset(User user, Asset asset , Team team){
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.addAssetToTeam(asset, team);
        }
        return false;
    }

    public List<Coach> getTeamCoaches(User user,Team team)
    {
        if(getAuthorization(user)!=null)
            return team.getCoaches();
        return null;
    }

    public List<Player> getTeamPlayers(User user,Team team)
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

    /*
    Remove Asset
     */
    public boolean removeAsset(User user,Asset asset , Team team){
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.removeAssetFromTeam(asset, team);
        }
        return false;
    }

    public boolean appointmentTeamOwner(User user, User userToAppoint, Team team){
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null) {
            if (authorization.appointTeamOwner(userToAppoint, team)) {
                    notificationSystem.notificationForAppointment(userToAppoint, true);
                    return true;
            }
        }
        return false;
    }


    public boolean appointmentTeamManager(User user,User userToAppoint, Team team){
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null) {
            if (authorization.appointTeamManager(userToAppoint, team)) {
                notificationSystem.notificationForAppointment(userToAppoint, true);
                return true;
            }
        }
        return false;
    }

    public boolean removeAppointmentTeamOwner(User user,User userToRemove, Team team){
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null) {
            if(authorization.removeAppointTeamOwner(userToRemove, team)) {
                notificationSystem.notificationForAppointment(userToRemove, false);
                return true;
            }
        }
        return false;
    }
    public boolean removeAppointmentTeamManager(User user,User userToRemove, Team team){
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null) {
            if(authorization.removeAppointTeamManager(userToRemove, team)){
                notificationSystem.notificationForAppointment(userToRemove, false);
                return true;
            }
        }
        return false;
    }

    public void deactivateField(User user,Field field){
        if(getAuthorization(user)!=null) {
            field.deactivate();
        }
    }


    public boolean closeTeam(User user, Team team) {
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null) {
            if(authorization.closeTeam(team)) {
                notificationSystem.openORCloseTeam("closed", team, false);
                return true;
            }
        }
        return false;
    }

    public boolean reopeningTeam(User user, Team team) {
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if(authorization!=null) {
            if(authorization.reopenTeam(team)){
                notificationSystem.openORCloseTeam("open", team, false);
                return true;
            }
        }
        return false;

    }

    private TeamOwnerAuthorization getAuthorization(User user) {
        for(AuthorizationRole role : user.getRoles()){
            if(role instanceof TeamOwnerAuthorization)
                return (TeamOwnerAuthorization)role;
        }
        return null;
    }

    public boolean isActiveTeam(Team team) {
        return team.isActive();
    }
}
