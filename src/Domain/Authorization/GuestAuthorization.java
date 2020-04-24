package Domain.Authorization;

import Data.Database;
import Domain.Fan;
import Domain.User;
import Domain.UserFactory;

import java.util.List;

public class GuestAuthorization extends AuthorizationRole {



    public GuestAuthorization() {
        super();
    }

    public List<Object> search(String wordToSearch){
        return Database.searchObject(wordToSearch);
    }

    public User register(String mail, String password, String firstName, String lastName, String phone, String address)
    {
        Object[] fan = UserFactory.getNewFan(password, firstName, lastName, mail, phone, address);
        if(fan!=null)
            return (User)fan[0];
        return null;
    }
    public User login(String mail, String password)
    {
        if(Database.authenticationCheck(mail, password))
            return Database.getUserByMail(mail);
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof GuestAuthorization;
    }


}
