package Service;

public class StubIsraeliTaxLawsSystem implements StubSystem {

    public double revenueAmount(double getTaxRate) {
        return getTaxRate;
    }

    @Override
    public boolean connect() {
        return true;
    }
}
