package Service;

import Domain.Searcher;
import Presentation.FootballManagementSystem;

public class GuestSystem {

    private Searcher searcher;

    public GuestSystem( Searcher searcher) {
        this.searcher = searcher;
    }

    public void registrationToSystem(){
        // need send form to guest to fill
        // check if mail exsits in database
        // create new user
    }
/*
    public User logIn(String mail, String password){
        if(database.getUserNamesAndPasswords().get(mail).equals(password))
            return database.getUser(mail);
        return null;
    }*/
    public void search(){

    }
    public void viewInformation(){

    }
}
