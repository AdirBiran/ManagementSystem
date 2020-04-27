package Domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TeamOwner extends Manager implements Role{


    /**Belongs only to a management class*/
    private boolean permissionAppointTOw;
    private boolean permissionAppointTM;
    private boolean permissionRemoveAppointTO;
    private boolean permissionRemoveAppointTM;
    private boolean permissionCloseTeam;
    private boolean permissionReopenTeam;
    private boolean permissionReportIncome;
    private boolean permissionReportExpanse;
    private boolean permissionManageBalance; //?


    private List<Team> teams;

    private List<Team> closedTeams;
    private HashMap<User, Team> appointedTeamOwners;
    private HashMap<User, Team> appointedTeamManagers;


    public TeamOwner() {
        super();

        fullPermission(true);
        closedTeams = new LinkedList<>();

        appointedTeamOwners = new HashMap<>();
        appointedTeamManagers = new HashMap<>();
    }


    public void fullPermission(boolean value){

        permissionAppointTOw = value;
        permissionAppointTM = value;
        permissionRemoveAppointTO = value;
        permissionRemoveAppointTM = value;
        permissionCloseTeam = value;
        permissionReopenTeam = value;
        permissionReportIncome = value;
        permissionReportExpanse = value;
        permissionManageBalance =value;

    }

    public void financePermission(boolean value){
        permissionReportIncome = value;
        permissionReportExpanse = value;
        permissionManageBalance = value;
    }

    public HashMap<User, Team> getAppointedTeamOwners() {
        return appointedTeamOwners;
    }

    public HashMap<User, Team> getAppointedTeamManagers() {
        return appointedTeamManagers;
    }

    public boolean isPermissionManageBalance() {
        return permissionManageBalance;
    }


    public boolean isPermissionAppointTOw() {
        return permissionAppointTOw;
    }

    public boolean isPermissionAppointTM() {
        return permissionAppointTM;
    }

    public boolean isPermissionRemoveAppointTO() {
        return permissionRemoveAppointTO;
    }

    public boolean isPermissionRemoveAppointTM() {
        return permissionRemoveAppointTM;
    }

    public boolean isPermissionCloseTeam() {
        return permissionCloseTeam;
    }

    public boolean isPermissionReopenTeam() {
        return permissionReopenTeam;
    }

    public boolean isPermissionReportIncome() {
        return permissionReportIncome;
    }

    public boolean isPermissionReportExpanse() {
        return permissionReportExpanse;
    }


    public void setPermissionAppointTOw(boolean permissionAppointTOw) {
        this.permissionAppointTOw = permissionAppointTOw;
    }

    public void setPermissionAppointTM(boolean permissionAppointTM) {
        this.permissionAppointTM = permissionAppointTM;
    }

    public void setPermissionRemoveAppointTO(boolean permissionRemoveAppointTO) {
        this.permissionRemoveAppointTO = permissionRemoveAppointTO;
    }

    public void setPermissionRemoveAppointTM(boolean permissionRemoveAppointTM) {
        this.permissionRemoveAppointTM = permissionRemoveAppointTM;
    }

    public void setPermissionCloseTeam(boolean permissionCloseTeam) {
        this.permissionCloseTeam = permissionCloseTeam;
    }

    public void setPermissionReopenTeam(boolean permissionReopenTeam) {
        this.permissionReopenTeam = permissionReopenTeam;
    }

    public void setPermissionReportIncome(boolean permissionReportIncome) {
        this.permissionReportIncome = permissionReportIncome;
    }

    public void setPermissionReportExpanse(boolean permissionReportExpanse) {
        this.permissionReportExpanse = permissionReportExpanse;
    }

    public void setPermissionManageBalance(boolean permissionManageBalance) {
        this.permissionManageBalance = permissionManageBalance;
    }

    public void addTeam(Team team)
    {
        if(!teams.contains(team))
            teams.add(team);
    }
    public List<Team> getTeams(){return teams;}

    public Team getTeamById(String id){
        for(Team team : teams){
            if(team.getID().equals(id))
                return team;
        }
        return null;
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }


    public boolean appointTeamOwner(User user, Team team){
        if(permissionAppointTOw && teams.contains(team)){
            if(!team.getTeamOwners().contains(user)){
                if(!user.getRoles().contains(this)){
                    TeamOwner teamOwner = new TeamOwner();
                    teamOwner.fullPermission(true);
                    teamOwner.addTeam(team);
                    user.addRole(teamOwner);
                }
                else{
                    int index = user.getRoles().indexOf(this);
                    ((TeamOwner)user.getRoles().get(index)).addTeam(team);
                }
                team.addTeamOwner(user);
                appointedTeamOwners.put(user, team);
                return true;
            }
        }
        return false;
    }

    /**
     * has only manage asset authorization!
     * @param user
     * @param team
     * @return
     */
    public boolean appointTeamManager(User user, Team team){
        if(permissionAppointTM && teams.contains(team)){
            if(!team.getTeamManagers().contains(user)){
                if(!user.getRoles().contains(this)){
                    TeamManager teamManagerAutho = new TeamManager(user.getID(), 0);
                    user.addRole(teamManagerAutho);
                }
                else{
                    int index = user.getRoles().indexOf(this);
                    ((TeamOwner)user.getRoles().get(index)).addTeam(team);
                }
                team.addTeamManager(user);
                appointedTeamManagers.put(user,team);
                return true;
            }
        }
        return false;
    }
    public boolean removeAppointTeamOwner(User user, Team team){
        if(permissionRemoveAppointTO && teams.contains(team) && appointedTeamOwners.containsKey(user)){
            if(team.getTeamOwners().contains(user) && user.getRoles().contains(this)){
                team.removeTeamOwner(user);
                appointedTeamOwners.remove(user);
                removeAuthorization(user, team);
                return true;
            }
        }
        return false;
    }
    public boolean removeAppointTeamManager(User user, Team team){
        if(permissionRemoveAppointTM && teams.contains(team)&& appointedTeamManagers.containsKey(user)){
            if(team.getTeamManagers().contains(user) && user.getRoles().contains(this)){
                team.removeTeamManager(user);
                removeAuthorization(user, team);
                appointedTeamManagers.remove(user);
                return true;
            }
        }
        return false;
    }

    private void removeAuthorization(User user, Team team){
        int index = user.getRoles().indexOf(this);
        TeamOwner autho = (TeamOwner) user.getRoles().get(index);
        if(autho.getTeams().size()==1)
            user.getRoles().remove(this);
        else{
            autho.removeTeam(team);
        }
        if(autho.isPermissionAppointTOw() || autho.isPermissionAppointTM()){
            for(User authorized : autho.getAppointedTeamOwners().keySet()){
                autho.removeAppointTeamOwner(authorized,team);
            }
            for(User authorized : autho.getAppointedTeamManagers().keySet()){
                autho.removeAppointTeamManager(authorized, team);
            }
        }
    }

    public boolean closeTeam(Team team){
        if(permissionCloseTeam && teams.contains(team)&& !closedTeams.contains(team)){
            team.setActive(false);
            teams.remove(team);
            closedTeams.add(team);
            return true;
        }
        return false;
    }
    public boolean reopenTeam(Team team){
        if(permissionReopenTeam && closedTeams.contains(team) &&!team.isActive() &&!team.isPermanentlyClosed()){
            team.setActive(true);
            closedTeams.remove(team);
            teams.add(team);
            return true;
        }
        return false;
    }
    public boolean reportIncome(Team team, double income){
        if(permissionReportIncome && teams.contains(team)){
            return team.getBudget().addIncome(income);
        }
        return false;
    }
    public boolean reportExpanse(Team team, double expanse){
        if(permissionReportExpanse && teams.contains(team)){
            return team.getBudget().addExpanse(expanse);
        }
        return false;
    }

    public double getBalance(Team team){
        if(permissionManageBalance && teams.contains(team))
            return team.getBudget().getBalance();
        return -1;
    }

    @Override
    public String myRole() {
        return "TeamOwner";
    }

  /*  @Override
    public boolean equals(Object obj) {
        return obj instanceof TeamOwner;
    }*/


}
