package Domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public abstract class GameAssignmentPolicy {

     public abstract List<Game> assignGames(List<Date> dates, LeagueInSeason league);

     protected boolean checkForDuplicates(List<Game> games, Team team1, Team team2) {
          for(Game game : games){
               if(game.getGuestTeam().equals(team1) && game.getHostTeam().equals(team2))
                    return false;
          }
          return true;
     }

     /*
     for now this function returns 2 side referees
     */
     protected List<Referee> getSideReferees(List<Referee> referees, Referee main) {
          List<Referee> sideReferees = new LinkedList<>();
          int index ;
          Referee ref1, ref2;
          do{
               index = (int)((Math.random())*referees.size())%referees.size();
               ref1 = referees.get(index);
          }
          while(ref1.equals(main));
          sideReferees.add(ref1);
          do{
               index = (int)((Math.random())*referees.size())%referees.size();
               ref2 = referees.get(index);
          }
          while(ref2.equals(ref1)|| ref2.equals(main));
          sideReferees.add(ref2);
          return sideReferees;
     }

     protected Referee getMainReferee(List<Referee> referees) {
          int index = (int)((Math.random())*referees.size())%referees.size();
          Referee main = referees.get(index);
          return main;

     }

     protected Date getDateFromList(List<Date> dates) {
          int index = (int)((Math.random())*dates.size())%dates.size();
          Date date = dates.get(index);
          dates.remove(date);
          return date;
     }

     protected void checkConstrains( List<Team> teams, List<Referee> referees) {
          if(referees.size()<3) throw new RuntimeException("not enough referees available to assign games");
          if(teams.size()<13) throw new RuntimeException("not enough teams available to assign games");
     }


     protected Game makeGame(List<Referee> referees, Team team1, Team team2, List<Date> dates, LeagueInSeason league) {
          List<Referee> sideRefs;
          Referee mainRef = getMainReferee(referees);
          sideRefs = getSideReferees(referees,mainRef);
          return new Game(getDateFromList(dates), team1.getField(), mainRef,sideRefs, team1,team2, league);
     }


}
