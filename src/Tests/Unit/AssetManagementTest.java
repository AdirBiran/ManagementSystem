package Tests.Unit;
import Domain.Team;
import Service.FootballManagementSystem;
import Presentation.UnionRepresentative;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssetManagementTest {

    @Test
    public void alertBudgetException() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Team team = (Team) system.database.searchObject("team0").get(0);
        assertFalse(system.assetManagement.alertBudgetException("closed", team, false));
        assertTrue(system.assetManagement.alertBudgetException("open", team, false));
        team.setActive(false);
        assertTrue(system.assetManagement.alertBudgetException("closed", team, false));
        assertFalse(system.assetManagement.alertBudgetException("open", team, false));
        team.setPermanentlyClosed(true);
        assertTrue(system.assetManagement.alertBudgetException("closed", team, true));
    }

    @Test
    public void alertBudgetException1() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        UnionRepresentative union = new UnionRepresentative("union", "rep", "unionrep@gmail.com");
        system.database.addUser("AAA123", union);
        int sizeOfMessageBox = union.getMessageBox().size();
        system.assetManagement.alertBudgetException("test message");
        assertEquals(sizeOfMessageBox+1, union.getMessageBox().size(), 0);
    }
}
