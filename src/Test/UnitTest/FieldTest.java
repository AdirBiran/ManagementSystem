package UnitTest;

import Domain.Field;
import Domain.LeagueInSeason;
import Domain.Team;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    FootballManagementSystem system;
    Field field;
    Team team;
    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        field = new Field("Tel-Aviv", 150000, 125000);
    }
    @Test
    public void testToString() {
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void getCapacity() {
    }

    @Test
    public void getGames() {
    }

    @Test
    public void getID() {
    }

    @Test
    public void deactivate() {
    }

    @Test
    public void reactivate() {
    }

    @Test
    public void getPrice() {
    }

    @Test
    public void setPrice() {
    }

    @Test
    public void getTeams() {
    }

    @Test
    public void addTeam() {
    }

    @Test
    public void removeTeam() {
    }

    @Test
    public void isActive() {
    }
}