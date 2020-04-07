package Domain;

import Data.Database;
import Presentation.Referee;

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
}
