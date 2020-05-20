package Domain;

import Data.Database;
import Logger.Logger;

import java.util.LinkedList;
import java.util.List;

public abstract class Manager extends Role{

    protected List<Team> teamsToManage;

    public Manager(){
        teamsToManage = new LinkedList<>();
    }

    public boolean addPlayerToTeam(String playerId, String teamId){
        User player = ((Player)Database.getAssetById(playerId)).getUser();
        Team team = Database.getTeam(teamId);
        if(player!=null && team!=null) {
            Role assetRole = player.checkUserRole("Player");
            if (teamsToManage.contains(team)) {
                if (team.getBudget().addExpanse(team, ((PartOfATeam) assetRole).getPrice())) {
                     return team.addPlayer(player);
                }
            }
        }
        return false;
    }

    public boolean addCoachToTeam(String coachId, String teamId){
        User coach = ((Coach)Database.getAssetById(coachId)).getUser();
        Team team = Database.getTeam(teamId);
        if(coach!=null && team!=null) {
            Role assetRole = coach.checkUserRole("Coach");
            if (teamsToManage.contains(team)) {//
                if (team.getBudget().addExpanse(team, ((PartOfATeam) assetRole).getPrice())) {
                    return team.addCoach(coach);
                }
            }
        }
        return false;
    }

    public boolean addTeamManagerToTeam(String teamManagerId, String teamId, double price, boolean manageAssets , boolean finance) {
        User teamManager = ((TeamManager)Database.getAssetById(teamManagerId)).getUser();
        Team team = Database.getTeam(teamId);
        if(teamManager!=null && team!=null) {
            Role assetRole = teamManager.checkUserRole("TeamManager");
            if (teamsToManage.contains(team)) {//
                if (team.getBudget().addExpanse(team, ((PartOfATeam) assetRole).getPrice())) {
                    return team.addTeamManager(teamManager, price, manageAssets, finance);
                }
            }
        }
        return false;
    }

    public boolean addFieldToTeam(String fieldId, String teamId){
        Field field = ((Field)Database.getAssetById(fieldId));
        Team team = Database.getTeam(teamId);
        if(field!=null && team!=null) {
            if (teamsToManage.contains(team)) {
                Database.addAsset(field);
                team.addField(field);
                return true;
            }
        }
        return false;
    }
    public boolean removeFieldFromTeam(String fieldId, String teamId){
        Field field = ((Field)Database.getAssetById(fieldId));
        Team team = Database.getTeam(teamId);
        if(field!=null && team!=null) {
            if (teamsToManage.contains(team) && team.getFields().contains(field)) {
                team.removeField(field);
                return true;
            }
        }
        return false;
    }

    public boolean removePlayerFormTeam(String playerId , String teamId){
        User player = ((Player)Database.getAssetById(playerId)).getUser();
        Team team = Database.getTeam(teamId);
        if(player!=null && team!=null) {
            if (teamsToManage.contains(team))
                return team.removePlayer(player);
        }
        return false;
    }
    public boolean removeCoachFormTeam(String coachId, String teamId){
        User coach = ((Coach)Database.getAssetById(coachId)).getUser();
        Team team = Database.getTeam(teamId);
        if(coach!=null && team!=null) {
            if (teamsToManage.contains(team))
                return team.removeCoach(coach);
        }
        return false;
    }
    public boolean removeTeamManagerFormTeam(String teamManagerId, String teamId) {
        User teamManager = ((TeamManager)Database.getAssetById(teamManagerId)).getUser();
        Team team = Database.getTeam(teamId);
        if(teamManager!=null && team!=null) {
            if (teamsToManage.contains(team))
                return team.removeTeamManager(teamManager);
        }
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

    public boolean reportIncome(String teamId, double income){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            if (teamsToManage.contains(team)) {
                return team.getBudget().addIncome(income);
            }
        }
        return false;
    }
    public boolean reportExpanse(String teamId, double expanse){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            if (teamsToManage.contains(team)) {
                return team.getBudget().addExpanse(team, expanse);
            }
        }
        return false;
    }

    public double getBalance(String teamId){
        Team team = Database.getTeam(teamId);
        if(team!=null) {
            if (teamsToManage.contains(team))
                return team.getBudget().getBalance();
        }
        return -1;
    }
    public List<String> getStringTeams(){
        List<String> teams = new LinkedList<>();
        for(Team team : teamsToManage)
            teams.add(team.toString());
        return teams;
    }

    public List<Team> getTeamsToManage(){return teamsToManage;}

    public List<String> getAllPlayers(){
        List<String> players = new LinkedList<>();
        /*for(Player player : Database.getAllPlayers())
            players.add(player.toString());*/
        return players;
    }

    public List<String> getAllCoaches() {
        List<String> coaches = new LinkedList<>();
        /*for(Player player : Database.getAllCoaches())
            coaches.add(player.toString());*/
        return coaches;
    }

    public List<String> getAllFields() {
        List<String> fields = new LinkedList<>();
        /*for(Player player : Database.getAllFields())
            fields.add(player.toString());*/
        return fields;
    }

    public List<String> getAllTeamAssets(String teamId) {
        List<String> teamAssets = new LinkedList<>();
        Team team = Database.getTeam(teamId);
        if(team!=null){
            for(User teamManager: team.getTeamManagers())
                teamAssets.add(teamManager.toString());
            for(User coach : team.getCoaches())
                teamAssets.add(coach.toString());
            for (User player : team.getPlayers())
                teamAssets.add(player.toString());
            for(Field field : team.getFields())
                teamAssets.add(field.toString());
        }
        return teamAssets;
    }
}
