package Domain;

import java.util.HashSet;

public interface PartOfATeam {

    String getID(); // - we need to think about uniqueId for the assets.
    void deactivate();
    void reactivate();
    double getPrice();
    void setPrice(double update);
    HashSet<Team> getTeams();
    void addTeam(Team team);
    void removeTeam(Team team);
    boolean isActive();
}
