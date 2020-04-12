package Service;

import Domain.FinanceTransactionsManagement;
import Domain.Team;
import Presentation.TeamOwner;

public class FinanceTransactionsSystem
{
    private FinanceTransactionsManagement financeTransactionsManagement;
    private NotificationSystem notificationSystem;

    public FinanceTransactionsSystem(FinanceTransactionsManagement financeTransactionsManagement,
                                     NotificationSystem notificationSystem) {
        this.financeTransactionsManagement = financeTransactionsManagement;
        this.notificationSystem = notificationSystem;
    }

    /*
     this function allows a Team Owner to add new income to his team budget
    */
    public boolean reportNewIncome(TeamOwner teamOwner, Team team, double income){
        if(!team.getTeamOwners().contains(teamOwner))
            return false;
        if(!financeTransactionsManagement.reportNewIncome(team.getBudget(),income)) {
            notificationSystem.exceededBudget(team);
            return false;
        }
        return true;
    }
    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(TeamOwner teamOwner, Team team, double expanse){
        if(!team.getTeamOwners().contains(teamOwner))
            return false;
        if(!financeTransactionsManagement.reportNewExpanse(team.getBudget(), expanse)) {
            notificationSystem.exceededBudget(team);
            return false;
        }
        return true;
    }

    public double getBalance(Team team){
        return financeTransactionsManagement.getBalance(team);
    }
    //how union representative uses this functionality? no use case for this

}
