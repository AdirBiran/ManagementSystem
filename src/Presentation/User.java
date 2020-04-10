package Presentation;

import Domain.Notice;

import java.util.List;
import java.util.LinkedList;

public abstract class User extends Guest {

    protected String ID; //unique id for system
    protected String firstName;
    protected String lastName;
    protected String name;
    protected String mail;
    protected List<Notice> messageBox;
    protected boolean isActive;


    public User(String firstName,String lastName, String ID , String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.mail = mail;
        this.messageBox = new LinkedList<>();
        this.isActive = true;
    }
    public void deactivate() {
        isActive = false;
    }
    public void activate() {
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
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
    public void editDetails(String firstName, String lastName)
    {

    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public void addMessage(Notice notice){
        if(messageBox!=null)
            messageBox.add(notice);
    }
}
