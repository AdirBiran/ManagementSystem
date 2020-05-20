package Service;

import Data.Database;
import Domain.*;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class NotificationSystem {

    public NotificationSystem() {
    }

    public static boolean notifyUser(User user, String message) {

       // if(!(Server.sendNotification(user.getID(),message)))
            return false;

       // return true;
    }
}
