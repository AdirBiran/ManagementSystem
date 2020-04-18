package Domain;

import java.util.List;
import java.util.LinkedList;

public abstract class User extends Guest {

    protected String ID; //unique id for system
    protected String firstName;
    protected String lastName;
    protected String mail;
    protected List<Notice> messageBox;
    protected boolean isActive;
    protected int amountOfTeams;
    //protected List<Role> roles;


    /**
     * constructor for user
     * @param firstName
     * @param lastName
     * @param ID - only first letter of user id... we add the number to it
     * @param mail
     */
    public User(String firstName, String lastName, String ID, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID + IdGenerator.getNewId();
        this.mail = mail;
        this.messageBox = new LinkedList<>();
        this.isActive = true;
        this.amountOfTeams=0;
        //roles = new LinkedList<>();
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
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    /**
     *
     */
    public void logOut() {

    }

    /**
     *
     */
    public void editDetails(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName =lastName;
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public String getName() {
        return firstName+" "+lastName;
    }

    public String getID() {
        return ID;
    }

    public void addMessage(Notice notice){
        if(messageBox!=null)
            messageBox.add(notice);
    }
    public int getAmountOfTeams(){
        return amountOfTeams;
    }
    public void setAmountOfTeams(int number){
        this.amountOfTeams=this.amountOfTeams+number;
    }

    public String getMail() {
        return mail;
    }

    public List<Notice> getMessageBox() {
        return messageBox;
    }
}
