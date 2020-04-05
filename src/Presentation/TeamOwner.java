package Presentation;

import Domain.Team;
import Service.NotificationSystem;

public class TeamOwner extends Manager {

    private Team team;
    private boolean closedTeam;
    private NotificationSystem notifications;

    public TeamOwner() { }


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
    public void closeTeam()
    {
        if(team.isActive() && !team.isPermanentlyClosed()){
            team.setActive(false);
            //removing team member privileges
            closedTeam=true;
            notifications.openORcloseTeam("closed", team, false);
            //past activity saved
            System.out.println("Done successfully");
        }
    }
    /**
     *
     */
    public void openTeam(){
        if(!team.isActive() && isClosedTeam() &&!team.isPermanentlyClosed()){
            team.setActive(true);
            closedTeam=false;
            notifications.openORcloseTeam("opened", team, false);
            //re-configure permissions for team member
            System.out.println("Done successfully");
        }
    }

    /**
     *
     */
    public void reportFinanceTrans()
    {

    }
    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public Team getTeam() {
        return team;
    }

    public boolean isClosedTeam() {
        return closedTeam;
    }
}
