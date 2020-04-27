package Domain;

import java.util.HashSet;

public class Coach implements PartOfATeam, Role {

    private String training;
    private String role;
    private String id;
    private HashSet<Team> teams;
    private boolean isActive;
    private double price;


    public Coach(String id,String training, String role, double price) {
        this.id = id;
        this.training = training;
        this.role = role;
        this.price = price;
        teams = new HashSet<>();
        this.isActive = true;
    }


// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    /**
     *
     * @return
     */
    public String getTraining() {
        return training;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }


    public void setTraining(String training) {
        this.training = training;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String getID() {
        return id;
    }

    @Override
    public void deactivate() {
        isActive=false;
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double update) {
        price = update;
    }

    @Override
    public HashSet<Team> getTeams() {
        return teams;
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void reactivate() {
        isActive = true;
    }

    @Override
    public String myRole() {
        return "Coach";
    }

    @Override
    public String toString() {
        return  '\''+ ", Coach training='" + training + '\''+
         ", Coach role='" + role + '\'' ;

    }
}
