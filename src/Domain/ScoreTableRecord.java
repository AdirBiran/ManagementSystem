package Domain;

public class ScoreTableRecord
{
    private Team team;
    private int totalScore;

    public ScoreTableRecord()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public Team getTeam() {
        return team;
    }

    /**
     *
     * @return
     */
    public int getTotalScore() {
        return totalScore;
    }
}
