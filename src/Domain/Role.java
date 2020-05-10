package Domain;

import java.util.List;
import java.util.Observable;

public abstract class Role{

    protected String myRole;
    protected User user;

    public User getUser() {
        return user;
    }

    public List<Notice> getMessageBox() {
        return user.getMessageBox();
    }

    public String myRole(){
        return myRole;
    }

}
