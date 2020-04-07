package Domain;

import Data.Database;
import Presentation.TeamManager;
import Presentation.TeamOwner;
import Presentation.User;

public class AssetManagement {
    private Database database;

    public AssetManagement(Database database) {
        this.database = database;
    }

    public void sendNotification(String option, Team team, boolean permanently) {
        for (TeamOwner owner: team.getTeamOwners()) {
            if(!owner.isClosedTeam())
                owner.addMessage(new Notice(true,"team " + option + " : " + team.getName() ));
        }
        for (TeamManager teamManager : team.getTeamManagers())
            teamManager.addMessage(new Notice(true,"team " + option + " : " + team.getName() ));

        if(!permanently) {
            for (User admin : database.getListOfAllSpecificUsers("Admin"))
                admin.addMessage(new Notice(true,"team " + option + " : " + team.getName() ));
        }
    }
    public void sendNotification(String msg){
        for(User unionRep : database.getListOfAllSpecificUsers("UnionRepresentative"))
            unionRep.addMessage(new Notice(true, msg));
    }
}
