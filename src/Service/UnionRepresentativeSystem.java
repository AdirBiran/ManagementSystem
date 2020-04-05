package Service;


import Domain.FinanceTransactionsManagement;
import Domain.GameManagement;
import Domain.RefereeManagement;
import Presentation.FootballManagementSystem;

public class UnionRepresentativeSystem {

    private FootballManagementSystem footballManagementSystem;
    private FinanceTransactionsManagement financeTransactionsManagement;
    private GameManagement gameManagement;
    private RefereeManagement refereeManagement;

    public UnionRepresentativeSystem(FootballManagementSystem footballManagementSystem, FinanceTransactionsManagement financeTransactionsManagement, GameManagement gameManagement, RefereeManagement refereeManagement) {
        this.footballManagementSystem = footballManagementSystem;
        this.financeTransactionsManagement = financeTransactionsManagement;
        this.gameManagement = gameManagement;
        this.refereeManagement = refereeManagement;
    }

    public boolean configureLeague(){
        return false;
    }
    public boolean appointReferee()
    {
        return false;
    }
    public boolean assignRefToLeague()
    {
        return false;
    }
    public boolean changePolicy()
    {
        return false;
    }
    public boolean assignGames()
    {
        return false;
    }

}
