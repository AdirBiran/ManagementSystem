package Service;

import Domain.*;
import Domain.User;
import Logger.Logger;

import java.util.Date;

public class AdminSystem {

    private NotificationSystem notificationSystem;

    public AdminSystem(NotificationSystem notificationSystem) {
        this.notificationSystem = notificationSystem;
    }

    /*
    Remove user by an administrator
    */
    public void removeUser(User user, String userId){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            String userMail = ((Admin)adminRole).removeUser(userId);
            notificationSystem.UserRemovalNotification(userMail);

            Logger.logEvent(user.getID() + " (Admin)", "Removed user " + userId);
        }
    }

    public User addNewPlayer(User user, String firstName, String lastName, String mail, Date birthDate, String role, double price){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            User playerAdded = ((Admin)adminRole).addNewPlayer(firstName, lastName, mail, birthDate, role, price);
            Logger.logEvent(user.getID() + " (Admin)", "Added player " + playerAdded.getID());
            return playerAdded;

        }
        return null;
    }
    public User addNewCoach(User user,String firstName, String lastName, String mail, String training, String role, double price){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            User coachAdded = ((Admin)adminRole).addNewCoach(firstName, lastName, mail, training, role, price);
            Logger.logEvent(user.getID() + " (Admin)", "Added coach " + coachAdded.getID());
            return coachAdded;
        }
        return null;
    }
    public User addNewTeamOwner(User user,String firstName, String lastName, String mail){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            User teamOwnerAdded = ((Admin)adminRole).addNewTeamOwner(firstName, lastName, mail);
            Logger.logEvent(user.getID() + " (Admin)", "Added Team owner " + teamOwnerAdded.getID());
            return teamOwnerAdded;
        }
        return null;
    }
    public User addNewTeamManager(User user,String firstName, String lastName, String mail, double price,boolean manageAssets , boolean finance){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            User managerAdded = ((Admin)adminRole).addNewTeamManager(firstName, lastName, mail, price, manageAssets, finance);
            Logger.logEvent(user.getID() + " (Admin)", "Added Team manager " + managerAdded.getID());
            return managerAdded;
        }
        return null;
    }
    public User addNewUnionRepresentative(User user,String firstName, String lastName, String mail){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            User representetiveAdded = ((Admin)adminRole).addNewUnionRepresentative(firstName, lastName, mail);
            Logger.logEvent(user.getID() + " (Admin)", "Added Union Representetive " + representetiveAdded.getID());
            return representetiveAdded;
        }
        return null;
    }

    public User addNewAdmin(User user,String password ,String firstName, String lastName, String mail){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            User adminAdded = ((Admin)adminRole).addNewAdmin(password,firstName, lastName, mail);
            Logger.logEvent(user.getID() + " (Admin)", "Added Admin " + adminAdded.getID());
            return adminAdded;
        }
        return null;

    }

    /*
    Permanently close a group only by an administrator
    */
    public boolean permanentlyCloseTeam(User user, Team team){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            if(((Admin)adminRole).closeTeamPermanently(team)){
                notificationSystem.openORCloseTeam("closed", team, true);
                Logger.logEvent(user.getID() + " (Admin)", " Closed Team " + team.getName() + " permanently");
                return true;
            }
        }
        return false;
    }

    public void responseToComplaint(User user, Complaint complaint)
    {
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            ((Admin)adminRole).responseToComplaint();
            Logger.logEvent(user.getID() + " (Admin)", " Responded to complaint");
        }
    }

    public void viewLog()
    {

    }

    public boolean trainModel(User user)
    {
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            StubRecommendationSystem recommendationSystem = new StubRecommendationSystem();
            recommendationSystem.connect();
            Logger.logEvent(user.getID() + " (Admin)", " activated the training model");
            return recommendationSystem.trainModel();
        }
        return false;
    }

}
