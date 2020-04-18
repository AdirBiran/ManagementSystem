package Domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameAssignmentHelper {

    public static boolean checkForDuplicates(List<Game> games, Team team1, Team team2) {
        for(Game game : games){
            if(game.getGuestTeam().equals(team1) && game.getHostTeam().equals(team2))
                return false;
        }
        return true;
    }

    /*
    for now this function returns 2 side referees
    */
    public static List<Referee> getSideReferees(List<Referee> referees, Referee main) {
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

    public static Referee getMainReferee(List<Referee> referees) {
        int index = (int)((Math.random())*referees.size())%referees.size();
        Referee main = referees.get(index);
        return main;

    }

    public static Date getDateFromList(List<Date> dates) {
        int index = (int)((Math.random())*dates.size())%dates.size();
        Date date = dates.get(index);
        dates.remove(date);
        return date;
    }

    public static void checkConstrains(List<Date> dates, List<Team> teams, List<Referee> referees) {
        if(dates.size()<teams.size()*2) throw new RuntimeException("not enough dates available to assign games");
        if(referees.size()<3) throw new RuntimeException("not enough referees available to assign games");
    }

    public static Game makeGame(List<Referee> referees, Team team1, Team team2, List<Date> dates, LeagueInSeason league) {
        Referee mainRef;
        List<Referee> sideRefs;
        mainRef = getMainReferee(referees);
        sideRefs = getSideReferees(referees,mainRef);
        return new Game(getDateFromList(dates), team1.getField(), mainRef,sideRefs, team1,team2, league);
    }
}
