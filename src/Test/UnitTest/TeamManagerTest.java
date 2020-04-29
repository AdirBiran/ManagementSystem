package UnitTest;

import Domain.TeamManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamManagerTest {

    @Test
    public void getID() {
        TeamManager teamManager=new TeamManager("123456789",20000,false,false);
        assertEquals(teamManager.getID(),"123456789");
    }

    @Test
    public void deactivate() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void setPrice() {
    }

    @Test
    public void getTeams() {
    }

    @Test
    public void isPermissionManageAssets() {
    }

    @Test
    public void isPermissionFinance() {
    }

    @Test
    public void addTeam() {
    }

    @Test
    public void removeTeam() {
    }

    @Test
    public void isActive() {
    }

    @Test
    public void reactivate() {
    }

    @Test
    public void myRole() {
    }
}