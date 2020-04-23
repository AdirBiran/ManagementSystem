package Domain;

import java.util.HashSet;

public abstract class Asset {

    protected String id;
    protected HashSet<Team> teams;
    protected boolean isActive;
    protected double price;

    public Asset(String id, double price) {
        this.id = id;
        this.price = price;
        this.isActive = true;
        this.teams = new HashSet<>();
    }

    public String getID(){return id;} // - we need to think about uniqueId for the assets.
    public void deactivate(){isActive = false;}

    public double getPrice(){return price;}

    public void setPrice(double update){
        if(update>0)
            price = update;
    }

    public HashSet<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team){
        teams.add(team);
    }
    public void removeTeam(Team team){
        teams.remove(team);
    }

    public boolean isActive() {
        return isActive;
    }
}
