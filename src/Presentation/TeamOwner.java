package Presentation;

import Domain.Team;
import java.util.List;

public class TeamOwner extends Manager {

    private List<Team> teams;
    private boolean isClosedTeam;

    public TeamOwner(String firstName,String lastName, String ID, String mail, List<Team> teams) {
        super(firstName,lastName, ID, mail);
        if(teams.size()<1)
            throw new RuntimeException("not enough teams to create TeamOwner");
        this.teams = teams;
        this.isClosedTeam= false;
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

    public boolean isClosedTeam() {
        return isClosedTeam;
    }

    public void setClosedTeam(boolean closed) {
        isClosedTeam = closed;
    }
}