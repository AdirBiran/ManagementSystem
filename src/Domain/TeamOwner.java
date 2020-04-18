package Domain;


import java.util.*;

public class TeamOwner extends Manager {

    public TeamOwner(String firstName,String lastName, String mail) {
        super(firstName,lastName, "TO", mail);
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    /*
     * allows to add only team owners
     * if the new asset is part of the team, but the rule change
     * */
    public boolean appointmentTeamOwner(User user, Team team){

        String assetId = user.getID();

        if(team.isActive()&& user.isActive()) {
            //if the team owner is not already exist
            if (team.getTeamOwners().contains(user)) {
                return false;
            }
            team.addTeamOwner(user);
            if(user instanceof TeamOwner)
                ((TeamOwner) user).setAppointmentAssetsInTeams(team);

            if (!getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                getAppointmentAssetsInTeams(team).put(assetId, user);
                return true;

            }

        }
        return false;
    }

    /*
     * if the new asset is part of the team, but the rule change
     * */
    public boolean appointmentTeamManager(User user, Team team){

        String assetId = user.getID();

        if(team.isActive()) {
            //if the team manager is not already exist
            if(team.getTeamManagers().contains(user)||team.getTeamOwners().contains(user)) {
                return false;
            }
            team.addTeamManager(user);

            if (!getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                getAppointmentAssetsInTeams(team).put(assetId, user);
                return true;
            }
        }
        return false;
    }

    /**
     * remove Team Owner Appointment
     */
    public boolean removeAppointmentTeamOwner(User user, Team team)
    {
        if (team.isActive() && getAppointmentAssetsInTeams(team) != null) {
            if(getAppointmentAssetsInTeams(team).containsKey(user.getID())) {
                removeTeamOwner(user, team);
                /**remove Appointments of team owner*/
                removeAppointmentsByLoop(team);
                user.addMessage(new Notice(true, "Your appointment hes been removed"));
                return true;
            }
        }

        return false;
    }

    /**
     * remove Team Manager Appointment
     */
    public boolean removeAppointmentTeamManager(User user, Team team)
    {
        if (team.isActive() && getAppointmentAssetsInTeams(team) != null) {
            if(getAppointmentAssetsInTeams(team).containsKey(user.getID())) {
                removeTeamManager( user, team);
                return true;
            }
        }
        return false;

        //maybe add more stuff here
    }


    /**
     * if the User is no longer part of something in the system
     * the user become not active
     * */
    private void removeTeamOwner(User user, Team team){
        String assetId = user.getID();
            if (getAppointmentAssetsInTeams(team).containsKey(assetId)) {
                team.removeTeamOwner(user);
                getAppointmentAssetsInTeams(team).remove(assetId);

                if (user.getAmountOfTeams() == 0) {
                    user.deactivate();
                }
            }
    }


    /**
     * remove the appointments of the team owner that was remove
     * */
    /*
     * for every asset in the assets list:
     * if the asset is a team owner or a team manager remove them.
     * if the asset is a team owner, check his appointments:
     * and do the same
     * */
    private void removeAppointmentsByLoop( Team team){

        //if the team owner has appointments
        if (getAppointmentAssetsInTeams(team) != null) {
            if (!getAppointmentAssetsInTeams(team).isEmpty()) {

                //create a list of assets
                List<User> assetsList = new LinkedList<>(getAppointmentAssetsInTeams(team).values());

                //take all his(team owner that was remove)
                // appointments assets and put in a list

                for (User user : assetsList) {
                    removeAppointmentTeamOwner(user, team);
                }
            }
        }
    }
    /**
     * if the User is no longer part of something in the system
     * the user become not active
     * */
    private void removeTeamManager( User user, Team team){
        String assetId = user.getID();
        if(getAppointmentAssetsInTeams(team).containsKey(assetId)){
            team.removeTeamManager(user);
            getAppointmentAssetsInTeams(team).remove(assetId);

                if (user.getAmountOfTeams() == 0) {
                    user.deactivate();
                }
            }
    }

    public boolean closeTeam(Team team){

        //List<User> teamowner = team.getTeamOwners();
        if(team.isActive()) {
            if(team.getTeamOwners().contains(this)) {
                setClosedTeam(team, true);
                team.setActive(false);
                //Removing permissions for team members
                return true;
            }
        }
        return false;
    }
    /*

     */
    public boolean reopeningTeam(Team team){
        if(!team.isActive() && !team.isPermanentlyClosed() && isClosedTeam(team)){
            setClosedTeam(team, false);
            team.setActive(true);
            //Re-configure permissions for team members
            return true;
        }
        return false;

    }
    /*

     */
    public void reportFinanceTrans()
    {

    }
    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public List<Team> getTeam() {
        return teams;
    }

    public boolean isClosedTeam(Team team) {
        return isClosedTeam.get(team);
    }

    public void setClosedTeam(Team team, boolean closeTeam) {
        isClosedTeam.replace(team, closeTeam);
    }


    public HashMap<String, User> getAppointmentAssetsInTeams(Team team) {
        if(!appointmentAssetsInTeams.isEmpty()) {
            return appointmentAssetsInTeams.get(team);
        }else{
            return null;
        }
    }

    public void setAppointmentAssetsInTeams(Team team){
        appointmentAssetsInTeams.put(team,new HashMap<>());
    }
}