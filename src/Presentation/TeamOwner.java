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
    /*public void closeTeam()
    {
        if(team.isActive() && !team.isPermanentlyClosed()){
            team.setActive(false);
            //removing team member privileges
            closedTeam=true;
            notifications.openORcloseTeam("closed", team, false);
            //past activity saved
            System.out.println("Done successfully");
        }
    }*/
    /**
     *
     */
    /*public void openTeam(){
        if(!team.isActive() && isClosedTeam() &&!team.isPermanentlyClosed()){
            team.setActive(true);
            closedTeam=false;
            notifications.openORcloseTeam("opened", team, false);
            //re-configure permissions for team member
            System.out.println("Done successfully");
        }
    }*/

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
