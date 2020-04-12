package Presentation;

import Domain.Team;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TeamOwner extends Manager {

    private List<Team> teams;
    private HashMap<Team, Boolean> isClosedTeam;

    public TeamOwner(String firstName,String lastName, String mail, List<Team> teams) {
        super(firstName,lastName, "TO", mail);
        if(teams.size()<1)
            throw new RuntimeException("not enough teams to create TeamOwner");
        this.teams = teams;
        this.isClosedTeam = new HashMap<>();
        if(teams!=null){
            for (Team t : teams)
                isClosedTeam.put(t, false);
        }
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    /**
     *
     */
    public void appointment()
    {

    }

    /**
     *
     */
    public void removeAppointment()
    {

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