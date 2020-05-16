package Data;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    DataAccess dataAccess;
    Database database;
    FootballManagementSystem system;
    Coach coach;
    Team team;
    Team team1;

    @Before
    public void init(){
        dataAccess=new DataAccess();
        database = new Database();


        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        team1 = league.getTeams().get(1);
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        User coachU= admin.addNewCoach("dor","dor","dor@mail.com", Coach.TrainingCoach.UEFA_B, Coach.RoleCoach.main,50000);
        coach = (Coach) coachU.checkUserRole("Coach");
        coach.addTeam(team);
        coach.addTeam(team1);

    }

    @Test
    public void updateObject(){

        database.updateObject(coach);
    }

}