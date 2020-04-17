package Tests.Unit;

import Domain.Fan;
import Service.FootballManagementSystem;
import Domain.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditPersonalInfoTest {

    @Test
    public void editPersonalDetails() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        system.getDatabase().addUser("Aa1234", fan);
        system.editPersonalInfo.editPersonalDetails(fan, "Avi", "Levi", "0544884666", "Yuda123", "");
        Fan checkFan = (Fan)system.getDatabase().getUser(fan.getID());
        assertEquals(checkFan.getPhone(), "0544884666"); ///
        system.editPersonalInfo.editPersonalDetails(fan, "Avi", "Levi", "0544884666", "Yuda123", "ABC123");
        assertTrue(system.getDatabase().authenticationCheck("AviLevi@gmail.com", "ABC123"));

    }

    @Test
    public void editPersonalDetails1() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Player player = (Player) system.getDatabase().getListOfAllSpecificUsers("Player").get(0);
        system.editPersonalInfo.editPersonalDetails(player, "leo", "mesi", "AAA123");
        assertEquals("leo mesi", system.getDatabase().getListOfAllSpecificUsers("Player").get(0).getName()); ///
        assertTrue(system.getDatabase().authenticationCheck(player.getMail(), "AAA123"));
    }
}