package Presentation;

import Domain.Team;

public class Admin extends User {

    public Admin(String firstName,String lastName, String mail) {

        super(firstName,lastName, "A", mail);
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     *
     * @param team
     */
    public void deleteTeam(Team team)
    {

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
