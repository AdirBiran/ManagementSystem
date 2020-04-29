package Service;

import Domain.*;
import Domain.User;
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
        }
    }

    public User addNewPlayer(User user, String firstName, String lastName, String mail, Date birthDate, String role, double price){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            return ((Admin)adminRole).addNewPlayer(firstName, lastName, mail, birthDate, role, price);
        }
        return null;
    }
    public User addNewCoach(User user,String firstName, String lastName, String mail, String training, String role, double price){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            return ((Admin)adminRole).addNewCoach(firstName, lastName, mail, training, role, price);
        }
        return null;
    }
    public User addNewTeamOwner(User user,String firstName, String lastName, String mail){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            return  ((Admin)adminRole).addNewTeamOwner(firstName, lastName, mail);
        }
        return null;
    }
    public User addNewTeamManager(User user,String firstName, String lastName, String mail, double price,boolean manageAssets , boolean finance){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            return ((Admin)adminRole).addNewTeamManager(firstName, lastName, mail, price, manageAssets, finance);
        }
        return null;
    }
    public User addNewUnionRepresentative(User user,String firstName, String lastName, String mail){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            return ((Admin)adminRole).addNewUnionRepresentative(firstName, lastName, mail);
        }
        return null;
    }

    public User addNewAdmin(User user,String password ,String firstName, String lastName, String mail){
        Role adminRole = user.checkUserRole("Admin");
        if(adminRole instanceof Admin){
            return ((Admin)adminRole).addNewAdmin(password,firstName, lastName, mail);
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
            return recommendationSystem.trainModel();
        }
        return false;
    }

}
