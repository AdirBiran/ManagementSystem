package Domain;

import Data.Database;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public boolean addPageToFollow(String pageId){
        PersonalPage personalPage = Database.getPage(pageId);
        if(!followPages.contains(personalPage)){
            followPages.add(personalPage);
            personalPage.addAFollower(this);
            return true;
        }
        return false;
    }

    public void editPersonalInfo(User user, String firstName, String lastName, String address, String phone){
        user.editPersonalInfo(firstName, lastName);
        this.address = address;
        this.phone = phone;
    }

    public boolean followGames(List<String> gamesId , boolean receiveAlerts){
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
        return Database.addComplaint(complaint);
    }

    public List<String> getFollowedPages(){
        List<String> pages = new LinkedList<>();
        for(PersonalPage p: followPages)
            pages.add(p.toString());
        return pages;
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


    public List<String> getAllPages(){
        List<String> pages = new LinkedList<>();
        for(PersonalPage p: Database.getAllPages())
            pages.add(p.toString());
        return pages;
    }

    public List<String> getAllFutureGames(){
        List<String> games = new LinkedList<>();
        for(Game g: Database.getAllFutureGames())
            games.add(g.toString());
        return games;
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Fan;
    }

    @Override
    public String myRole() {
        return "Fan";
    }

    @Override
    public String toString() {
        return "Fan" +
                ", address=" + address +
                ", phone=" + phone;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Event) {
            arg = arg.toString();
        }
        String news = (String)arg;
        user.addMessage(news);
    }
}
