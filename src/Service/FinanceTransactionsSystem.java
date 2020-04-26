package Service;

import Domain.Role;
import Domain.Team;
import Domain.TeamOwner;
import Domain.User;

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
        TeamOwner authorization = getAuthorization(user);
        if (authorization != null) {
            return authorization.reportIncome(team, income);
        }
        return false;
    }

    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(User user, Team team, double expanse){
        TeamOwner authorization = getAuthorization(user);
        if (authorization != null) {
            if(!authorization.reportExpanse(team, expanse)){
                notificationSystem.exceededBudget(team);
                return false;
            }
            else
                return true;
        }
        return false;
    }

    public double getBalance(User user, Team team){
        TeamOwner authorization = getAuthorization(user);
        if (authorization != null)
        {
            return authorization.getBalance(team);
        }
        return -1;
    }
    //how union representative uses this functionality? no use case for this

    private TeamOwner getAuthorization(User user) {
        for(Role role : user.getRoles()){
            if(role instanceof  TeamOwner)
                return (TeamOwner)role;
        }
        return null;
    }
}
