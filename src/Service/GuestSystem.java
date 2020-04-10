package Service;

import Domain.Searcher;
import Domain.UserManagement;
import Presentation.Guest;
import Presentation.User;

public class GuestSystem {

    protected Searcher searcher;
    protected UserManagement userManagement;

    public GuestSystem(Searcher searcher, UserManagement userManagement) {
        this.searcher = searcher;
        this.userManagement = userManagement;
    }

    public boolean registrationToSystem(String mail, String password, String firstName, String lastName,
                                        String phone, String address){
        if(userManagement.registrationToSystem(mail, password, firstName, lastName, phone, address))
            return true;
        return false;
    }

    /**
     * @param mail- Email login to system
     * @param password- password for the same user
     * @return if exists return connected user
     * if the password is invalid or there is no such email in the system return null
     */
    public User logIn(String mail, String password){
        return userManagement.logInUserToSystem(mail, password);
    }

    /**
     *
     * @param g - the guest in the system
     * @param wordToSearch - a word for searching information in the system
     */
    public void search(Guest g, String wordToSearch){
        searcher.searchInfo(g, wordToSearch);

    }
    /**
     * @param viewAbout- the guest chooses what to watch - teams, players, coaches, leagues and more
     */
    public void viewInformation(String viewAbout){
        searcher.viewInfoAbout(viewAbout);

    }
}
