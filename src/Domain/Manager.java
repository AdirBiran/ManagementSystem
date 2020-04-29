package Domain;

import Data.Database;
import java.util.LinkedList;
import java.util.List;

public abstract class Manager implements Role {

    protected List<Team> teamsToManage;

    public Manager(){
        teamsToManage = new LinkedList<>();
    }

    public boolean addPlayerToTeam(User player, Team team){
        Role assetRole = player.checkUserRole("Player");
        if(teamsToManage.contains(team)) {//
            if (team.getBudget().addExpanse(((PartOfATeam) assetRole).getPrice())) {
                return team.addPlayer(player);
            }
        }
        return false;
    }

    public boolean addCoachToTeam(User coach, Team team){
        Role assetRole = coach.checkUserRole("Coach");
        if(teamsToManage.contains(team)) {//
            if (team.getBudget().addExpanse(((PartOfATeam) assetRole).getPrice())) {
                return team.addCoach(coach);
            }
        }
        return false;
    }

    public boolean addTeamManagerToTeam(User teamManager, Team team, double price, boolean manageAssets , boolean finance) {
        Role assetRole = teamManager.checkUserRole("TeamManager");
        if(teamsToManage.contains(team)) {//
            if (team.getBudget().addExpanse(((PartOfATeam) assetRole).getPrice())) {
                return team.addTeamManager(teamManager, price, manageAssets, finance);
            }
        }
        return false;
    }

    public boolean addFieldToTeam(Field field, Team team){
        if(teamsToManage.contains(team)){
            Database.addAsset(field);
            team.addField(field);
            return true;
        }
        return false;
    }
    public boolean removeFieldFromTeam(Field field, Team team){
        if(teamsToManage.contains(team) && team.getFields().contains(field)){
            team.removeField(field);
            return true;
        }
        return false;
    }

    public boolean removePlayerFormTeam(User player , Team team){
        if(teamsToManage.contains(team))
            return team.removePlayer(player);
        return false;
    }
    public boolean removeCoachFormTeam(User coach , Team team){
        if(teamsToManage.contains(team))
            return team.removeCoach(coach);
        return false;
    }
    public boolean removeTeamManagerFormTeam(User teamManager , Team team){
        if(teamsToManage.contains(team))
            return team.removeTeamManager(teamManager);
        return false;
    }

    /**
     * decide what actions we allow
     */
    public boolean updateAsset(String assetId, String action, String update){

        PartOfATeam asset = Database.getAsset(assetId);
        switch(action){
            case("Price"):{
                asset.setPrice(Double.valueOf(update));
                return true;
            }
        }
        return false;
    }

    public boolean reportIncome(Team team, double income){
        if(teamsToManage.contains(team)){
            return team.getBudget().addIncome(income);
        }
        return false;
    }
    public boolean reportExpanse(Team team, double expanse){
        if(teamsToManage.contains(team)){
            return team.getBudget().addExpanse(expanse);
        }
        return false;
    }

    public double getBalance(Team team){
        if(teamsToManage.contains(team))
            return team.getBudget().getBalance();
        return -1;
    }
}
