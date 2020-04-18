package Tests.Unit;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamOwnerTest {

    FootballManagementSystem system;
    TeamOwner teamOwner1;
    TeamOwner teamOwner2;
    TeamOwner teamOwner3;
    Player player;
    TeamManager teamManager;
    Team team;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        teamManager = new TeamManager("Doron" , "Shamai" , "Mail" , team ,100);
        teamOwner1 = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(0);
        teamOwner2 = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(1);
        teamOwner3 = (TeamOwner) system.getDatabase().getListOfAllSpecificUsers("TeamOwner").get(2);
        teamOwner1.appointmentTeamOwner(teamOwner3,teamOwner1.getTeam().get(0));

        teamOwner3.appointmentTeamManager(teamManager,teamOwner3.getTeam().get(0));
        player = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
   //     teamManager = (TeamManager) system.getDatabase().getListOfAllSpecificUsers("TeamManager").get(0);
        team = system.getDatabase().getTeams().get(0);
    }

    @Test
    public void appointmentTeamOwner() {
        assertTrue(teamOwner1.appointmentTeamOwner(teamOwner2,teamOwner1.getTeam().get(0)));
        assertFalse(teamOwner1.appointmentTeamOwner(teamOwner2,teamOwner1.getTeam().get(0)));
        assertTrue(teamOwner2.appointmentTeamOwner(player,teamOwner2.getTeam().get(0)));
    }

    @Test
    public void appointmentTeamManager() {
        assertTrue(teamOwner1.appointmentTeamManager(teamManager,teamOwner1.getTeam().get(0)));
        assertFalse(teamOwner1.appointmentTeamManager(teamManager,teamOwner1.getTeam().get(0)));

    }

    @Test
    public void removeAppointmentTeamOwner() {
        assertFalse(teamOwner2.removeAppointmentTeamOwner(teamOwner1,teamOwner2.getTeam().get(0)));
        assertFalse(teamOwner1.removeAppointmentTeamOwner(teamOwner2,teamOwner1.getTeam().get(0)));
        assertTrue(teamOwner1.removeAppointmentTeamOwner(teamOwner3,teamOwner1.getTeam().get(0)));

    }

    @Test
    public void removeAppointmentTeamManager() {
        assertFalse(teamOwner2.removeAppointmentTeamManager(teamManager,teamOwner2.getTeam().get(0)));
        assertTrue(teamOwner3.removeAppointmentTeamManager(teamManager,teamOwner3.getTeam().get(0)));

    }

    @Test
    public void closeTeam() {
        assertTrue(teamOwner1.closeTeam(teamOwner1.getTeam().get(0)));
        assertFalse(teamOwner1.closeTeam(teamOwner2.getTeam().get(0)));
    }

    @Test
    public void reopeningTeam() {
        teamOwner1.closeTeam(teamOwner1.getTeam().get(0));
        assertTrue(teamOwner1.reopeningTeam(teamOwner1.getTeam().get(0)));

        assertFalse(teamOwner1.reopeningTeam(teamOwner1.getTeam().get(0)));

    }

    /*@Test
    public void reportFinanceTrans() {
    }*/

    @Test
    public void isClosedTeam() {
        assertTrue();
    }
}