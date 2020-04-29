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
    public void removeUser(User admin, String userId){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            String userMail = authorization.removeUser(userId);
            notificationSystem.UserRemovalNotification(userMail);
        }

    }

    public User addNewPlayer(User admin, String firstName, String lastName, String mail, Date birthDate, String role, double price){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            return authorization.addNewPlayer(firstName, lastName, mail, birthDate, role, price);
        }
        return null;
    }
    public User addNewCoach(User admin,String firstName, String lastName, String mail, String training, String role, double price){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            return authorization.addNewCoach(firstName, lastName, mail, training, role, price);
        }
        return null;
    }
    public User addNewTeamOwner(User admin,String firstName, String lastName, String mail){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            return  authorization.addNewTeamOwner(firstName, lastName, mail);
        }
        return null;
    }
    public User addNewTeamManager(User admin,String firstName, String lastName, String mail, double price,boolean manageAssets , boolean finance){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            return authorization.addNewTeamManager(firstName, lastName, mail, price, manageAssets, finance);
        }
        return null;
    }
    public User addNewUnionRepresentative(User admin,String firstName, String lastName, String mail){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            return authorization.addNewUnionRepresentative(firstName, lastName, mail);
        }
        return null;
    }

    public User addNewAdmin(User admin,String password ,String firstName, String lastName, String mail){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            return authorization.addNewAdmin(password,firstName, lastName, mail);
        }
        return null;

    }


    /*
Permanently close a group only by an administrator
 */
    public boolean permanentlyCloseTeam(User admin, Team team){
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            if(authorization.closeTeamPermanently(team)){
                notificationSystem.openORCloseTeam("closed", team, true);
                return true;
            }
        }
        return false;
    }

    public void responseToComplaint(User admin, Complaint complaint)
    {
        Admin authorization = getAuthorization(admin);
        if(authorization!=null){
            authorization.responseToComplaint();
        }
    }

    public void viewLog()
    {

    }

    public boolean trainModel(User admin)
    {
        Admin authorization = getAuthorization(admin);
        if(authorization!=null) {
            StubRecommendationSystem recommendationSystem = new StubRecommendationSystem();
            recommendationSystem.connect();
            return recommendationSystem.trainModel();
        }
        return false;

    }

    private Admin getAuthorization(User user) {
        for(Role role : user.getRoles()){
            if(role instanceof Admin)
                return (Admin)role;
        }
        return null;
    }
}
