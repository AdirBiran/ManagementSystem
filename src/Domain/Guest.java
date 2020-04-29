package Domain;

import Data.Database;

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

    public List<Team> viewInfoAboutTeams() {
        return Database.getTeams();
    }

    public List<PartOfATeam> viewInfoAboutPlayers() {
        return Database.getListOfAllSpecificAssets("Player");
    }

    public List<PartOfATeam> viewInfoAboutCoaches() {
        return Database.getListOfAllSpecificAssets("Coach");
    }

    public List<League> viewInfoAboutLeagues() {
        return Database.getLeagues();
    }
    public List<Season> viewInfoAboutSeasons() {
        return Database.getSeasons();
    }
    public List<Referee> viewInfoAboutReferees(){
        return Database.getReferees();
    }
}
