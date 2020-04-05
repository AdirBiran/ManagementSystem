package Domain;

import java.util.LinkedList;
import java.util.List;

public class LeagueInSeason {

    private GameAssignmentPolicy assignmentPolicy;
    private ScorePolicy scorePolicy;
    private List<ScoreTableRecord> scoreTable;
    private League league;
    private Season season;
    private List<Game> games;


    public LeagueInSeason(GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, League league, Season season, List<Game> games) {
        this.assignmentPolicy = assignmentPolicy;
        this.scorePolicy = scorePolicy;
        scoreTable = new LinkedList<>();
        this.league = league;
        this.season = season;
        if(games==null||games.size()<1)
            throw new RuntimeException("not enough games to open a league");
        this.games = games;
    }
    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    public void addScoreTableRecord(ScoreTableRecord scoreTableRecord){
        scoreTable.add(scoreTableRecord);
    }
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
        return scoreTable;
    }
}
