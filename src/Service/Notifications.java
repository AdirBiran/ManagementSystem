package Service;

import Domain.*;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Notifications {

    private Database database;

    public Notifications(){
        database= new Database();
    }
    /**
     * registration for game alerts
     * @param fan - want to get alerts for the game
     * @param game -fan will receive alerts for this game
     */
    public boolean registrationForGameAlerts(Fan fan, Game game){
        if(game.addFanForNotifications(fan))
            return true;
        return false;
    }

    /**
     *
     * @param team- team closed
     */
    public void openORcloseTeam(String option, Team team, boolean permanently){
        for (TeamOwner owner: team.getTeamOwners()) {
            if(!owner.isClosedTeam())
                System.out.println("team " + option + " : " + team.getName());
        }
        for (TeamManager teamManager : team.getTeamManagers())
            System.out.println("team "+option+" : "+team.getName());
        if(!permanently) {
            for (User admin : database.getListOfAllSpecificUsers("Admin"))
                System.out.println("team " + option + " : " + team.getName());
        }
    }

    public LinkedList<Game> chooseFutureGames() {
        LinkedList games=database.getAllFutureGames(); //game that fan choose
        return games;
    }

    public void refereeAlertsChangeDate(Game game, Date newDate){
        for(Referee referee : game.getSideReferees())
            System.out.println("The game: "+game.toString()+" have new date: "+newDate);
        //to main referee
        System.out.printf("The game: "+game.toString()+" have new date: "+newDate);
    }
    public void refereeAlertsChangeDate(Game game, Time newTime){
        for(Referee referee : game.getSideReferees())
            System.out.println("The game: "+game.toString()+" have new time: "+newTime);
        //to main referee
        System.out.printf("The game: "+game.toString()+" have new time: "+newTime);
    }
    public  void refereeAlertsChangeGameLocation(Game game, Field field){
        for (Referee referee : game.getSideReferees())
            System.out.printf("The game: "+game.toString()+" change location: "+field.getLocation());
        //to main referee
        System.out.printf("The game: "+game.toString()+" change location: "+field.getLocation());
    }
}
