package Tests.Unit;

import Domain.Team;
import Domain.TeamOwner;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    FootballManagementSystem system;
    Team team;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        team = system.getDatabase().getTeams().get(0);
    }

    @Test
    public void addTeamOwner() {
        TeamOwner teamOwner = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        assertTrue(team.addTeamOwner(teamOwner));

    }

    @Test
    public void addTeamManager() {
    }

    @Test
    public void addPlayer() {
    }

    @Test
    public void addCoach() {
    }

    @Test
    public void removeTeamOwner() {
    }

    @Test
    public void removeTeamManager() {
    }

    @Test
    public void removePlayer() {
    }

    @Test
    public void removeCoach() {
    }

    @Test
    public void isActive() {
    }

    @Test
    public void isPermanentlyClosed() {
    }
}