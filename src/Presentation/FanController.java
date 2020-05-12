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

    public void followPage(){}
    public void followGames(){}
    public void editPersonalInfo(){}
    public void submitComplaint(){}
}
