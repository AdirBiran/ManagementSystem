package Domain;

import java.util.List;
import java.util.LinkedList;

public class PersonalPage {

    private String id;
    private String data;
    private User user;
    private List<Fan> followers;

    public PersonalPage(String data, User user) {
        this.id = "PP"+IdGenerator.getNewId();
        this.data = data;
        this.user = user;
        followers = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    @Override
    public String toString() {
        return "PersonalPage{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", user=" + user +
                '}';
    }

    public String getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getData() {
        return data;
    }

    public User getUser() {
        return user;
    }

    public void addAFollower(Fan follower) {
        if(!followers.contains(follower)){
            followers.add(follower);
            follower.addPageToFollow(this.getId());
        }
    }

    public void setData(String data) {
        this.data = data;
    }
    public void addData(String data) {
        this.data +=" "+ data;
    }
}
