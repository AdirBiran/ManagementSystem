package Domain;

import java.util.List;

public abstract class Role {

    protected List<Notice> messageBox;
    protected String myRole;

    public String myRole(){
        return myRole;
    }
}
