package Service;

import Domain.Searcher;
import Domain.UserManagement;
import Domain.Guest;
import Domain.User;

import java.util.List;

public class GuestSystem {

    protected Searcher searcher;
    protected UserManagement userManagement;

    public GuestSystem(Searcher searcher, UserManagement userManagement) {
        this.searcher = searcher;
        this.userManagement = userManagement;
    }

    /*
    Guest registration for the system
     */
    public boolean registrationToSystem(String mail, String password, String firstName, String lastName,
                                        String phone, String address){
        if(userManagement.registrationToSystem(mail, password, firstName, lastName, phone, address))
            return true;
        return false;
    }

    /*
     * User login to system
     * if exists return connected user
     * if the password is invalid or there is no such email in the system return null
     */
    public User logIn(String mail, String password){
        return userManagement.logInUserToSystem(mail, password);
    }

    /*
    Search results in a system
     */
    public List<Object> search(Guest g, String wordToSearch){
       return searcher.searchInfo(g, wordToSearch);

    }
    /*
    the guest chooses what to watch - teams, players, coaches, leagues and more
     */
    public void viewInformation(String viewAbout){
        searcher.viewInfoAbout(viewAbout);

    }
}
