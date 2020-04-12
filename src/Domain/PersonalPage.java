package Domain;

import Presentation.HasAPage;
import Presentation.User;

public class PersonalPage {

    private String id;
    private String data;
    private HasAPage user;

    public PersonalPage(String data, HasAPage user) {
        this.id = "PP"+IdGenerator.getNewId();
        this.data = data;
        this.user = user;
        user.setPage(this);
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
}
