package Service;

public class StubSystemProxy implements StubSystem {

    private static StubSystem accountingSystem;
    private static StubSystem israeliTaxLawsSystem;
    private static StubSystem recommendationSystem;

    @Override
    public boolean connect() {
        if(accountingSystem==null){
            accountingSystem = new StubAccountingSystem();
            accountingSystem.connect();
        }
        if(israeliTaxLawsSystem==null){
            israeliTaxLawsSystem = new StubAccountingSystem();
            israeliTaxLawsSystem.connect();
        }
        if(recommendationSystem==null){
            recommendationSystem = new StubAccountingSystem();
            recommendationSystem.connect();
        }
        return false;

    }
}
