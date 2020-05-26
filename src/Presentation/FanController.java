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
    private GridPane mainPane;

    public FanController(HBox mainView1, String loggedUser, Client client, GridPane mainPane) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
        this.mainPane = mainPane;
    }

    public void followPage(){
        m_general.clearMainView(mainView1);
        //show all pages
        //let user select page
        //send request to server with fan and page
    }
    public void followGames(){
        m_general.clearMainView(mainView1);
        m_general.clearMainView(mainPane);
        List<String> games = client.sendToServer("getAllFutureGames|"+loggedUser);

        int rowIdx=0;
        Label label = new Label("Please select games to follow:");
        mainPane.add(label,0,rowIdx);
        rowIdx++;
        Label l_games = new Label("Games:");
        Label l_selectedGames = new Label("Selected Games:");
        mainPane.add(l_games, 0, rowIdx);
        mainPane.add(l_selectedGames, 1, rowIdx);
        rowIdx++;
        ListView<String> lv_games = new ListView<>(FXCollections.observableArrayList(games));
        ListView<String> lv_selectedGames = new ListView<>();
        m_general.linkSelectionLists(lv_games, lv_selectedGames);
        mainPane.add(lv_games, 0, rowIdx);
        mainPane.add(lv_selectedGames, 1, rowIdx);
        rowIdx++;
        CheckBox email = new CheckBox("send notification to your Email");
        mainPane.add(email, 0, rowIdx);
        rowIdx++;
        Button followBtn = new Button("Follow");
        followBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<String> selected = lv_selectedGames.getItems();
                if(selected.size()>0){
                    boolean cb_email = email.isSelected();
                    String strGames = client.ListToString(selected);
                    List<String> receive = client.sendToServer("followGames|"+loggedUser+"|"+strGames+"|"+cb_email);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
                else
                    m_general.showAlert("Action canceled - No game was selected", Alert.AlertType.ERROR);
            }
        });
        mainPane.add(followBtn, 0, rowIdx);
        mainView1.getChildren().add(mainPane);
        //let user select game/games/add all?
        //send request to server with fan and games
    }
    public void editPersonalInfo(){
        m_general.clearMainView(mainView1);
    }
    public void submitComplaint(){
        m_general.clearMainView(mainView1);
        m_general.clearMainView(mainPane);
        Label label = new Label("Submit A Complaint");
        mainPane.add(label, 0, 0);
        TextArea ta_complaint = new TextArea("type your complaint here...");
        mainPane.add(ta_complaint, 0, 1);
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
        mainPane.add(submitBtn, 0, 2);
        mainView1.getChildren().add(mainPane);
    }
}
