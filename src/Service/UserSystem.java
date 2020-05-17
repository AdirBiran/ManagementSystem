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
    public List<String> viewSearchHistory(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Logger.logEvent(user.getID(), "View Search History");
            return user.getSearchHistory();
        }
        return null;
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
    public String viewPersonalDetails(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Logger.logEvent(user.getID(), "View Personal Details");
            return user.toString(); // toString by user!?!?
        }
        return "";
    }

    /*
    Edit fan personal information
     */
    public boolean editFanPersonalDetails(String userId, String firstName, String lastName, String phone, String address) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                ((Fan) role).editPersonalInfo(user, firstName, lastName, phone, address);
                Logger.logEvent(user.getID(), "Edit Fan Personal Details");
                return true;
            }
        }
        Logger.logError("Failed editing fan's personal details");
        return false;
    }

    /*
    Edit user personal information
     */
    public void editPersonalInfo(String userId, String firstName, String lastName) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            user.editPersonalInfo(firstName, lastName);
            Logger.logEvent(user.getID(), "Edit Personal Details");
        }
    }

    /*
    user adds a complaint to the system
     */
    public boolean addComplaint(String userId, String description) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                ((Fan) role).submitComplaint(description);
                Logger.logEvent(user.getID(), "Added Complaint");
                return true;
            }
        }
        Logger.logEvent(user.getID(), "Adding complaint Failed");
        return false;
    }

    public boolean registrationToFollowUp(String userId, String pageId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                boolean success = ((Fan) role).addPageToFollow(pageId);
                if (success)
                    Logger.logEvent(user.getID(), "Follow page Success");
                else
                    Logger.logError("Follow page Failed");

                return success;
            }
        }
        return false;
    }

    public List<String> getFanPages(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                Logger.logEvent(user.getID(), "Requested followed pages");
                return ((Fan) role).getFollowedPages();
            }
        }
        return null;
    }
   /* public List<String> getAllPages(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                Logger.logEvent(user.getID(), "Requested all the personal pages in the system");
                return ((Fan) role).getAllPages();
            }
        }
        return null;
    }*/
    public List<String> getAllFutureGames(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                Logger.logEvent(user.getID(), "Requested all the future games");
                return ((Fan) role).getAllFutureGames();
            }
        }
        return null;
    }

    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(String userId, List<String> gamesId, boolean toMail, boolean toPhone) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Fan");
            if (role instanceof Fan) {
                ReceiveAlerts receive = new ReceiveAlerts(toMail, toPhone);
                boolean success = ((Fan) role).followGames(gamesId, receive);
                if (success)
                    Logger.logEvent(user.getID(), "Game Alerts Registration Success");
                else
                    Logger.logError("Game Alerts Registration Failed");

                return success;
            }
        }
        return false;
    }


    public boolean updateTrainingForCoach(String userId, String training) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Coach");
            if (role instanceof Coach) {
                Coach.TrainingCoach trainingCoach = Coach.TrainingCoach.valueOf(training);
                ((Coach) role).setTraining(trainingCoach);
                return true;
            }
        }
        return false;
    }
    public boolean updateTrainingForReferee(String userId, String training) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Referee");
            if (role instanceof Referee) {
                Referee.TrainingReferee trainingReferee = Referee.TrainingReferee.valueOf(training);
                ((Referee) role).setTraining(trainingReferee);

                return true;
            }
        }
        return false;
    }

    public boolean updateRoleForPlayer(String userId, String newRole) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Player");
            if (role instanceof Player) {
                Player.RolePlayer rolePlayer = Player.RolePlayer.valueOf(newRole);
                ((Player) role).setRole(rolePlayer);
                return true;
            }
        }
        return false;
    }
    public boolean updateRoleForCoach(String userId, String newRole) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Coach");
            if (role instanceof Coach) {
                Coach.RoleCoach roleCoach = Coach.RoleCoach.valueOf(newRole);
                ((Coach) role).setRoleInTeam(roleCoach);
                return true;
            }
        }
        return false;
    }

    public String getRoleForPlayer(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Player");
            if (role instanceof Player) {
                return ((Player) role).getRole();
            }
        }
        return "";
    }
    public String getRoleForCoach(String userId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("Coach");
            if (role instanceof Coach) {
                return ((Coach) role).getRoleInTeam();
            }
        }
        return "";
    }

     /*
    Search results in a system
     */
    public List<String> search(String userId,  String wordToSearch){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Logger.logEvent(user.getID(), "Searched " + wordToSearch);
            return user.search(wordToSearch);
        }
        return null;
    }

    public List<String> getUserRoles(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            return user.getStringRoles();
        }
        return null;
    }
}
