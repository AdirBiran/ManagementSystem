package Presentation;

import Domain.Notice;

import java.util.List;
import java.util.LinkedList;

public abstract class User extends Guest {

    protected String ID; //unique id for system
    protected String name;
    protected String mail;
    protected List<Notice> messageBox;

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
