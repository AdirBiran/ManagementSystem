package Service;


import Domain.*;

import java.util.List;

public class GuestSystem {

    Guest guest;

    public GuestSystem() {
        guest = new Guest();
    }

    /*
    Guest registration for the system
     */
    public User registr(String mail, String password, String firstName, String lastName,
                                        String phone, String address){

        return guest.register(mail, password, firstName, lastName, phone, address);

    }

    /*
     * User login to system
     * if exists return connected user
     * if the password is invalid or there is no such email in the system return null
     */
    public User logIn( String mail, String password){

        return guest.login(mail, password);
    }

    /*
    Search results in a system
     */
    public List<Object> search( String wordToSearch){
       return guest.search(wordToSearch);
    }

    /*
    the guest chooses what to watch - teamsToManage, players, coaches, leagues and more
     */
    public List<Team> viewInformationAboutTeams(){
        return guest.viewInfoAboutTeams();
    }

    public List<PartOfATeam> viewInformationAboutPlayers(){
        return guest.viewInfoAboutPlayers();
    }

    public List<PartOfATeam> viewInformationAboutCoaches(){
        return guest.viewInfoAboutCoaches();
    }

    public List<League> viewInformationAboutLeagues(){
        return guest.viewInfoAboutLeagues();
    }

    public List<Season> viewInformationAboutSeasons(){
        return guest.viewInfoAboutSeasons();
    }

    public List<Referee> viewInformationAboutReferees(){
        return guest.viewInfoAboutReferees();
    }
}
