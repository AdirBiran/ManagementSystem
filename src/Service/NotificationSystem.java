package Service;

import Data.Database;
import Domain.*;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class NotificationSystem extends Observable implements Observer {

    public NotificationSystem() {
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    /*
        When a team is closed / permanently closed or reopened, alerts are sent accordingly
         */
    public boolean openORCloseTeam(String option, String teamId, boolean permanently){
        return alertBudgetException(option, teamId, permanently);
    }
    /*
    Send alerts to the referee when there is a change in game date
     */
    public void refereeAlertsChangeDate(Game game, Date newDate){
        String msg = "The game: "+game.toString()+" have new date: "+newDate;
        sendNotification(game, msg);
    }
    /*
    Send alerts to the referee when there is a change in game location
     */
    public  void refereeAlertsChangeGameLocation(Game game, Field field){
        String msg= "The game: "+game.toString()+" change location: "+field.getLocation();
        sendNotification(game, msg);
    }

    private void sendNotification(Game game, String msg) {
        /*for(User sideRef : game.getSideReferees())
            sideRef.addMessage(new Notice(msg));
        game.getMainReferee().addMessage(new Notice(msg));*/
    }

    /*
    Send notifications to union representatives when a team exceeds the budget
     */
    public void exceededBudget(String teamId){
        /*
        String msg = "Exception in the team budget"+team.getName()+", the budget is "+team.getBudget().getBalance();
        for(User owner : team.getTeamOwners()){
            owner.addMessage(new Notice(msg));
        }*/
    }
    /*
    Send a notification to the user when the administrator removes it
     */
    public boolean UserRemovalNotification(String userMail){
        return MailSender.send(userMail, "you have been removed from system");
    }

    public void notificationForAppointment(User user, boolean addAppointment){
        String message;
        if(addAppointment){
            message = "You have a new appointment";
        }
        else{
            message = "Your appointment hes been removed";
        }
        user.addMessage(new Notice(message));
    }

    /*
    When a team is closed / permanently closed or reopened, alerts are sent accordingly
     */
    public boolean alertBudgetException(String option, String teamId, boolean permanently) {
        /*if((!team.isActive() && option.equals("closed")) || (team.isActive() && option.equals("open"))) {
            for (User owner : team.getTeamOwners()) {
                owner.addMessage(new Notice("team " + option + " : " + team.getName()));
            }
            for (User teamManager : team.getTeamManagers())
                teamManager.addMessage(new Notice("team " + option + " : " + team.getName()));

            if (!permanently) {
                for (User admin : Database.getSystemAdmins())
                    admin.addMessage(new Notice("team " + option + " : " + team.getName()));
            }
            return true;
        }*/
        return false;
    }
}
