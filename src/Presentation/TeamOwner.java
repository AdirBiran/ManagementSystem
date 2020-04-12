package Presentation;

import Domain.IdGenerator;
import Domain.Team;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

public class TeamOwner extends Manager {

    private List<Team> teams;
    private HashMap<Team, Boolean> isClosedTeam;

    public TeamOwner(String firstName,String lastName, String mail) {
        super(firstName,lastName, "TO", mail);
        this.teams = new LinkedList<>();
        this.isClosedTeam = new HashMap<>();

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public void addTeam(Team team) {
       if(!teams.contains(team)){
           teams.add(team);
           isClosedTeam.put(team, false);
       }

    }

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