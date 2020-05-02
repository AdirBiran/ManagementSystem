package Domain;

import Data.Database;

import java.util.LinkedList;
import java.util.List;

public class Guest {

    public Guest() {

    }

    public List<Object> search(String wordToSearch){
        return Database.searchObject(wordToSearch);
    }

    public User register(String mail, String password, String firstName, String lastName, String phone, String address)
    {
        return UserFactory.getNewFan(password, firstName, lastName, mail, phone, address);
    }
    public User login(String mail, String password)
    {
        return Database.getUserByMail(mail ,password );
    }

    public List<String> viewInfoAboutTeams() {
        List<Team> teams =  Database.getTeams();
        List<String> stringOfTeams = new LinkedList<>();
        for(Team t : teams)
            stringOfTeams.add(t.toString());
        return stringOfTeams;
    }

    public List<String> viewInfoAboutPlayers() {
        List<PartOfATeam> players = Database.getListOfAllSpecificAssets("Player");
        List<String> stringOfPlayers = new LinkedList<>();
        for(PartOfATeam p: players)
            stringOfPlayers.add(((Player)p).toString());
        return stringOfPlayers;
    }

    public List<String> viewInfoAboutCoaches() {
        List<PartOfATeam> coaches =  Database.getListOfAllSpecificAssets("Coach");
        List<String> stringOfCoaches = new LinkedList<>();
        for(PartOfATeam c : coaches)
            stringOfCoaches.add(((Coach)c).toString());
        return stringOfCoaches;
    }

    public List<String> viewInfoAboutLeagues() {
        List<League> leagues = Database.getLeagues();
        List<String> stringOfLeagues = new LinkedList<>();
        for (League l : leagues)
            stringOfLeagues.add(l.toString());
        return stringOfLeagues;
    }
    public List<String> viewInfoAboutSeasons() {
        List<Season> seasons = Database.getSeasons();
        List <String> stringOfSeasons = new LinkedList<>();
        for (Season s: seasons)
            stringOfSeasons.add(s.toString());
        return stringOfSeasons;
    }
    public List<String> viewInfoAboutReferees(){
        List<Referee> referees = Database.getReferees();
        List <String> stringOfReferees = new LinkedList<>();
        for(Referee r : referees)
            stringOfReferees.add(r.toString());
        return stringOfReferees;
    }
}
