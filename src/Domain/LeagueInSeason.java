package Domain;

import java.util.HashMap;
import java.util.List;

public class LeagueInSeason {

    private GameAssignmentPolicy assignmentPolicy;
    private ScorePolicy scorePolicy;
    private List<ScoreTableRecord> scoreTabel;
    private League league;
    private Season season;

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

    public League getLeague() {
        return league;
    }

    public Season getSeason() {
        return season;
    }

    /**
     *
     * @return
     */
    public List<ScoreTableRecord> getScoreTabel() {
        return scoreTabel;
    }
}
