package Presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ManagementController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;

    private GeneralController m_general = new GeneralController();

    public ManagementController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void addPlayerToTeam(){
        //show all authorized teams
        //show all players
        //let user select team and player

        String request = "addAssetPlayer|";
    }
    public void addCoachToTeam()
    {
        //show all authorized teams
        //show all coaches
        //let user select team and coach
    }
    public void addFieldToTeam(){
        //show all authorized teams
        //show all fields
        //let user select team and field
    }
    public void updateAsset(){
        //show all authorized teams
        //let user select team
        //show list of assets of team
        //let user select what asset to update
        //update user

    }
    public void reportIncome(){
        //show all authorized teams
        //let user select team
        //let user enter income

    }
    public void reportExpanse(){
        //show all authorized teams
        //let user select team
        //let user enter expanse

    }
    public void getBalance(){
        //show all authorized teams
        //let user select team
        //show team balance
    }
}
