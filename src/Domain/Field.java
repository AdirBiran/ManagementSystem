package Domain;

import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

public class Field implements PartOfATeam {


    private String location;
    private String name;
    private int capacity;
    private List<Game> games;
    private String id;
    private HashSet<Team> teams;
    private boolean isActive;
    private double price;


    public Field(String location,String fieldName, int capacity, double price) {
        this.id = "Field"+IdGenerator.getNewId();
        this.location = location;
        this.name = fieldName;
        this.capacity = capacity;
        this.games = new LinkedList<>();
        teams = new HashSet<>();
        this.price = price;
        isActive = true;
    }
    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public void reactivate() {
        isActive = true;
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

    public String getName() {
        return name;
    }
}
