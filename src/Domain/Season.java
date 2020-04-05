package Domain;

import java.util.List;

public class Season {

    private int year;
    private List<LeagueInSeason> league;

    public Season()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    public List<LeagueInSeason> getLeague() {
        return league;
    }
}
