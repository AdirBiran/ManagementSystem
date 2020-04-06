package Presentation;

import Domain.Team;
import java.util.List;

public class TeamOwner extends Manager {

    private List<Team> teams;

    public TeamOwner(String name, String ID, String mail, List<Team> teams) {
        super(name, ID, mail);
        if(teams.size()<1)
            throw new RuntimeException("not enough teams to create TeamOwner");
        this.teams = teams;
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

}
