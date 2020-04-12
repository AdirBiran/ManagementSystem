package Domain;

import Presentation.Referee;

import java.util.*;
import java.sql.Time;


public class PlayOnceWithEachTeamPolicy implements GameAssignmentPolicy {

    @Override
    public List<Game> assignGames(List<Team> teams, List<Date> dates, LeagueInSeason league) {
        if(dates.size()<teams.size()*2) throw new RuntimeException("not enough dates available to assign games");
        List<Game> games = new LinkedList<>();
        List<Referee> referees = league.getReferees();
        if(referees.size()<3) throw new RuntimeException("not enough referees available to assign games");
        Game game;

        Referee mainRef;
        List<Referee> sideRefs;
        int gameId;
        for(Team team1 : teams){
            for(Team team2: teams){
                if(!team1.equals(team2)&& checkForDuplicates(games, team1, team2)){
                    //String id, Date date, Time time, Field field, Referee mainReferee, List<Referee> sideReferees,
                    //Team hostTeam, Team guestTeam
                    mainRef = getMainReferee(referees);
                    sideRefs = getSideReferees(referees,mainRef);
                    gameId = IdGenerator.getNewId();
                    game = new Game("g"+gameId,getDateFromList(dates), team1.getField(), mainRef,sideRefs, team1,team2);
                    games.add(game);
                }
            }
        }
        return games;
    }

    private boolean checkForDuplicates(List<Game> games, Team team1, Team team2) {
        for(Game game : games){
            if(game.getGuestTeam().equals(team1) && game.getHostTeam().equals(team2))
                return false;
        }
        return true;
    }

    /*
    for now this function returns 2 side referees
    */
    private List<Referee> getSideReferees(List<Referee> referees, Referee main) {
        List<Referee> sideReferees = new LinkedList<>();
        Random random = new Random(referees.size());
        Referee ref1, ref2;
        do{
            ref1 = referees.get(random.nextInt());
        }
        while(ref1.equals(main));
        sideReferees.add(ref1);
        do{
            ref2 = referees.get(random.nextInt());
        }
        while(ref2.equals(ref1)|| ref2.equals(main));
        sideReferees.add(ref2);
        return sideReferees;
    }

    private Referee getMainReferee(List<Referee> referees) {
        Random random = new Random(referees.size());
        return referees.get(random.nextInt());

    }

    private Date getDateFromList(List<Date> dates) {
        Random random = new Random(dates.size());
        Date date = dates.get(random.nextInt());
        dates.remove(date);
        return date;
    }
}
