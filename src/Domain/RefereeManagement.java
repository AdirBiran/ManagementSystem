package Domain;

import Data.Database;
import Presentation.Referee;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.*;

public class RefereeManagement {

    private Database database;

    public RefereeManagement(Database database) {
        this.database = database;
    }

    public void sendNotification(Game game, String msg) {
        for(Referee sideRef : game.getSideReferees())
            sideRef.addMessage(new Notice(true, msg));
        game.getMainReferee().addMessage(new Notice(true, msg));
    }

    public boolean appointReferee(String firstName, String lastName, String id, String mail, String training) {
        Referee referee = new Referee(firstName,lastName, id, mail, training);
        String randomInitialPassword = PasswordGenerator.generateRandPassword(6);
        //send referee mail to approve?
        return database.addUser(id, randomInitialPassword, referee);
    }

}
