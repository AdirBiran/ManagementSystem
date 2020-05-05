package Service;

import Domain.*;
import Logger.Logger;

import java.util.List;

public class UserSystem extends GuestSystem {


    public UserSystem() {
    }

    /*
    View fan search history
     */
    public List<String> viewSearchHistory(User user) {
        Logger.logEvent(user.getID(), "View Search History");
        return user.viewSearchHistory();
    }

    /*
    log out user from system
     */
    public Guest logOut()
    {
        Logger.logEvent("(Guest)", "Logout");
        return this.guest;
    }

    /*
    View user's personal information
     */
    public String viewPersonalDetails(User user) {
        Logger.logEvent(user.getID(), "View Personal Details");

        return user.toString(); // toString by user!?!?
    }

    /*
    Edit fan personal information
     */
    public boolean editFanPersonalDetails(User user, String firstName, String lastName, String phone, String address) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            ((Fan)role).editPersonalInfo(user, firstName, lastName, phone, address);
            Logger.logEvent(user.getID(), "Edit Fan Personal Details");
            return true;

        }
        Logger.logError("Failed editing fan's personal details");
        return false;
    }

    /*
    Edit user personal information
     */
    public void editPersonalInfo(User user, String firstName, String lastName) {
        user.editPersonalInfo(firstName, lastName);
        Logger.logEvent(user.getID(), "Edit Personal Details");

    }

    /*
    user adds a complaint to the system
     */
    public boolean addComplaint(User user, String description) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            ((Fan)role).submitComplaint(description);
            Logger.logEvent(user.getID(), "Added Complaint");
            return true;
        }

        Logger.logEvent(user.getID(), "Adding complaint Failed");
        return false;
    }

    public boolean registrationToFollowUp(User user, PersonalPage page) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
        boolean success = ((Fan)role).followPage(page);
        if (success)
            Logger.logEvent(user.getID(), "Follow page Success");
        else
            Logger.logError("Follow page Failed");

            return success;
        }


        return false;

    }

    public List<String> getFanPages(User user) {
        Role role = user.checkUserRole("Fan");
        if(role instanceof Fan) {
            Logger.logEvent(user.getID(), "Requested followed pages");
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
            boolean success = ((Fan)role).followGames(games, receive);
            if (success)
                Logger.logEvent(user.getID(), "Game Alerts Registration Success");
            else
                Logger.logError("Game Alerts Registration Failed");

            return success;
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
        Logger.logEvent(user.getID(), "Searched " + wordToSearch);
        return user.search(wordToSearch);

    }

}
