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

public class UnionController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;


    private GeneralController m_general = new GeneralController();

    public UnionController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void configureNewLeague()
    {
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        Label name = new Label("League Name:");
        pane.add(name, 0, 0);
        TextField tf_name = new TextField();
        pane.add(tf_name, 2, 0);
        Label level = new Label("League Level:");
        pane.add(level, 0, 1);
        ChoiceBox<String> cb_level = new ChoiceBox<>();
        pane.add(cb_level, 2, 1);
        ObservableList<String> levels = FXCollections.observableArrayList();
        levels.add("level 1");
        levels.add("level 2");
        levels.add("level 3");
        levels.add("level 4");
        Button b_add = new Button("Add");
        b_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = tf_name.getText(),level = cb_level.getValue();
                if(Checker.isValid(name)&& level.length()>0){
                    String request = "configureNewLeague|"+loggedUser+"|"+name+"|"+level;
                    List<String> response = client.sendToServer(request);
                    if(response.get(0).contains("Succeed")){
                        m_general.showAlert("New league was added!", Alert.AlertType.INFORMATION);
                        m_general.clearMainView(mainView1);
                    }
                    else
                        m_general.showAlert("Failed to add new league", Alert.AlertType.ERROR);

                }
                else
                    m_general.showAlert("Invalid name or level", Alert.AlertType.ERROR);
            }
        });

    }
    public void configureNewSeason(){}
    public void configureLeagueInSeason(){}
    public void assignGames(){}
    public void appointReferee()
    {
        m_general.buildForm("referee", mainView1, client, loggedUser);

    }
    public void addRefereeToLeague(){
        GridPane pane = new GridPane();
        addLeaguesToPane(pane);

    }

    private ChoiceBox<String> cb_leagues;
    private ChoiceBox<String> cb_policies;

    public void changeScorePolicy(){
        GridPane pane = new GridPane();
        addLeaguesToPane(pane);
        addPolicyToPane("getAllScorePolicies", pane, "Please select league and score policy", "Score Policy:");
        addChangeButton(pane);
        pane.setAlignment(Pos.TOP_CENTER);
        mainView1.getChildren().add(pane);

    }

    public void changeAssignmentPolicy(){
        GridPane pane = new GridPane();
        addLeaguesToPane(pane);
        addPolicyToPane("getAllAssignmentsPolicies", pane, "Please select league and assignment policy", "Assignment Policy:");
        addChangeButton(pane);
        pane.setAlignment(Pos.TOP_CENTER);
        mainView1.getChildren().add(pane);
    }



    public void addTUTUPaymentToTeam(){}
    public void addPaymentsFromTheTUTU(){}
    public void addTeamToLeague(){}
    public void calculateLeagueScore(){}
    public void calculateGameScore(){}
    public void changeRegistrationFee(){}


    //make getAllLeagues()return a list of <League.name:season.year> of all LeagueInSeason
    private void addLeaguesToPane(GridPane pane) {
        List<String> receiveLeagues = client.sendToServer("getAllLeagues|"+loggedUser);
        Label league = new Label("League:");
        pane.add(league, 0, 1);
        ObservableList<String> leagues = FXCollections.observableArrayList(receiveLeagues);
        cb_leagues = new ChoiceBox<>(leagues);
        pane.add(cb_leagues, 1, 1);
    }

    private void addPolicyToPane(String type, GridPane pane, String label1, String policy1) {
        List<String> receivePolicies = client.sendToServer(type+"|"+loggedUser);
        Label label = new Label(label1);
        Label policy = new Label(policy1);
        pane.add(label, 0, 0);
        pane.add(policy, 0, 2);
        ObservableList<String> policies = FXCollections.observableArrayList(receivePolicies);
        cb_policies = new ChoiceBox<>(policies);
        pane.add(cb_policies, 1, 2);
    }
    private void addChangeButton(GridPane pane) {
        Button changeBtn = new Button("Change");
        changeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cb_leagues.getValue().length()<1|| cb_policies.getValue().length()<1){
                    m_general.showAlert("No league or policy eas selected!", Alert.AlertType.ERROR);
                    return;
                }
                List<String> receive = client.sendToServer("changeScorePolicy|"+loggedUser+"|"+cb_leagues.getValue()+"|"+cb_policies.getValue());
                if(receive.get(0).contains("Succeed")){
                    m_general.showAlert("Policy has changed to"+cb_policies.getValue(), Alert.AlertType.INFORMATION);
                }
                else
                    m_general.showAlert("Couldn't change policy", Alert.AlertType.ERROR);
            }
        });
        pane.add(changeBtn, 0, 3);
    }
}
