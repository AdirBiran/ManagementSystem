package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.List;

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
            Label labelGame = new Label(game);
            mainPane.add(labelGame, 0,0);
            Label evenType = new Label("Event Type:");
            mainPane.add(evenType, 0,1);
            ObservableList<String> types = FXCollections.observableArrayList("Goal", "Offside", "Foul", "RedCard", "YellowCard","Injury", "Replacement");
            ChoiceBox<String> cb_types = new ChoiceBox<>(types);
            mainPane.add(cb_types, 1,1);
            //let user select team-
            //let user select player
            Label description = new Label("Description:");
            mainPane.add(description,0,3);
            TextArea t_description = new TextArea();
            mainPane.add(t_description,1,3);
            Button addBtn = new Button("Add");
            addBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(cb_types.getValue().length()>0&&t_description.getText().length()>0){
                        List<String> receive = client.sendToServer("addEventToGame|"+loggedUser+"|"+game+"|"+cb_types.getValue()+"|"+t_description.getText());
                        showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            });
            mainPane.add(addBtn,0,4);
            mainView1.getChildren().add(mainPane);
        }

    }

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

        //show all past games
        //let user select a game
        //send request for game report
        //show report


    }
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
