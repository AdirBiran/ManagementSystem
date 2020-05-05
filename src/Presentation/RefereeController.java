package Presentation;

import javafx.scene.layout.HBox;

public class RefereeController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;

    public RefereeController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void  addEventToGame(){};
    public void  setScoreInGame(){};
    public void  getEventReport(){};
    public void  changeEvent(){};
}
