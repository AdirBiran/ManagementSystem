package Presentation;

import java.util.List;

public abstract class User extends Guest {

    protected String name;
    protected String ID; //unique id for system
    protected String mail;
    protected String password;
    protected List<String> messageBox; //object of notification

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

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
}
