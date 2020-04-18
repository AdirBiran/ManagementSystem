package Domain;

import Data.Database;

import java.util.Date;
import java.util.List;

public class LeagueAndGameManagement {

    private Database database;

    public LeagueAndGameManagement(Database database) {
        this.database = database;
    }


    public boolean configureNewLeague(String name, String level) {
        League league = new League(name, level);
        return database.addLeague(league);
    }

    public boolean configureNewSeason(int year, Date startDate) {
        Season season = new Season(year, startDate);
        return database.addSeason(season);
    }

    public LeagueInSeason configureLeagueInSeason(String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double registrationFee) {
        League league = database.getLeague(nameOfLeague);
        Season season = database.getSeason(yearOfSeason);
        if(league==null||season==null)return null;
        LeagueInSeason leagueInSeason = new LeagueInSeason(assignmentPolicy, scorePolicy, league, season, registrationFee);
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);
        return leagueInSeason;
    }


    public boolean assignGames(LeagueInSeason league, List<Date> dates) {
        List<Game> games = league.getAssignmentPolicy().assignGames(league.getTeams(), dates, league);
        if(games!=null){
            league.setGames(games);
            return true;
        }
        return false;
    }

}
