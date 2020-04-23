package Service;

import Domain.Authorization.AuthorizationRole;
import Domain.Authorization.HasPageAuthorization;
import Domain.User;

public class PersonalPageSystem {


    public PersonalPageSystem() {
    }

    public boolean uploadToPage(User user, String data){
        HasPageAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.uploadToPage(data);
            return true;
        }
        return false;
    }

    private HasPageAuthorization getAuthorization(User user) {
        for(AuthorizationRole role : user.getRoles()){
            if(role.getRoleName().contains("HasPage"))
                return (HasPageAuthorization)role;
        }
        return null;
    }
}
