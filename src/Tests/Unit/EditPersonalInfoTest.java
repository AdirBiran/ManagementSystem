package Tests.Unit;

import Data.Database;
import Domain.EditPersonalInfo;
import Presentation.Fan;
import Presentation.FootballManagementSystem;
import Presentation.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditPersonalInfoTest {

    @Test
    public void editPersonalDetails() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        system.database.addUser("Aa1234", fan);
        system.editPersonalInfo.editPersonalDetails(fan, "Avi", "Levi", "0544884666", "Yuda123", "");
        Fan checkFan = (Fan)system.database.getUser(fan.getID());
        assertEquals(checkFan.getPhone(), "0544884666"); ///
        system.editPersonalInfo.editPersonalDetails(fan, "Avi", "Levi", "0544884666", "Yuda123", "ABC123");
        assertTrue(system.database.authenticationCheck("AviLevi@gmail.com", "ABC123"));

    }

    @Test
    public void editPersonalDetails1() {
        FootballManagementSystem system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        Player player = (Player) system.database.getListOfAllSpecificUsers("Player").get(0);
        system.editPersonalInfo.editPersonalDetails(player, "leo", "mesi", "AAA123");
        assertEquals("leo mesi", system.database.getListOfAllSpecificUsers("Player").get(0).getName()); ///
        assertTrue(system.database.authenticationCheck(player.getMail(), "AAA123"));
    }
}