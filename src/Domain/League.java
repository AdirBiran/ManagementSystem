package Domain;

import java.util.List;

public class League {

    private String name;
    private String level;
    private List<Game> games;
    private List<Season> seasons;

    public League()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
      * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     *
     * @return
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     *
     * @return
     */
    public List<Season> getSeasons() {
        return seasons;
    }
}
