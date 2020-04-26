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
    public User logOut() {
        return null;
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
    public void editFanPersonalDetails(User user, String firstName, String lastName, String phone, String address) {
        Fan authorization = getFanAuthorization(user);
        if (authorization != null) {
            authorization.editPersonalInfo(firstName, lastName, phone, address);
        }
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
    public void addComplaint(User user, String description) {
        Fan authorization =getFanAuthorization(user);
        if (authorization != null) {
            authorization.submitComplaint(description);
        }
    }

    public boolean registrationToFollowUp(User user, PersonalPage page) {
        Fan authorization =getFanAuthorization(user);
        if (authorization != null) {
            return authorization.followPage(page);
        }
        return false;
    }

    public List<PersonalPage> getFanPages(User user) {
        Fan authorization =getFanAuthorization(user);
        if (authorization != null) {
            return authorization.getFollowedPages();
        }
        return null;
    }

    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(User user, List<Game> games, ReceiveAlerts receive) {
        Fan authorization =getFanAuthorization(user);
        if (authorization != null) {
            for(Game game : games){
                authorization.followGame(game, receive);
            }
            return true;
        }
        return false;
    }

    /*
    public boolean updateTraining(User user, String training) {
        if (user instanceof Coach) {
            ((Coach) user).setTraining(training);
            return true;
        } else if (user instanceof Referee) {
            ((Referee) user).setTraining(training);
            return true;
        }
        return false;
    }

    public boolean updateRole(User user, String role) {

        if (user instanceof Player) {
            ((Player) user).setRole(role);
            return true;
        }
        if (user instanceof Coach) {
            ((Coach) user).setRole(role);
            return true;
        }
        return false;
    }

    public String getRole(User user) {

        if (user instanceof Player) {
            return ((Player) user).getRole();
        }
        if (user instanceof Coach) {
            return ((Coach) user).getRole();
        }

        return "";
    }
*/
     /*
    Search results in a system
     */
    public List<Object> search(User user,  String wordToSearch){
        return user.search(wordToSearch);

    }


    private Fan getFanAuthorization(User user) {

        for (Role role : user.getRoles()) {
            if (role instanceof Fan)
                return (Fan)role;

        }

        return null;
    }

    public void updateRole(Player player, String role) {
        player.setRole(role);
    }

    public String getRole(Player player) {
        return player.getRole();
    }
}
