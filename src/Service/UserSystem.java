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

    public List<PersonalPage> getFanPages(User user) {
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


    public boolean updateTraining(Role role, String training) {
        if (role instanceof Coach) {
            ((Coach) role).setTraining(training);
            return true;
        } else if (role instanceof Referee) {
            ((Referee) role).setTraining(training);
            return true;
        }
        return false;
    }

    public boolean updateRole(Role role, String newRole) {

        if (role instanceof Player) {
            ((Player) role).setRole(newRole);
            return true;
        }
        if (role instanceof Coach) {
            ((Coach) role).setRole(newRole);
            return true;
        }
        return false;
    }

    public String getRole(Role role) {

        if (role instanceof Player) {
            return ((Player) role).getRole();
        }
        if (role instanceof Coach) {
            return ((Coach) role).getRole();
        }

        return "";
    }

     /*
    Search results in a system
     */
    public List<Object> search(User user,  String wordToSearch){
        return user.search(wordToSearch);

    }

}
