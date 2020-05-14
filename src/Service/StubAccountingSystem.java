package Service;

import Logger.Logger;

public class StubAccountingSystem {

    private static double associationBudget;

    public static boolean connect(boolean flag) {
        return flag;
    }

    public static boolean addPayment(String teamName, String date, double amount){
        associationBudget+=amount;
      //  Logger.logEvent(user.getID() + " (Admin)", "Removed user " + userId);
        return true;
    }


}
