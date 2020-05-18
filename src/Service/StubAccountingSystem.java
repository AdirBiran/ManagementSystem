package Service;

public class StubAccountingSystem implements StubSystem {

    public static boolean addPayment(String teamName, String date, double amount){
        return true;
    }


    @Override
    public boolean connect() {
        return true;
    }
}
