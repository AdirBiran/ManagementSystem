package Service;

import Domain.*;
import Logger.Logger;

public class FinanceTransactionsSystem
{

    public FinanceTransactionsSystem() {
    }

    /*
     this function allows a Team Owner to add new income to his team budget
    */
    public boolean reportNewIncome(String userId, String teamId, double income){
        User user = UserFactory.getUser(userId);
        if (user != null) {
            Role role = user.checkUserRole("Team");
            if (role instanceof Manager) {
                if (role.myRole().equals("TeamManager") && !((TeamManager) role).isPermissionFinance()) {
                    Logger.logError("Reporting new income Failed");
                    return false;
                }
                boolean success = ((Manager) role).reportIncome(teamId, income);

                if (success)
                    Logger.logEvent(user.getID(), "Reported income " + income);
                else
                    Logger.logError("Reporting new income Failed");

                return success;
            }
        }
        return false;
    }

    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(String userId, String teamId, double expanse){
        User user = UserFactory.getUser(userId);
        if (user != null) {
            Role role = user.checkUserRole("Team");
            if (role instanceof Manager) {
                if (role.myRole().equals("TeamManager") && !((TeamManager) role).isPermissionFinance()) {
                    Logger.logError("Reporting new expense Failed");
                    return false;
                }
                if (((Manager) role).reportExpanse(teamId, expanse)) {
                    Logger.logEvent(user.getID(), "Reported expense " + expanse);
                    return true;

                }
            }
        }
        Logger.logError("Reporting new expense Failed");

        return false;
    }

    public double getBalance(String userId, String teamId){
        User user = UserFactory.getUser(userId);
        if (user != null) {
            Role role = user.checkUserRole("Team");
            return ((Manager) role).getBalance(teamId);
        }
        return -1;

    }

    //how union representative uses this functionality? no use case for this

}
