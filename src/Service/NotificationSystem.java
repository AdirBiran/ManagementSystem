package Service;

import Domain.*;

public class NotificationSystem {

    public NotificationSystem() {
    }

    public static boolean notifyUser(User user, String message) {

        if(!(Server.sendNotification(user.getID(),message)))
            return false;

        return true;
    }
}
