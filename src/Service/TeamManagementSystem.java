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
    public boolean addAssetPlayer(User user, User asset, Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).addPlayerToTeam(asset , team);
        }
        return false;
    }

    public boolean addAssetCoach(User user, User asset, Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).addCoachToTeam(asset , team);
        }
        return false;
    }

    public boolean addAssetTeamManager(User user, User asset, Team team, double price, boolean manageAssets , boolean finance){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).addTeamManagerToTeam(asset , team, price, manageAssets, finance);
        }
        return false;
    }

    public boolean addField(User user, Field asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).addFieldToTeam(asset, team);
        }
        return false;
    }

    public boolean removeField(User user, Field asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).removeFieldFromTeam(asset, team);
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
            return ((Manager) role).removePlayerFormTeam(asset, team);
        }
        return false;
    }
    public boolean removeAssetCoach(User user, User asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).removeCoachFormTeam(asset, team);
        }
        return false;
    }
    public boolean removeAssetTeamManager(User user, User asset , Team team){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).removeTeamManagerFormTeam(asset, team);
        }
        return false;
    }

    public boolean updateAsset(User user, String assetId, String action, String update){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionManageAssets())
                return false;
            return ((Manager) role).updateAsset(assetId,action,update);
        }
        return false;
    }


    public boolean createTeam(User user , String teamName , String pageData , List<String> players, List<String> coaches, String field){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).createTeam(user , teamName ,pageData,players,coaches,field)) {
                /**need to sent  notification to all users*/
//                notificationSystem.notificationForAppointment(userToAppoint, true);
                return true;
            }
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

    public boolean appointmentTeamManager(User user,User userToAppoint, Team team, double price, boolean manageAssets , boolean finance){
        Role role = user.checkUserRole("TeamOwner");
        if(role instanceof TeamOwner){
            if (((TeamOwner) role).appointTeamManager(userToAppoint, team, price, manageAssets, finance)) {
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

}
