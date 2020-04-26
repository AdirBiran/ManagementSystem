package Service;


import Domain.Guest;
import Domain.User;

import java.util.List;

public class GuestSystem {


    Guest authorization;


    public GuestSystem() {
        authorization = new Guest();
    }

    /*
    Guest registration for the system
     */
    public User registrationToSystem(String mail, String password, String firstName, String lastName,
                                        String phone, String address){
        return authorization.register(mail, password, firstName, lastName, phone, address);

    }

    /*
     * User login to system
     * if exists return connected user
     * if the password is invalid or there is no such email in the system return null
     */
    public User logIn( String mail, String password){

        return authorization.login(mail, password);
    }

    /*
    Search results in a system
     */
    public List<Object> search( String wordToSearch){
       return authorization.search(wordToSearch);
    }
    /*
    the guest chooses what to watch - teamsToManage, players, coaches, leagues and more
     *//*
    public void viewInformation(String viewAbout){
        searcher.viewInfoAbout(viewAbout);

    }*/
}
