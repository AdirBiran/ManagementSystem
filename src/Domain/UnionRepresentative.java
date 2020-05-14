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

    public LeagueInSeason configureLeagueInSeason(String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double registrationFee) {
        League league = Database.getLeague(nameOfLeague);
        Season season = Database.getSeason(yearOfSeason);
        if(league==null||season==null||assignmentPolicy==null||scorePolicy==null)
            return null;
        LeagueInSeason leagueInSeason = new LeagueInSeason(assignmentPolicy, scorePolicy, league, season, registrationFee);
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);
        Database.addLeagueInSeason(leagueInSeason);
        return leagueInSeason;
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


    public boolean changeScorePolicy(String leagueId, ScorePolicy policy)
    {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        if(league!=null)
            return league.changeScorePolicy(policy);
        return false;
    }
    public boolean changeAssignmentPolicy(String leagueId, GameAssignmentPolicy policy)
    {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        if(league!=null)
            return league.changeAssignmentPolicy(policy);
        return false;
    }

    public void addTUTUPayment(String teamId, double payment) {
        Team team = Database.getTeam(teamId);
        team.getBudget().addIncome(payment);
    }

    public List<String> allLeaguesInSeasons() {
        List<String> allLeagues= new LinkedList<>();
        for(League league: Database.getLeagues()){
            for(LeagueInSeason leagueInSeason : league.getLeagueInSeasons())
                allLeagues.add(leagueInSeason.toString());
        }

        return allLeagues;
    }

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

    public void calculateLeagueScore(String leagueId) {
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        if(league!=null)
            league.getScorePolicy().calculateLeagueScore(league);
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
        user.addMessage(new Notice(news));
    }

}
