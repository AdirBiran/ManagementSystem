package Tests.Acceptance;

import Domain.*;
import Service.FootballManagementSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FollowPageTest {

    private FootballManagementSystem system;
    private UserSystem userSystem;
    private Team team;


    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        userSystem = system.getUserSystem();

        system.dataReboot();

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        TeamOwner owner = new TeamOwner("Team","Owner", "a"+"@gmail.com");
        List<TeamOwner> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550);
        team = new Team("Team",page,owners,players,coaches, field);
    }

    @Test
    public void followPageSuccess_9()
    {
        Fan fan = new Fan("a@b.com", "AAA", "BBB", "0123456789", "Israel");

        Player player = system.getTeamManagementSystem().getTeamPlayers(team).get(0);
        PersonalPage page = new PersonalPage("Personal Page", player);

        userSystem.registrationToFollowUp(fan, page);
        List<PersonalPage> pages = userSystem.getFanPages(fan);

        boolean found = false;

        for (PersonalPage p : pages)
            if (p == page)
                found = true;

        assertTrue(found);

    }

    @Test
    public void followPageFail_10()
    {
        Fan fan = new Fan("a@b.com", "AAA", "BBB", "0123456789", "Israel");

        Player player = system.getTeamManagementSystem().getTeamPlayers(team).get(0);
        PersonalPage page = new PersonalPage("Personal Page", player);

        userSystem.registrationToFollowUp(fan, page);

        userSystem.registrationToFollowUp(fan, page);


        int counter = 0;

        List<PersonalPage> pages = userSystem.getFanPages(fan);

        for (PersonalPage p : pages)
            if (p == page)
                counter++;

        assertEquals(1, counter);

    }
}
