package Service;

import Domain.Authorization.AuthorizationRole;
import Domain.Authorization.FanAuthorization;
import Domain.Authorization.UserAuthorization;
import Domain.*;

import java.util.List;

public class UserSystem extends GuestSystem {


    public UserSystem() {
    }

    /*
    View fan search history
     */
    public List<String> viewSearchHistory(User user) {
        UserAuthorization authorization = (UserAuthorization)getAuthorization(user, "User");
        if (authorization != null) {
            return authorization.viewSearchHistory();
        }
        return null;
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
        FanAuthorization authorization = (FanAuthorization)getAuthorization(user, "Fan");
        if (authorization != null) {
            authorization.editPersonalInfo(firstName, lastName, phone, address);
        }
    }

    /*
    Edit user personal information
     */
    public void editPersonalInfo(User user, String firstName, String lastName) {
        UserAuthorization authorization = (UserAuthorization)getAuthorization(user, "User");
        if (authorization != null) {
            authorization.editPersonalInfo(firstName, lastName);
        }
    }

    /*
    user adds a complaint to the system
     */
    public void addComplaint(User user, String description) {
        FanAuthorization authorization = (FanAuthorization)getAuthorization(user, "Fan");
        if (authorization != null) {
            authorization.submitComplaint(description);
        }
    }

    public boolean registrationToFollowUp(User user, PersonalPage page) {
        FanAuthorization authorization = (FanAuthorization)getAuthorization(user, "Fan");
        if (authorization != null) {
            return authorization.followPage(page);
        }
        return false;
    }

    public List<PersonalPage> getFanPages(User user) {
        FanAuthorization authorization = (FanAuthorization)getAuthorization(user, "Fan");
        if (authorization != null) {
            return authorization.getFollowedPages();
        }
        return null;
    }

    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(User user, List<Game> games, ReceiveAlerts receive) {
        FanAuthorization authorization = (FanAuthorization)getAuthorization(user, "Fan");
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
        UserAuthorization authorization = (UserAuthorization)getAuthorization(user, "User");
        if (authorization != null) {
            return authorization.search(wordToSearch);
        }
        return null;
    }


    private AuthorizationRole getAuthorization(User user, String type) {
        switch (type){
            case("User"):
                return user.getRoles().get(0);
            case("Fan"):{
                for (AuthorizationRole role : user.getRoles()) {
                    if (role.getRoleName().contains(type))
                        return (UserAuthorization) role;
                }
            }
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
