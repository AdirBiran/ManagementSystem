package Domain;

import java.util.LinkedList;
import java.util.List;

public class Fan {


    private String id;
    private String address;
    private String phone;
    private List<Complaint> complaints;
    private List<PersonalPage> followPages;


    public Fan(String id,String phone, String address) {
        this.id = id;
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



}
