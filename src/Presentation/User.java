package Presentation;

import java.util.List;
import java.util.LinkedList;

public abstract class User extends Guest {

    protected String ID; //unique id for system
    protected String name;
    protected String mail;
    protected List<String> messageBox; //object of notification

    public User(String name,String ID , String mail) {
        this.name = name;
        this.ID = ID;
        this.mail = mail;
        this.messageBox = new LinkedList<>();
    }

    /**
     *
     */
    public void logOut()
    {

    }

    /**
     *
     */
    public void editDetails()
    {

    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getID() {
        return ID;
    }

}
