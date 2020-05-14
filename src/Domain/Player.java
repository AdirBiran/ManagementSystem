package Domain;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

public class Player extends Role implements PartOfATeam {

    public enum RolePlayer {
        goalkeeper,
        playerBack,
        midfielderPlayer,
        attackingPlayer
    }
    private Date birthDate;
    private RolePlayer roleInTeam;
    private String id;
    private HashSet<Team> teams;
    private boolean isActive;
    private double price;


    public Player(User user, Date birthDate, RolePlayer role, double price) {
        this.user = user;
        this.birthDate = birthDate;
        this.roleInTeam = role;
        this.teams = new HashSet<>();
        this.isActive = true;
        this.price = price;
        myRole = "Player";

    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public Date getBirthDate() {
        return birthDate;
    }

    public String getRole() {
        return roleInTeam.toString();
    }

    public void setRole(RolePlayer role) {
        this.roleInTeam = role;
    }

    @Override
    public String getID() {
        return user.getID();
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double update) {
        this.price = update;
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
        return "Player";
    }

    @Override
    public String toString() {
        return "Player" +
                ", id= " +user.getID() +
                ": name=" +user.getName() +
                ", birthDate=" + birthDate +
                ", role in team='" + roleInTeam+
                ", teams= "+ teamsString(teams);

    }
}
