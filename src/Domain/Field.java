package Domain;

import java.util.List;
import java.util.LinkedList;

public class Field implements Asset {

    private String id;
    private String location;
    private int capacity;
    private Team team;
    private List<Game> games;

    public Field(String location, int capacity, Team team) {
        this.id = "Filed"+IdGenerator.getNewId();
        this.location = location;
        this.capacity = capacity;
        this.team = team;
        this.games = new LinkedList<>();
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }
// ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *
     * @return
     */
    public Team getTeam() {
        return team;
    }

    /**
     *
     * @return
     */
    public List<Game> getGames() {
        return games;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPrice() {
        return 0;
    }

}
