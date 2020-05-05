package Service;

import Domain.*;
import Logger.Logger;

public class FinanceTransactionsSystem
{
    private NotificationSystem notificationSystem;

    public FinanceTransactionsSystem(NotificationSystem notificationSystem) {
        this.notificationSystem = notificationSystem;
    }

    /*
     this function allows a Team Owner to add new income to his team budget
    */
    public boolean reportNewIncome(User user, Team team, double income){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionFinance())
            {
                Logger.logError("Reporting new income Failed");
                return false;
            }
            boolean success = ((Manager) role).reportIncome(team, income);

            if (success)
                Logger.logEvent(user.getID(), "Reported income " + income);
            else
                Logger.logError("Reporting new income Failed");

            return success;
        }
        return false;
    }

    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(User user, Team team, double expanse){
        Role role = user.checkUserRole("Team");
        if(role instanceof Manager){
            if(role.myRole().equals("TeamManager") && !((TeamManager)role).isPermissionFinance())
            {
                Logger.logError("Reporting new expense Failed");
                return false;
            }
            if(((Manager) role).reportExpanse(team, expanse))
            {
                Logger.logEvent(user.getID(), "Reported expense " + expanse);
                return true;

            }
            else
                notificationSystem.exceededBudget(team);
        }
        Logger.logError("Reporting new expense Failed");

        return false;
    }

    public double getBalance(User user, Team team){
        Role role = user.checkUserRole("Team");
        return ((Manager) role).getBalance(team);

    }

    //how union representative uses this functionality? no use case for this

}
