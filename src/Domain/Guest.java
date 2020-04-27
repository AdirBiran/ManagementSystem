package Domain;

import Data.Database;
import Domain.User;
import Domain.UserFactory;

import java.util.List;

public class Guest {


    public Guest() {

    }

    public List<Object> search(String wordToSearch){
        return Database.searchObject(wordToSearch);
    }

    public User register(String mail, String password, String firstName, String lastName, String phone, String address)
    {
        return UserFactory.getNewFan(password, firstName, lastName, mail, phone, address);
    }
    public User login(String mail, String password)
    {
        return Database.getUserByMail(mail ,password );
    }
}
