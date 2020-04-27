package Service;

import Domain.*;

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
                return false;
            return ((Manager) role).reportIncome(team, income);
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
                return false;
            if(((Manager) role).reportExpanse(team, expanse))
                return true;
            else
                notificationSystem.exceededBudget(team);
        }
        return false;
    }

    public double getBalance(User user, Team team){
        Role role = user.checkUserRole("Team");
        return ((Manager) role).getBalance(team);

    }
    //how union representative uses this functionality? no use case for this

}
