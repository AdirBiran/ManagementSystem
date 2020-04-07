package Domain;


import java.util.List;
import java.util.Date;

public interface GameAssignmentPolicy {

     List<Game> assignGames(List<Team> teams, List<Date> dates, LeagueInSeason league);


}
