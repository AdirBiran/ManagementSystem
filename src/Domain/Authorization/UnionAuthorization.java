package Domain.Authorization;

import Data.Database;
import Domain.*;

import java.util.Date;
import java.util.List;

public class UnionAuthorization extends UserAuthorization {

    public UnionAuthorization(User user) {
        super( user);
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
        if(league==null||season==null||assignmentPolicy==null||scorePolicy==null)return null;
        LeagueInSeason leagueInSeason = new LeagueInSeason(assignmentPolicy, scorePolicy, league, season, registrationFee);
        league.addLeagueInSeason(leagueInSeason);
        season.addLeagueInSeason(leagueInSeason);
        return leagueInSeason;
    }

    public boolean assignGames(LeagueInSeason league, List<Date> dates) {
        List<Game> games =league.getAssignmentPolicy().assignGames(league.getTeams(), dates, league);

        if(games!=null){
            league.setGames(games);
            return true;
        }
        return false;
    }
    public Object[] appointReferee(String firstName, String lastName, String mail, String training)
    {
        Object[]referee = UserFactory.getNewReferee(firstName, lastName, mail, training);
        if(referee!=null){
            Database.addReferee((User)referee[0], (Referee)referee[1]);
            return referee;
        }
        return null;
    }
    public void removeReferee(User user)
    {

    }
    public boolean addRefereeToLeague(LeagueInSeason league, Referee referee)
    {
        return league.addReferee(referee);
    }
    public boolean changeScorePolicy(LeagueInSeason league, ScorePolicy policy)
    {
        return league.changeScorePolicy(policy);
    }
    public boolean changeAssignmentPolicy(LeagueInSeason league, GameAssignmentPolicy policy)
    {
        return league.changeAssignmentPolicy(policy);
    }

    public void addTUTUPayment(Team team, double payment) {
        team.getBudget().addIncome(payment);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnionAuthorization;
    }

    public boolean addTeamToDatabase(Team team) {
        return Database.addTeam(team);
    }


}
