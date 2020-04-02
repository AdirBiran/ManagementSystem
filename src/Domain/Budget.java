package Domain;

public class Budget {

    private double income;
    private double expanses;
    private double balance;
    private Team team;

    public Budget()
    {

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
}
