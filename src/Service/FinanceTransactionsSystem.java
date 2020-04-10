package Service;

import Domain.FinanceTransactionsManagement;
import Domain.Team;
import Presentation.FootballManagementSystem;
import Presentation.TeamOwner;

public class FinanceTransactionsSystem
{
    private FinanceTransactionsManagement financeTransactionsManagement;

    public FinanceTransactionsSystem(FinanceTransactionsManagement financeTransactionsManagement) {
        this.financeTransactionsManagement = financeTransactionsManagement;
    }

    /*
     this function allows a Team Owner to add new income to his team budget
    */
    public boolean reportNewIncome(TeamOwner teamOwner, Team team, double income){
        if(!team.getTeamOwners().contains(teamOwner))
            return false;
        return financeTransactionsManagement.reportNewIncome(team.getBudget(),income);
    }
    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(TeamOwner teamOwner, Team team, double expanse){
        if(!team.getTeamOwners().contains(teamOwner))
            return false;
        return financeTransactionsManagement.reportNewExpanse(team.getBudget(), expanse);
    }

    public double getBalance(Team team){
        return financeTransactionsManagement.getBalance(team);
    }
    //how union representative uses this functionality? no use case for this

}
