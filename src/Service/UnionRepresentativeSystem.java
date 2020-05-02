package Service;
import Domain.*;
import java.util.List;
import java.util.Date;

public class UnionRepresentativeSystem {

    private UnionBudget unionBudget;

    public UnionRepresentativeSystem() {
        unionBudget = new UnionBudget();
    }

    public boolean configureNewLeague(User user, String name, String level){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).configureNewLeague(name, level);
        }
        return false;
    }

    public boolean configureNewSeason(User user, int year, Date startDate){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).configureNewSeason(year, startDate);
        }
        return false;
    }

    public String configureLeagueInSeason(User user, String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double fee){
        if (assignmentPolicy == null || scorePolicy == null)
            return null;
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy, scorePolicy,fee );
        }
        return null;
    }
    public User appointReferee(User user, String firstName,String lastName, String mail, String training)
    {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).appointReferee(firstName, lastName, mail, training);
        }
        return null;
    }

    public boolean assignRefToLeague(User user, LeagueInSeason league, User referee)
    {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).addRefereeToLeague(league, referee);
        }
        return false;
    }


    public boolean changeScorePolicy(User user, LeagueInSeason league, ScorePolicy policy)
    {
        if(user.checkUserRole("UnionRepresentative")!=null)
            return league.changeScorePolicy(policy);
        return false;
    }

    public boolean changeAssignmentPolicy(User user, LeagueInSeason league, GameAssignmentPolicy policy)
    {
        if(user.checkUserRole("UnionRepresentative")!=null)
            return league.changeAssignmentPolicy(policy);
        return false;
    }

    /*
    throws exceptions
     */
    public boolean assignGames(User user, LeagueInSeason league, List<Date> dates)
    {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).assignGames(league, dates);
        }
        return false;
    }

    public boolean addTeamToLeague(User user, LeagueInSeason league, Team team) {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative && team.isActive()){
            if(team.getBudget().addExpanse(league.getRegistrationFee()))
                unionBudget.addPayment(league.getRegistrationFee());
            else
                return false;
            league.addATeam(team);
            return ((UnionRepresentative)role).addTeamToDatabase(team);
        }
        return false;
    }

    public void calculateLeagueScore(User user, LeagueInSeason league){

        if(user.checkUserRole("UnionRepresentative")!=null)
            league.getScorePolicy().calculateLeagueScore(league);
    }

    public void calculateGameScore(User user, LeagueInSeason league,Game game){
        if(user.checkUserRole("UnionRepresentative")!=null)
            league.getScorePolicy().calculateScore(game);
    }
    public void changeRegistrationFee(User user, LeagueInSeason league, double newFee){
        if(user.checkUserRole("UnionRepresentative")!=null)
            league.changeRegistrationFee(newFee);
    }

    public double getRegistrationFee(User user,LeagueInSeason league)
    {
        if(user.checkUserRole("UnionRepresentative")!=null)
            return league.getRegistrationFee();
        return -1;
    }

    public void addTUTUPayment(User user, Team team, double payment){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            ((UnionRepresentative)role).addTUTUPayment(team, payment);
        }
    }
    public void addPaymentsFromTheTUTU(User user,double payment){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            unionBudget.addPayment( payment);
        }
    }
}
