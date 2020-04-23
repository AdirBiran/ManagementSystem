package Acceptance;

import Domain.*;
import Domain.Authorization.HasPageAuthorization;
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

        List<Player> players = FootballManagementSystem.createPlayers();
        List<Coach> coaches = FootballManagementSystem.createCoaches();
        owner = UserFactory.getNewTeamOwner("Team","Owner", "a"+"@gmail.com");
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", players.get(0).getUser());
        Field field = new Field( "jerusalem", 550, 15000);
        team = new Team("Team",page,owners,players,coaches, field);
    }

    @Test
    public void followPageSuccess_9()
    {
        Object[] fan = UserFactory.getNewFan("Aa123456","AAA", "BBB","a@b.com" , "0123456789", "Israel");

        Player player = system.getTeamManagementSystem().getTeamPlayers(owner,team).get(0);
        PersonalPage page = ((HasPageAuthorization)player.getUser().getRoles().get(0)).getPage();

        userSystem.registrationToFollowUp((User)fan[0], page);
        List<PersonalPage> pages = userSystem.getFanPages((User) fan[0]);

        boolean found = false;

        for (PersonalPage p : pages)
            if (p == page)
                found = true;

        assertTrue(found);

    }

    @Test
    public void followPageFail_10()
    {
        Object[] fan = UserFactory.getNewFan("Aa234567", "AAA", "BBB","a@b.com","0123456789", "Israel");

        Player player = system.getTeamManagementSystem().getTeamPlayers(owner,team).get(0);
        PersonalPage page =((HasPageAuthorization)player.getUser().getRoles().get(0)).getPage();

        userSystem.registrationToFollowUp((User)fan[0], page);

        userSystem.registrationToFollowUp((User)fan[0], page);


        int counter = 0;

        List<PersonalPage> pages = userSystem.getFanPages((User)fan[0]);

        for (PersonalPage p : pages)
            if (p == page)
                counter++;

        assertEquals(1, counter);

    }
}
