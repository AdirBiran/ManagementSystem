package Presentation;

import Domain.Complaint;
import Domain.PersonalPage;
import java.util.LinkedList;
import java.util.List;

public class Fan extends User {

    private String address;
    private String phone;
    private List<Complaint> complaints;
    private List<PersonalPage> pages;
    private List<String> searchHistory;

    public Fan(String mail, String password, String firstName, String lastName,
               String phone, String address, String ID) {
        super(firstName, lastName, ID, mail);
        this.address = address;
        this.phone = phone;
        complaints = new LinkedList<>();
        pages = new LinkedList<>();
        searchHistory = new LinkedList<>();
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
    public void editDetails(String firstName, String lastName, String password, String address, String phone) {
        super.editDetails(firstName, lastName);
        this.address = address;
        this.phone = phone;
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

    public boolean addToSearchHistory(String word){ return searchHistory.add(word);}

    public List<String> getSearchHistory() {
        return searchHistory;
    }

}
