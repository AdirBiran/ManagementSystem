package Tests.Unit;
import Domain.Team;
import Service.FootballManagementSystem;
import Domain.UnionRepresentative;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssetManagementTest {

    @Test
    public void alertBudgetException() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Team team = (Team) system.getDatabase().searchObject("team0").get(0);
        assertFalse(system.getAssetManagement().alertBudgetException("closed", team, false));
        assertTrue(system.getAssetManagement().alertBudgetException("open", team, false));
        team.setActive(false);
        assertTrue(system.getAssetManagement().alertBudgetException("closed", team, false));
        assertFalse(system.getAssetManagement().alertBudgetException("open", team, false));
        team.setPermanentlyClosed(true);
        assertTrue(system.getAssetManagement().alertBudgetException("closed", team, true));
    }

    @Test
    public void alertBudgetException1() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        UnionRepresentative union = new UnionRepresentative("union", "rep", "unionrep@gmail.com");
        system.getDatabase().addUser("AAA123", union);
        int sizeOfMessageBox = union.getMessageBox().size();
        system.getAssetManagement().alertBudgetException("test message");
        assertEquals(sizeOfMessageBox+1, union.getMessageBox().size(), 0);
    }

    @Test
    public void addAsset(){

    }

    @Test
    public void removeAsset(){

    }

    @Test
    public void updateAsset(){

    }
}
