package Domain;

import java.util.List;
import java.util.LinkedList;

public class Field extends Asset {


    private String location;
    private int capacity;
    private List<Game> games;


    public Field(String location, int capacity, double price) {
        super("Field", price);
        this.location = location;
        this.capacity = capacity;
        this.games = new LinkedList<>();
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
    public List<Game> getGames() {
        return games;
    }

}
