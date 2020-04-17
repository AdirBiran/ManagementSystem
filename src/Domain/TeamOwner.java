package Domain;


import java.util.*;

public class TeamOwner extends Manager {

    private List<Team> teams;
    private HashMap<Team, Boolean> isClosedTeam;
    private HashMap<Team,HashMap<String,User>> appointmentAssetsInTeams;


    public TeamOwner(String firstName,String lastName, String mail) {
        super(firstName,lastName, "TO", mail);
        this.teams = new LinkedList<>();
        this.isClosedTeam = new HashMap<>();
        this.appointmentAssetsInTeams = new HashMap<>();

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public void addTeam(Team team) {
        if(!teams.contains(team)){
            teams.add(team);
            isClosedTeam.put(team, false);

            //not sure about this
            appointmentAssetsInTeams.put(team,new HashMap<>());
        }

    }

    public void addExistAsset(){
    }

    public void addNewAsset(){

    }

    public void removeAsset(){

    }

    public void appointmentTeamOwner(){

    }

    public void appointmentTeamManager(){


    }

    /**
     * remove Team Owner Appointment
     */
    public void removeAppointmentTeamOwner() {

    }



    /**
     * remove Team Manager Appointment
     */
    public void removeAppointmentTeamManager()
    {



    }

    public void closeTeam(){

    }
    /*

     */
    public void openTeam(){

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