package Tests.Unit;

import Data.Database;
import Domain.EditPersonalInfo;
import Presentation.Fan;
import Presentation.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditPersonalInfoTest {

    @Test
    public void editPersonalDetails() {
        Database database = new Database();
        EditPersonalInfo personalInfo = new EditPersonalInfo(database);
        Fan fan = new Fan("AviLevi@gmail.com", "Avi", "Levi", "0500004544", "Yuda123");
        database.addUser("Aa1234", fan);
        personalInfo.editPersonalDetails(fan, "Avi", "Levi", "0544884666", "Yuda123", "");
        Fan checkFan = (Fan)database.getUser(fan.getID());
        assertEquals(checkFan.getPhone(), "0544884666"); ///
        personalInfo.editPersonalDetails(fan, "Avi", "Levi", "0544884666", "Yuda123", "ABC123");
        assertTrue(database.authenticationCheck("AviLevi@gmail.com", "ABC123"));

    }

    @Test
    public void editPersonalDetails1() {
        Database database = new Database();
        EditPersonalInfo personalInfo = new EditPersonalInfo(database);
        Player player = (Player) database.getListOfAllSpecificUsers("Player").get(0);
        personalInfo.editPersonalDetails(player, "leo", "mesi", "AAA123");
        assertEquals("leo mesi", database.getListOfAllSpecificUsers("Player").get(0).getName()); ///
        assertTrue(database.authenticationCheck(player.getID(), "AAA123"));
    }
}