package Domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PlayTwiceWithEachTeamPolicy extends GameAssignmentPolicy {

    @Override
    public List<Game> assignGames(List<Team> teams, List<Date> dates, LeagueInSeason league) {
        if(teams.size()<2)
            return null;
        if(dates.size()<findOutHowManyDatesRequired(teams.size())) throw new RuntimeException("not enough dates available to assign games");
        List<User> referees = league.getReferees();
        checkConstrains(teams, referees);
        List<Game> games = new LinkedList<>();

        for(Team team1 : teams){
            for(Team team2: teams){
                if(team1.isActive()&&team2.isActive()&&!team1.equals(team2)){
                    games.add(makeGame(referees, team1, team2, dates,league));
                }
            }
        }
        return games;
    }

    private int findOutHowManyDatesRequired(int size) {
        int res = (size)*(size-1);
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlayTwiceWithEachTeamPolicy);
    }
}
