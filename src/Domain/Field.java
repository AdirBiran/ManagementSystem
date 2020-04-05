package Domain;

import java.util.List;

public class Field implements Asset {

    private String id;
    private String location;
    private int capacity;
    private Team team;
    private List<Game> games;

    public Field()
    {

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
