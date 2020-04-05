package Service;

import Domain.Asset;
import Domain.Field;
import Domain.Team;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database db = new Database();
    @Test
    public void getAsset()
    {
        Team team = new Team();
        Asset field = new Field("1", "jerusalem",550 ,team);
        db.addAsset(field);
        assertEquals(field, db.getAsset("1"));
        assertNull(db.getAsset("2"));

    }

    @Test
    public void getUser() {
    }

    @Test
    public void getGame() {
    }

    @Test
    public void getAllGames() {
    }

    @Test
    public void getAllFutureGames() {
    }

    @Test
    public void addAsset() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void addGame() {
    }

    @Test
    public void authenticationCheck() {
    }

    @Test
    public void getListOfAllSpecificUsers() {
    }
}
