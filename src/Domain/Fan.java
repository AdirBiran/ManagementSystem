package Domain;

import java.util.List;

public class Fan extends User {

    private String address;
    private List<Complaint> complaints;
    private List<PersonalPage> pages;

    public Fan()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    /**
     *
     * @param page
     */
    public void followPage(PersonalPage page)
    {

    }

    /**
     *
     * @param game
     */
    public void gameNotification(Game game)
    {

    }

    /**
     *
     */
    public void submitAComplaint()
    {

    }

    /**
     *
     */
    public void viewSearchHistory()
    {

    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return
     */
    public List<Complaint> getComplaints() {
        return complaints;
    }

    /**
     *
     * @return
     */
    public List<PersonalPage> getPages() {
        return pages;
    }
}
