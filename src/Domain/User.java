package Domain;

import Data.Database;


import java.util.List;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends Guest{

    private String ID; //unique id for system
    private String firstName;
    private String lastName;
    private String mail;
    private List<Notice> messageBox;
    private boolean isActive;
    private List<Role> roles;
    private List<String> searchHistory;


    /**
     * constructor for user
     * @param firstName
     * @param lastName
     * @param ID - only first letter of user id... we add the number to it
     * @param mail
     */
    public User(String firstName, String lastName, String ID, String mail) {

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mail);
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID + IdGenerator.getNewId();
        if(!matcher.find())
            throw new RuntimeException("email address not valid");
        this.mail = mail;
        this.messageBox = new LinkedList<>();
        this.isActive = true;
        this.roles = new LinkedList<>();
        this.searchHistory = new LinkedList<>();

    }


    public void deactivate() {
        isActive = false;
    }

    public void reactivate() {
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        String userToString="User{" +
                "ID='" + ID + '\'' +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", isActive=" + isActive;

        for(Role role : roles){
            if(role instanceof Coach || role instanceof Player
               || role instanceof Fan){

                userToString = userToString + role.toString();
            }
        }
        return userToString+"}";
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public String getName() {
        return firstName+" "+lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void addMessage(Notice notice){
        if(notice!=null)
            messageBox.add(notice);
    }

    public String getMail() {
        return mail;
    }

    public List<Notice> getMessageBox() {
        return messageBox;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public boolean addToSearchHistory(String word){ return searchHistory.add(word);}

    public List<String> getSearchHistory() {
        return searchHistory;
    }


    public User logout(){return null;}

    public void editPersonalInfo(String firstName, String lastName){
        setFirstName(firstName);
        setLastName(lastName);
    }

    public List<Object> search(String wordToSearch)
    {
        addToSearchHistory(wordToSearch);
        return Database.searchObject(wordToSearch);
    }

    public List<String> viewSearchHistory(){
        return getSearchHistory();
    }


    public boolean changePassword(String oldPassword, String newPassword){

        return Database.changePassword(this.mail,oldPassword ,  newPassword);
    }

}
