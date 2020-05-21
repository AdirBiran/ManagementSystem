package AcceptanceTest;

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

        TeamManagementSystem teamManagementSystem;
        TeamOwner teamOwner;
        FootballManagementSystem system;
        Admin admin;
        Team team;
        User userTeamOwner;
       User mesiU;
       Player mesi;

    @Before
        public void init(){
        teamManagementSystem=new TeamManagementSystem();
        system = new FootballManagementSystem();
        system.systemInit(true);
        String  leagueId = system.dataReboot();
        LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        team = league.getTeams().get(0);
        admin = (Admin) system.getAdmin().checkUserRole("Admin");
        userTeamOwner= admin.addNewTeamOwner("team", "owner", "teamOwner@gmail.com");
        teamOwner = (TeamOwner) userTeamOwner.checkUserRole("TeamOwner");
        userTeamOwner= team.getTeamOwners().get(0);
        mesiU = admin.addNewPlayer("mesi", "mesi", "mesi@mail.com", new Date(30 / 5 / 93), Player.RolePlayer.goalkeeper, 200000);
        mesi=(Player) mesiU.checkUserRole("Player");
        }
    /*    system = new FootballManagementSystem();
        system.systemInit(true);
        representativeSystem = system.getUnionRepresentativeSystem();
        UnionRep = UserFactory.getNewUnionRepresentative("","","234@g.com");
        representativeSystem.configureNewSeason(UnionRep,2020, new Date(120,4,1 ));
        representativeSystem.configureNewLeague(UnionRep,"Haal", "3");
        leagueInSeason = representativeSystem.configureLeagueInSeason(UnionRep,"Haal", "2020", new PlayTwiceWithEachTeamPolicy(), new StandardScorePolicy(), 300);
        players = FootballManagementSystem.createPlayers();
        coaches = FootballManagementSystem.createCoaches();
        owner = UserFactory.getNewTeamOwner("Team","Owner", "a"+"@gmail.com");*/



    @Test
    public void manageAssets_26()
    {
        teamManagementSystem.addAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID());
        assertFalse(teamManagementSystem.addAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID()));
    }

    @Test
    public void manageAssets_27()
    {
        teamManagementSystem.addAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID());
       assertTrue(teamManagementSystem.removeAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID()));

       /* List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 150000);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player playerAdded = teamSystem.getTeamPlayers(owner,team).get(0);
        List<Player> assets = teamSystem.getTeamPlayers(owner,team);

        teamSystem.removeAsset(owner,playerAdded, team);

        boolean flag = false;

        for (Player p: assets)
            if (p == playerAdded)
                flag = true;

        assertFalse(flag);*/
    }

    @Test
    public void manageAssets_28()
    {
        teamOwner.addTeam(team);
        teamManagementSystem.addAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID());
        teamManagementSystem.updateAsset(userTeamOwner.getID(),mesiU.getID(),"Price","30");
       /* List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        UserSystem userSystem = system.getUserSystem();
        Player player = players.get(0);

        userSystem.updateRole(player, "Goalkeeper");
        String role = userSystem.getRole(player);

        assertEquals(role, "Goalkeeper");*/
    }

    @Test
    public void manageAssets_31()
    {
        assertTrue(teamManagementSystem.addAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID()));
        assertEquals(mesi.getTeams().size(),1);
    }

    @Test
    public void appointManager_32()
    {


        /*List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 1500);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep,leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        Player manager = teamSystem.getTeamPlayers(owner,team).get(1);
        team.setActive(true);
        List<User> managers = teamSystem.getTeamManagers(owner, team);
        assertEquals(0, managers.size());

        teamSystem.appointmentTeamManager(owner, manager.getUser(), team);

        managers = teamSystem.getTeamManagers(owner,team);
        assertEquals(1, managers.size());*/

    }

    @Test
    public void appointManager_33()//is it not suppose to work? why not?
    {
       /* List<User> owners = new LinkedList<>();
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
        assertEquals(1, managers.size());*/

    }

    @Test
    public void closeTeam_34()
    {


     /*   List<User> owners = new LinkedList<>();
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
        assertFalse(active);*/

    }

    @Test
    public void closeTeam_35()
    {


       /* List<User> owners = new LinkedList<>();
        owners.add(owner);
        PersonalPage page = new PersonalPage("", owner);
        Field field = new Field( "jerusalem", 550, 15000);
        Team team = new Team("team",page,owners,players,coaches, field);
        Database.addTeam(team);
        representativeSystem.addTeamToLeague(UnionRep, leagueInSeason, team);

        TeamManagementSystem teamSystem = system.getTeamManagementSystem();
        teamSystem.closeTeam(owner, team);

        boolean flag = teamSystem.closeTeam(owner, team);
        assertFalse(flag);*/

    }

    @Test
    public void openTeam_37()
    {



     /*   List<User> owners = new LinkedList<>();
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
        assertTrue(active);*/
    }

    @Test
    public void openTeam_38()
    {

      /*  List<User> owners = new LinkedList<>();
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
        assertTrue(active);*/
    }

    @Test
    public void removeAsset_46()
    {
      /*  List<User> owners = new LinkedList<>();
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
        assertEquals(1, teamSystem.getTeamFields(owner,team).size());*/


    }

    @Test
    public void removeAsset_47()
    {
       /* List<User> owners = new LinkedList<>();
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
        assertEquals(1, teamSystem.getTeamFields(owner,team).size());*/

    }


}
