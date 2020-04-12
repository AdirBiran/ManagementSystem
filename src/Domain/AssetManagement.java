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

    /*
    When a team is closed / permanently closed or reopened, alerts are sent accordingly
     */
    public boolean alertBudgetException(String option, Team team, boolean permanently) {
        if((!team.isActive() && option.equals("closed")) || (team.isActive() && option.equals("open"))) {
            for (TeamOwner owner : team.getTeamOwners()) {
                if (!owner.isClosedTeam(team))
                    owner.addMessage(new Notice(true, "team " + option + " : " + team.getName()));
            }
            for (TeamManager teamManager : team.getTeamManagers())
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
}
