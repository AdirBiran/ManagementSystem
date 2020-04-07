package Service;


import Domain.*;
import Presentation.Referee;

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

    public boolean configureLeagueInSeason(String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, List<Game> games){
        return leagueAndGameManagement.configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy,scorePolicy,games);
    }
    public boolean appointReferee(String name, String ID, String mail, String training)
    {
        return refereeManagement.appointReferee(name, ID, mail, training);
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
    public boolean assignGames(LeagueInSeason league, List<Date> dates)
    {
        return leagueAndGameManagement.assignGames(league,dates);
    }

}
