package Presentation;

import Presentation.Records.RefereeRecord;
import Presentation.Records.TeamRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.LinkedList;


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
        GridPane pane = new GridPane();
        Label label = new Label("Please select League and Season");
        pane.add(label, 0,0);
        Label l_leagues = new Label("Leagues:");
        pane.add(l_leagues, 0,1);
        List<String> leagues = client.sendToServer("getAllLeagues|"+loggedUser);
        ChoiceBox<String> cb_leagues = new ChoiceBox<>(FXCollections.observableArrayList(leagues));
        pane.add(cb_leagues, 1,1);
        Label l_seasons = new Label("Seasons:");
        pane.add(l_seasons, 0,2);
        List<String> seasons = client.sendToServer("getAllSeasons|"+loggedUser);
        ChoiceBox<String> cb_seasons = new ChoiceBox<>(FXCollections.observableArrayList(seasons));
        pane.add(cb_seasons, 1,2);
        Button configureBtn = new Button("Configure");
        configureBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String league =cb_leagues.getValue() , season = cb_seasons.getValue();
                if(Checker.isValid(league)&&Checker.isValid(season)){
                    List<String> receive = client.sendToServer("configureLeagueInSeason|"+loggedUser+"|"+league+"|"+season);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
            }
        });
        pane.add(configureBtn, 0, 3);
        mainView1.getChildren().add(pane);
    }
    public void assignGames(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        Label label = new Label("please select a league to assign games to it:");
        pane.add(label, 0,0);
        addLeaguesInSeasonToPane(pane);
        Button assignBtn = new Button("Assign");
        assignBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String league = cb_leagueInSeasons.getValue();
                if(Checker.isValid(league)){
                    List<String> receive = client.sendToServer("assignGames|"+loggedUser+"|"+league);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
            }
        });
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
        Label label = new Label("please select League and Referee:");
        GridPane pane = new GridPane();
        pane.add(label, 0,0);
        addLeaguesInSeasonToPane(pane);
        List<String> referees = client.sendToServer("getAllReferees|"+loggedUser);
        ObservableList<String> refList = FXCollections.observableArrayList();
        List<RefereeRecord> refRecords = new LinkedList<>();
        for(String ref: referees){
            RefereeRecord record = new RefereeRecord(ref);
            refList.add(record.getName());
            refRecords.add(record);
        }
        ChoiceBox<String> cb_referees = new ChoiceBox<>(refList);
        Label ref = new Label("Referee:");
        pane.add(ref, 0, 2);
        pane.add(cb_referees, 1, 2);
        Button addBtn = new Button("add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ref = cb_referees.getValue(), league = cb_leagueInSeasons.getValue(), refId="";
                if(Checker.isValid(ref)&&Checker.isValid(league)){
                    for(RefereeRecord record : refRecords){
                        if(record.getName().equals(ref)) {
                            refId = record.getId();
                        }
                    }
                    List<String> receive = client.sendToServer("addRefereeToLeague|"+loggedUser+"|"+refId+"|"+league);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
                else
                    m_general.showAlert("Invalid selection of values", Alert.AlertType.ERROR);
            }
        });
        pane.add(addBtn, 0, 3);
        mainView1.getChildren().add(pane);

    }

    private ChoiceBox<String> cb_leagueInSeasons;
    private ChoiceBox<String> cb_policies;

    public void changeScorePolicy(){
        changePolicy("score");
    }

    public void changeAssignmentPolicy(){
        changePolicy("assignment");
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
        GridPane pane = new GridPane();
        Label label = new Label("please select League and a team");
        pane.add(label, 0,0);
        addLeaguesInSeasonToPane(pane);
        List<String> teams = client.sendToServer("getAllOpenTeams|"+loggedUser);
        ObservableList<String> ol_teams = FXCollections.observableArrayList();
        List<TeamRecord> teamRecords = new LinkedList<>();
        for(String team : teams){
            TeamRecord record = new TeamRecord(team);
            ol_teams.add(record.getName());
            teamRecords.add(record);
        }
        ChoiceBox<String> cb_teams = new ChoiceBox<>(ol_teams);
        Label l_teams = new Label("Teams: ");
        pane.add(l_teams, 0, 2);
        pane.add(cb_teams, 1, 2);
        Button addBtn = new Button("Add");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String league = cb_leagueInSeasons.getValue(), team = cb_teams.getValue(), teamId="";
                if(Checker.isValid(league)&&Checker.isValid(team)){
                    for(TeamRecord record : teamRecords){
                        if(record.getName().equals(team))
                            teamId = record.getId();
                    }
                    List<String> receive = client.sendToServer("addTeamToLeague|"+loggedUser+"|"+teamId+"|"+league);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }
                else
                    m_general.showAlert("Invalid selection of values", Alert.AlertType.ERROR);
            }
        });
        pane.add(addBtn, 0, 3);
        mainView1.getChildren().add(pane);
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
        cb_leagueInSeasons = new ChoiceBox<>(leagues);
        pane.add(cb_leagueInSeasons, 1, 1);
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
                if(cb_leagueInSeasons.getValue().length()<1|| cb_policies.getValue().length()<1){
                    m_general.showAlert("No league or policy was selected!", Alert.AlertType.ERROR);
                    return;
                }
                List<String> receive = client.sendToServer("changeScorePolicy|"+loggedUser+"|"+ cb_leagueInSeasons.getValue()+"|"+cb_policies.getValue());
                if(receive.get(0).contains("Succeed")){
                    m_general.showAlert("Policy has changed to"+cb_policies.getValue(), Alert.AlertType.INFORMATION);
                }
                else
                    m_general.showAlert("Couldn't change policy", Alert.AlertType.ERROR);
            }
        });
        pane.add(changeBtn, 0, 3);
    }

    private void changePolicy(String type) {
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        addLeaguesInSeasonToPane(pane);
        switch (type){
            case "score":{
                addPolicyToPane("getAllScorePolicies", pane, "Please select league and score policy", "Score Policy:");
                break;
            }
            case "assignment":{
                addPolicyToPane("getAllAssignmentsPolicies", pane, "Please select league and assignment policy", "Assignment Policy:");
                break;
            }
        }
        addChangeButton(pane);
        pane.setAlignment(Pos.TOP_CENTER);
        mainView1.getChildren().add(pane);
    }
}
