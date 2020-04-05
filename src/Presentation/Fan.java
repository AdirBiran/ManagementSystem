package Presentation;

import Domain.Complaint;
import Domain.Game;
import Domain.PersonalPage;
import Service.NotificationSystem;

import java.util.LinkedList;
import java.util.List;

public class Fan extends User {

    private String address;
    private List<Complaint> complaints;
    private List<PersonalPage> pages;
    private NotificationSystem notifications;

    public Fan(String addr)
    {
        address = addr;
        complaints = new LinkedList<>();
        pages = new LinkedList<>();
    }
    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     * use case - registration for game alerts- after choose games for alerts
     */
    public void registrationForGameAlerts(LinkedList <Game> games){
        for(Game game: games) {
            if (!notifications.registrationForGameAlerts(this, game))
                System.out.println("Registration failed:" + game.toString());
        }
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
