package Domain;

import java.util.List;
import java.util.LinkedList;
import java.util.Objects;

public class League {

    private String name;
    private String level;
    private List<LeagueInSeason> leagueInSeasons;

    /*
    create each league once and for every season add new leagueInSeason
     */
    public League(String name, String level) {
        this.name = name;
        this.level = level;
        leagueInSeasons = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    public void addLeagueInSeason(LeagueInSeason leagueInSeason){
        leagueInSeasons.add(leagueInSeason);
    }
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

    public List<LeagueInSeason> getLeagueInSeasons() {
        return leagueInSeasons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof League)) return false;
        League league = (League) o;
        return Objects.equals(getName(), league.getName()) &&
                Objects.equals(getLevel(), league.getLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel());
    }
}
