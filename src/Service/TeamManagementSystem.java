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
    public boolean addAsset(User user, User asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            return ((Manager) role).addAssetToTeam(asset , team);
        }
        return false;
    }

    public boolean addField(User user, Field asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            return ((Manager) role).addFieldToTeam(asset, team);
        }
        return false;
    }

    public boolean removeField(User user, Field asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            return ((Manager) role).removeFieldFromTeam(asset, team);
        }
        return false;
    }
    /*
    Remove PartOfATeam
     */
    public boolean removeAsset(User user, User asset , Team team)
    {
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            return ((Manager) role).removeAssetFromTeam(asset, team);
        }
        return false;
    }

    public boolean appointmentTeamOwner(User user, User userToAppoint, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).appointTeamOwner(userToAppoint, team)) {
                notificationSystem.notificationForAppointment(userToAppoint, true);
                return true;
            }
        }
        return false;
    }



    public boolean appointmentTeamManager(User user,User userToAppoint, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).appointTeamManager(userToAppoint, team)) {
                notificationSystem.notificationForAppointment(userToAppoint, true);
                return true;
            }
        }
        return false;
    }

    public boolean removeAppointmentTeamOwner(User user,User userToRemove, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).removeAppointTeamOwner(userToRemove, team)) {
                notificationSystem.notificationForAppointment(userToRemove, false);
                return true;
            }
        }
        return false;

    }

    public boolean removeAppointmentTeamManager(User user,User userToRemove, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).removeAppointTeamManager(userToRemove, team)) {
                notificationSystem.notificationForAppointment(userToRemove, false);
                return true;
            }
        }
        return false;

    }

   /* public void deactivateField(TeamOwner user,Field field){
        if(getAuthorization(user)!=null) {
            field.deactivate();
        }
    }*/


    public boolean closeTeam(User user, Team team) {
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).closeTeam(team)) {
                notificationSystem.openORCloseTeam("closed", team, false);
                return true;
            }
        }
        return false;
    }

    public boolean reOpeningTeam(User user, Team team) {
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).reopenTeam(team)) {
                notificationSystem.openORCloseTeam("open", team, false);
                return true;
            }
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
