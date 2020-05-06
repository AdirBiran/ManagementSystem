package Domain;

import java.util.HashSet;

public class Coach implements PartOfATeam, Role {

    public enum TrainingCoach {
        Guide,
        CertifiedTrainer
    }

    private TrainingCoach training;
    private String roleInTeam;
    private String id;
    private String name;
    private HashSet<Team> teams;
    private boolean isActive;
    private double price;


    public Coach(String id,String name, TrainingCoach training, String role, double price) {
        this.id = id;
        this.name = name;
        this.training = training;
        this.roleInTeam = role;
        this.price = price;
        teams = new HashSet<>();
        this.isActive = true;
    }


// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public String getTraining() {
        return training.toString();
    }

    public String getRoleInTeam() {
        return roleInTeam;
    }

    public void setTraining(TrainingCoach training) {
        this.training = training;
    }

    public void setRoleInTeam(String roleInTeam) {
        this.roleInTeam = roleInTeam;
    }

    @Override
    public String getName() {
        return name;
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
        return "Coach{" +
                "training='" + training + '\'' +
                ", role in team='" + roleInTeam + '\'' +
                '}';
    }
}
