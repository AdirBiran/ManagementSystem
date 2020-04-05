package Presentation;

import Domain.Complaint;
import Domain.Team;
import Service.NotificationSystem;

import java.util.List;

public class Admin extends User {



    public Admin(String name, String ID, String mail) {
        super(name, ID, mail);
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @param team
     */
    public void deleteTeam(Team team)
    {
        if(!team.isPermanentlyClosed()){
            team.setActive(false);
            team.setPermanentlyClosed(true);
            //notifications.openORcloseTeam("permanently Closed", team , true);
            System.out.println("Done successfully");
        }
    }

    /**
     *
     * @param user
     */
    public void deleteUser(User user)
    {

        // need to check that the Admin doesn't delete himself

    }

    /**
     *
     */
    public void responseToComplaint()
    {

    }

    /**
     *
     */
    public void viewLog()
    {

    }

    /**
     *
     */
    public void trainModel()
    {

    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++

}
