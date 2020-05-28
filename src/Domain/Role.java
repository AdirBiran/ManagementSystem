package Domain;

import Data.Database;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class Role{

    protected String myRole;
    protected String userId;

    public String getID() {
        return userId;
    }

    public List<String> getMessageBox() {
        return Database.getUser(userId).getMessageBox();
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
        for (Team team : Database.getOpenTeams())
            details.add(team.toString() + " " + team.AllDetailsAboutTeam());
        return details;
    }

    public List<String> getAllOpenTeams() {
        List<String> details = new LinkedList<>();
        for (Team team : Database.getOpenTeams())
            details.add(team.toString());
        return details;
    }

    public List<String> getAllUsers() {
        List<String> users = new LinkedList<>();
        String details;
        for (User user : Database.getAllUsers()){
            details= user.getID()+":"+ user.getName()+",";
            for(Role role : user.getRoles()){
                details=details+role.myRole+":";
            }
            users.add(details);
        }
        return users;
    }

    public String getUserInfo() {
        return "Fan" +
                ", firstName=" + Database.getUser(userId).getFirstName() +
                ", lastName=" + Database.getUser(userId).getLastName() ;
    }
}
