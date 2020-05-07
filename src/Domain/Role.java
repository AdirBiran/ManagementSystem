package Domain;

import java.util.List;
import java.util.Observable;

public abstract class Role{

    protected List<Notice> messageBox;
    protected String myRole;

    public List<Notice> getMessageBox() {
        return messageBox;
    }
    public String myRole(){
        return myRole;
    }

}
