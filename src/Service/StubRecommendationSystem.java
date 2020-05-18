package Service;

public class StubRecommendationSystem implements StubSystem{

    public StubRecommendationSystem() {
    }
    @Override
    public boolean connect(){
        return true;
    }

    public boolean trainModel(){
        return true;
    }
}
