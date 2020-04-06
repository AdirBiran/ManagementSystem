package Domain;

public class Budget {

    private double income;
    private double expanses;
    private double balance;
    private Team team;

    //is this suppose to be budget for season?
    //do we want to have a more detailed budget log?
    public Budget(Team team)
    {
        this.team = team;
        income =0;
        expanses=0;
        balance =0;
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @return
     */
    public double getExpanses() {
        return expanses;
    }

    /**
     *
     * @return
     */
    public double getIncome() {
        return income;
    }

    /**
     *
     * @return
     */
    public Team getTeam() {
        return team;
    }

    public boolean addIncome(double income){
        if(income>0){
            this.income+=income;
            updateBalance();
            return true;
        }
        return false;

    }
    public boolean addExpanse(double expense){
        if(expense>0){
            this.expanses +=expense;
            updateBalance();
            if(balance<0){
                this.expanses -=expense;
                updateBalance();
                return false;
            }
            else
                return true;
        }
        return false;

    }


    private void updateBalance() {
        balance = income - expanses;
    }
}
