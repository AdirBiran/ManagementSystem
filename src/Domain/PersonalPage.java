package Domain;

import Presentation.HasAPage;
import Presentation.User;

public class PersonalPage {

    private String id;
    private String data;
    private HasAPage user;

    public PersonalPage(String id, String data, HasAPage user) {
        this.id = id;
        this.data = data;
        this.user = user;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


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
