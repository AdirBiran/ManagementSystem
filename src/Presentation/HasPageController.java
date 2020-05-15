package Presentation;

import javafx.scene.layout.HBox;

public class HasPageController {


    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GeneralController m_general = new GeneralController();

    public HasPageController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void viewPage(){m_general.clearMainView(mainView1);}
    public void uploadToPage(){m_general.clearMainView(mainView1);}
}
