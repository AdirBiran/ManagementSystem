package Domain;

public class Admin extends User {

    public Admin(String firstName,String lastName, String mail) {

        super(firstName,lastName, "A", mail);
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     *
     * @param team
     */
    public boolean permanentlyCloseTeam(Team team) {
        if(!team.isPermanentlyClosed()) {
            if (team.isActive())
                team.setActive(false);
            team.setPermanentlyClosed(true);
            //Maybe add a list of permanently closed teams to Database
            //What happens to the members of the teams???
        }
        return false;
    }

    /**
     *
     * @param user
     */
    public void removeUser(User user)
    {

        // need to check that the Admin doesn't delete himself

    }

    /**
     *
     * @param complaint
     */
    public void responseToComplaint(Complaint complaint)
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
