package Service;

import Domain.HasPage;
import Domain.Role;
import Domain.User;

public class PersonalPageSystem {

    public PersonalPageSystem() {
    }

    public boolean uploadToPage(User user, String data){
        Role role = user.checkUserRole("HasPage");
        if(role instanceof HasPage){
            ((HasPage)role).uploadToPage(data);
            return true;
        }
        return false;
    }
}
