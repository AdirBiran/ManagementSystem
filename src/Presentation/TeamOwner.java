package Presentation;

import Domain.Asset;
import Domain.Field;
import Domain.IdGenerator;
import Domain.Team;
import Service.UserSystem;

import java.util.*;

public class TeamOwner extends Manager {

    private List<Team> teams;
    private HashMap<Team, Boolean> isClosedTeam;
    //private HashMap<String, Asset> appointmentAssets;
    private HashMap<Team,HashMap<String,Asset>> appointmentAssetsInTeams;


    public TeamOwner(String firstName,String lastName, String mail) {
        super(firstName,lastName, "TO", mail);
        this.teams = new LinkedList<>();
        this.isClosedTeam = new HashMap<>();
        //this.appointmentAssets = new HashMap<>();
        this.appointmentAssetsInTeams = new HashMap<>();

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

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
    }

    public void removeAsset(Asset asset , Team team){
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
    }

    public void addTeam(Team team) {
       if(!teams.contains(team)){
           teams.add(team);
           isClosedTeam.put(team, false);

           //not sure about this
           appointmentAssetsInTeams.put(team,new HashMap<>());
       }

    }


    public void appointmentTeamOwner(Asset asset, Team team){

        String assetId = asset.getID();

        /*
         * if the new asset is part of the team, but the rule change
         * */
        if(team.isActive()) {
            if (!appointmentAssetsInTeams.get(team).containsKey(assetId)) {
                if (asset instanceof TeamManager || asset instanceof Player
                        || asset instanceof Coach) {

                    //if the team owner is not already exist
                    if(!team.getTeamOwners().contains(asset)) {
                        team.addTeamOwner((TeamOwner) asset);
                        appointmentAssetsInTeams.get(team).put(assetId,asset);
                    }

                }

            }
        }
    }

    public void appointmentTeamManager(Asset asset, Team team){

        String assetId = asset.getID();

        /*
         * if the new asset is part of the team, but the rule change
         * */
        if(team.isActive()) {
            if (!appointmentAssetsInTeams.get(team).containsKey(assetId)) {
                if (asset instanceof Player || asset instanceof Coach) {

                    //if the team manager is not already exist
                    if(!team.getTeamManagers().contains(asset)) {
                        team.addTeamManager((TeamManager) asset);
                        appointmentAssetsInTeams.get(team).put(assetId,asset);
                    }

                }

            }
        }
    }




    /**
     * remove Team Owner Appointment
     */
    public void removeAppointmentTeamOwner(Asset asset, Team team)
    {

        removeTeamOwner(asset , team);

        /**remove Appointments of team owner*/
        removeAppointmentsByLoop((TeamOwner) asset , team);

    }

    private void removeTeamOwner(Asset asset, Team team){
        String assetId = asset.getID();

        if(team.isActive()){
            if(appointmentAssetsInTeams.get(team).containsKey(assetId)){
                team.removeTeamOwner((TeamOwner) asset);
                appointmentAssetsInTeams.get(team).remove(assetId);

                /**
                 * if the User is no longer part of something in the system
                 * the user become not active
                 * */
                if(((TeamOwner) asset).amountOfTeams == 0){
                    asset.deactivate();
                }
            }
        }
    }

    private void removeAppointmentsByLoop(TeamOwner teamOwner , Team team){

        if(!teamOwner.appointmentAssetsInTeams.isEmpty()){

            List<Asset> assets = new LinkedList<>();
            assets.addAll(teamOwner.appointmentAssetsInTeams.get(team).values());
            for (Asset asset:assets) {
                if(asset instanceof TeamOwner){
                    removeTeamOwner(asset , team);
                    teamOwner.appointmentAssetsInTeams.get(team).remove(asset.getID());
                }
                else if(asset instanceof TeamManager){
                    removeTeamManager(asset , team);
                    teamOwner.appointmentAssetsInTeams.get(team).remove(asset.getID());
                }
            }
        }
    }


    /**
     * remove Team Manager Appointment
     */
    public void removeAppointmentTeamManager(Asset asset, Team team)
    {
        removeTeamManager(asset , team);

        //maybe add more stuff here
    }

    private void removeTeamManager(Asset asset, Team team){
        String assetId = asset.getID();

        if(team.isActive()){
            if(appointmentAssetsInTeams.get(team).containsKey(assetId)){
                team.removeTeamManager((TeamManager) asset);
                appointmentAssetsInTeams.get(team).remove(assetId);

                /**
                 * if the User is no longer part of something in the system
                 * the user become not active
                 * */
                if(((TeamManager) asset).amountOfTeams == 0){
                    asset.deactivate();
                }
            }
        }
    }


    /**
     *
     */
    public void closeTeam(){

    }
    /**
     *
     */
    public void openTeam(){

    }

    /**
     *
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

    public void setClosedTeam(Team team) {
        isClosedTeam.replace(team, true);
    }

}