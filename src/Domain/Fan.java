package Domain;

import Data.Database;
import java.util.*;

public class Fan extends Role implements Observer {

    private String address;
    private String phone;
    private List<Complaint> complaints;
    private List<PersonalPage> followPages;

    public Fan(User user, String phone, String address) {
        this.user = user;
        this.address = address;
        this.phone = phone;
        complaints = new LinkedList<>();
        followPages = new LinkedList<>();
        myRole = "Fan";
    }

    public Fan(User user, String address, String phone, List<PersonalPage> personalPages, List<Complaint> complaints)
    {
        this.user = user;
        this.address = address;
        this.phone = phone;
        this.followPages = personalPages;
        this.complaints = complaints;
    }

    public boolean addPageToFollow(String pageId){
        PersonalPage personalPage = Database.getPersonalPage(pageId);
        if(!followPages.contains(personalPage)){
            followPages.add(personalPage);
            personalPage.addAFollower(this);
            //Database.updateObject(this);
            //Database.updateObject(personalPage);
            return true;
        }
        return false;
    }

    public void editPersonalInfo(User user, String firstName, String lastName, String address, String phone){
        user.editPersonalInfo(firstName, lastName);
        this.address = address;
        this.phone = phone;
        //Database.updateObject(this);
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
        user.addMessage(news);
    }

    @Override
    public String toString() {
        return "Fan" +
                ", address=" + address +
                ", phone=" + phone;
    }

    public String getUserInfo() {
        return "Fan" +
                ", firstName=" + user.getFirstName() +
                ", lastName=" + user.getLastName() +
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
        Date today = new Date();
        LinkedList<String> futureGames = new LinkedList<>();
        for(Game game : Database.getAllGames()){
            if(today.before(game.getDate()))
                futureGames.add(game.toString());
        }
        return futureGames;
    }

    public String getComplaintsId(){
        String listOfId = "";
        for (Complaint comp: complaints) {
            if(listOfId.equals("")){
                listOfId = listOfId + comp.getId();
            }
            else {
                listOfId = listOfId + ","+comp.getId();
            }
        }
        return listOfId;
    }

    public String getfollowPagesId(){
        String listOfId = "";
        for (PersonalPage page: followPages) {
            if(listOfId.equals("")){
                listOfId = listOfId + page.getId();
            }
            else {
                listOfId = listOfId + ","+page.getId();
            }
        }
        return listOfId;
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
