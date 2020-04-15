package Domain;

import Data.Database;
import Presentation.Admin;
import Presentation.Fan;
import Presentation.Referee;
import Presentation.TeamOwner;

import java.util.Date;
import java.util.List;

public class LeagueAndGameManagement {

    private Database database;

    public LeagueAndGameManagement(Database database) {
        this.database = database;
    }

    /*
    Fan registration for alerts for games you've selected
     */
    public boolean registrationForGamesAlerts(Fan fan, List<Game> games, ReceiveAlerts receiveAlerts) {
        for(Game game : games){
            if(!game.addFanForNotifications(fan, receiveAlerts))
                return false;
        }
        return true;
    }

    public boolean configureNewLeague(String name, String level) {
        League league = new League(name, level);
        return database.addLeague(league);
    }

    public boolean configureNewSeason(int year) {
        Season season = new Season(year);
        return database.addSeason(season);
    }

    public boolean configureLeagueInSeason(String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, List<Game> games) {
        League league = database.getLeague(nameOfLeague);
        Season season = database.getSeason(yearOfSeason);
        if(league==null||season==null)return false;
        LeagueInSeason leagueInSeason = new LeagueInSeason(assignmentPolicy, scorePolicy, league, season, games);
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);
        return true;
    }

    public boolean assignRefToLeague(LeagueInSeason league, Referee referee) {
        return league.addReferee(referee);
    }

    public boolean changeScorePolicy(LeagueInSeason league, ScorePolicy policy) {
        return league.changeScorePolicy(policy);
    }

    public boolean changeAssignmentPolicy(LeagueInSeason league, GameAssignmentPolicy policy) {
        return league.changeAssignmentPolicy(policy);
    }

    public boolean assignGames(LeagueInSeason league, List<Date> dates) {
        List<Game> games = league.getAssignmentPolicy().assignGames(league.getTeams(), dates, league);
        if(games!=null){
            league.setGames(games);
            return true;
        }
        return false;
    }
    public boolean closeTeam(TeamOwner teamOwner, Team team) {
        if(team.isActive()){
            teamOwner.setClosedTeam(team, true);
            team.setActive(false);
            //Removing permissions for team members
            return true;
        }
        return false;
    }

    public boolean reopeningTeam(TeamOwner teamOwner, Team team) {
        if(!team.isActive() && !team.isPermanentlyClosed() && teamOwner.isClosedTeam(team)){
            teamOwner.setClosedTeam(team, false);
            team.setActive(true);
            //Re-configure permissions for team members
            return true;
        }
        return false;
    }

    public boolean permanentlyCloseTeam(Team team) {
        if(!team.isPermanentlyClosed()) {
            if (team.isActive())
                team.setActive(false);
            team.setPermanentlyClosed(true);
            //Maybe add a list of permanently closed teams to Database
            //What happens to the members of the teams???
        }
        return false;
    }
}
