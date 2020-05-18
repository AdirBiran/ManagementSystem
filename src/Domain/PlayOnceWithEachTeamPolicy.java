package Domain;

import java.util.*;

public class PlayOnceWithEachTeamPolicy extends GameAssignmentPolicy {

    @Override
    public List<Game> assignGames(List<Date> dates, LeagueInSeason league) {
        if(league.getTeams().size()>2) {
            if (dates.size() < findOutHowManyDatesRequired(league.getTeams().size()))
                throw new RuntimeException("not enough dates available to assign games");
            List<Referee> referees = league.getReferees();
            checkConstrains(league.getTeams(), referees);
            List<Game> games = new LinkedList<>();

            for (Team team1 : league.getTeams()) {
                for (Team team2 : league.getTeams()) {
                    if (team1.isActive() && team2.isActive() && !team1.equals(team2) && checkForDuplicates(games, team1, team2)) {
                        games.add(makeGame(referees, team1, team2, dates, league));
                    }
                }
            }
            return games;
        }
        return null;
    }

    private int findOutHowManyDatesRequired(int teamsSize){
        int counter=0;
        teamsSize--;
        while(teamsSize>0){
            counter +=teamsSize;
            teamsSize--;
        }
        return counter;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PlayOnceWithEachTeamPolicy) ;
    }

    public String getName(){
        return "PlayOnceWithEachTeamPolicy";
    }
}
