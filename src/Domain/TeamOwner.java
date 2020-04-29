package Domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TeamOwner extends Manager implements Role{

    private List<Team> closedTeams;
    private HashMap<User, Team> appointedTeamOwners;
    private HashMap<User, Team> appointedTeamManagers;
    private HashMap<Team,PersonalPage> personalPages;


    public TeamOwner() {
        super();
        closedTeams = new LinkedList<>();
        personalPages = new HashMap<>();
        appointedTeamOwners = new HashMap<>();
        appointedTeamManagers = new HashMap<>();
    }

    public HashMap<User, Team> getAppointedTeamOwners() {
        return appointedTeamOwners;
    }

    public HashMap<User, Team> getAppointedTeamManagers() {
        return appointedTeamManagers;
    }

    public void addTeam(Team team)
    {
        if(!teamsToManage.contains(team))
            teamsToManage.add(team);
    }

    public void addTeamPersonalPage(Team team , PersonalPage personalPage){
        personalPages.put(team , personalPage);
    }

    public List<Team> getTeamsToManage(){return teamsToManage;}

    public Team getTeamById(String id){
        for(Team team : teamsToManage){
            if(team.getID().equals(id))
                return team;
        }
        return null;
    }

    public void removeTeam(Team team) {
        teamsToManage.remove(team);
    }


    public boolean appointTeamOwner(User user, Team team){
        Role teamOwnerRole = user.checkUserRole("TeamOwner");
        if(teamsToManage.contains(team)){
            if(!team.getTeamOwners().contains(user)){
                if(teamOwnerRole==null){
                    TeamOwner teamOwner = new TeamOwner();
                    teamOwner.addTeam(team);
                    user.addRole(teamOwner);
                }
                else{
                    ((TeamOwner)teamOwnerRole).addTeam(team);
                }
                team.addTeamOwner(user);
                appointedTeamOwners.put(user, team);
                return true;
            }
        }
        return false;
    }

    public boolean appointTeamManager(User user, Team team, double price, boolean manageAssets , boolean finance){
        Role teamManagerRole = user.checkUserRole("TeamManager");
        if(teamsToManage.contains(team)){
            if(!team.getTeamManagers().contains(user) && !team.getTeamOwners().contains(user)){
                if(teamManagerRole==null){
                    TeamManager teamManager = new TeamManager(user.getID(), price, manageAssets, finance);
                    user.addRole(teamManager);
                }
                else{
                    ((TeamManager)teamManagerRole).addTeam(team);
                }
                team.addTeamManager(user, price, manageAssets, finance);
                appointedTeamManagers.put(user,team);
                return true;
            }
        }
        return false;
    }
    public boolean removeAppointTeamOwner(User user, Team team){
        if(teamsToManage.contains(team) && appointedTeamOwners.containsKey(user)){
            if(team.getTeamOwners().contains(user)){
                team.removeTeamOwner(user);
                appointedTeamOwners.remove(user);
                removeAllAppoint(user, team);
                return true;
            }
        }
        return false;
    }
    public boolean removeAppointTeamManager(User user, Team team){
        if(teamsToManage.contains(team)&& appointedTeamManagers.containsKey(user)){
            if(team.getTeamManagers().contains(user)){
                team.removeTeamManager(user);
                appointedTeamManagers.remove(user);
                return true;
            }
        }
        return false;
    }

    private void removeAllAppoint(User user, Team team){
        TeamOwner teamOwnerRole = (TeamOwner) user.checkUserRole("TeamOwner");

        teamOwnerRole.removeTeam(team);
        if(teamOwnerRole.getTeamsToManage().size()==0)
            user.getRoles().remove(teamOwnerRole);

        for(User userTO : teamOwnerRole.getAppointedTeamOwners().keySet()){
            teamOwnerRole.removeAppointTeamOwner(userTO,team);
        }
        for(User userTM : teamOwnerRole.getAppointedTeamManagers().keySet()){
            teamOwnerRole.removeAppointTeamManager(userTM, team);
        }
    }

    public boolean closeTeam(Team team){
        if(teamsToManage.contains(team)&& !closedTeams.contains(team)){
            team.setActive(false);
            teamsToManage.remove(team);
            closedTeams.add(team);
            return true;
        }
        return false;
    }
    public boolean reopenTeam(Team team){
        if(closedTeams.contains(team) &&!team.isActive() &&!team.isPermanentlyClosed()){
            team.setActive(true);
            closedTeams.remove(team);
            teamsToManage.add(team);
            return true;
        }
        return false;
    }

    @Override
    public String myRole() {
        return "TeamOwner";
    }

}
