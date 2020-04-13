package Presentation;

import Domain.Asset;
import Domain.Field;
import Domain.IdGenerator;
import Domain.Team;

import java.util.*;

public class TeamOwner extends Manager {

    private List<Team> teams;
    private HashMap<Team, Boolean> isClosedTeam;
    private HashMap<String, Asset> appointmentAssets;


    public TeamOwner(String firstName,String lastName, String mail) {
        super(firstName,lastName, "TO", mail);
        this.teams = new LinkedList<>();
        this.isClosedTeam = new HashMap<>();
        this.appointmentAssets = new HashMap<>();

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public void addTeam(Team team) {
       if(!teams.contains(team)){
           teams.add(team);
           isClosedTeam.put(team, false);
       }

    }

    /**
     * Appointment an assets
     */
    public void appointment(Asset asset, Team team)
    {
        String assetId = asset.getID();
        if(!appointmentAssets.containsKey(assetId)){
            appointmentAssets.put(assetId,asset);
        }

        if(asset instanceof User){
            if(asset instanceof TeamOwner){
                team.addTeamOwner((TeamOwner) asset);
            }
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
            //team.getField()
        }

    }

    /**
     * remove Appointment of assets
     */
    public void removeAppointment(Asset asset)
    {
        String assetId = asset.getID();
        if(appointmentAssets.containsKey(assetId)) {
            //remove asset from appointment list.
            appointmentAssets.remove(assetId);

            if (asset instanceof TeamOwner) {
                removeTeamOwnerAppointment((TeamOwner) asset);
            }
        }
    }

    /**
     * remove Team Owner Appointment
     */
    public void removeTeamOwnerAppointment(TeamOwner teamOwner){
        ArrayList<Asset> teamOwnerAppointment = new ArrayList<>();
        teamOwnerAppointment.addAll(teamOwner.appointmentAssets.values());

        for (Asset asset:teamOwnerAppointment) {
                teamOwner.removeAppointment(asset);
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

    public HashMap<String,Asset> getAppointmentAssets(){
        return appointmentAssets;
    }
}