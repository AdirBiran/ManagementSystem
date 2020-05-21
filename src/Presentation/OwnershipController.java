package Presentation;

import Presentation.Records.CoachRecord;
import Presentation.Records.PlayerRecord;
import Presentation.Records.Record;
import Presentation.Records.UserRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class OwnershipController {

    private HBox mainView1;
    private String loggedUser;
    private Client m_client;
    private HashMap<String,String> teams; //<id , name>

    private GeneralController m_general = new GeneralController();


    public OwnershipController(HBox mainView1, String loggedUser, Client m_client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.m_client = m_client;
        this.teams = m_general.initHashSet(m_client.sendToServer("getTeams|"+loggedUser));
    }



    public void openNewTeam(){
        int rowCount=0;
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        Label label = new Label("Please fill out this form: ");
        pane.add(label,0,rowCount);
        rowCount++;
        Label teamName = new Label("Team Name: ");
        TextField tf_teamName = new TextField();
        pane.add(teamName,0,rowCount);
        pane.add(tf_teamName,1,rowCount);
        rowCount++;

        List<String> players = m_client.sendToServer("gatAllPlayers|"+loggedUser);
        List<String> coaches = m_client.sendToServer("getAllCoaches|"+loggedUser);
        List<String> fields = m_client.sendToServer("getAllFields|"+loggedUser);

        ObservableList<PlayerRecord> playerRecordArrayList = FXCollections.observableArrayList();
        for(String player: players){
            if(player.length()>0){
                PlayerRecord record = new PlayerRecord(player);
                playerRecordArrayList.add(record);
            }
        }
        ListView<Record> lv_players = new ListView(playerRecordArrayList);
        lv_players.setCellFactory(new PropertyValueFactory("name"));
        ListView lv_selectedPlayers = new ListView();
        m_general.linkSelectionLists(lv_players,lv_selectedPlayers);

        pane.add(new Label("Players - at least 11: "),0,rowCount);
        pane.add(new Label("Selected Players: "),1,rowCount);
        rowCount++;
        addToPane(pane, lv_players, lv_selectedPlayers, 0, rowCount);
        rowCount++;

        ObservableList<CoachRecord> coachRecordArrayList = FXCollections.observableArrayList();
        for(String coach: coaches){
            if(coach.length()>0){
                CoachRecord record = new CoachRecord(coach);
                coachRecordArrayList.add(record);
            }
        }
        ListView<Record> lv_coaches = new ListView(coachRecordArrayList);
        lv_coaches.setCellFactory(new PropertyValueFactory("name"));
        ListView lv_selectedCoaches = new ListView();
        m_general.linkSelectionLists(lv_coaches,lv_selectedCoaches );
        pane.add(new Label("Coaches - at least 1: "),0,rowCount);
        pane.add(new Label("Selected Coaches: "),1,rowCount);
        rowCount++;
        addToPane(pane, lv_coaches,lv_selectedCoaches,0,rowCount);
        rowCount++;


        ObservableList<String> fieldsArrayList = FXCollections.observableArrayList();
        fieldsArrayList.addAll(fields);
        ListView<String> lv_field = new ListView(fieldsArrayList);
        ListView lv_selectedFields = new ListView();
        m_general.linkSelectionLists(lv_field,lv_selectedFields );
        pane.add(new Label("Fields - at least 1: "),0,rowCount);
        pane.add(new Label("Selected Fields: "),1,rowCount);
        rowCount++;
        addToPane(pane, lv_field,lv_selectedFields,0,rowCount);
        rowCount++;
        Button addBtn = new Button("Add Team");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = tf_teamName.getText();
                if(Checker.isValid(name)&&lv_selectedPlayers.getItems().size()>10&&lv_selectedCoaches.getItems().size()>1&&lv_selectedFields.getItems().size()>1){
                    String players = m_client.ListToString(getStringsIds(lv_selectedPlayers.getItems()));
                    String coaches = m_client.ListToString(getStringsIds(lv_selectedCoaches.getItems()));
                    String fields = m_client.ListToString(lv_selectedFields.getItems());
                    List<String> receive = m_client.sendToServer("createTeam|"+loggedUser+"|"+name+"|"+players+"|"+coaches+"|"+fields);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    if(receive.get(0).contains("Succeed"))
                        FootballSpellChecker.addWord(name);
                }
                else
                    m_general.showAlert("invalid arguments - please select at least 11 players one coach and one field!", Alert.AlertType.ERROR);

            }
        });
        pane.add(addBtn,1,rowCount);
        mainView1.getChildren().add(pane);
    }

    public void appointTeamOwner(){
        m_general.clearMainView(mainView1);
        appoint("owner");
    }


    public void appointTeamManager(){
        m_general.clearMainView(mainView1);
        appoint("manager");

    }


    public void closeTeam()
    {
        m_general.clearMainView(mainView1);
        GridPane gridPane = new GridPane();
        Label label = new Label("Please select team to close");
        gridPane.add(label,0,0);
        m_general.addTeamsChoiceBox(gridPane,cb_teams, 1, teams.values());
        Button addBtn = new Button("Close");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String teamName = cb_teams.getValue() , id = m_general.getIdFromName(teamName, teams);
                List<String> receive =  m_client.sendToServer("closeTeam|"+loggedUser+"|"+id);
                m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
            }
        });
        gridPane.add(addBtn,0,2);
        mainView1.getChildren().add(gridPane);
    }
    public void reopenTeam(){
        m_general.clearMainView(mainView1);
        //getAllClosedTeams()
        //let user select team
        //send request to reopen
    }

    private List<String> getStringsIds(ObservableList<Record> items) {
        List<String> results = new ArrayList<>();
        for(Record item:items){
            results.add(item.getId());
        }
        return results;
    }

    private void addToPane(GridPane pane,Node object ,Node selected, int col, int row){
        pane.add(object ,col,row);
        pane.add(selected ,col+1,row);
    }

    private ChoiceBox<String> cb_teams;
    private ChoiceBox<String> cb_users;


    private void addUsersChoiceBox(GridPane pane, int rowIdx) {
        List<String> users = m_client.sendToServer("getAllUsers_Team|"+loggedUser);
        List<UserRecord> userRecords = new ArrayList<>();
        ObservableList<String> ol_users = FXCollections.observableArrayList();
        for(String user:users){
            UserRecord record = new UserRecord(user);
            ol_users.add(record.getName());
            userRecords.add(record);
        }
        Label l_users = new Label("Users: ");
        pane.add(l_users,0,rowIdx);
        cb_users = new ChoiceBox(ol_users);
        pane.add(cb_users,1,rowIdx);
    }

    private void appoint(String type) {
        int rowIdx= 0;
        StringBuilder request = new StringBuilder();
        GridPane pane = new GridPane();
        Label label = new Label("");
        switch (type){
            case "manager":{
                label = new Label("please select team and user to appoint as team manager");
                request.append("appointTeamManager|");
                break;
            }
            case "owner":{
                label = new Label("please select team and user to appoint as team owner");
                request.append("appointTeamOwner|");
                break;
            }
        }
        pane.add(label,0,rowIdx);
        rowIdx++;
        m_general.addTeamsChoiceBox(pane,cb_teams, rowIdx, teams.values());
        rowIdx++;
        addUsersChoiceBox(pane, rowIdx);
        rowIdx++;
        Button appointBtn = new Button("Appoint");
        appointBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String team=cb_teams.getValue(), user=cb_users.getValue() , id = m_general.getIdFromName(team, teams);
                if(Checker.isValid(team)&&Checker.isValid(user)){
                    request.append(loggedUser+"|"+user+"|"+id);
                    List<String> receive = m_client.sendToServer(request.toString());
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
                else{
                    m_general.showAlert("Invalid values selection!", Alert.AlertType.ERROR);
                }
            }
        });
        pane.add(appointBtn,0,rowIdx);
        mainView1.getChildren().add(pane);
    }
}
