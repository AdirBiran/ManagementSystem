package Domain;

import Presentation.Fan;
import Presentation.HasAPage;
import java.util.List;
import java.util.LinkedList;

public class PersonalPage {

    private String id;
    private String data;
    private HasAPage user;
    private List<Fan> followers;

    public PersonalPage(String data, HasAPage user) {
        this.id = "PP"+IdGenerator.getNewId();
        this.data = data;
        this.user = user;
        user.setPage(this);
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

    public HasAPage getUser() {
        return user;
    }

    public void addAFollower(Fan follower) {
        if(!followers.contains(follower)){
            followers.add(follower);
            follower.addPageToFollow(this);
        }
    }
}
