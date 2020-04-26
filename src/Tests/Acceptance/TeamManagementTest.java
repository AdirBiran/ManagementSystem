package Acceptance;

import Data.Database;
import Domain.*;
import Domain.Coach;
import Domain.Player;
import Service.FootballManagementSystem;
import Service.TeamManagementSystem;
import Service.UnionRepresentativeSystem;
import Service.UserSystem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import static org.junit.Assert.*;

public class TeamManagementTest {

    private UnionRepresentativeSystem representativeSystem;
    private FootballManagementSystem system;
    private LeagueInSeason leagueInSeason;
    private List<User> players;
    private List<User> coaches;
    private User owner, UnionRep;


    @Before
    public void init()
    {
        system = new FootballManagementSystem();
        system.systemInit(true);
        representativeSystem = system.getUnionRepresentativeSystem();
        UnionRep = UserFactory.getNewUnionRepresentative("","","234@g.com");
        representativeSystem.configureNewSeason(UnionRep,2020, new Date(120,4,1 ));
        representativeSystem.configureNewLeague(UnionRep,"Haal", "3");
        leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        players = FootballManagementSystem.createPlayers();
        coaches = FootballManagementSystem.createCoaches();
        owner = UserFactory.getNewTeamOwner("Team","Owner", "a"+"@gmail.com");

    }

    @Test
    public void manageAssets_26()
    {

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);
        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        User playerAdded = teamSystem.getTeamPlayers(owner,team).get(0);

        teamSystem.addAsset(owner,playerAdded, team);


        boolean existsOnce = false;
        boolean existsTwice = false;

        List<User> assets = teamSystem.getTeamPlayers(owner,team);

        for (User p : assets)
            if (p == playerAdded)
                if (existsOnce)
                    existsTwice = true;
                else
                    existsOnce = true;

        assertFalse(existsTwice);


    }

    @Test
    public void manageAssets_27()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        User playerAdded = teamSystem.getTeamPlayers(owner,team).get(0);
        List<User> assets = teamSystem.getTeamPlayers(owner,team);

        teamSystem.removeAsset(owner,playerAdded, team);

        boolean flag = false;

        for (User p: assets)
            if (p == playerAdded)
                flag = true;

        assertFalse(flag);
    }

    @Test
    public void manageAssets_28()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        UserSystem userSystem = system.getUserSystem();
        User player = players.get(0);

        userSystem.updateRole(player, "Goalkeeper");
        String role = userSystem.getRole(player);

        assertEquals(role, "Goalkeeper");
    }

    @Test
    public void manageAssets_31()
    {

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("team",page,owners,players,coaches, field);

        Field fieldAdded = new Field( "tel-aviv", 700, 150000);

        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        teamSystem.addAsset(owner,fieldAdded, team);
        List<Field> assets = teamSystem.getTeamFields(owner,team);


        boolean flag = false;

        for (Field f : assets)
            if (f == fieldAdded)
                flag = true;

        assertTrue(flag);

    }

    @Test
    public void appointManager_32()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        User manager = teamSystem.getTeamPlayers(owner,team).get(1);
        team.setActive(true);
        List<User> managers = teamSystem.getTeamManagers(owner, team);
        assertEquals(0, managers.size());

        teamSystem.appointmentTeamManager(owner, manager.getUser(), team);

        managers = teamSystem.getTeamManagers(owner,team);
        assertEquals(1, managers.size());

    }

    @Test
    public void appointManager_33()//is it not suppose to work? why not?
    {
        List<User> owners = new LinkedList<>();
        User owner2 = UserFactory.getNewTeamOwner("Team2","Owner", "a2www"+"@gmail.com");

        owners.add(owner);
        owners.add(owner2);

        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        teamSystem.appointmentTeamManager(owner, owner2, team);

        List<User> managers = teamSystem.getTeamManagers(owner,team);
        assertEquals(1, managers.size());

    }

    @Test
    public void closeTeam_34()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        boolean active = teamSystem.isActiveTeam(team);

        assertTrue(active);

        teamSystem.closeTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertFalse(active);

    }

    @Test
    public void closeTeam_35()
    {


        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        boolean flag = teamSystem.closeTeam(owner, team);
        assertFalse(flag);

    }

    @Test
    public void openTeam_37()
    {



        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();

        boolean active = teamSystem.isActiveTeam(team);
        assertTrue(active);

        teamSystem.reopeningTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertTrue(active);
    }

    @Test
    public void openTeam_38()
    {

        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        boolean active = teamSystem.isActiveTeam(team);
        assertFalse(active);

        teamSystem.reopeningTeam(owner, team);

        active = teamSystem.isActiveTeam(team);
        assertTrue(active);
    }

    @Test
    public void removeAsset_46()
    {
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Field field2 = new Field("tel-aviv", 300, 1000);
        team.setActive(true);

        teamSystem.addAsset(owner,field2, team);
        assertEquals(2, teamSystem.getTeamFields(owner,team).size());

        teamSystem.removeAsset(owner,field2, team);
        assertEquals(1, teamSystem.getTeamFields(owner,team).size());


    }

    @Test
    public void removeAsset_47()
    {
        List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Field field2 = new Field("tel-aviv", 300, 1000);
        team.setActive(true);

        assertEquals(1, teamSystem.getTeamFields(owner,team).size());
        teamSystem.removeAsset(owner,field2, team);
        assertEquals(1, teamSystem.getTeamFields(owner,team).size());

    }


}
