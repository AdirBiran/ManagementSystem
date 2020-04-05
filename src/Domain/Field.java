package Domain;

import java.util.List;
import java.util.LinkedList;

public class Field implements Asset {

    private String id;
    private String location;
    private int capacity;
    private Team team;
    private List<Game> games;

    public Field(String id, String location, int capacity, Team team) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.team = team;
        this.games = new LinkedList<>();
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
    public String getName() {
        return id;
    }
}
