package Domain;

import Data.Database;
import Presentation.*;

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
    public void addUser(String userId, String password, User user) {
        database.addUser(userId, password, user);
    }
    /*
    Remove user
     */
    public void removeUser(String userId){
        database.removeUser(userId);
    }

    /*
     this function update a user in the system
     */
    public void updateUser(String userId){
    User user = database.getUser(userId);

}
    /*
    *this function adds a new asset to the system
    * */
    public void addAsset(Asset asset , Team team){
        if(asset instanceof User){
            if(asset instanceof TeamManager){
                team.addTeamManager((TeamManager) asset);
            }
            if(asset instanceof Player){
                team.addPlayer((Player) asset);
            }
            if(asset instanceof Coach){
                team.addCoach((Coach) asset);
            }
        }

        /**
         * Need to decide what happen if the team has a Field
         * */
        if(asset instanceof Field){
            team.getFields().add((Field) asset);
        }

        database.addAsset(asset);
    }
    /*
       Remove asset
        */
    public void removeAsset(Asset asset , Team team) {
        if(asset instanceof User){
            if(asset instanceof TeamManager){
                team.removeTeamManager((TeamManager) asset);
            }
            if(asset instanceof Player){
                team.removePlayer((Player) asset);
            }
            if(asset instanceof Coach){
                team.removeCoach((Coach) asset);
            }
        }

        /**
         * Need to decide what happen if the team has a Field
         * */
        if(asset instanceof Field){
            team.getFields().remove(asset);
        }
        database.removeAsset(asset.getID());
    }

    /*
    this function update a asset in the system
    */
    public void updateAsset(String assetId, String action) {
        Asset asset = database.getAsset(assetId);
        if(action.equals("Some_Action")){
            //do the action
        }
    }

    /*
     * User login to system
     */
    public User logInUserToSystem(String mail, String password) {
        if(database.authenticationCheck(mail, password))
            return database.getUser(mail);
        return null;
    }
    /*
    Guest registration for the system
     */
    public boolean registrationToSystem(String mail, String password, String firstName, String lastName, String phone,
                                        String address) {
        if(!database.authenticationCheck(mail, password) && database.getUser(mail)==null){
            Fan fan = new Fan(mail, firstName, lastName, phone,address);
            database.addUser(fan.getID(), password, fan);
            return true;
        }
        return false;
    }
    /*
    Edit user personal information
     */
    public void editPersonalDetails(User user, String firstName, String lastName, String phone,
                                    String address, String password) {
        if(password!=null)
            //change password ?!!?
            if(user instanceof Fan)
                ((Fan)user).editDetails(firstName, lastName, phone, address, lastName);
            else // player? coach? what change !?!?!
                user.editDetails(firstName, lastName);
    }


    public void appointmentTeamOwner(TeamOwner teamOwner , User user, Team team){

        String assetId = user.getID();

        /*
         * if the new asset is part of the team, but the rule change
         * */
        if(team.isActive()) {
            if(!teamOwner.getAppointmentAssetsInTeams(team).isEmpty()) {
                if (!teamOwner.getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                    if (user instanceof TeamOwner || user instanceof TeamManager
                            || user instanceof Player || user instanceof Coach) {

                        //if the team owner is not already exist
                        if (!team.getTeamOwners().contains(user)) {
                            team.addTeamOwner((TeamOwner) user);
                            teamOwner.getAppointmentAssetsInTeams(team).put(assetId, user);


                            /**NEED TO MAKE THE USER A TEAM OWNER*/
                            if (user instanceof Player || user instanceof Coach || user instanceof TeamManager) {
                                //user become a Team Owner
                            }
                        }

                    }

                }
            }
        }
    }

    public void appointmentTeamManager(TeamOwner teamOwner, User user, Team team){

        String assetId = user.getID();

        /*
         * if the new asset is part of the team, but the rule change
         * */
        if(team.isActive()) {
            if (!teamOwner.getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                if (user instanceof TeamManager || user instanceof Player || user instanceof Coach) {

                    //if the team manager is not already exist
                    if(!team.getTeamManagers().contains(user)) {
                        team.addTeamManager((TeamManager) user);
                        teamOwner.getAppointmentAssetsInTeams(team).put(assetId,user);

                        /**NEED TO MAKE THE USER A TEAM Manager*/
                        if(user instanceof Player || user instanceof Coach){
                            //user become a Team Manager
                        }
                    }

                }

            }
        }
    }

    /**
     * remove Team Owner Appointment
     */
    public void removeAppointmentTeamOwner(TeamOwner teamOwner, User user, Team team)
    {


        if(user instanceof TeamOwner) {
            if (teamOwner.getAppointmentAssetsInTeams(team) != null) {
                removeTeamOwner(teamOwner, user, team);

                /**remove Appointments of team owner*/
                removeAppointmentsByLoop((TeamOwner) user, team);
            }
        }

    }

    /**
     * remove Team Manager Appointment
     */
    public void removeAppointmentTeamManager(TeamOwner teamOwner,User user, Team team)
    {
        if(user instanceof TeamManager) {
            if (teamOwner.getAppointmentAssetsInTeams(team) != null) {
                removeTeamManager(teamOwner, user, team);
            }
        }

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
                if (((TeamOwner) user).getAmountOfTeams() == 0) {
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
}
