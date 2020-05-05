package Presentation;

import javafx.scene.layout.HBox;

public class ManagementController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;

    public ManagementController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void addPlayerToTeam(){};
    public void addCoachToTeam(){};
    public void addFieldToTeam(){};
    public void updateAsset(){};
    public void reportIncome(){};
    public void reportExpanse(){};
    public void getBalance(){};
}
