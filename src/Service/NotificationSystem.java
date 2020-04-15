package Service;

import Domain.*;
import Presentation.*;
import java.util.Date;

public class NotificationSystem {

    private RefereeManagement refereeManagement;
    private AssetManagement assetManagement;
    private MailSender mailSender; //??

    public NotificationSystem(LeagueAndGameManagement leagueAndGameManagement, RefereeManagement refereeManagement,
                              AssetManagement assetManagement, MailSender mailSender) {
        this.refereeManagement = refereeManagement;
        this.assetManagement = assetManagement;
        this.mailSender = mailSender;
    }

    /*
    When a team is closed / permanently closed or reopened, alerts are sent accordingly
     */
    public boolean openORCloseTeam(String option, Team team, boolean permanently){
        return assetManagement.alertBudgetException(option, team, permanently);
    }
    /*
    Send alerts to the referee when there is a change in game date
     */
    public void refereeAlertsChangeDate(Game game, Date newDate){
        String msg = "The game: "+game.toString()+" have new date: "+newDate;
        refereeManagement.sendNotification(game, msg);
    }
    /*
    Send alerts to the referee when there is a change in game location
     */
    public  void refereeAlertsChangeGameLocation(Game game, Field field){
        String msg= "The game: "+game.toString()+" change location: "+field.getLocation();
        refereeManagement.sendNotification(game, msg);
    }
    /*
    Send notifications to union representatives when a team exceeds the budget
     */
    public void exceededBudget(Team team){
        String msg = "Exception in the team budget"+team.getName()+", the budget is "+team.getBudget().getBalance();
        assetManagement.alertBudgetException(msg);
    }
    /*
    Send a notification to the user when the administrator removes it
     */
    public boolean UserRemovalNotification(String userMail){
        return mailSender.send(userMail);
    }

}
