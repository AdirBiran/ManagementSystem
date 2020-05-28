package UnitTest;

import Data.Database;
import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuestTest {
    FootballManagementSystem system;
    User mesi;
    PersonalPage mesiPage;
    Game game;
    Guest guest;

    @Before
    public void init() {
        system = new FootballManagementSystem();
        system.systemInit(false);
        //String  leagueId = system.dataReboot();
        //LeagueInSeason league = Database.getLeagueInSeason(leagueId);
        //LeagueInSeason league =system.getDatabase().getAllLeaguesInSeasons().get(0);

        Admin admin = (Admin) system.getAdmin();
        guest = new Guest();

        //mesi = admin.addNewPlayer("mesi", "mesi", "mesi@mail.com", new Date(30 / 5 / 93), Player.RolePlayer.goalkeeper, 200000);
        //Role pageRole = mesi.checkUserRole("HasPage");
        //mesiPage = ((HasPage) pageRole).getPage();
        //User union = admin.addNewUnionRepresentative("Union", "Rep", "unionRep@gmail.com");
        //UnionRepresentative unionRole = ((UnionRepresentative)union.checkUserRole("UnionRepresentative"));
        //unionRole.assignGames(league.getId(), system.getDates());
        //game = league.getGames().get(0);
    }

    @Test
    public void search() {
        assertNotNull(guest.search("mesiPage"));
    }

    @Test
    public void register() {
        assertNotNull(guest.register("fan@gmail.com", "Aa1234", "fan", "fan", "0500001234", "yosef23"));

    }

    @Test
    public void login() {
        User user = guest.register("fan@gmail.com", "aa1234", "fan", "fan", "0500001234", "yosef23");
        assertNotNull(guest.login("fan@gmail.com","aa1234"));
    }

    @Test
    public void viewInfoAboutTeams() {
        assertNotNull(guest.viewInfoAboutTeams());
    }

    @Test
    public void viewInfoAboutPlayers() {
        assertNotNull(guest.viewInfoAboutPlayers());
    }

    @Test
    public void viewInfoAboutCoaches() {
        assertNotNull(guest.viewInfoAboutCoaches());
        System.out.println(guest.viewInfoAboutCoaches());
    }

    @Test
    public void viewInfoAboutLeagues() {
        assertNotNull(guest.viewInfoAboutLeagues());
    }

    @Test
    public void viewInfoAboutSeasons() {
        assertNotNull(guest.viewInfoAboutSeasons());
    }

    @Test
    public void viewInfoAboutReferees() {
        assertNotNull(guest.viewInfoAboutReferees());
        System.out.println(guest.viewInfoAboutReferees());
    }
}