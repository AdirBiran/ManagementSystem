package Presentation;

import javafx.scene.layout.HBox;

public class HasPageController {


    private HBox mainView1;
    private String loggedUser;
    private Client client;

    public HasPageController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void viewPage(){};
    public void uploadToPage(){};
}
