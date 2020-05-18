package Service;
import Domain.*;
import Logger.Logger;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Date;

public class UnionRepresentativeSystem {

    public UnionRepresentativeSystem() {
    }

    public boolean configureNewLeague(String userId, String name, String level){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative) role).configureNewLeague(name, level);

                if (success)
                    Logger.logEvent(user.getID(), "Configured new League");
                else
                    Logger.logError("Configure league Failed");
                return success;
            }
        }
        return false;
    }

    public boolean configureNewSeason(String userId, int year, Date startDate){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative) role).configureNewSeason(year, startDate);
                if (success)
                    Logger.logEvent(user.getID(), "Configured new Season");
                else
                    Logger.logError("Configure Season Failed");
                return success;
            }
        }
        return false;
    }

    public String configureLeagueInSeason(String userId, String nameOfLeague, String yearOfSeason, String assignmentPolicy, String scorePolicy, double fee){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            if (assignmentPolicy == null || scorePolicy == null)
                return null;
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                LeagueInSeason lis = ((UnionRepresentative) role).configureLeagueInSeason(nameOfLeague, yearOfSeason, assignmentPolicy, scorePolicy, fee);

                if (lis != null)
                    Logger.logEvent(user.getID(), "Configured league in season");
                else
                    Logger.logError("Configuring league in season Failed");

                return lis.getId();
            }
        }
        return null;
    }
    public String appointReferee(String userId, String firstName,String lastName, String mail, String training)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                Referee.TrainingReferee trainingCoach= Referee.TrainingReferee.valueOf(training);
                User appointedRef = ((UnionRepresentative) role).appointReferee(firstName, lastName, mail, trainingCoach);

                if (appointedRef != null)
                    Logger.logEvent(user.getID(), "Appointed referee " + appointedRef.getID());
                else
                    Logger.logError("Appointing Referee Failed");

                return appointedRef.getID();
            }
        }
        return null;
    }

    public boolean assignRefToLeague(String userId, String leagueId, String refereeId)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                User referee = UserFactory.getUser(refereeId);
                Referee refereeRole = (Referee) referee.checkUserRole("Referee");
                if (refereeRole instanceof Referee) {
                    boolean success = ((UnionRepresentative) role).addRefereeToLeague(leagueId, refereeRole);
                    if (success)
                        Logger.logEvent(user.getID(), "Assigned Referee " + referee.getID() + " to League");
                    else
                        Logger.logError("Assigning referee to league Failed");

                    return success;
                }
            }
        }
        return false;
    }


    public boolean changeScorePolicy(String userId, String leagueId, String policy)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative) role).changeScorePolicy(leagueId, policy);
                if (success)
                    Logger.logEvent(user.getID(), "Changed score policy");
                else
                    Logger.logError("Change score policy Failed");

                return success;
            }
        }
        return false;
    }

    public boolean changeAssignmentPolicy(String userId, String leagueId, String policy)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative) role).changeAssignmentPolicy(leagueId, policy);
                if (success)
                    Logger.logEvent(user.getID(), "Changed assignment policy");
                else
                    Logger.logError("Change assignment policy Failed");
                return success;
            }
        }
        return false;
    }

    /*
    throws exceptions
     */
    public boolean assignGames(String userId, String leagueId, List<Date> dates)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative) role).assignGames(leagueId, dates);

                if (success)
                    Logger.logEvent(user.getID(), "Assigned games to league");
                else
                    Logger.logError("Assigning games to league Failed");

                return success;
            }
        }
        return false;
    }

    public boolean changeGameDate(String userId, String gameId, Date newDate){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative)role).changeGameDate(gameId, newDate);
            }
        }
        return false;
    }

    public boolean changeGameLocation(String userId, String gameId, String fieldId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative)role).changeGameLocation(gameId, fieldId);
            }
        }
        return false;
    }

    public boolean addTeamToLeague(String userId, String leagueId, String teamId) {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return  ((UnionRepresentative)role).addTeamToLeague(leagueId, teamId);
            }
        }
        return false;
    }

    public boolean calculateLeagueScore(String userId, String leagueId)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative)role).calculateLeagueScore(leagueId);
                if(success)
                    Logger.logEvent(user.getID(), "Calculated league score");
            }
        }
        return false;
    }

    public boolean calculateGameScore(String userId, String leagueId,String gameId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative)role).calculateGameScore(leagueId, gameId);
                if(success) {
                    Logger.logEvent(user.getID(), "Calculated game score");
                    return true;
                }
            }
        }
        return false;
    }
    public boolean changeRegistrationFee(String userId, String leagueId, double newFee)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative)role).changeRegistrationFee(leagueId, newFee);
                if(success) {
                    Logger.logEvent(user.getID(), "Changed registration fee to " + newFee);
                    return true;
                }
            }
        }
        return false;
    }

    public double getRegistrationFee(String userId,String leagueId)
    {
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative)role).getRegistrationFee(leagueId);

            }
        }
        return -1;
    }

    public boolean addTUTUPayment(String userId, String teamId, double payment){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                boolean success = ((UnionRepresentative) role).addTUTUPayment(teamId, payment);
                if(success) {
                    Logger.logEvent(user.getID(), "Added TUTU payment of " + payment);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean addPaymentsFromTheTUTU(String userId, String teamName, String date ,double payment){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return StubAccountingSystem.addPayment(teamName, date, payment);
            }
        }
        return false;
    }

    public boolean addFieldToSystem(String userId,String location,String fieldName, int capacity, double price){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative)role).addFieldToSystem(location, fieldName, capacity, price);
            }
        }
        return false;
    }

    public List<String> allLeaguesInSeasons(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative) role).allLeaguesInSeasons();
            }
        }
        return null;
    }
    public List<String> getAllLeagues(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative) role).getAllLeagues();
            }
        }
        return null;
    }
    public List<String> getAllSeasons(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative) role).getAllSeasons();
            }
        }
        return null;
    }
    public List<String> getAllScorePolicies(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return ((UnionRepresentative) role).getAllScorePolicies();
            }
        }
        return null;
    }

    public List<String> getAllDetailsAboutOpenTeams(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return role.getAllDetailsAboutOpenTeams();
            }
        }
        return null;
    }

    public List<String> getAllOpenTeams(String userId){
        User user = UserFactory.getUser(userId);
        if(user!=null) {
            Role role = user.checkUserRole("UnionRepresentative");
            if (role instanceof UnionRepresentative) {
                return role.getAllOpenTeams();
            }
        }
        return null;
    }

    public List<String> getAllPastGames(String userID){
        User user= UserFactory.getUser(userID);
        Role role = user.checkUserRole("UnionRepresentative");
        if(role instanceof  UnionRepresentative ) {
            return  ((UnionRepresentative)role).getAllPastGames();
        }
        return null;
    }
}
