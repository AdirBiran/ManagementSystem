package Service;


import Domain.*;
import Domain.Referee;

import java.util.List;
import java.util.Date;

public class UnionRepresentativeSystem {

    private UnionBudget unionBudget;

    public UnionRepresentativeSystem() {
        unionBudget = new UnionBudget();
    }

    public boolean configureNewLeague(User user, String name, String level){
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.configureNewLeague(name, level);
        }
        return false;
    }



    public boolean configureNewSeason(User user, int year, Date startDate){
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.configureNewSeason(year, startDate);
        }
        return false;
    }

    public LeagueInSeason configureLeagueInSeason(User user, String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double fee){
        if (assignmentPolicy == null || scorePolicy == null)
            return null;
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy, scorePolicy,fee );
        }
        return null;
    }
    public User appointReferee(User user, String firstName,String lastName, String mail, String training)
    {
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.appointReferee(firstName, lastName, mail, training);
        }
        return null;
    }

    public boolean assignRefToLeague(User user, LeagueInSeason league, User referee)
    {
        UnionRepresentative authorization = getAuthorization(user);
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
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            return authorization.assignGames(league, dates);
        }
        return false;
    }

    public boolean addTeamToLeague(User user, LeagueInSeason league, Team team) {
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null && team.isActive()){
            if(team.getBudget().addExpanse(league.getRegistrationFee()))
                unionBudget.addPayment(league.getRegistrationFee());
            else
                return false;
            league.addATeam(team);
            return authorization.addTeamToDatabase(team);
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

        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            authorization.addTUTUPayment(team, payment);
        }
    }
    public void addPaymentsFromTheTUTU(User user,double payment){
        UnionRepresentative authorization = getAuthorization(user);
        if(authorization!=null){
            unionBudget.addPayment( payment);
        }
    }

    private UnionRepresentative getAuthorization(User user) {
        for(Role role : user.getRoles()){
            if(role instanceof UnionRepresentative)
                return (UnionRepresentative)role;
        }
        return null;
    }

}
