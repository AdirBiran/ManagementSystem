package Presentation;

import Domain.Asset;
import Domain.Team;

public class TeamManager extends Manager implements Asset {

    private Team team;

    public TeamManager(Team team)
    {

        this.team = team;
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public Team getTeam() {
        return team;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
