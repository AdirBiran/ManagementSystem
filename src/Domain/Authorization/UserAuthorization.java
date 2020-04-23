package Domain.Authorization;

import Data.Database;
import Domain.User;

import java.util.List;

public class UserAuthorization extends GuestAuthorization {

    private User user;


    public UserAuthorization(User user) {
        super();
        this.user = user;
    }

    public User logout(){return null;}

    public void editPersonalInfo(String firstName, String lastName){
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    public List<Object> search(String wordToSearch)
    {
        user.addToSearchHistory(wordToSearch);
        return Database.searchObject(wordToSearch);
    }

    public List<String> viewSearchHistory(){return user.getSearchHistory();}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserAuthorization;
    }

    public User getUser() {
        return user;
    }

    public boolean changePassword(String oldPassword, String newPassword){
        if(Database.authenticationCheck(user.getMail(), oldPassword)){
            Database.changePassword(user.getMail(), newPassword);
            return true;
        }
        return false;
    }

    public String getRoleName(){return "UserAuthorization";}
}
