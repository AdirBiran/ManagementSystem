package Presentation;

import javafx.scene.layout.HBox;

public class OwnershipController {

    private HBox mainView1;
    private String loggedUser;
    private Client m_client;


    public OwnershipController(HBox mainView1, String loggedUser, Client m_client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.m_client = m_client;
    }

    public void addTeam(){};
    public void getTeamById(){};
    public void removeTeam(){};
    public void appointTeamOwner(){};
    public void appointTeamManager(){};
    public void closeTeam(){};
    public void reopenTeam(){};
}
