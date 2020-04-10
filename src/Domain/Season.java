package Domain;

import java.util.List;
import java.util.LinkedList;

public class Season {

    private int year;
    private List<LeagueInSeason> leagueInSeasons;

    public Season(int year) {
        this.year = year;
        leagueInSeasons = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    public List<LeagueInSeason> getLeagueInSeasons() {
        return leagueInSeasons;
    }
    public void addLeagueInSeason(LeagueInSeason leagueInSeason){
        leagueInSeasons.add(leagueInSeason);
    }

    @Override
    public String toString() {
        return "Season{" +
                "year=" + year +
                '}';
    }
}
