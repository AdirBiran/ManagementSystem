package Domain;

import Data.Database;

import java.util.Date;
import java.util.List;

public class UnionRepresentative implements Role{


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
    public User appointReferee(String firstName, String lastName, String mail, String training)
    {
        return UserFactory.getNewReferee(firstName, lastName, mail, training);
    }
    public void removeReferee(User user)
    {

    }
    public boolean addRefereeToLeague(LeagueInSeason league, User referee)
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
        return obj instanceof UnionRepresentative;
    }

    public boolean addTeamToDatabase(Team team) {
        return Database.addTeam(team);
    }

    @Override
    public String myRole() {
        return "UnionRepresentative";
    }
}
