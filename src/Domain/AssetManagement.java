package Domain;

import Data.Database;

public class AssetManagement {
    private Database database;

    public AssetManagement(Database database) {
        this.database = database;
    }

    /*
    When a team is closed / permanently closed or reopened, alerts are sent accordingly
     */
    public boolean alertBudgetException(String option, Team team, boolean permanently) {
        if((!team.isActive() && option.equals("closed")) || (team.isActive() && option.equals("open"))) {
            for (User owner : team.getTeamOwners()) {
                if (owner instanceof TeamOwner && !((TeamOwner)owner).isClosedTeam(team))
                    owner.addMessage(new Notice(true, "team " + option + " : " + team.getName()));
            }
            for (User teamManager : team.getTeamManagers())
                teamManager.addMessage(new Notice(true, "team " + option + " : " + team.getName()));

            if (!permanently) {
                for (User admin : database.getListOfAllSpecificUsers("Admin"))
                    admin.addMessage(new Notice(true, "team " + option + " : " + team.getName()));
            }
            return true;
        }
        return false;
    }
    /*
    Send notifications to union representatives when a team exceeds the budget
     */
    public void alertBudgetException(String msg){
        for(User unionRep : database.getListOfAllSpecificUsers("UnionRepresentative"))
            unionRep.addMessage(new Notice(true, msg));
    }

    /*
     *this function adds a new asset to the system
     * */
    public void addAsset(Asset asset , Team team){
        if(asset instanceof User){
            if(database.getAsset(asset.getID())!=null)
                asset = database.getAsset(asset.getID());
            else
                database.addUser("Aa123",(User)asset);

            if(asset instanceof TeamManager){
                team.addTeamManager((TeamManager) asset);
            }
            if(asset instanceof Player){
                team.addPlayer((Player) asset);
            }
            if(asset instanceof Coach){
                team.addCoach((Coach) asset);
            }

        }

        /**
         * Need to decide what happen if the team has a Field
         * */
        else if(asset instanceof Field){
            team.getFields().add((Field) asset);
            database.addAsset(asset);
        }
    }
    /*
       Remove asset
        */
    public boolean removeAsset(Asset asset , Team team) {
        if(asset instanceof User){
            if(asset instanceof TeamManager){
                if(!team.removeTeamManager((TeamManager) asset))
                    return false;
            }
            if(asset instanceof Player){
                if(!team.removePlayer((Player) asset))
                    return false;
            }
            if(asset instanceof Coach){
                if(!team.removeCoach((Coach) asset))
                    return false;
            }
        }

        /**
         * Need to decide what happen if the team has a Field
         * */
        if(asset instanceof Field){
            team.getFields().remove(asset);
        }
        database.removeAsset(asset.getID());
        return true;
    }

    /*
    this function update a asset in the system
    */
    public void updateAsset(String assetId, String action, String update) {
        Asset asset = database.getAsset(assetId);
        if(action.equals("Price")){
            asset.setPrice(Double.valueOf(update));
        }
    }

    public double getAssetPrice(Asset asset)
    {
        return asset.getPrice();
    }
}
