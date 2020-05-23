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
        UserSystem userSystem;
        TeamOwner teamOwner;
        FootballManagementSystem system;
        Admin admin;
        Team team;
        User userTeamOwner;
       User mesiU;
       Player mesi;
       User manager;

    @Before
        public void init(){
        teamManagementSystem=new TeamManagementSystem();
        userSystem=new UserSystem();
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
        manager=admin.addNewTeamManager("Maor","Buzaglo","maor@gmail.com",3000,true,true);
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

    }

    @Test
    public void manageAssets_28()
    {
        teamOwner.addTeam(team);
        teamManagementSystem.addAssetPlayer(userTeamOwner.getID(),mesiU.getID(),team.getID());
        teamManagementSystem.updateAsset(userTeamOwner.getID(),mesiU.getID(),"Price","30");

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
        assertEquals(team.getTeamManagers().size(),0);
        assertTrue(teamManagementSystem.addAssetTeamManager(userTeamOwner.getID(),manager.getID(),team.getID(),200,false,false));
        assertEquals(team.getTeamManagers().size(),1);
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
    assertTrue(teamManagementSystem.closeTeam(userTeamOwner.getID(),team.getID()));

    }

    @Test
    public void closeTeam_35() {
        assertEquals(teamManagementSystem.getTeams(userTeamOwner.getID()).size(), 1);
        teamManagementSystem.closeTeam(userTeamOwner.getID(), team.getID());
        assertEquals(teamManagementSystem.getTeams(userTeamOwner.getID()).size(), 0);
        assertEquals(userSystem.search(userTeamOwner.getID(),"team0").size(),0);

    }




    @Test
    public void openTeam_37()
    {
    assertFalse(teamManagementSystem.reOpeningTeam(userTeamOwner.getID(), team.getID()));
    }

    @Test
    public void openTeam_38()
    {
        teamManagementSystem.closeTeam(userTeamOwner.getID(), team.getID());
        assertTrue(teamManagementSystem.reOpeningTeam(userTeamOwner.getID(), team.getID()));

    }

    @Test
    public void removeAsset_46()
    {
        teamManagementSystem.addAssetTeamManager(userTeamOwner.getID(),manager.getID(),team.getID(),200,false,false);
        assertTrue(teamManagementSystem.removeAssetTeamManager(userTeamOwner.getID(),manager.getID(),team.getID()));
    }

    @Test
    public void removeAsset_47()
    {
        User userTeamOwner2= admin.addNewTeamOwner("team2", "owner2", "teamOwner2@gmail.com");
        teamManagementSystem.appointmentTeamOwner(userTeamOwner.getID(),userTeamOwner2.getID(),team.getID());
        teamManagementSystem.addAssetTeamManager(userTeamOwner.getID(),manager.getID(),team.getID(),200,false,false);
        assertFalse(teamManagementSystem.removeAssetTeamManager(userTeamOwner2.getID(),manager.getID(),team.getID()));

    }


}
