package UnitTest;

import Domain.*;
import Service.FootballManagementSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoachTest {

    FootballManagementSystem system;
    Coach coach;
    Team team;

    @Before
    public void init(){
        system = new FootballManagementSystem();
        system.systemInit(true);
        LeagueInSeason league = system.dataReboot();
        team = league.getTeams().get(0);
        Admin admin = (Admin) system.getAdmin().checkUserRole("Admin");
        User coachU= admin.addNewCoach("dor","dor","dor@mail.com", Coach.TrainingCoach.Guide,"main",50000);
        coach = (Coach) coachU.checkUserRole("Coach");
    }
    @Test
    public void getTraining() {
        assertEquals(coach.getTraining(), Coach.TrainingCoach.Guide.toString());

    }

    @Test
    public void getRoleInTeam() {
        assertEquals(coach.getRoleInTeam(),"main");
    }

    @Test
    public void setTraining() {
        coach.setTraining(Coach.TrainingCoach.CertifiedTrainer);
        assertEquals(coach.getTraining(), Coach.TrainingCoach.CertifiedTrainer.toString());
    }

    @Test
    public void setRoleInTeam() {
        coach.setRoleInTeam("no main");
        assertEquals(coach.getRoleInTeam(),"no main");
    }

    @Test
    public void getID() {
        assertNotNull(coach.getID());
    }

    @Test
    public void deactivate() {
        coach.deactivate();
        assertFalse(coach.isActive());
    }

    @Test
    public void getPrice() {
        assertEquals(coach.getPrice(),50000,1);
    }

    @Test
    public void setPrice() {
        coach.setPrice(20000);
        assertEquals(coach.getPrice(),20000,1);
    }

    @Test
    public void getTeams() {
        assertEquals(coach.getTeams().size(),0);
    }

    @Test
    public void addTeam() {
        coach.addTeam(team);
        assertEquals(coach.getTeams().size(),1);

    }

    @Test
    public void removeTeam() {
        coach.addTeam(team);
        coach.removeTeam(team);
        assertEquals(coach.getTeams().size(),0);

    }

    @Test
    public void isActive() {
        assertTrue(coach.isActive());
    }

    @Test
    public void reactivate() {
        coach.reactivate();
        assertTrue(coach.isActive());
    }

    @Test
    public void myRole() {
        assertEquals(coach.myRole(),"Coach");

    }

}