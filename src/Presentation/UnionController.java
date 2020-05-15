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
        ObservableList<String> levels = FXCollections.observableArrayList("level 1","level 2", "level 3","level 4");
        cb_level.setItems(levels);
        Button b_add = new Button("Add");
        b_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = tf_name.getText(),level = cb_level.getValue();
                if(Checker.isValid(name)&& level.length()>0){
                    String request = "configureNewLeague|"+loggedUser+"|"+name+"|"+level;
                    List<String> response = client.sendToServer(request);
                    m_general.showAlert(response.get(0), Alert.AlertType.INFORMATION);
                    m_general.clearMainView(mainView1);
                }
                else
                    m_general.showAlert("Invalid name or level", Alert.AlertType.ERROR);
            }
        });
        pane.add(b_add, 2,2);
        mainView1.getChildren().add(pane);
    }
    public void configureNewSeason(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        Label label = new Label("Please select year and start date:");
        pane.add(label, 0,0);
        Label year = new Label("Season Year:");
        pane.add(year, 0,1);
        Label date = new Label("Season Start Date:");
        pane.add(date, 0,2);
        TextField tf_year = new TextField();
        pane.add(tf_year, 1,1);
        DatePicker dp_start = new DatePicker();
        pane.add(dp_start, 1,2);
        Button addBtn = new Button("Add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String year = tf_year.getText(), date = dp_start.getValue().toString();
                if(Checker.isValidNumber(year) && date.length()>0){
                    String request = "configureNewSeason|"+loggedUser+"|"+year+"|"+date;
                    List<String> receive = client.sendToServer(request);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    m_general.clearMainView(mainView1);
                }
            }
        });
        pane.add(addBtn, 1,3);
        mainView1.getChildren().add(pane);
    }
    public void configureLeagueInSeason(){
        m_general.clearMainView(mainView1);
        //let user select a league
        //let user select season
        //send request to configure league in season
        //show ack to user
    }
    public void assignGames(){
        m_general.clearMainView(mainView1);
        //let user select a league in season - from leagues without games
        //send request to assign games
        //show ack to user
    }

    public void appointReferee()
    {
        m_general.buildForm("referee", mainView1, client, loggedUser);
    }

    public void addRefereeToLeague(){
        m_general.clearMainView(mainView1);
        //let user select a league in season
        GridPane pane = new GridPane();
        addLeaguesInSeasonToPane(pane);

    }

    private ChoiceBox<String> cb_leagues;
    private ChoiceBox<String> cb_policies;

    public void changeScorePolicy(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        addLeaguesInSeasonToPane(pane);
        addPolicyToPane("getAllScorePolicies", pane, "Please select league and score policy", "Score Policy:");
        addChangeButton(pane);
        pane.setAlignment(Pos.TOP_CENTER);
        mainView1.getChildren().add(pane);

    }

    public void changeAssignmentPolicy(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        addLeaguesInSeasonToPane(pane);
        addPolicyToPane("getAllAssignmentsPolicies", pane, "Please select league and assignment policy", "Assignment Policy:");
        addChangeButton(pane);
        pane.setAlignment(Pos.TOP_CENTER);
        mainView1.getChildren().add(pane);
    }



    public void addTUTUPaymentToTeam(){
        m_general.clearMainView(mainView1);
        //let user select a team and amount
        //send request to add payment to team
    }
    public void addPaymentsFromTheTUTU(){
        m_general.clearMainView(mainView1);
        //let user enter a double that represents the amount to add to union accounting system
        //show ack to user
    }
    public void addTeamToLeague(){
        m_general.clearMainView(mainView1);
        //let user select a team
        //let user select league in season
        //send request to add team to league
        //send ack to user
    }
    public void calculateLeagueScore()
    {
        m_general.clearMainView(mainView1);
        //select league
        //send request to server
        //show league score table
    }

    public void calculateGameScore()
    {
        m_general.clearMainView(mainView1);
        //select league
        //select game/all games/some games?
        //send request to server to calculate score
    }
    public void changeRegistrationFee(){
        m_general.clearMainView(mainView1);
        //select league
        //show fee
        //les user enter new fee
        //check fee
        //send request to change fee
    }


    //make getAllLeagues()return a list of <League.name:season.year> of all LeagueInSeason
    private void addLeaguesInSeasonToPane(GridPane pane) {
        List<String> receiveLeagues = client.sendToServer("allLeaguesInSeasons|"+loggedUser);
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
                    m_general.showAlert("No league or policy was selected!", Alert.AlertType.ERROR);
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
