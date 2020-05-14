package Presentation;

import Domain.User;
import javafx.scene.layout.HBox;

public class FanController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;

    public FanController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void followPage(){
        //show all pages
        //let user select page
        //send request to server with fan and page
    }
    public void followGames(){
        //show all games
        //let user select game/games/add all?
        //send request to server with fan and games
    }
    public void editPersonalInfo(){}
    public void submitComplaint(){}
}
