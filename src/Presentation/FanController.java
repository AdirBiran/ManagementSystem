package Presentation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class FanController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GeneralController m_general = new GeneralController();

    public FanController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void followPage(){
        m_general.clearMainView(mainView1);
        //show all pages
        //let user select page
        //send request to server with fan and page
    }
    public void followGames(){
        m_general.clearMainView(mainView1);
        List<String> games = client.sendToServer("getAllFutureGames|"+loggedUser);
        GridPane pane = new GridPane();
        int rowIdx=0;
        Label label = new Label("Please select games to follow:");
        pane.add(label,0,rowIdx);
        rowIdx++;
        Label l_games = new Label("Games:");
        Label l_selectedGames = new Label("Selected Games:");
        pane.add(l_games, 0, rowIdx);
        pane.add(l_selectedGames, 1, rowIdx);
        rowIdx++;
        ListView<String> lv_games = new ListView<>(FXCollections.observableArrayList(games));
        ListView<String> lv_selectedGames = new ListView<>();
        m_general.linkSelectionLists(lv_games, lv_selectedGames);
        pane.add(lv_games, 0, rowIdx);
        pane.add(lv_selectedGames, 1, rowIdx);
        rowIdx++;
        Button followBtn = new Button("Follow");
        followBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> selected = lv_selectedGames.getItems();
                if(selected.size()>0){
                    String strGames = client.ListToString(selected);
                    List<String> receive = client.sendToServer("followGames|"+loggedUser+"|"+strGames);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
                else
                    m_general.showAlert("Action canceled - No game was selected", Alert.AlertType.ERROR);
            }
        });
        pane.add(followBtn, 0, rowIdx);
        mainView1.getChildren().add(pane);
        //let user select game/games/add all?
        //send request to server with fan and games
    }
    public void editPersonalInfo(){
        m_general.clearMainView(mainView1);
    }
    public void submitComplaint(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        Label label = new Label("Submit A Complaint");
        pane.add(label, 0, 0);
        TextArea ta_complaint = new TextArea("type your complaint here...");
        pane.add(ta_complaint, 0, 1);
        Button submitBtn = new Button("Submit");
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = ta_complaint.getText();
                if(!text.equals("type your complaint here...")){
                    List<String> receive = client.sendToServer("addComplaint|"+loggedUser+"|"+text);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
            }
        });
        pane.add(submitBtn, 0, 2);
        mainView1.getChildren().add(pane);
    }
}
