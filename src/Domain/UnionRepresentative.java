package Domain;

import Data.Database;
import Logger.Logger;
import Service.StubAccountingSystem;

import java.util.*;

public class UnionRepresentative extends Role implements Observer {

    public UnionRepresentative(User user) {
        this.user = user;
        myRole = "UnionRepresentative";

    }

    public boolean configureNewLeague(String name, String level) {
        League league = new League(name, level);
        return Database.addLeague(league);
    }

    public boolean configureNewSeason(int year, Date startDate) {
        try{
            Season season = new Season(year, startDate);
            return Database.addSeason(season);
        }
        catch (Exception e){
            return false;
        }
    }
    public static LinkedList<String> getAllPastGames(){
        Date today = new Date();
        LinkedList<String> pastGames = new LinkedList<>();
        for(Game game : Database.getAllGames()){
            if(today.after(game.getDate()))
                pastGames.add(game.toString());
        }
        return pastGames;
    }

    public List<String> getAllLeagues() {
        List<String> leagues = new LinkedList<>();
        for(League l: Database.getLeagues()) {
            leagues.add(l.toString());
        }
        return leagues;
    }

    public List<String> getAllSeasons() {
        List<String> seasons = new LinkedList<>();
        for(Season s: Database.getSeasons()) {
            seasons.add(s.toString());
        }
        return seasons;
    }
    public LeagueInSeason configureLeagueInSeason(String nameOfLeague, String yearOfSeason, String assignmentPolicy, String scorePolicy, double registrationFee) {
        League league = Database.getLeague(nameOfLeague);
        Season season = Database.getSeason(yearOfSeason);

        if(league!=null && season!=null && assignmentPolicy!=null && scorePolicy!=null) {
            GameAssignmentPolicy gameAssignmentPolicy = GameAssignmentPolicy.checkPolicy(assignmentPolicy);
            ScorePolicy gameScorePolicy = ScorePolicy.checkPolicy(scorePolicy);
            if (gameAssignmentPolicy != null && gameScorePolicy != null) {
                LeagueInSeason leagueInSeason = new LeagueInSeason(gameAssignmentPolicy, gameScorePolicy, league, season, registrationFee);
                league.addLeagueInSeason(leagueInSeason);
                season.addLeagueInSeason(leagueInSeason);
                Database.addLeagueInSeason(leagueInSeason);
                return leagueInSeason;
            }
        }
        return null;
    }

    public boolean assignGames(String leagueId, List<Date> dates) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        List<Game> games =league.getAssignmentPolicy().assignGames(dates, league);
        if(games!=null){
            league.setGames(games);
            return true;
        }
        return false;
    }
    public User appointReferee(String firstName, String lastName, String mail, Referee.TrainingReferee training)
    {
        return UserFactory.getNewReferee(firstName, lastName, mail, training);
    }
    public void removeReferee(User user)
    {

    }
    public boolean addRefereeToLeague(String leagueId, Referee referee)
    {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        return league.addReferee(referee);
    }


    public boolean changeScorePolicy(String leagueId, String policy)
    {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        ScorePolicy scorePolicy = ScorePolicy.checkPolicy(policy);
        if(league!=null && scorePolicy!=null)
            return league.changeScorePolicy(scorePolicy);
        return false;
    }
    public boolean changeAssignmentPolicy(String leagueId, String policy)
    {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        GameAssignmentPolicy assignmentPolicy = GameAssignmentPolicy.checkPolicy(policy);
        if(league!=null && assignmentPolicy!=null)
            return league.changeAssignmentPolicy(assignmentPolicy);
        return false;
    }

    public boolean addTUTUPayment(String teamId, double payment) {
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            team.getBudget().addIncome(payment);
            return true;
        }
        return false;
    }

    public List<String> allLeaguesInSeasons() {
        List<String> allLeagues= new LinkedList<>();
        for(League league: Database.getLeagues()){
            for(LeagueInSeason leagueInSeason : league.getLeagueInSeasons())
                allLeagues.add(leagueInSeason.toString());
        }

        return allLeagues;
    }


    public List<String> getAllScorePolicies() {
        List<String> scorePolicies= new LinkedList<>();
        /*for(ScorePolicy s: Database.getAllScorePolicies()){
            scorePolicies.add(s.getName());
        }*/
        return scorePolicies;
    }
    public List<String> getAllAssignmentsPolicies() {
        List<String> assignmentsPolicies= new LinkedList<>();
       /* for(GameAssignmentPolicy a: Database.getAllAssignmentsPolicies()){
            assignmentsPolicies.add(a.getName());
        }*/
        return assignmentsPolicies;
    }
///////////////////////////////////////////////////
    public boolean changeGameDate(String gameId, Date newDate) {
        Game game = Database.getGame(gameId);
        if(game.getDate().after(new Date())) {
            game.setDate(newDate);
            return true;
        }
        return false;
    }

    public boolean changeGameLocation(String gameId, String fieldId) {
        Game game = Database.getGame(gameId);
        Field field = (Field) Database.getAssetById(fieldId);
        if(field!=null && field.isActive()) {
            game.setField(field);
            return true;
        }
        return false;
    }

    public boolean calculateLeagueScore(String leagueId) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        if(league!=null) {
            league.getScorePolicy().calculateLeagueScore(league);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnionRepresentative;
    }

    public double getRegistrationFee(String leagueId) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        if(league!=null)
            return league.getRegistrationFee();
        return -1;
    }

    public boolean changeRegistrationFee(String leagueId, double newFee) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        if(league!=null){
            league.changeRegistrationFee(newFee);
            return true;
        }
        return false;
    }

    public boolean calculateGameScore(String leagueId, String gameId) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        Game game= Database.getGame(gameId);
        if(league!=null && game!=null) {
            league.getScorePolicy().calculateScore(game);
            return true;
        }
        return false;
    }

    public boolean addTeamToLeague(String leagueId, String teamId) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        Team team = Database.getTeam(teamId);
        if(team!=null && league!=null && team.isActive()) {
            if (team.getBudget().addExpanse(team, league.getRegistrationFee())) {
                StubAccountingSystem.addPayment(team.getName(),(new Date()).toString() ,league.getRegistrationFee());
                league.addATeam(team);
                Logger.logEvent(user.getID(), "Added team " + team.getName() + " to league");
                return true;
            }
        }
        Logger.logError("Adding Team to league failed");
        return false;
    }

    public boolean addFieldToSystem(String location,String fieldName, int capacity, double price){
        Field field = new Field(location, fieldName, capacity, price);
        return Database.addAsset(field);
    }
    @Override
    public String myRole() {
        return "UnionRepresentative";
    }

    @Override
    public String toString() {
        return "UnionRepresentative" +
                ", id=" + user.getID() +
                ": name=" +user.getName();
    }

    @Override
    public void update(Observable o, Object arg) {
        String news = (String)arg;
        user.addMessage(news);
    }

}
