package Domain;

import java.util.List;

public class Team {

    private String name;
    private int wins;
    private int losses;
    private int draws;
    private PersonalPage page;
    private List<TeamOwner> teamOwners; // at least one
    private List<TeamManager> teamManagers;
    private List<Player> players; // at least 11
    private List<Coach> coaches; // at least one
    private Budget budget;
    private List<Game> games;
    private Field field;

    public Team()
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
    public int getWins() {
        return wins;
    }

    /**
     *
     * @return
     */
    public int getLosses() {
        return losses;
    }

    /**
     *
     * @return
     */
    public int getDraws() {
        return draws;
    }

    /**
     *
     * @return
     */
    public PersonalPage getPage() {
        return page;
    }

    /**
     *
     * @return
     */
    public List<TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    /**
     *
     * @return
     */
    public List<TeamManager> getTeamManagers() {
        return teamManagers;
    }

    /**
     *
     * @return
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    public List<Coach> getCoaches() {
        return coaches;
    }

    /**
     *
     * @return
     */
    public Budget getBudget() {
        return budget;
    }

    /**
     *
     * @return
     */
    public List<Game> getGames() {
        return games;
    }

    public Field getField() {
        return field;
    }
}
