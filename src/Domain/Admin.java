package Domain;

import java.util.List;

public class Admin extends User {

    private List<Complaint> allComplaints; // needs to be synchronized with all users' complaints

    public Admin()
    {
        System.out.println("Test");
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

    /**
     *
     * @return
     */
    public List<Complaint> getAllComplaints() {
        return allComplaints;
    }
}
