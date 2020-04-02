package Domain;

public class TeamManager extends Manager implements Asset {

    private Team team;

    public TeamManager()
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
}
