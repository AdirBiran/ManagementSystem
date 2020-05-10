package Service;
import Domain.*;
import Logger.Logger;

import javax.xml.crypto.Data;
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
            boolean success = ((UnionRepresentative)role).configureNewLeague(name, level);

            if (success)
                Logger.logEvent(user.getID(), "Configured new League");
            else
                Logger.logError("Configure league Failed");
            return success;
        }
        return false;
    }

    public boolean configureNewSeason(User user, int year, Date startDate){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            boolean success = ((UnionRepresentative)role).configureNewSeason(year, startDate);
            if (success)
                Logger.logEvent(user.getID(), "Configured new Season");
            else
                Logger.logError("Configure Season Failed");

            return success;

        }
        return false;
    }

    public LeagueInSeason configureLeagueInSeason(User user, String nameOfLeague, String yearOfSeason, GameAssignmentPolicy assignmentPolicy, ScorePolicy scorePolicy, double fee){
        if (assignmentPolicy == null || scorePolicy == null)
            return null;
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            LeagueInSeason lis = ((UnionRepresentative)role).configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy, scorePolicy,fee );

            if (lis != null)
                Logger.logEvent(user.getID(), "Configured league in season");
            else
                Logger.logError("Configuring league in season Failed");

            return lis;
        }
        return null;
    }
    public User appointReferee(User user, String firstName,String lastName, String mail, Referee.TrainingReferee training)
    {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            User appointedRef = ((UnionRepresentative)role).appointReferee(firstName, lastName, mail, training);

            if (appointedRef != null)
                Logger.logEvent(user.getID(), "Appointed referee " + appointedRef.getID());
            else
                Logger.logError("Appointing Referee Failed");

            return appointedRef;
        }
        return null;
    }

    public boolean assignRefToLeague(User user, LeagueInSeason league, User referee)
    {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            Referee refereeRole = (Referee) referee.checkUserRole("Referee");
            if(refereeRole instanceof Referee) {
                boolean success = ((UnionRepresentative) role).addRefereeToLeague(league, refereeRole);
                if (success)
                    Logger.logEvent(user.getID(), "Assigned Referee " + referee.getID() + " to League");
                else
                    Logger.logError("Assigning referee to league Failed");

                return success;
            }
        }
        return false;
    }


    public boolean changeScorePolicy(User user, LeagueInSeason league, ScorePolicy policy)
    {
        if(user.checkUserRole("UnionRepresentative")!=null)
        {
            boolean success = league.changeScorePolicy(policy);

            if (success)
                Logger.logEvent(user.getID(), "Changed score policy");
            else
                Logger.logError("Change score policy Failed");

            return success;

        }
        return false;
    }

    public boolean changeAssignmentPolicy(User user, LeagueInSeason league, GameAssignmentPolicy policy)
    {
        if(user.checkUserRole("UnionRepresentative")!=null)
        {
            boolean success = league.changeAssignmentPolicy(policy);

            if (success)
                Logger.logEvent(user.getID(), "Changed assignment policy");
            else
                Logger.logError("Change assignment policy Failed");

            return success;

        }
        return false;
    }

    /*
    throws exceptions
     */
    public boolean assignGames(User user, LeagueInSeason league, List<Date> dates)
    {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            boolean success = ((UnionRepresentative)role).assignGames(league, dates);

            if (success)
                Logger.logEvent(user.getID(), "Assigned games to league");
            else
                Logger.logError("Assigning games to league Failed");

            return success;
        }
        return false;
    }

    public boolean changeGameDate(User user, Game game, Date newDate){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative && game.getDate().after(new Date())){
            game.setDate(newDate);
            return true;
        }
        return false;
    }

    public boolean changeGameLocation(User user, Game game, Field newField){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative && newField.isActive()){
            game.setField(newField);
            return true;
        }
        return false;
    }

    public boolean addTeamToLeague(User user, LeagueInSeason league, Team team) {
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative && team.isActive()){
            if(team.getBudget().addExpanse(league.getRegistrationFee()))
            {
                unionBudget.addPayment(league.getRegistrationFee());
                league.addATeam(team);
                boolean success = ((UnionRepresentative)role).addTeamToDatabase(team);

                if (success)
                    Logger.logEvent(user.getID(), "Added team " + team.getName() + " to league");
                else
                    Logger.logError("Adding Team to league failed");

                return success;
            }
            else
                return false;

        }
        return false;
    }

    public void calculateLeagueScore(User user, LeagueInSeason league){

        if(user.checkUserRole("UnionRepresentative")!=null) {
            league.getScorePolicy().calculateLeagueScore(league);
            Logger.logEvent(user.getID(), "Calculated league score");
        }

    }

    public void calculateGameScore(User user, LeagueInSeason league,Game game){
        if(user.checkUserRole("UnionRepresentative")!=null) {
            league.getScorePolicy().calculateScore(game);
            Logger.logEvent(user.getID(), "Calculated game score");
        }
    }
    public void changeRegistrationFee(User user, LeagueInSeason league, double newFee){
        if(user.checkUserRole("UnionRepresentative")!=null)
        {
            league.changeRegistrationFee(newFee);
            Logger.logEvent(user.getID(), "Changed registration fee to " + newFee);
        }
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
            Logger.logEvent(user.getID(), "Added TUTU payment of " + payment);
        }
    }
    public void addPaymentsFromTheTUTU(User user,double payment){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            unionBudget.addPayment( payment);
        }
    }

    public List<String> allLeaguesInSeasons(User user){
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof UnionRepresentative){
            return ((UnionRepresentative)role).allLeaguesInSeasons();
        }
        return null;
    }
}
