package Domain;

import Data.Database;

import java.util.LinkedList;

public abstract class Manager implements Role {

    private LinkedList<Team> teamsToManage;
    /**
     * //we assume the asset is a registered user
     * @param asset - registered user known to database!! this function only adds fields to database
     * @param team
     * @return
     */
    public boolean addAssetToTeam(User asset, Team team){
        if(teamsToManage.contains(team)){//
            for(Role role: asset.getRoles()){
                if(role instanceof TeamManager&&team.getBudget().addExpanse(((PartOfATeam)role).getPrice())){
                    return team.addTeamManager(asset);
                }
                else if(role instanceof Player &&team.getBudget().addExpanse(((PartOfATeam)role).getPrice())){

                    return team.addPlayer(asset);
                }
                else if(role instanceof Coach &&team.getBudget().addExpanse(((PartOfATeam)role).getPrice())){
                    return team.addCoach(asset);
                }
                return false;
            }

        }
        else
            return false;
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
            //Database.removeAsset(field.getID());
            team.removeField(field);
            return true;
        }
        return false;
    }

    public boolean removeAssetFromTeam(User asset , Team team){
        if( teamsToManage.contains(team)){
            for(Role role:asset.getRoles()){
                if(role instanceof TeamManager){
                    return team.removeTeamManager(asset);
                }
                if(role instanceof Player){
                    return team.removePlayer(asset);
                }
                if(role instanceof Coach){
                    return team.removeCoach(asset);
                }
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

        PartOfATeam asset = Database.getAsset(assetId);
        switch(action){
            case("Price"):{
                asset.setPrice(Double.valueOf(update));
                return true;
            }
            /*case("Name"):{
                return true;
            }*/
        }
        return false;
    }
}
