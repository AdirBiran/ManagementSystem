package Presentation;

import Presentation.Records.GameRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.List;
import java.util.HashMap;


public class RefereeController extends GeneralController{

    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GridPane mainPane;

    public RefereeController(HBox mainView1, String loggedUser, Client client, GridPane mainPane) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
        this.mainPane = mainPane;
    }

    //make sure getOccurringGame() returns the game relevant for the user -
    //a game that he is assign to and is occurring now + 5 hours
    //if you cant find any return ""
    public void  addEventToGame(){
        clearMainView(mainView1);
        clearMainView(mainPane);
        String game = getOccurringGame();
        if(game.length()>0){
            int row =0;
            GameRecord gameRecord = new GameRecord(game);
            Label labelGame = new Label(""+gameRecord.getName()+", "+ gameRecord.getDate());
            mainPane.add(labelGame, 0,row);
            row++;
            Label evenType = new Label("Event Type:");
            mainPane.add(evenType, 0,row);
            ChoiceBox<String> cb_types = new ChoiceBox<>(FXCollections.observableArrayList("Goal", "Offside", "Foul", "RedCard", "YellowCard","Injury", "Replacement"));
            mainPane.add(cb_types, 1,row);
            row++;
            String[] split = gameRecord.getName().split("vs");
            ChoiceBox<String> cb_teams = new ChoiceBox<>(FXCollections.observableArrayList(split[0],split[1]));
            Label team = new Label("Team:");
            mainPane.add(team, 0,row);
            mainPane.add(cb_teams, 1,row);
            row++;

            //let user select player  -  howw??
            Label description = new Label("Description:");
            mainPane.add(description,0,row);
            TextArea t_description = new TextArea();
            mainPane.add(t_description,1,row);
            row++;
            Button addBtn = new Button("Add");
            addBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(cb_types.getValue().length()>0&&t_description.getText().length()>0 && cb_teams.getValue().length()>0){
                        List<String> receive = client.sendToServer("addEventToGame|"+loggedUser+"|"+game+"|"+cb_teams.getValue()+"|"+cb_types.getValue()+"|"+t_description.getText());
                        showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            });
            mainPane.add(addBtn,0,row);
            mainView1.getChildren().add(mainPane);
        }

    }

    //main referee only?
    public void  setScoreInGame(){
        clearMainView(mainView1);
        clearMainView(mainPane);
        String game = getOccurringGame();
        if(game.length()>0){
            Label labelGame = new Label(game);
            mainPane.add(labelGame, 0,0);
            Label hostScore = new Label("Host Score:");
            mainPane.add(hostScore, 0,1);
            ChoiceBox<String> h_score = new ChoiceBox<>(addScores());
            mainPane.add(h_score, 1,1);

            Label guestScore = new Label("Guest Score:");
            mainPane.add(guestScore, 0,2);
            ChoiceBox<String> g_score = new ChoiceBox<>(addScores());
            mainPane.add(g_score, 1,2);

            Button addBtn = new Button("Add");
            addBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String host =h_score.getValue(),guest = g_score.getValue();
                    if(Checker.isValidNumber(host)&& Checker.isValidNumber(guest)){
                        List<String> receive = client.sendToServer("setScoreInGame|"+loggedUser+"|"+game+"|"+host+"|"+guest);
                        showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            });
            mainPane.add(addBtn, 0,3);
            mainView1.getChildren().add(mainPane);
        }
    }

    public void getGameReport(){
        clearMainView(mainView1);
        clearMainView(mainPane);
        List<String> games = client.sendToServer("getAllPastGames_R|"+loggedUser);
        HashMap<String, GameRecord> gamesMap = new HashMap<>();
        ObservableList<String> gameList = FXCollections.observableArrayList();
        for(String game: games){
            GameRecord record = new GameRecord(game);
            gameList.add(record.getName());
            gamesMap.put(record.getId(), record);
        }
        ChoiceBox<String> cb_games = new ChoiceBox<>(gameList);
        cb_games.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearMainView(mainView1);
                clearMainView(mainPane);
                String selectedGame = cb_games.getValue();
                cb_games.setValue("");
                for(String id: gamesMap.keySet()){
                    if(selectedGame.equals(gamesMap.get(id).getName())){
                        List<String> receive = client.sendToServer("getGameReport|"+loggedUser+"|"+id);
                        showListOnScreen("Game Report", receive, mainPane, 2);
                    }
                }
            }
        });
        Label label = new Label("Please select a game: ");
        mainPane.add(label, 0, 0);
        mainPane.add(cb_games, 0, 1);
        mainView1.getChildren().add(mainPane);
    }

    //main referee only
    public void  changeEvent(){
        clearMainView(mainView1);
        clearMainView(mainPane);
        String game = getOccurringGame();
        if(game.length()>0){
            Label label = new Label("Please select an event to change:");
            mainPane.add(label, 0,0);
            Label lb_events = new Label("Events:");
            mainPane.add(lb_events, 0,1);
            ChoiceBox<String> events = new ChoiceBox<>();

            ObservableList<String> listEvents = FXCollections.observableArrayList();
            mainPane.setAlignment(Pos.CENTER);
            List<String> gameReport = client.sendToServer("getEventReport|"+loggedUser+"|"+game);
            for(String report :gameReport){
                if(report.length()>0){
                    listEvents.add(report);
                }
                else{
                    showAlert("Can't get events", Alert.AlertType.ERROR);
                    return;
                }
            }
            events.setItems(listEvents);
            mainPane.add(events, 1,1);
            mainView1.getChildren().add(mainPane);
            events.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    clearMainView(mainPane);
                    changeEvent();
                    showEventDetails(mainPane,events.getValue(),game,2);
                }
            });
        }
    }

    private void showEventDetails(GridPane pane, String eventString,String game, int startRow) {
        Label event = new Label(eventString);
        pane.add(event,0,startRow);
        Label description = new Label("Description:");
        TextField tf_description = new TextField();
        pane.add(description,0,startRow+1);
        pane.add(tf_description,1,startRow+1);
        Button change = new Button("Change");
        change.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String request = "changeEvent|"+loggedUser+"|"+game+"|"+eventString+"|"+tf_description.getText();
                List<String> receive =client.sendToServer(request);
                showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                clearMainView(mainView1);
                clearMainView(mainPane);
            }
        });
    }

    private ObservableList<String> addScores() {
        ObservableList<String> scores = FXCollections.observableArrayList();
        for (int i = 0; i <16 ; i++) {
            scores.add(""+i);
        }
        return scores;
    }
    private String getOccurringGame() {
        List<String> occurringGame = client.sendToServer("getOccurringGame|"+loggedUser);
        String game = occurringGame.get(0);
        if(game.length()<1) {
            showAlert("Illegal action! - None of your games is occurring now", Alert.AlertType.ERROR);
            return "";
        }
        return game;

    }

}
