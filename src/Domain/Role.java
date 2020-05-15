package Domain;

import Data.Database;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public abstract class Role{

    protected String myRole;
    protected User user;

    public User getUser() {
        return user;
    }

    public List<Notice> getMessageBox() {
        return user.getMessageBox();
    }

    public String myRole(){
        return myRole;
    }

    public String teamsString(HashSet<Team> teams){
        String teamsName="";
        for (Team team : teams){
            teamsName = teamsName + team.getName() + ",";
        }
        return teamsName;
    }
    public List<String> getAllDetailsAboutOpenTeams() {
        List<String> details = new LinkedList<>();
        if(this instanceof Admin || this instanceof UnionRepresentative) {
            for (Team team : Database.getOpenTeams())
                details.add(team.toString() + " " + team.AllDetailsAboutTeam());
        }
        return details;
    }

    public List<String> getAllOpenTeams() {
        List<String> details = new LinkedList<>();
        if(this instanceof Admin || this instanceof UnionRepresentative) {
            for (Team team : Database.getOpenTeams())
                details.add(team.toString());
        }
        return details;
    }
}
