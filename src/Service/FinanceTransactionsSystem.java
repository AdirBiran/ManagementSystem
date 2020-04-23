package Service;

import Domain.Authorization.AuthorizationRole;
import Domain.Authorization.TeamOwnerAuthorization;
import Domain.Team;
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
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if (authorization != null) {
            return authorization.reportIncome(team, income);
        }
        return false;
    }

    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(User user, Team team, double expanse){
        TeamOwnerAuthorization authorization = getAuthorization(user);
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
        TeamOwnerAuthorization authorization = getAuthorization(user);
        if (authorization != null)
        {
            return authorization.getBalance(team);
        }
        return -1;
    }
    //how union representative uses this functionality? no use case for this

    private TeamOwnerAuthorization getAuthorization(User user) {
        for(AuthorizationRole role : user.getRoles()){
            if(role.getRoleName().contains("TeamOwner"))
                return (TeamOwnerAuthorization)role;
        }
        return null;
    }
}
