package Domain;

public interface Asset {

    String getID(); // - we need to think about uniqueId for the assets.
    void deactivate();
    double getPrice();

}
