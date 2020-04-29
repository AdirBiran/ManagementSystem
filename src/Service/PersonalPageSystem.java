package Service;

import Domain.HasPage;
import Domain.Role;
import Domain.User;

public class PersonalPageSystem {

    public PersonalPageSystem() {
    }

    public boolean uploadToPage(User user, String data){
        HasPage authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.uploadToPage(data);
            return true;
        }
        return false;
    }

    private HasPage getAuthorization(User user) {
        for(Role role : user.getRoles()){
            if(role instanceof HasPage)
                return (HasPage)role;
        }
        return null;
    }
}
