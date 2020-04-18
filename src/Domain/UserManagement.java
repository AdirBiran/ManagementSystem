package Domain;

import Data.Database;

import java.util.LinkedList;
import java.util.List;

public class UserManagement//for admins
{
    private Database database;

    public UserManagement(Database database) {
        this.database = database;
    }

    /*
       this function adds a new user to the system
    */
    public void addUser(String password, User user) {
        database.addUser(password, user);
    }
    /*
    Remove user
     */
    public String removeUser(String userId){
        return database.removeUser(userId);
    }

    /*
     this function update a user in the system
     */
    public void updateUser(String userId){
    User user = database.getUser(userId);

    }

    /*
     * User login to system
     */
    public User logInUserToSystem(String mail, String password) {
        if(database.authenticationCheck(mail, password))
            return database.getUserbyMail(mail);
        return null;
    }
    /*
    Guest registration for the system
     */
    public boolean registrationToSystem(String mail, String password, String firstName, String lastName, String phone,
                                        String address) {
        if(!database.authenticationCheck(mail, password)){
            Fan fan = new Fan(mail, firstName, lastName, phone,address);
            database.addUser(password, fan);
            return true;
        }
        return false;
    }

    public boolean registrationToFollowUp(Fan fan, PersonalPage page){
        return fan.addPageToFollow(page);
    }

    public List<PersonalPage> getFanPages(Fan fan)
    {
        return fan.getFollowPages();
    }


    public boolean appointmentTeamOwner(TeamOwner teamOwner , User user, Team team){

        String assetId = user.getID();
        boolean ans = false;

        /*
         * allows to add only team owners
         * if the new asset is part of the team, but the rule change
         * */
        if(team.isActive()) {
                if (user instanceof TeamOwner) {// user instanceof TeamManage || user instanceof Player || user instanceof Coach
                    //if the team owner is not already exist
                    if (team.getTeamOwners().contains(user)) {
                        return false;
                    }

                    team.addTeamOwner((TeamOwner) user);
                    ((TeamOwner) user).setAppointmentAssetsInTeams(team);

                    if (!teamOwner.getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                        teamOwner.getAppointmentAssetsInTeams(team).put(assetId, user);
                        ans = true;

                    }

                }
            /**NEED TO MAKE THE USER A TEAM OWNER*/
            if (user instanceof Player || user instanceof Coach || user instanceof TeamManager) {
                //user become a Team Owner
            }

        }
        return ans;
    }

    public boolean appointmentTeamManager(TeamOwner teamOwner, User user, Team team){

        String assetId = user.getID();
        boolean ans = false;
        /*
         * if the new asset is part of the team, but the rule change
         * */
        if(team.isActive()) {
                if (user instanceof TeamManager){// || user instanceof Player || user instanceof Coach) {

                    //if the team manager is not already exist
                    if(team.getTeamManagers().contains(user)) {
                        return false;
                    }
                        team.addTeamManager((TeamManager) user);

                        if (!teamOwner.getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                            teamOwner.getAppointmentAssetsInTeams(team).put(assetId, user);
                            ans = true;
                        }
                }
            /**NEED TO MAKE THE USER A TEAM Manager*/
            if(user instanceof Player || user instanceof Coach){
                //user become a Team Manager
            }
        }
        return ans;
    }

