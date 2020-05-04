package Service;

import Domain.HasPage;
import Logger.Logger;
import Domain.Role;
import Domain.User;

public class PersonalPageSystem {

    public PersonalPageSystem() {
    }

    public boolean uploadToPage(User user, String data){
        Role role = user.checkUserRole("HasPage");
        if(role instanceof HasPage){
            ((HasPage)role).uploadToPage(data);
            Logger.logEvent(user.getID() + " (User)","Uploaded data to personal page");
            return true;
        }
        Logger.logError(user.getID() + "No personal page");

        return false;
    }
}
