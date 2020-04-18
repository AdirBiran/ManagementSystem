package Domain;

import java.util.*;



public class PlayOnceWithEachTeamPolicy implements GameAssignmentPolicy {

    @Override
    public List<Game> assignGames(List<Team> teams, List<Date> dates, LeagueInSeason league) {

        List<Referee> referees = league.getReferees();
        GameAssignmentHelper.checkConstrains(dates, teams, referees);
        List<Game> games = new LinkedList<>();

        for(Team team1 : teams){
            for(Team team2: teams){
                if(team1.isActive()&&team2.isActive()&&!team1.equals(team2)&& GameAssignmentHelper.checkForDuplicates(games, team1, team2)){
                    games.add(GameAssignmentHelper.makeGame(referees, team1, team2, dates,league));
                }
            }
        }
        return games;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PlayOnceWithEachTeamPolicy) return true;
        return false;
    }
}
