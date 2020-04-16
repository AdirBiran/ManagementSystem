package Domain;

import Presentation.Referee;

import java.util.LinkedList;
import java.util.List;

public class LeagueInSeason {

    private GameAssignmentPolicy assignmentPolicy;
    private ScorePolicy scorePolicy;
    private List<ScoreTableRecord> scoreTable;
    private League league;
    private Season season;
    private List<Game> games;
    private List<Referee> referees;
    private List<Team> teams;
    private double registrationFee;


    public LeagueInSeason(GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, League league, Season season, double registrationFee) {
        this.assignmentPolicy = assignmentPolicy;
        this.scorePolicy = scorePolicy;
        this.registrationFee = registrationFee;
        scoreTable = new LinkedList<>();
        this.league = league;
        this.season = season;

        this.games = new LinkedList<>();
        this.referees = new LinkedList<>();
        this.teams = new LinkedList<>();
    }
    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    public void changeRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    public void addATeam(Team team) {
        if(!teams.contains(team))
            teams.add(team);
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        if(!games.contains(game))
            games.add(game);
    }

    public List<ScoreTableRecord> getScoreTable() {
        return scoreTable;
    }

    public List<Game> getAllGames() {
        return games;
    }

    public Game getGameById(String gameId){
        for(Game game: games){
            if(game.getId().equals(gameId))
                return game;
        }
        return null;
    }

    public List<Referee> getReferees() {
        return referees;
    }

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


    public boolean addReferee(Referee referee) {
        if(!referees.contains(referee)){
            referees.add(referee);
            return true;
        }
        return false;

    }

    public boolean changeScorePolicy(ScorePolicy policy) {
        if(policy!=null){
            this.scorePolicy = policy;
            return true;
        }
        return false;
    }

    public boolean changeAssignmentPolicy(GameAssignmentPolicy policy) {
        if(policy!=null){
            this.assignmentPolicy=policy;
            return true;
        }
        return false;
    }
}
