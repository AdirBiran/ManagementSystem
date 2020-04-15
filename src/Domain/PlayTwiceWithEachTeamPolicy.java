package Domain;

import Presentation.Referee;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlayTwiceWithEachTeamPolicy implements GameAssignmentPolicy {

    @Override
    public List<Game> assignGames(List<Team> teams, List<Date> dates, LeagueInSeason league) {
        List<Referee> referees = league.getReferees();
        GameAssignmentChecker.checkConstrains(dates, teams, referees);
        List<Game> games = new LinkedList<>();


        for(Team team1 : teams){
            for(Team team2: teams){
                if(team1.isActive()&&team2.isActive()&&!team1.equals(team2)){
                    games.add(GameAssignmentChecker.makeGame(referees, team1, team2, dates));
                }
            }
        }
        return games;
    }
}
