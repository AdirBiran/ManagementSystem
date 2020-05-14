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

public class RefereeController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GeneralController m_general = new GeneralController();

    public RefereeController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    //make sure getOccurringGame() returns the game relevant for the user -
    //a game that he is assign to and is occurring now + 5 hours
    //if you cant find any return ""
    public void  addEventToGame(){
        GridPane grid = new GridPane();
        String game = getOccurringGame();
        if(game.length()>0){
            Label labelGame = new Label(game);
            grid.add(labelGame, 0,0);
            Label evenType = new Label("Event Type:");
            grid.add(evenType, 0,1);
            ObservableList<String> types = FXCollections.observableArrayList("Goal", "Offside", "Foul", "RedCard", "YellowCard","Injury", "Replacement");
            ChoiceBox<String> cb_types = new ChoiceBox<>(types);
            grid.add(cb_types, 1,1);
            //Label minInGame = new Label("Minute In Game:");
            //grid.add(minInGame, 0,2);
            //ObservableList<String> minutes = FXCollections.observableArrayList();
            //ChoiceBox<String> cb_minutes = new ChoiceBox<>(addMinutes());
            //grid.add(cb_minutes, 1, 2);
            Label description = new Label("Description:");
            grid.add(description,0,3);
            TextArea t_description = new TextArea();
            grid.add(t_description,1,3);
            Button addBtn = new Button("Add");
            addBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(cb_types.getValue().length()>0&&t_description.getText().length()>0){
                        List<String> receive = client.sendToServer("addEventToGame|"+loggedUser+"|"+game+"|"+cb_types.getValue()+"|"+t_description.getText());
                        m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            });
            grid.add(addBtn,0,4);
            mainView1.getChildren().add(grid);
        }

    }

    public void  setScoreInGame(){
        GridPane grid = new GridPane();
        String game = getOccurringGame();
        if(game.length()>0){
            Label labelGame = new Label(game);
            grid.add(labelGame, 0,0);
            Label hostScore = new Label("Host Score:");
            grid.add(hostScore, 0,1);
            ChoiceBox<String> h_score = new ChoiceBox<>(addScores());
            grid.add(h_score, 1,1);

            Label guestScore = new Label("Guest Score:");
            grid.add(guestScore, 0,2);
            ChoiceBox<String> g_score = new ChoiceBox<>(addScores());
            grid.add(g_score, 1,2);

            Button addBtn = new Button("Add");
            addBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String host =h_score.getValue(),guest = g_score.getValue();
                    if(Checker.isValidNumber(host)&& Checker.isValidNumber(guest)){
                        List<String> receive = client.sendToServer("setScoreInGame|"+loggedUser+"|"+game+"|"+host+"|"+guest);
                        m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            });
            grid.add(addBtn, 0,3);
            mainView1.getChildren().add(grid);
        }
    }

    public void getGameReport(){
        //show all past games
        //let user select a game
        //send request for game report
        //show report


    }
    public void  changeEvent(){
        String game = getOccurringGame();
        if(game.length()>0){
            GridPane pane = new GridPane();
            Label label = new Label("Please select an event to change:");
            pane.add(label, 0,0);
            Label lb_events = new Label("Events:");
            pane.add(lb_events, 0,1);
            ChoiceBox<String> events = new ChoiceBox<>();

            ObservableList<String> listEvents = FXCollections.observableArrayList();
            pane.setAlignment(Pos.CENTER);
            List<String> gameReport = client.sendToServer("getEventReport|"+loggedUser+"|"+game);
            for(String report :gameReport){
                if(report.length()>0){
                    listEvents.add(report);
                }
                else{
                    m_general.showAlert("Can't get events", Alert.AlertType.ERROR);
                    return;
                }
            }
            events.setItems(listEvents);
            pane.add(events, 1,1);
            mainView1.getChildren().add(pane);
            events.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    m_general.clearMainView(pane);
                    changeEvent();
                    showEventDetails(pane,events.getValue(),game,2);
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
                m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
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
            m_general.showAlert("Illegal action! - None of your games is occurring now", Alert.AlertType.ERROR);
            return "";
        }
        return game;

    }

    private ObservableList<String> addMinutes() {
        ObservableList<String> minutes = FXCollections.observableArrayList();
        for (int i = 1; i < 91; i++) {
            minutes.add(""+i);
        }
        return minutes;
    }
}
