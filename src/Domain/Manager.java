package Domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Manager extends User {

    protected HashMap<Team, Boolean> isClosedTeam;
    protected HashMap<Team,HashMap<String,User>> appointmentAssetsInTeams;

    public Manager(String firstName,String lastName, String ID, String mail) {
        super(firstName,lastName, ID, mail);
        this.isClosedTeam = new HashMap<>();
        this.appointmentAssetsInTeams = new HashMap<>();
    }

    /**
     *
     */
    public void addTeam(Team team) {
        if(!teams.contains(team)){
            teams.add(team);
            isClosedTeam.put(team, false);
            //not sure about this
            appointmentAssetsInTeams.put(team,new HashMap<>());
        }

    }

}
