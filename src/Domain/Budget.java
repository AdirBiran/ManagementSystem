package Domain;

import Data.Database;

import java.util.Date;
import java.util.Observable;

public class Budget extends Observable {

    private double income;
    private double expanses;
    private double balance;

    //is this suppose to be budget for season?
    //do we want to have a more detailed budget log?
    public Budget() {
        income = 0;
        expanses = 0;
        balance = 0;
    }

    public Budget(double income,double expanses){
        this.income=income;
        this.expanses=expanses;
        this.balance=income - expanses;
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public boolean addIncome(double income) {
        if (income > 0) {
            this.income += income;
            updateBalance();
            return true;
        }
        return false;

    }

    public boolean addExpanse(Team team ,double expense) {
        if (expense > 0) {
            this.expanses += expense;
            updateBalance();
            if (balance < 0) {
                this.expanses -= expense;
                updateBalance();
                updateAllUnionRep(new Date() + "The team: "+team.getName()+" has exceeded its budget");
                return false;
            } else
                return true;
        }
        return false;

    }

    private void updateAllUnionRep(String news) {
        for (Role union : Database.getListOfAllSpecificRoles("UnionRepresentative")){
            ((UnionRepresentative)union).update(this, news);
        }
    }

    private void updateBalance() {
        balance = income - expanses;
    }
    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++

    public double getBalance() {
        return balance;
    }

    public double getExpanses() {
        return expanses;
    }

    public double getIncome() {
        return income;
    }
}

