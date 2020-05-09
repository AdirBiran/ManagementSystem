package Domain;

import java.util.HashSet;
import java.util.LinkedList;

public class Coach extends Role implements PartOfATeam {

    public enum TrainingCoach {
        IFA_C,
        UEFA_A,
        UEFA_B,
        UEFA_PRO
    }

    public enum RoleCoach{
        main,
        assistantCoach,
        fitness,
        goalkeeperCoach
    }

    private TrainingCoach training;
    private RoleCoach roleInTeam;
    private HashSet<Team> teams;
    private boolean isActive;
    private double price;


    public Coach(User user,TrainingCoach training, RoleCoach role, double price) {
        this.user = user;
        this.training = training;
        this.roleInTeam = role;
        this.price = price;
        teams = new HashSet<>();
        this.isActive = true;
        myRole = "Coach";
    }


// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public String getTraining() {
        return training.toString();
    }

    public String getRoleInTeam() {
        return roleInTeam.toString();
    }

    public void setTraining(TrainingCoach training) {
        this.training = training;
    }

    public void setRoleInTeam(RoleCoach roleInTeam) {
        this.roleInTeam = roleInTeam;
    }

    @Override
    public String getID() {
        return user.getID();
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
        return "Coach" +
                ", id= " +user.getID() +
                ": name=" +user.getName() +
                ", training='" + training +
                ", role in team='" + roleInTeam;
    }
}
