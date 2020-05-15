package Service;

import Domain.*;
import Domain.User;
import Logger.Logger;
import java.util.Date;
import java.util.List;

public class AdminSystem {

    private NotificationSystem notificationSystem;

    public AdminSystem(NotificationSystem notificationSystem) {
        this.notificationSystem = notificationSystem;
    }

    /*
    Remove user by an administrator
    */
    public boolean removeUser(String adminId, String userId){
        User user = UserFactory.getUser(adminId);
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            String userMail = ((Admin)adminRole).removeUser(userId);
            if(!(userMail.equals(""))) {
                notificationSystem.UserRemovalNotification(userMail);
                Logger.logEvent(user.getID() + " (Admin)", "Removed user " + userId);
                return true;
            }
        }
        return false;
    }

    public User addNewPlayer(String adminId, String firstName, String lastName, String mail, Date birthDate, String role, double price){
        User user = UserFactory.getUser(adminId);
        if(user!=null){
            Role adminRole = user.checkUserRole("Admin");
            if(adminRole instanceof Admin) {
                Player.RolePlayer rolePlayer = Player.RolePlayer.valueOf(role);
                if (rolePlayer != null) {
                    User playerAdded = ((Admin) adminRole).addNewPlayer(firstName, lastName, mail, birthDate, rolePlayer, price);
                    Logger.logEvent(user.getID() + " (Admin)", "Added player " + playerAdded.getID());
                    return playerAdded;
                }
            }
        }
        return null;
    }
    public User addNewCoach(String adminId, String firstName, String lastName, String mail, String training, String role, double price){
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                Coach.TrainingCoach trainingCoach = Coach.TrainingCoach.valueOf(training);
                Coach.RoleCoach roleCoach = Coach.RoleCoach.valueOf(role);
                User coachAdded = ((Admin) adminRole).addNewCoach(firstName, lastName, mail, trainingCoach, roleCoach, price);
                Logger.logEvent(user.getID() + " (Admin)", "Added coach " + coachAdded.getID());
                return coachAdded;
            }
        }
        return null;
    }
    public User addNewTeamOwner(String adminId,String firstName, String lastName, String mail){
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                User teamOwnerAdded = ((Admin) adminRole).addNewTeamOwner(firstName, lastName, mail);
                Logger.logEvent(user.getID() + " (Admin)", "Added Team owner " + teamOwnerAdded.getID());
                return teamOwnerAdded;
            }
        }
        return null;
    }
    public User addNewTeamManager(String adminId,String firstName, String lastName, String mail, double price,boolean manageAssets , boolean finance){
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                User managerAdded = ((Admin) adminRole).addNewTeamManager(firstName, lastName, mail, price, manageAssets, finance);
                Logger.logEvent(user.getID() + " (Admin)", "Added Team manager " + managerAdded.getID());
                return managerAdded;
            }
        }
        return null;
    }
    public User addNewUnionRepresentative(String adminId,String firstName, String lastName, String mail){
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                User representetiveAdded = ((Admin) adminRole).addNewUnionRepresentative(firstName, lastName, mail);
                Logger.logEvent(user.getID() + " (Admin)", "Added Union Representetive " + representetiveAdded.getID());
                return representetiveAdded;
            }
        }
        return null;
    }

    public User addNewAdmin(String adminId,String password ,String firstName, String lastName, String mail){
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                User adminAdded = ((Admin) adminRole).addNewAdmin(password, firstName, lastName, mail);
                Logger.logEvent(user.getID() + " (Admin)", "Added Admin " + adminAdded.getID());
                return adminAdded;
            }
        }
        return null;

    }

    /*
    Permanently close a group only by an administrator
    */
    public boolean permanentlyCloseTeam(String adminId, String teamId){
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                if (((Admin) adminRole).closeTeamPermanently(teamId)) {
                    //notificationSystem.openORCloseTeam("closed", team, true);
                    return true;
                }
            }
        }
        return false;
    }

    public void responseToComplaint(String adminId, String complaintId, String response)
    {
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                ((Admin) adminRole).responseToComplaint(complaintId, response);
                Logger.logEvent(user.getID() + " (Admin)", " Responded to complaint");
            }
        }
    }

    public List<String> viewLog(String adminId, String type)
    {
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                return ((Admin) adminRole).viewLog(type);
            }
        }
        return null;
    }

    public boolean trainModel(String adminId)
    {
        User user = UserFactory.getUser(adminId);
        if(user!=null) {
            Role adminRole = user.checkUserRole("Admin");
            if (adminRole instanceof Admin) {
                StubRecommendationSystem recommendationSystem = new StubRecommendationSystem();
                recommendationSystem.connect();
                Logger.logEvent(user.getID() + " (Admin)", " activated the training model");
                return recommendationSystem.trainModel();
            }
        }
        return false;
    }

}