    /**
     * remove Team Owner Appointment
     */
    public boolean removeAppointmentTeamOwner(TeamOwner teamOwner, User user, Team team)
    {

        if(user instanceof TeamOwner) {
            if (teamOwner.getAppointmentAssetsInTeams(team) != null) {
                if(teamOwner.getAppointmentAssetsInTeams(team).containsKey(user.getID())) {
                    removeTeamOwner(teamOwner, user, team);

                    /**remove Appointments of team owner*/
                    removeAppointmentsByLoop((TeamOwner) user, team);
                    user.addMessage(new Notice(true, "Your appointment hes been removed"));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * remove Team Manager Appointment
     */
    public boolean removeAppointmentTeamManager(TeamOwner teamOwner,User user, Team team)
    {
        if(user instanceof TeamManager) {
            if (teamOwner.getAppointmentAssetsInTeams(team) != null) {
                if(teamOwner.getAppointmentAssetsInTeams(team).containsKey(user.getID())) {
                    removeTeamManager(teamOwner, user, team);
                    return true;
                }
            }
        }
        return false;

        //maybe add more stuff here
    }


    private void removeTeamOwner(TeamOwner teamOwner, User user, Team team){
        String assetId = user.getID();

        if(team.isActive()) {
            if (teamOwner.getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                team.removeTeamOwner((TeamOwner) user);
                teamOwner.getAppointmentAssetsInTeams(team).remove(assetId);

                /**
                 * if the User is no longer part of something in the system
                 * the user become not active
                 * */
                if (user.getAmountOfTeams() == 0) {
                    user.deactivate();
                }
            }
        }
    }


    /**
     * remove the appointments of the team owner that was remove
     * */
    private void removeAppointmentsByLoop(TeamOwner teamOwner , Team team){

        //if the team owner has appointments
        if (teamOwner.getAppointmentAssetsInTeams(team) != null) {
            if (!teamOwner.getAppointmentAssetsInTeams(team).isEmpty()) {

                //create a list of assets
                List<User> assetsList = new LinkedList<>();

                //take all his(team owner that was remove)
                // appointments assets and put in a list
                assetsList.addAll(teamOwner.getAppointmentAssetsInTeams(team).values());

                /*
                 * for every asset in the assets list:
                 * if the asset is a team owner or a team manager remove them.
                 * if the asset is a team owner, check his appointments:
                 * and do the same
                 * */
                for (User user : assetsList) {
                    if (user instanceof TeamOwner) {
                        //removeTeamOwner(teamOwner ,asset , team);
                        //maybe need to put here the public function:
                        // removeAppointmentTeamOwner , because we removed teamOwner
                        removeAppointmentTeamOwner(teamOwner, user, team);

                        //teamOwner.getAppointmentAssetsInTeams(team).remove(asset.getID());
                    } else if (user instanceof TeamManager) {

                        removeAppointmentTeamManager(teamOwner, user, team);
                        //removeTeamManager(teamOwner ,asset , team);
                        //teamOwner.getAppointmentAssetsInTeams(team).remove(asset.getID());
                    }
                }
            }
        }
    }

    private void removeTeamManager(TeamOwner teamOwner, User user, Team team){
        String assetId = user.getID();

        if(team.isActive()){
            if(teamOwner.getAppointmentAssetsInTeams(team).containsKey(assetId)){
                team.removeTeamManager((TeamManager) user);
                teamOwner.getAppointmentAssetsInTeams(team).remove(assetId);

                /**
                 * if the User is no longer part of something in the system
                 * the user become not active
                 * */
                if (((TeamManager) user).getAmountOfTeams() == 0) {
                    user.deactivate();
                }
            }
        }
    }

    public String getRole(User user) {

        if (user instanceof Player) {
            return ((Player)user).getRole();
        }
        if (user instanceof Coach) {
            return ((Coach)user).getRole();
        }

        return "";
    }

    public boolean updateRole(User user, String role) {
        if(user instanceof Player){
            ((Player)user).setRole(role);
            return true;
        }
        if(user instanceof Coach){
            ((Coach)user).setRole(role);
            return true;
        }
        return false;
    }
    public boolean updateTraining(User user, String training) {
        if(user instanceof Coach){
            ((Coach)user).setRole(training);
            return true;
        }
        return false;
    }

    public void deactivateField(Field field) {
        field.deactivate();
    }

    public void notificationForAppointment(User user, String message) {

        user.addMessage(new Notice(true, message));
    }
}
