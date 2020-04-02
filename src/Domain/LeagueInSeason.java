package Domain;

import java.util.HashMap;
import java.util.List;

public class LeagueInSeason {

    private GameAssignmentPolicy assignmentPolicy;
    private ScorePolicy scorePolicy;
    private List<ScoreTableRecord> scoreTabel;
    private HashMap<Season, League> leaguesInSeason;

    public LeagueInSeason()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public GameAssignmentPolicy getAssignmentPolicy() {
        return assignmentPolicy;
    }

    /**
     *
     * @return
     */
    public ScorePolicy getScorePolicy() {
        return scorePolicy;
    }

    /**
     *
     * @return
     */
    public HashMap<Season, League> getLeaguesInSeason() {
        return leaguesInSeason;
    }

    /**
     *
     * @return
     */
    public List<ScoreTableRecord> getScoreTabel() {
        return scoreTabel;
    }
}
