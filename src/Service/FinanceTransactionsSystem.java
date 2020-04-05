package Service;

import Domain.FinanceTransactionsManagement;
import Domain.Team;
import Presentation.FootballManagementSystem;
import Presentation.TeamOwner;

public class FinanceTransactionsSystem
{
    private FootballManagementSystem footballManagementSystem;
    private FinanceTransactionsManagement financeTransactionsManagement;

    public FinanceTransactionsSystem(FootballManagementSystem footballManagementSystem, FinanceTransactionsManagement financeTransactionsManagement) {
        this.footballManagementSystem = footballManagementSystem;
        this.financeTransactionsManagement = financeTransactionsManagement;
    }

    /*
        this function allows a Team Owner to add new income to his team budget
         */
    public boolean reportNewIncome(TeamOwner teamOwner, Team team, double income){
        if(!team.getTeamOwners().contains(teamOwner))
            return false;
        return team.getBudget().addIncome(income);
    }
    /*
    this function allows a Team Owner to add new expanse to his team budget
     */
    public boolean reportNewExpanse(TeamOwner teamOwner, Team team, double expanse){
        if(!team.getTeamOwners().contains(teamOwner))
            return false;
        return team.getBudget().addExpanse(expanse);
    }
    //how union representative uses this functionality? no use case for this

}
