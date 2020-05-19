package Domain;

import Data.Database;

import java.util.*;

public class TeamOwner extends Manager implements Observer {

    private List<Team> closedTeams;
    private HashMap<User, Team> appointedTeamOwners;
    private HashMap<User, Team> appointedTeamManagers;
    private HashMap<Team,PersonalPage> personalPages;


    public TeamOwner(User user) {
        super();
        closedTeams = new LinkedList<>();
        personalPages = new HashMap<>();
        appointedTeamOwners = new HashMap<>();
        appointedTeamManagers = new HashMap<>();
        myRole = "TeamOwner";
        this.user = user;
    }

    public TeamOwner(User user, List<Team> teams, List<Team> closedTeams, HashMap<User, Team> appointedTeamOwners, HashMap<User, Team> appointedTeamManagers, HashMap<Team, PersonalPage> personalPages)
    {
        this.user = user;
        this.teamsToManage = teams;
        this.closedTeams = closedTeams;
        this.appointedTeamOwners = appointedTeamOwners;
        this.appointedTeamManagers = appointedTeamManagers;
        this.personalPages = personalPages;
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


    public boolean createTeam(User user , String teamName, List<String> playersId, List<String> coachesId, String fieldId){
        Role teamOwnerRole = user.checkUserRole("TeamOwner");
        if(teamOwnerRole != null){
            List<User> teamOwner = new LinkedList<>();
            teamOwner.add(user);

            Field field = (Field) Database.getAssetById(fieldId);
            List<User> players = findUsers(playersId);
            List<User> coaches = findUsers(coachesId);

            /**create new team*/
            Team team = new Team(teamName ,teamOwner ,players,coaches, field);
            return Database.addTeam(team);
        }
        return false;
    }

    private List<User> findUsers(List<String> usersId) {
        List<User> listOfUsers = new LinkedList<>();
        for (String id : usersId){
            User user = Database.getUser(id);
            if(user!=null){
                listOfUsers.add(user);
            }
        }
        return listOfUsers;
    }

    public boolean appointTeamOwner(User user, String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            Role teamOwnerRole = user.checkUserRole("TeamOwner");
            if (teamsToManage.contains(team)) {
                if (!team.getTeamOwners().contains(user)) {
                    if (teamOwnerRole == null) {
                        TeamOwner teamOwner = new TeamOwner(user);
                        user.addRole(teamOwner);
                        teamOwnerRole = user.checkUserRole("TeamOwner");
                    }
                    ((TeamOwner) teamOwnerRole).addTeam(team);
                    team.addTeamOwner(user);
                    appointedTeamOwners.put(user, team);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean appointTeamManager(User user, String teamId, double price, boolean manageAssets , boolean finance){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            Role teamManagerRole = user.checkUserRole("TeamManager");
            if (teamsToManage.contains(team)) {
                if (!team.getTeamManagers().contains(user) && !team.getTeamOwners().contains(user)) {
                    if (teamManagerRole == null) {
                        TeamManager teamManager = new TeamManager(user, price, manageAssets, finance);
                        user.addRole(teamManager);
                    }
                    team.addTeamManager(user, price, manageAssets, finance);
                    appointedTeamManagers.put(user, team);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeAppointTeamOwner(User user, String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            TeamOwner teamOwnerRole = (TeamOwner) user.checkUserRole("TeamOwner");
            if (this.teamsToManage.contains(team) && this.appointedTeamOwners.containsKey(user)) {
                if (team.getTeamOwners().contains(user)) {
                    this.appointedTeamOwners.remove(user);
                    removeAllAppoint(user, team.getID());
                    team.removeTeamOwner(user);
                    if (teamOwnerRole.getTeamsToManage().size() == 0)
                        user.getRoles().remove(teamOwnerRole);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean removeAppointTeamManager(User user, String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            TeamManager teamManagerRole = (TeamManager) user.checkUserRole("TeamManager");
            if (this.teamsToManage.contains(team) && this.appointedTeamManagers.containsKey(user)) {
                if (team.getTeamManagers().contains(user)) {
                    team.removeTeamManager(user);
                    this.appointedTeamManagers.remove(user);
                    if (teamManagerRole.getTeamsToManage().size() == 0)
                        user.getRoles().remove(teamManagerRole);
                    return true;
                }
            }
        }
        return false;
    }

    private void removeAllAppoint(User user, String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            TeamOwner teamOwnerRole = (TeamOwner) user.checkUserRole("TeamOwner");

            for (User userTO : teamOwnerRole.getAppointedTeamOwners().keySet()) {
                teamOwnerRole.removeAppointTeamOwner(userTO, team.getID());
            }
            for (User userTM : teamOwnerRole.getAppointedTeamManagers().keySet()) {
                teamOwnerRole.removeAppointTeamManager(userTM, team.getID());
            }
        }
    }

    public boolean closeTeam(String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            if (teamsToManage.contains(team) && !closedTeams.contains(team)) {
                team.setActive(false);
                teamsToManage.remove(team);
                closedTeams.add(team);
                return true;
            }
        }
        return false;
    }
    public boolean reopenTeam(String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            if (closedTeams.contains(team) && !team.isActive() && !team.isPermanentlyClosed()) {
                team.setActive(true);
                closedTeams.remove(team);
                teamsToManage.add(team);
                return true;
            }
        }
        return false;
    }

    public List<Team> getClosedTeams() {
        return closedTeams;
    }

    @Override
    public String myRole() {
        return "TeamOwner";
    }

    @Override
    public String toString() {
        return "TeamOwner" +
                ", id=" + user.getID() +
                ": name=" + user.getName();
    }

    @Override
    public void update(Observable o, Object arg) {
        String news = (String)arg;
        user.addMessage(news);
    }

}
