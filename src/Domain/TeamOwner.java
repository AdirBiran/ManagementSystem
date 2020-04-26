package Domain;

import Data.Database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TeamOwner extends Manager implements Role{


    private boolean appointTeamOwner;
    private boolean appointTeamManager;
    private boolean removeAppointTeamOwner;
    private boolean removeAppointTeamManager;
    private boolean closeTeam;
    private boolean reopenTeam;
    private boolean reportIncome;
    private boolean reportExpanse;
    private boolean getBalance;

    private LinkedList<Team> teamsToManage;
    private List<Team> closedTeams;
    private HashMap<User, Team> appointedTeamOwners;
    private HashMap<User, Team> appointedTeamManagers;


    public TeamOwner() {
        giveAll(true);

        teamsToManage = new LinkedList<>();
        closedTeams = new LinkedList<>();

        appointedTeamOwners = new HashMap<>();
        appointedTeamManagers = new HashMap<>();
    }


    public void giveAll(boolean value){

        appointTeamOwner= value;
        appointTeamManager= value;
        removeAppointTeamOwner= value;
        removeAppointTeamManager= value;
        closeTeam= value;
        reopenTeam= value;
        reportIncome= value;
        reportExpanse= value;
        getBalance=value;

    }

    public void giveFinance(){
        reportIncome= true;
        reportExpanse= true;
        getBalance = true;
    }

    public HashMap<User, Team> getAppointedTeamOwners() {
        return appointedTeamOwners;
    }

    public HashMap<User, Team> getAppointedTeamManagers() {
        return appointedTeamManagers;
    }

    public boolean isGetBalance() {
        return getBalance;
    }


    public boolean isAppointTeamOwner() {
        return appointTeamOwner;
    }

    public boolean isAppointTeamManager() {
        return appointTeamManager;
    }

    public boolean isRemoveAppointTeamOwner() {
        return removeAppointTeamOwner;
    }

    public boolean isRemoveAppointTeamManager() {
        return removeAppointTeamManager;
    }

    public boolean isCloseTeam() {
        return closeTeam;
    }

    public boolean isReopenTeam() {
        return reopenTeam;
    }

    public boolean isReportIncome() {
        return reportIncome;
    }

    public boolean isReportExpanse() {
        return reportExpanse;
    }


    public void setAppointTeamOwner(boolean appointTeamOwner) {
        this.appointTeamOwner = appointTeamOwner;
    }

    public void setAppointTeamManager(boolean appointTeamManager) {
        this.appointTeamManager = appointTeamManager;
    }

    public void setRemoveAppointTeamOwner(boolean removeAppointTeamOwner) {
        this.removeAppointTeamOwner = removeAppointTeamOwner;
    }

    public void setRemoveAppointTeamManager(boolean removeAppointTeamManager) {
        this.removeAppointTeamManager = removeAppointTeamManager;
    }

    public void setCloseTeam(boolean closeTeam) {
        this.closeTeam = closeTeam;
    }

    public void setReopenTeam(boolean reopenTeam) {
        this.reopenTeam = reopenTeam;
    }

    public void setReportIncome(boolean reportIncome) {
        this.reportIncome = reportIncome;
    }

    public void setReportExpanse(boolean reportExpanse) {
        this.reportExpanse = reportExpanse;
    }

    public void setGetBalance(boolean getBalance) {
        this.getBalance = getBalance;
    }

    public void addTeam(Team team)
    {
        if(!teamsToManage.contains(team))
            teamsToManage.add(team);
    }
    public LinkedList<Team> getTeamsToManage(){return teamsToManage;}

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


<<<<<<< Updated upstream:src/Domain/Authorization/TeamOwnerAuthorization.java

    public boolean addAssetToTeam(Asset asset, Team team){
        if(addAssetToTeam && teamsToManage.contains(team)){
            if(Database.getAsset(asset.getID())!=null)
                asset = Database.getAsset(asset.getID());
            else
                Database.addAsset(asset);// make sure real database support it - can only add assets to system

            if(asset instanceof TeamManager){
                return team.addTeamManager(((TeamManager) asset).getUser());
            }
            if(asset instanceof Player){

                return team.addPlayer((Player) asset);
            }
            else if(asset instanceof Coach){
               return team.addCoach((Coach) asset);
            }
            else if(asset instanceof Field){
                team.getFields().add((Field) asset);
                return Database.addAsset(asset);
            }
            return false;
        }
        else
            return false;
    }

    public boolean removeAssetFromTeam(Asset asset , Team team){
        if(removeAssetFromTeam && teamsToManage.contains(team)){
            if(asset instanceof TeamManager){
                return team.removeTeamManager((((TeamManager) asset).getUser()));
            }
            if(asset instanceof Player){
                return team.removePlayer((Player) asset);
            }
            if(asset instanceof Coach){
                return team.removeCoach((Coach) asset);
            }

            if(asset instanceof Field){
                team.getFields().remove(asset);
                if(((Field)asset).getTeams().size()<1)
                    Database.removeAsset(asset.getID());
                return true;
            }

            return false;
        }
        return false;
    }

    /**
     * decide what actions we allow
     * @param assetId
     * @param action
     * @param update
     */
    public boolean updateAsset(String assetId, String action, String update){
        if(updateAsset){
            Asset asset = Database.getAsset(assetId);
            switch(action){
                case("Price"):{
                    asset.setPrice(Double.valueOf(update));
                    return true;
                }
                case("Name"):{
                    return true;
                }
            }
        }
        return false;
    }

=======
>>>>>>> Stashed changes:src/Domain/TeamOwner.java
    public boolean appointTeamOwner(User user, Team team){
        if(appointTeamOwner && teamsToManage.contains(team)){
            if(!team.getTeamOwners().contains(user)){
                if(!user.getRoles().contains(this)){
                    TeamOwner teamOwner = new TeamOwner();
                    teamOwner.giveAll(true);
                    teamOwner.addTeam(team);
                    user.addAuthorization(teamOwner);
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
        if(appointTeamManager && teamsToManage.contains(team)){
            if(!team.getTeamManagers().contains(user)){
                if(!user.getRoles().contains(this)){
                    TeamManager teamManagerAutho = new TeamManager(user.getID(), 0);
                    user.addAuthorization(teamManagerAutho);
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
        if(removeAppointTeamOwner && teamsToManage.contains(team) && appointedTeamOwners.containsKey(user)){
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
        if(removeAppointTeamManager && teamsToManage.contains(team)&& appointedTeamManagers.containsKey(user)){
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
        if(autho.getTeamsToManage().size()==1)
            user.getRoles().remove(this);
        else{
            autho.removeTeam(team);
        }
        if(autho.isAppointTeamOwner() || autho.isAppointTeamManager()){
            for(User authorized : autho.getAppointedTeamOwners().keySet()){
                autho.removeAppointTeamOwner(authorized,team);
            }
            for(User authorized : autho.getAppointedTeamManagers().keySet()){
                autho.removeAppointTeamManager(authorized, team);
            }
        }
    }

    public boolean closeTeam(Team team){
        if(closeTeam && teamsToManage.contains(team)&& !closedTeams.contains(team)){
            team.setActive(false);
            teamsToManage.remove(team);
            closedTeams.add(team);
            return true;
        }
        return false;
    }
    public boolean reopenTeam(Team team){
        if(reopenTeam && closedTeams.contains(team) &&!team.isActive() &&!team.isPermanentlyClosed()){
            team.setActive(true);
            closedTeams.remove(team);
            teamsToManage.add(team);
            return true;
        }
        return false;
    }
    public boolean reportIncome(Team team, double income){
        if(reportIncome && teamsToManage.contains(team)){
            return team.getBudget().addIncome(income);
        }
        return false;
    }
    public boolean reportExpanse(Team team, double expanse){
        if(reportExpanse && teamsToManage.contains(team)){
            return team.getBudget().addExpanse(expanse);
        }
        return false;
    }

    public double getBalance(Team team){
        if(getBalance && teamsToManage.contains(team))
            return team.getBudget().getBalance();
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TeamOwner;
    }


}
