package Presentation;

import Domain.Complaint;
import Domain.PersonalPage;
import java.util.LinkedList;
import java.util.List;

public class Fan extends User {

    private String address;
    private List<Complaint> complaints;
    private List<PersonalPage> pages;

    public Fan(String name, String ID, String mail, String address) {
        super(name, ID, mail);
        this.address = address;
        complaints = new LinkedList<>();
        pages = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     * registration for game alerts- In menu of fan
     */
    public void registrationForGameAlerts(){
        //notificationSystem.registrationForGameAlerts(this, listOfGames, notice)
    }
    /**
     *
     * @param page
     */
    public void followPage(PersonalPage page)
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


    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public String getAddress() {
        return address;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<PersonalPage> getPages() {
        return pages;
    }

}
