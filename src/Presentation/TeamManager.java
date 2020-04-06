package Presentation;

import Domain.Asset;
import Domain.Team;

import java.util.List;

public class TeamManager extends Manager implements Asset {

    private Team team;

    public TeamManager(String name, String ID, String mail, Team team) {
        super(name, ID, mail);
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
