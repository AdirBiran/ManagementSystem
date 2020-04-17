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
    public boolean configureNewSeason(int year){
        return leagueAndGameManagement.configureNewSeason(year);
    }

    public LeagueInSeason configureLeagueInSeason(String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double fee){
        return leagueAndGameManagement.configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy,scorePolicy,fee);
    }
    public Referee appointReferee(String firstName,String lastName, String mail, String training)
    {
        return refereeManagement.appointReferee(firstName,lastName, mail, training);
    }

    public boolean assignRefToLeague(LeagueInSeason league, Referee referee)
    {
        return leagueAndGameManagement.assignRefToLeague(league, referee);
    }


    public boolean changeScorePolicy(LeagueInSeason league, ScorePolicy policy)
    {
        return leagueAndGameManagement.changeScorePolicy(league, policy);
    }

    public boolean changeAssignmentPolicy(LeagueInSeason league, GameAssignmentPolicy policy)
    {
        return leagueAndGameManagement.changeAssignmentPolicy(league, policy);
    }

    /*
    throws exceptions
     */
    public boolean assignGames(LeagueInSeason league, List<Date> dates)
    {
        return leagueAndGameManagement.assignGames(league,dates);
    }

    public void addTeamToLeague(LeagueInSeason league, Team team) {
         leagueAndGameManagement.addTeamToLeague(league, team);
    }
    public void calculateLeagueScore(LeagueInSeason league){
        leagueAndGameManagement.calculateLeagueScore(league);
    }

    public void calculateGameScore(LeagueInSeason league,Game game){
        leagueAndGameManagement.calculateGameScore(league,game);
    }
    public void changeRegistrationFee(LeagueInSeason league, double newFee){
        leagueAndGameManagement.changeRegistrationFee(league, newFee);
    }

    public void addTUTUPayment(Team team, double payment){
        financeTransactionsManagement.addTUTUPayment(team, payment);
    }

}
