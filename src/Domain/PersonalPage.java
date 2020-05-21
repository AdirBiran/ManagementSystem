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

    public PersonalPage(String id, User owner, String data, List<Fan> followers)
    {
        this.id = id;
        this.user = owner;
        this.data = data;
        this.followers = followers;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    @Override
    public String toString() {
        return  id +","+user.getName()+","+data;

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

    public List<Fan> getFollowers(){
        return followers;
    }

    public String getFollowersIds(){
        String listOfId = "";
        for (Fan fan: followers) {
            if(listOfId.equals("")){
                listOfId = listOfId + fan.getUser().getID();
            }
            else {
                listOfId = listOfId + ","+fan.getUser().getID();
            }
        }
        return listOfId;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void addData(String data) {
        this.data +=" "+ data;
    }
}
