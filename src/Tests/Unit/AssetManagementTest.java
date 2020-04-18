package Tests.Unit;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AssetManagementTest {

    FootballManagementSystem system;
    AssetManagement assetManagement;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        assetManagement = system.getAssetManagement();
    }

    @Test
    public void alertBudgetException() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Team team = (Team) system.getDatabase().searchObject("team0").get(0);
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
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        UnionRepresentative union = new UnionRepresentative("union", "rep", "unionrep@gmail.com");
        system.getDatabase().addUser("AAA123", union);
        int sizeOfMessageBox = union.getMessageBox().size();
        assetManagement.alertBudgetException("test message");
        assertEquals(sizeOfMessageBox+1, union.getMessageBox().size(), 0);
    }

    @Test
    public void addAsset(){
        int sizeOfAssets = system.getDatabase().getAssetsInDatabase().size();
        Team team = system.getDatabase().getTeams().get(0);
        Player player = new Player("player100", "", "p100@gmail,com", null, new Date(1990,10,10), "goalkeeper",10000);
        assetManagement.addAsset(player, team);
        assertEquals(sizeOfAssets+1, system.getDatabase().getAssetsInDatabase().size(), 0);

    }

    @Test
    public void removeAsset(){
        Team team = system.getDatabase().getTeams().get(0);
        Player player = (Player) system.getDatabase().getAssetsInDatabase().get(system.getDatabase().getAssetsInDatabase().keySet().iterator().next());
        assetManagement.removeAsset(player, team);
        assertEquals(false, player.isActive());
    }

    @Test
    public void updateAsset(){
        Player player = (Player) system.getDatabase().getAssetsInDatabase().get(system.getDatabase().getAssetsInDatabase().keySet().iterator().next());
        assetManagement.updateAsset(player.getID(), "Price", "10000");
        assertEquals(10000, player.getPrice(), 0);
    }
}
