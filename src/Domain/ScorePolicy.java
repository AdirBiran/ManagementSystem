package Domain;

public interface ScorePolicy {

    void calculateScore(Game game);
    void calculateLeagueScore(LeagueInSeason league);

}
