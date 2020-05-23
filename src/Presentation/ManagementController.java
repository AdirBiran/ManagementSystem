package Presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.HashMap;


public class ManagementController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private HashMap<String,String> teams; //<id , name>

    private GeneralController m_general = new GeneralController();

    public ManagementController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
        this.teams = m_general.initHashSet(client.sendToServer("getTeams|"+loggedUser));
    }

    private ChoiceBox<String> cb_teams;

    public void addPlayerToTeam(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        int rowIdx =0;
        teamSelection(pane, rowIdx);
        rowIdx++;
        //show all authorized teams
        //show all players
        //let user select team and player

        String request = "addAssetPlayer|";
    }
    public void addCoachToTeam()
    {
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        int rowIdx =0;
        teamSelection(pane, rowIdx);
        rowIdx++;
        //show all authorized teams
        //show all coaches
        //let user select team and coach
    }
    public void addFieldToTeam(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        int rowIdx =0;
        teamSelection(pane, rowIdx);
        rowIdx++;
        //show all authorized teams
        //show all fields
        //let user select team and field
    }
    public void updateAsset(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        int rowIdx =0;
        teamSelection(pane, rowIdx);
        rowIdx++;
        //show all authorized teams
        //let user select team
        //show list of assets of team
        //let user select what asset to update
        //update user

    }
    public void reportIncome(){
        m_general.clearMainView(mainView1);
        report("income");

    }
    public void reportExpanse(){
        m_general.clearMainView(mainView1);
        report("expanse");

    }
    public void getBalance(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        int rowIdx =0;
        teamSelection(pane, rowIdx);
        rowIdx++;
        Button getBalanceBtn = new Button("Get Balance");
        getBalanceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String teamName = cb_teams.getValue(), id = m_general.getIdFromName(teamName, teams);
                String request = "getBalance|"+loggedUser+"|"+id;
                List<String> receive = client.sendToServer(request);
                m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
            }
        });
        pane.add(getBalanceBtn, 0, rowIdx);
        mainView1.getChildren().add(pane);
        //let user select team
        //show team balance
    }

    private void report(String type){
        GridPane pane = new GridPane();
        int rowIdx =0;
        teamSelection(pane, rowIdx);
        rowIdx++;
        rowIdx++;
        Label l_type = null;
        StringBuilder request = new StringBuilder();
        switch (type){
            case"income":{
                l_type = new Label("Income :");
                request.append("reportIncome|");
                break;
            }
            case"expanse":{
                l_type = new Label("Expanse :");
                request.append("reportExpanse|");
                break;
            }
        }
        TextField tf_input = new TextField();
        pane.add(l_type, 0, rowIdx);
        pane.add(tf_input, 1, rowIdx);
        rowIdx++;
        Button reportBtn = new Button("Report");
        reportBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String input = tf_input.getText();
                String teamName = cb_teams.getValue();
                if(Checker.isValidNumber(input)&& Checker.isValid(teamName)){
                    String id = m_general.getIdFromName(teamName, teams);
                    request.append(loggedUser+"|"+id+"|"+input);
                    List<String> receive = client.sendToServer(request.toString());
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
                else{
                    m_general.showAlert("Invalid team or input - enter numbers only!", Alert.AlertType.ERROR);
                }
            }
        });
        pane.add(reportBtn, 0, rowIdx);
        mainView1.getChildren().add(pane);
    }

    private void teamSelection(GridPane pane , int index){
        Label label = new Label("Please select team:");
        pane.add(label, 0, index);
        index++;
        cb_teams = m_general.addTeamsChoiceBox(pane, index, teams.values());
    }

}
