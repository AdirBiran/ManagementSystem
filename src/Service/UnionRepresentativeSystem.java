package Service;


import Domain.Authorization.AuthorizationRole;
import Domain.Authorization.UnionAuthorization;
import Domain.*;
import Domain.Referee;

import java.util.List;
import java.util.Date;

public class UnionRepresentativeSystem {


    public UnionRepresentativeSystem() {
    }

    public boolean configureNewLeague(User user, String name, String level){
        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.configureNewLeague(name, level);
        }
        return false;
    }



    public boolean configureNewSeason(User user, int year, Date startDate){
        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.configureNewSeason(year, startDate);
        }
        return false;
    }

    public LeagueInSeason configureLeagueInSeason(User user, String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double fee){
        if (assignmentPolicy == null || scorePolicy == null)
            return null;
        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy, scorePolicy,fee );
        }
        return null;
    }
    public Object[] appointReferee(User user, String firstName,String lastName, String mail, String training)
    {
        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.appointReferee(firstName, lastName, mail, training);
        }
        return null;
    }

    public boolean assignRefToLeague(User user, LeagueInSeason league, Referee referee)
    {
        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.addRefereeToLeague(league, referee);
        }
        return false;
    }


    public boolean changeScorePolicy(User user, LeagueInSeason league, ScorePolicy policy)
    {
        if(getAuthorization(user)!=null)
            return league.changeScorePolicy(policy);
        return false;
    }

    public boolean changeAssignmentPolicy(User user, LeagueInSeason league, GameAssignmentPolicy policy)
    {
        if(getAuthorization(user)!=null)
            return league.changeAssignmentPolicy(policy);
        return false;
    }

    /*
    throws exceptions
     */
    public boolean assignGames(User user, LeagueInSeason league, List<Date> dates)
    {
        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.assignGames(league, dates);
        }
        return false;
    }

    public boolean addTeamToLeague(User user, LeagueInSeason league, Team team) {
        if(getAuthorization(user)!=null && team.isActive()){
            league.addATeam(team);
            return getAuthorization(user).addTeamToDatabase(team);
        }
        return false;
    }

    public void calculateLeagueScore(User user, LeagueInSeason league){

        if(getAuthorization(user)!=null)
            league.getScorePolicy().calculateLeagueScore(league);
    }

    public void calculateGameScore(User user, LeagueInSeason league,Game game){
        if(getAuthorization(user)!=null)
            league.getScorePolicy().calculateScore(game);
    }
    public void changeRegistrationFee(User user, LeagueInSeason league, double newFee){
        if(getAuthorization(user)!=null)
            league.changeRegistrationFee(newFee);
    }

    public double getRegistrationFee(User user,LeagueInSeason league)
    {
        if(getAuthorization(user)!=null)
            return league.getRegistrationFee();
        return -1;
    }

    public void addTUTUPayment(User user, Team team, double payment){

        UnionAuthorization authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.addTUTUPayment(team, payment);
        }
    }

    private UnionAuthorization getAuthorization(User user) {
        for(AuthorizationRole role : user.getRoles()){
            if(role.getRoleName().contains("Union"))
                return (UnionAuthorization)role;
        }
        return null;
    }

}
