package Domain;

import Data.Database;

import java.util.LinkedList;
import java.util.List;

public class Fan implements Role{



    private String address;
    private String phone;
    private List<Complaint> complaints;
    private List<PersonalPage> followPages;


    public Fan(String phone, String address) {

        this.address = address;
        this.phone = phone;
        complaints = new LinkedList<>();
        followPages = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    public void editDetails(String address, String phone) {

        this.address = address;
        this.phone = phone;

    }
    public boolean addPageToFollow(PersonalPage page){
        if(!followPages.contains(page)){
            followPages.add(page);
            page.addAFollower(this);
            return true;
        }
        return false;
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


    public void editPersonalInfo(String firstName, String lastName, String address, String phone){

        editDetails(address, phone);

    }
    public boolean followPage(PersonalPage page){
        return addPageToFollow(page);
    }

    public boolean followGame(Game game , ReceiveAlerts receiveAlerts){
        return game.addFanForNotifications(this, receiveAlerts);
    }
    public boolean submitComplaint(String description)
    {
        if(description.length()<1)
            return false;
        Complaint complaint = new Complaint(description, this);
        return Database.addComplaint(complaint);

    }

    public List<PersonalPage> getFollowedPages()
    {
        return getFollowPages();
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
        return  '\''+ ", Fan phone='" + phone + '\''+
                ", Fan address='" + address + '\'' ;
    }
}
