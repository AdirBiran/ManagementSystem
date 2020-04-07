package Service;

import Domain.*;
import Presentation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class NotificationSystem {

    private GameManagement gameManagement;
    private RefereeManagement refereeManagement;
    private AssetManagement assetManagement;
    private FootballManagementSystem system;

    public NotificationSystem(GameManagement gameManagement, RefereeManagement refereeManagement,
                              AssetManagement assetManagement, FootballManagementSystem system) {
        this.gameManagement = gameManagement;
        this.refereeManagement = refereeManagement;
        this.system = system;
        this.assetManagement = assetManagement;
    }

    /**
     * registration for game alerts
     * @param fan - want to get alerts for the games
     * @param games -fan will receive alerts for this games
     * @param notice -how to get the alerts
     */
    public void registrationForGameAlerts(Fan fan, List<Game> games, Notice notice){
        gameManagement.registrationForGameAlerts(fan, games, notice);
    }

    /**
     * @param team- team to close/closed permanently or open
     */
    public void openORcloseTeam(String option, Team team, boolean permanently){
        assetManagement.sendNotification(option, team, permanently);
    }

    public void refereeAlertsChangeDate(Game game, Date newDate){
        String msg = "The game: "+game.toString()+" have new date: "+newDate;
        refereeManagement.sendNotification(game, msg);
    }
    public void refereeAlertsChangeDate(Game game, Time newTime){
        String msg= "The game: "+game.toString()+" have new time: "+newTime;
        refereeManagement.sendNotification(game, msg);
    }
    public  void refereeAlertsChangeGameLocation(Game game, Field field){
        String msg= "The game: "+game.toString()+" change location: "+field.getLocation();
        refereeManagement.sendNotification(game, msg);
    }
    public void exceededBuget(Budget budget){
        String msg = "There is an exception to the budget, the budget is "+budget.getBalance();
        assetManagement.sendNotification(msg);
    }
    public void UserRemovalNotification(User user){
        user.addMessage(new Notice(true, "Your subscription has been removed"));
    }

}
