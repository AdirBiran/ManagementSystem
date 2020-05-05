package Service;

import Domain.*;
import java.util.List;

public class UserSystem extends GuestSystem {


    public UserSystem() {
    }

    /*
    View fan search history
     */
    public List<String> viewSearchHistory(User user) {
        return user.viewSearchHistory();
    }

    /*
    log out user from system
     */
    public Guest logOut() {
        return this.guest;
    }

    /*
    View user's personal information
     */
    public String viewPersonalDetails(User user) {
        return user.toString(); // toString by user!?!?
    }

    /*
    Edit fan personal information
     */
    public boolean editFanPersonalDetails(User user, String firstName, String lastName, String phone, String address) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            ((Fan)role).editPersonalInfo(user, firstName, lastName, phone, address);
            return true;
        }
        return false;
    }

    /*
    Edit user personal information
     */
    public void editPersonalInfo(User user, String firstName, String lastName) {
        user.editPersonalInfo(firstName, lastName);
    }

    /*
    user adds a complaint to the system
     */
    public boolean addComplaint(User user, String description) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            ((Fan)role).submitComplaint(description);
            return true;
        }
        return false;
    }

    public boolean registrationToFollowUp(User user, PersonalPage page) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
        return ((Fan)role).followPage(page);
        }
        return false;

    }

    public List<String> getFanPages(User user) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            return ((Fan)role).getFollowedPages();
        }
        return null;
    }

    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(User user, List<Game> games, ReceiveAlerts receive) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            return ((Fan)role).followGames(games, receive);
        }
        return false;
    }


    public boolean updateTrainingForCoach(User user, String training) {
        Role role = user.checkUserRole("Coach");
        if (role instanceof Coach) {
            ((Coach) role).setTraining(training);
            return true;
        }
        return false;
    }
    public boolean updateTrainingForReferee(User user, String training) {
        Role role = user.checkUserRole("Referee");
        if (role instanceof Referee) {
            ((Referee) role).setTraining(training);
            return true;
        }
        return false;
    }

    public boolean updateRoleForPlayer(User user, String newRole) {
        Role role = user.checkUserRole("Player");
        if (role instanceof Player) {
            ((Player) role).setRole(newRole);
            return true;
        }
        return false;
    }
    public boolean updateRoleForCoach(User user, String newRole) {
        Role role = user.checkUserRole("Coach");
        if (role instanceof Coach) {
            ((Coach) role).setRole(newRole);
            return true;
        }
        return false;
    }

    public String getRoleForPlayer(User user) {
        Role role = user.checkUserRole("Player");
        if (role instanceof Player) {
            return ((Player) role).getRole();
        }
        return "";
    }
    public String getRoleForCoach(User user) {
        Role role = user.checkUserRole("Coach");
        if (role instanceof Coach) {
            return ((Coach) role).getRole();
        }
        return "";
    }

     /*
    Search results in a system
     */
    public List<String> search(User user,  String wordToSearch){
        return user.search(wordToSearch);

    }

}
