package Tests.Unit;

import Data.Database;
import Domain.AssetManagement;
import Domain.Team;
import Presentation.UnionRepresentative;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssetManagementTest {

    @Test
    public void alertBudgetException() {
        Database database = new Database();
        AssetManagement assetManagement = new AssetManagement(database);
        Team team = database.getTeams().values().iterator().next();
        assertFalse(assetManagement.alertBudgetException("closed", team, false));
        assertTrue(assetManagement.alertBudgetException("open", team, false));
        team.setActive(false);
        assertTrue(assetManagement.alertBudgetException("closed", team, false));
        assertFalse(assetManagement.alertBudgetException("open", team, false));
        team.setPermanentlyClosed(true);
        assertTrue(assetManagement.alertBudgetException("closed", team, true));
    }

    @Test
    public void alertBudgetException1() {
        Database database = new Database();
        AssetManagement assetManagement = new AssetManagement(database);
        UnionRepresentative union = (UnionRepresentative) database.getListOfAllSpecificUsers("UnionRepresentative").get(0);
        int sizeOfMessageBox = union.getMessageBox().size();
        assetManagement.alertBudgetException("test message");
        assertEquals(sizeOfMessageBox+1, union.getMessageBox().size(), 0);
    }
}
