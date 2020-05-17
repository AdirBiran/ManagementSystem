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
        initHashSet(m_client.sendToServer("getTeams|"+loggedUser));;
    }

    private void initHashSet(List<String> received) {
        this.teams = new HashMap<>();
        for(String team: received){
            String[] split = team.split(":");
            this.teams.put(split[0], split[1]);
        }
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
        //let user select user to appoint
        //send request to appoint user
    }
    public void appointTeamManager(){m_general.clearMainView(mainView1);}
    public void closeTeam()
    {
        m_general.clearMainView(mainView1);
        GridPane gridPane = new GridPane();
        Label label = new Label("Please select team to close");
        ObservableList<String> list = FXCollections.observableArrayList(teams.values());
        ChoiceBox<String> choiceBox = new ChoiceBox<>(list);
        gridPane.add(label, 0,0);
        gridPane.add(choiceBox,0,1);
        Button addBtn = new Button("Close");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String teamName = choiceBox.getValue();
                for(String id: teams.keySet()){
                    if(teamName.equals(teams.get(id))){
                        List<String> receive =  m_client.sendToServer("closeTeam|"+loggedUser+"|"+id);
                        m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            }
        });

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
}
