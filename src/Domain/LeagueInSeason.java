package Domain;

import Data.Database;

import java.util.*;

public class LeagueInSeason {

    private String id;
    private GameAssignmentPolicy assignmentPolicy;
    private ScorePolicy scorePolicy;
    private Queue<ScoreTableRecord> scoreTable;
    private League league;
    private Season season;
    private List<Game> games;
    private List<Referee> referees;
    private List<Team> teams;
    private double registrationFee;


    public LeagueInSeason(GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, League league, Season season, double registrationFee) {
        this.id = league.getId()+season.getId();
        this.assignmentPolicy = assignmentPolicy;
        this.scorePolicy = scorePolicy;
        this.registrationFee = registrationFee;
        scoreTable = new PriorityQueue<>(new Comparator<ScoreTableRecord>() {
            @Override
            public int compare(ScoreTableRecord o1, ScoreTableRecord o2) {
                return Integer.compare(o2.getTotalScore(), o1.getTotalScore());
            }
        });
        this.league = league;
        this.season = season;

        this.games = new LinkedList<>();
        this.referees = new LinkedList<>();
        this.teams = new LinkedList<>();
    }

    public LeagueInSeason(String id, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, List<Game> games, List<Referee> referees, List<Team> teams, double registrationFee, Queue<ScoreTableRecord> tableRecord)
    {
        this.id = id;
        this.assignmentPolicy = assignmentPolicy;
        this.scorePolicy = scorePolicy;
        this.games = games;
        this.referees = referees;
        this.teams = teams;
        this.registrationFee = registrationFee;
        this.scoreTable = tableRecord;
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
        if(!teams.contains(team)){
            teams.add(team);
            team.addLeague(this);
            Database.addTeam(team);
        }

    }

    public void setGames(List<Game> games) {
        this.games = games;
        for (Game game : games)
            Database.addGame(game);
    }

    public void addGame(Game game) {
        if(!games.contains(game))
            games.add(game);
    }

    public Queue<ScoreTableRecord> getScoreTable() {
        return scoreTable;
    }

    public Game getGameById(String gameId){
        for(Game game: games){
            if(game.getId().equals(gameId))
                return game;
        }
        return null;
    }

    public boolean addReferee(Referee referee) {
        if(!referees.contains(referee)){
            referees.add(referee);
            return true;
        }
        return false;

    }

    public boolean changeScorePolicy(ScorePolicy policy) {
        if(season.getStartDate().after(new Date())&&policy!=null&& !scorePolicy.equals(policy)){
            this.scorePolicy = policy;
            return true;
        }

        return false;
    }

    public boolean changeAssignmentPolicy(GameAssignmentPolicy policy) {
        if(season.getStartDate().after(new Date())&&policy!=null&&!assignmentPolicy.equals(policy)){
            this.assignmentPolicy=policy;
            return true;
        }
        return false;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public void addScoreTableRecord(ScoreTableRecord scoreTableRecord){
        scoreTable.add(scoreTableRecord);
    }

    public GameAssignmentPolicy getAssignmentPolicy() {
        return assignmentPolicy;
    }

    public ScorePolicy getScorePolicy() {
        return scorePolicy;
    }

    public League getLeague() {
        return league;
    }

    public Season getSeason() {
        return season;
    }

    public double getRegistrationFee() {
        return this.registrationFee;
    }

    public List<Game> getGames() {
        return games;
    }

    public String getId() {
        return id;
    }

    public String getGamesId(){
        String listOfId = "";
        for (Game game: games) {
            if(listOfId.equals("")){
                listOfId = listOfId + game.getId();
            }
            else {
                listOfId = listOfId + ","+game.getId();
            }
        }
        return listOfId;
    }

    public String getRefereesId(){
        String listOfId = "";
        for (Referee referee: referees) {
            if(listOfId.equals("")){
                listOfId = listOfId+referee.getUser().getID();
            }
            else {
                listOfId = listOfId + ","+referee.getUser().getID();
            }
        }
        return listOfId;
    }

    public String getTeamsId(){
        String listOfId = "";
        for (Team team: teams) {
            if(listOfId.equals("")){
                listOfId = listOfId+team.getID();
            }
            else {
                listOfId = listOfId + ","+team.getID();
            }
        }
        return listOfId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeagueInSeason)) return false;
        LeagueInSeason leagueInSeason = (LeagueInSeason) o;
        return Objects.equals(this.league.getName(), leagueInSeason.league.getName()) &&
                Objects.equals(this.league.getLevel(), leagueInSeason.league.getLevel()) &&
                        Objects.equals(this.season.getYear(), leagueInSeason.season.getYear());
    }

    @Override
    public String toString() {
        return "LeagueInSeason" +
                ", league=" + league.getName() +
                ", season=" + season.getYear();
    }
}
