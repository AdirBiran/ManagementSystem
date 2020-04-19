package Acceptance;

import Data.Database;
import Domain.*;
import Service.AdminSystem;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CloseTeamPermanentlyTest {

    private FootballManagementSystem system;
    private AdminSystem adminSystem;
    private Database database;

    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        adminSystem = system.getAdminSystem();
        database = system.getDatabase();


    }

    @Test
    public void closeFail_41()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        database.addTeam(team);
        adminSystem.permanentlyCloseTeam(database.GetSystemAdmins().get(0), team);

        boolean flag = adminSystem.permanentlyCloseTeam(database.GetSystemAdmins().get(0),team);
        assertFalse(flag);
    }

    @Test
    public void closeSuccess_42()
    {
        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team2","Owner2", "a2"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "tel-aviv", 550, 150000);
        Team team = new Team("team2",page,owners,players,coaches, field);
        database.addTeam(team);
        boolean flag = adminSystem.permanentlyCloseTeam(database.GetSystemAdmins().get(0),team);

        assertTrue(flag);
    }
}
