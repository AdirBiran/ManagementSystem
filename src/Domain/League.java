package Domain;

import java.util.List;
import java.util.LinkedList;
import java.util.Objects;

public class League {

    private String id;
    private String name;
    private String level;
    private List<LeagueInSeason> leagueInSeasons;

    /*
    create each league once and for every season add new leagueInSeason
     */
    public League(String name, String level) {
        this.name = name;
        this.level = level;
        this.id = "L" + IdGenerator.getNewId();
        leagueInSeasons = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    public void addLeagueInSeason(LeagueInSeason leagueInSeason){
        leagueInSeasons.add(leagueInSeason);
    }

    public List<LeagueInSeason> getLeagueInSeasons() {
        return leagueInSeasons;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLevel());
    }

    @Override
    public String toString() {
        return  id+
                ":" + name +
                "," + level;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }
}
