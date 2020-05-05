package Presentation;

import javafx.scene.layout.HBox;

public class UnionController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;

    public UnionController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void configureNewLeague(){};
    public void configureNewSeason(){};
    public void configureLeagueInSeason(){};
    public void assignGames(){};
    public void appointReferee(){};
    public void addRefereeToLeague(){};
    public void changeScorePolicy(){};
    public void changeAssignmentPolicy(){};
    public void addTUTUPaymentToTeam(){};
    public void addPaymentsFromTheTUTU(){};
    public void addTeamToLeague(){};
    public void calculateLeagueScore(){};
    public void calculateGameScore(){};
    public void changeRegistrationFee(){};

}
