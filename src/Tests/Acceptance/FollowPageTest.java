package Acceptance;

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
    private User owner;


    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        userSystem = system.getUserSystem();

        system.dataReboot();

        List<User> players = FootballManagementSystem.createPlayers();
        List<User> coaches = FootballManagementSystem.createCoaches();
        owner = UserFactory.getNewTeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0));
        Field field = new Field( "jerusalem", 550, 15000);
        team = new Team("Team",page,owners,players,coaches, field);
    }

    @Test
    public void followPageSuccess_9()
    {
        User fan = UserFactory.getNewFan("Aa123456","AAA", "BBB","a@b.com" , "0123456789", "Israel");

        User player = system.getTeamManagementSystem().getTeamPlayers(owner,team).get(0);
        PersonalPage page = ((HasPage)player.getRoles().get(1)).getPage();

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
        User fan = UserFactory.getNewFan("Aa234567", "AAA", "BBB","a@b.com","0123456789", "Israel");

        User player = system.getTeamManagementSystem().getTeamPlayers(owner,team).get(0);
        PersonalPage page =((HasPage)player.getRoles().get(1)).getPage();

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
