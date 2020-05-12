package Domain;

import java.util.HashSet;
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

}
