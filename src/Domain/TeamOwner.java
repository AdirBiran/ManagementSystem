package Domain;


import java.util.*;

public class TeamOwner extends Manager {

    public TeamOwner(String firstName,String lastName, String mail) {
        super(firstName,lastName, "TO", mail);
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++



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