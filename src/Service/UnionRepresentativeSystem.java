package Service;


import Domain.*;
import Domain.Referee;

import java.util.List;
import java.util.Date;

public class UnionRepresentativeSystem {

    private FinanceTransactionsManagement financeTransactionsManagement;
    private LeagueAndGameManagement leagueAndGameManagement;
    private RefereeManagement refereeManagement;

    public UnionRepresentativeSystem(FinanceTransactionsManagement financeTransactionsManagement, LeagueAndGameManagement leagueAndGameManagement, RefereeManagement refereeManagement) {
        this.financeTransactionsManagement = financeTransactionsManagement;
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.refereeManagement = refereeManagement;
    }

    public boolean configureNewLeague(String name, String level){
        return leagueAndGameManagement.configureNewLeague(name, level);
    }
    public boolean configureNewSeason(int year, Date startDate){
        return leagueAndGameManagement.configureNewSeason(year,startDate);
    }

    public LeagueInSeason configureLeagueInSeason(String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double fee){
        if (assignmentPolicy == null || scorePolicy == null)
            return null;
        return leagueAndGameManagement.configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy,scorePolicy,fee);
    }
    public Referee appointReferee(String firstName,String lastName, String mail, String training)
    {
        return refereeManagement.appointReferee(firstName,lastName, mail, training);
    }

    public boolean assignRefToLeague(LeagueInSeason league, Referee referee)
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

    /*
    throws exceptions
     */
    public boolean assignGames(LeagueInSeason league, List<Date> dates)
    {
        return leagueAndGameManagement.assignGames(league,dates);
    }

    public void addTeamToLeague(LeagueInSeason league, Team team) {
         league.addATeam(team);
    }

    public void calculateLeagueScore(LeagueInSeason league){

        league.getScorePolicy().calculateLeagueScore(league);
    }

    public void calculateGameScore(LeagueInSeason league,Game game){
        league.getScorePolicy().calculateScore(game);
    }
    public void changeRegistrationFee(LeagueInSeason league, double newFee){
        league.changeRegistrationFee(newFee);
    }

    public double getRegistrationFee(LeagueInSeason league)
    {
        return league.getRegistrationFee();
    }

    public void addTUTUPayment(Team team, double payment){
        financeTransactionsManagement.addTUTUPayment(team, payment);
    }

}
