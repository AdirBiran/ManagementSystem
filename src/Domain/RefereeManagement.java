package Domain;

import Data.Database;

public class RefereeManagement {

    private Database database;

    public RefereeManagement(Database database) {
        this.database = database;
    }

    /*
    Send alerts to the referee when there is a changes in game
    */
    public void sendNotification(Game game, String msg) {
        for(Referee sideRef : game.getSideReferees())
            sideRef.addMessage(new Notice(true, msg));
        game.getMainReferee().addMessage(new Notice(true, msg));
    }

    public Referee appointReferee(String firstName, String lastName, String mail, String training) {
        Referee referee = new Referee(firstName,lastName, mail, training);
        String initialPassword = "Ac1234";
        //send referee mail to approve?
        if(database.addUser(initialPassword, referee))
            return referee;
        else
            return null;
    }

    /**
     *
     */
    public void viewGames()
    {

    }

}
