package Domain;

import java.util.List;

public class League {

    private String name;
    private String level;
    private List<Game> games;
    private List<LeagueInSeason> season;

    public League(String name, String level, List<Game> games) {
        this.name = name;
        this.level = level;
        this.games = games;
    }

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

    public List<LeagueInSeason> getSeason() {
        return season;
    }
}
