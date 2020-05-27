package Domain;

import Data.Database;
import java.util.*;

public class Fan extends Role implements Observer {

    private String address;
    private String phone;
    private List<Complaint> complaints;
    private List<PersonalPage> followPages;

    public Fan(String userId, String phone, String address) {
        this.userId = userId;
        this.address = address;
        this.phone = phone;
        complaints = new LinkedList<>();
        followPages = new LinkedList<>();
        myRole = "Fan";
    }

    public Fan(String userId, String address, String phone, List<PersonalPage> personalPages, List<Complaint> complaints)
    {
        this.userId = userId;
        this.address = address;
        this.phone = phone;
        this.followPages = personalPages;
        this.complaints = complaints;
    }

    public boolean addPageToFollow(String pageId){
        PersonalPage personalPage = Database.getPersonalPage(pageId);
        if(!followPages.contains(personalPage)){
            followPages.add(personalPage);
            Database.updateObject(this);
            personalPage.addAFollower(this);
            return true;
        }
        return false;
    }

    public void editPersonalInfo(User user, String firstName, String lastName, String address, String phone){
        user.editPersonalInfo(firstName, lastName);
        this.address = address;
        this.phone = phone;
        Database.updateObject(this);
    }

    public boolean registrationForGamesAlerts(List<String> gamesId , boolean receiveAlerts){
        for(String gameId: gamesId) {
            Game game = Database.getGame(gameId);
            if(!game.addFanForNotifications(this, receiveAlerts))
                return false;
        }
        return true;
    }
    public boolean submitComplaint(String description)
    {
        if(description.length()<1)
            return false;
        Complaint complaint = new Complaint(description, this);
        complaints.add(complaint);
        Database.updateObject(this);
        return Database.addComplaint(complaint);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Fan;
    }

    @Override
    public String myRole() {
        return "Fan";
    }

    @Override
    public void update(Observable o, Object arg) {
        String news = (String)arg;
        Database.getUser(userId).addMessage(news);
    }

    @Override
    public String toString() {
        return "Fan" +
                ", address=" + address +
                ", phone=" + phone;
    }

    public String getUserInfo() {
        return "Fan" +
                ", firstName=" + Database.getUser(userId).getFirstName() +
                ", lastName=" + Database.getUser(userId).getLastName() +
                ", phone=" + phone +
                ", address=" + address;
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++

    public List<String> getFollowedPages(){
        List<String> pages = new LinkedList<>();
        for(PersonalPage p: followPages)
            pages.add(p.toString());
        return pages;
    }

    public List<String> getAllPages(){
        List<String> pages = new LinkedList<>();
        for(PersonalPage p: Database.getAllPages())
            pages.add(p.toString());
        return pages;
    }

    /*
    this function returns a list of all future games
     */
    public static LinkedList<String> getAllFutureGames(){
        Date today = Database.getCurrentDate();
        LinkedList<String> futureGames = new LinkedList<>();
        for(Game game : Database.getAllGames()){
            if(today.before(game.getDate()))
                futureGames.add(game.toString());
        }
        return futureGames;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<PersonalPage> getFollowPages() {
        return followPages;
    }
}
