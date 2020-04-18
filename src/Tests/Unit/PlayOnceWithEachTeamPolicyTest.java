package Tests.Unit;

import Domain.Team;
import Service.FootballManagementSystem;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayOnceWithEachTeamPolicyTest {

    FootballManagementSystem system;

    @Test
    public void assignGames() {
        system = new FootballManagementSystem();
        system.systemInit(true);
        system.dataReboot();
        List <Team> teams =new LinkedList<>();
        for (Object t : system.database.searchObject("team"))
            teams.add((Team) t);

    }
}