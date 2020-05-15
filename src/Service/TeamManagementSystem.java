package Service;

import Domain.*;
import Domain.User;
import Logger.Logger;

import java.util.List;

public class TeamManagementSystem {

    private NotificationSystem notificationSystem;

    public TeamManagementSystem(NotificationSystem notificationSystem) {
        this.notificationSystem = notificationSystem;
    }
    /*
    this function adds a new asset to the system
     */
    public boolean addAssetPlayer(User user, User asset, Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).addPlayerToTeam(asset , team);

            if (success)
                Logger.logEvent(user.getID(),"Added Player " + asset.getID() + " to Team " + team.getName());
            else
                Logger.logError("Adding Player to team Failed");

            return success;

        }
        return false;
    }

    public boolean addAssetCoach(User user, User asset, Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).addCoachToTeam(asset , team);

            if (success)
                Logger.logEvent(user.getID(),"Added Coach " + asset.getID() + " to Team " + team.getName());
            else
                Logger.logError("Adding Coach to team Failed");

            return success;
        }
        return false;
    }

    public boolean addAssetTeamManager(User user, User asset, Team team, double price, boolean manageAssets , boolean finance){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).addTeamManagerToTeam(asset , team, price, manageAssets, finance);

            if (success)
                Logger.logEvent(user.getID(),"Added TeamManager " + asset.getID() + " to Team " + team.getName());
            else
                Logger.logError("Adding TeamManager to team Failed");

            return success;
        }
        return false;
    }

    public boolean addField(User user, Field asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).addFieldToTeam(asset, team);

            if (success)
                Logger.logEvent(user.getID(),"Added Field " + asset.getID() + " to Team " + team.getName());
            else
                Logger.logError("Adding Field to team Failed");

            return success;
        }
        return false;
    }

    public boolean removeField(User user, Field asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).removeFieldFromTeam(asset, team);

            if (success)
                Logger.logEvent(user.getID(),"Removed Field " + asset.getID() + " from Team " + team.getName());
            else
                Logger.logError("Removing Field from team Failed");

            return success;
        }
        return false;
    }
    /*
    Remove PartOfATeam
     */
    public boolean removeAssetPlayer(User user, User asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).removePlayerFormTeam(asset, team);

            if (success)
                Logger.logEvent(user.getID(),"Removed Player " + asset.getID() + " from Team " + team.getName());
            else
                Logger.logError("Removing Player from team Failed");

            return success;
        }
        return false;
    }
    public boolean removeAssetCoach(User user, User asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).removeCoachFormTeam(asset, team);

            if (success)
                Logger.logEvent(user.getID(),"Removed Coach " + asset.getID() + " from Team " + team.getName());
            else
                Logger.logError("Removing Coach from team Failed");

            return success;
        }
        return false;
    }
    public boolean removeAssetTeamManager(User user, User asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).removeTeamManagerFormTeam(asset, team);

            if (success)
                Logger.logEvent(user.getID(),"Removed TeamManager " + asset.getID() + " from Team " + team.getName());
            else
                Logger.logError("Removing TeamManager from team Failed");

            return success;
        }
        return false;
    }

    public boolean updateAsset(User user, String assetId, String action, String update){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            boolean success = ((Manager) role).updateAsset(assetId,action,update);

            if (success)
                Logger.logEvent(user.getID(),"Updated asset " + assetId);
            else
                Logger.logError("Updating Asset Failed");

            return success;
        }
        return false;
    }


    public boolean createTeam(User user , String teamName, List<User> players, List<User> coaches, Field field){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).createTeam(user , teamName ,players,coaches,field)) {
                Logger.logEvent(user.getID(),"Created Team " + teamName);

                return true;
            }
        }

        Logger.logError("Failed Creating Team");

        return false;
    }

    public boolean appointmentTeamOwner(User user, User userToAppoint, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).appointTeamOwner(userToAppoint, team)) {
                notificationSystem.notificationForAppointment(userToAppoint, true);
                Logger.logEvent(user.getID(),"Appointed Team Owner " + userToAppoint.getID());
                return true;
            }
        }

        Logger.logError("Failed Appointing Team Owner");

        return false;
    }

    public boolean appointmentTeamManager(User user,User userToAppoint, Team team, double price, boolean manageAssets , boolean finance){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).appointTeamManager(userToAppoint, team, price, manageAssets, finance)) {
                notificationSystem.notificationForAppointment(userToAppoint, true);
                Logger.logEvent(user.getID(),"Appointed Team Manager " + userToAppoint.getID());

                return true;
            }
        }
        Logger.logError("Failed Appointing Team Manager");

        return false;
    }

    public boolean removeAppointmentTeamOwner(User user,User userToRemove, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).removeAppointTeamOwner(userToRemove, team)) {
                notificationSystem.notificationForAppointment(userToRemove, false);
                Logger.logEvent(user.getID(),"Removed Team Owner " + userToRemove.getID());
                return true;
            }
        }
        Logger.logError("Failed removing appointed Team Owner");

        return false;
    }

    public boolean removeAppointmentTeamManager(User user,User userToRemove, Team team){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).removeAppointTeamManager(userToRemove, team)) {
                notificationSystem.notificationForAppointment(userToRemove, false);
                Logger.logEvent(user.getID(),"Removed Team Manager " + userToRemove.getID());
                return true;
            }
        }

        Logger.logError("Failed removing appointed Team Manager");

        return false;

    }

    public boolean closeTeam(User user, Team team) {
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).closeTeam(team)) {
                notificationSystem.openORCloseTeam("closed", team, false);
                Logger.logEvent(user.getID(),"Closed Team " + team.getName());
                return true;
            }
        }

        Logger.logError("Failed closing Team");

        return false;
    }

    public boolean reOpeningTeam(User user, Team team) {
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).reopenTeam(team)) {
                notificationSystem.openORCloseTeam("open", team, false);
                Logger.logEvent(user.getID(),"Reopened Team " + team.getName());
                return true;
            }
        }
        Logger.logError("Failed reopening Team");
        return false;
    }

}
