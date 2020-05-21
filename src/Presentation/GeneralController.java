package Presentation;

import Presentation.Records.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class GeneralController {

    public void setSceneByFXMLPath(String path, List<String> roles, String loggedUser, Client m_client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            if(path.equals("UserView.fxml")){
                ((UserController)loader.getController()).setUser(loggedUser);
                ((UserController)loader.getController()).setClient(m_client);
                ((UserController)loader.getController()).buildPresentation(roles);
            }
            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearMainView(Pane view)
    {
        view.getChildren().clear();
    }

    public void showListOnScreen(String type,List<String> list, GridPane gridPane, int startIndex) {
        if(list.size()<1|| list.get(0).length()<1){
            Label label = new Label("No Results...try again :(");
            gridPane.add(label,0,startIndex);
            return;
        }
        ObservableList<Record> data = FXCollections.observableArrayList();
        TableView tableView = new TableView();
        Label tableLabel = new Label();
        tableLabel.setFont(new Font("Tahoma", 16));
        tableLabel.setAlignment(Pos.CENTER);
        switch(type){
            case ("Teams"):{
                tableLabel.setText("Teams:");
                ArrayList<TeamRecord> teams = new ArrayList<>();
                for(String string: list){
                    TeamRecord record = new TeamRecord(string);
                    teams.add(record);
                }
                data.addAll(teams);

                TableColumn active = new TableColumn("Active");
                active.setCellValueFactory(new PropertyValueFactory("active"));
                TableColumn permanentlyClosed = new TableColumn("Permanently Closed");
                permanentlyClosed.setCellValueFactory(new PropertyValueFactory("permanentlyClosed"));

                tableView.getColumns().addAll(getNameColumn(),active,permanentlyClosed);

                break;
            }
            case ("Players"):{
                tableLabel.setText("Players:");
                ArrayList<PlayerRecord> players = new ArrayList<>();
                for(String string: list){
                    PlayerRecord record = new PlayerRecord(string);
                    players.add(record);
                }
                data.addAll(players);
                //columns
                TableColumn birthDate = new TableColumn("Birth Date");
                birthDate.setCellValueFactory(new PropertyValueFactory("birthDate"));
                getTeamsColumn();

                tableView.getColumns().addAll(getNameColumn(),birthDate,getRoleColumn(), getTeamsColumn());
                break;
            }
            case ("Coaches"):{
                tableLabel.setText("Coaches:");
                ArrayList<CoachRecord> coaches = new ArrayList<>();
                for(String string: list){
                    CoachRecord record = new CoachRecord(string);
                    coaches.add(record);
                }
                data.addAll(coaches);
                tableView.getColumns().addAll(getNameColumn(),getTrainingColumn(),getRoleColumn(), getTeamsColumn());
                break;
            }
            case ("Leagues"):{
                tableLabel.setText("Leagues:");
                ArrayList<LeagueRecord> leagues = new ArrayList<>();
                for(String string: list){
                    LeagueRecord record = new LeagueRecord(string);
                    leagues.add(record);
                }
                data.addAll(leagues);
                TableColumn level = new TableColumn("Level");
                level.setCellValueFactory(new PropertyValueFactory("level"));

                tableView.getColumns().addAll(getNameColumn(),level);
                break;
            }
            case ("Seasons"):{
                tableLabel.setText("Seasons:");
                ArrayList<SeasonRecord> seasons = new ArrayList<>();
                for(String string: list){
                    SeasonRecord record = new SeasonRecord(string);
                    seasons.add(record);
                }
                data.addAll(seasons);
                TableColumn year = new TableColumn("Year");
                year.setCellValueFactory(new PropertyValueFactory("year"));

                tableView.getColumns().add(year);
                break;
            }
            case ("Referees"):{
                tableLabel.setText("Referees:");
                ArrayList<RefereeRecord> referees = new ArrayList<>();
                for(String string: list){
                    RefereeRecord record = new RefereeRecord(string);
                    referees.add(record);
                }
                data.addAll(referees);
                getNameColumn();
                getTrainingColumn();


                break;
            }
            case ("Pages"):{
                tableLabel.setText("Pages:");
                //implement (the results of search)
                break;
            }
            default:{
                for(String string: list){
                    Label label = new Label(string);
                    gridPane.add(label, 0, startIndex);
                }
                return;
            }
        }

        tableView.setItems(data);
        gridPane.add(tableLabel, 0, startIndex);
        gridPane.add(tableView, 0, startIndex+1);

    }


    public void showAlert(String s, Alert.AlertType type) {
        Alert alert = new Alert(type,s);
        alert.show();
    }

    public void buildViewInfoScene(GridPane l_viewPane, HBox mainView, Client client) {
        Label label = new Label("Please select subject:");
        label.setAlignment(Pos.CENTER_LEFT);
        l_viewPane.add(label, 0,0);
        List<String> values = new LinkedList<>();
        values.add("Teams");
        values.add("Players");
        values.add("Coaches");
        values.add("Leagues");
        values.add("Seasons");
        values.add("Referees");
        ObservableList<String> obList = FXCollections.observableList(values);
        ChoiceBox<String> subjects = new ChoiceBox(obList);
        subjects.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearMainView(mainView);
                clearMainView(l_viewPane);
                buildViewInfoScene(l_viewPane, mainView,client);

                String choice = (String)subjects.getValue();
                showInfo(choice, client, l_viewPane);
            }
        });
        l_viewPane.add(subjects, 0,1);
        l_viewPane.setAlignment(Pos.TOP_CENTER);
        mainView.getChildren().add(l_viewPane);
    }

    public void showInfo(String choice, Client m_client, GridPane l_viewPane) {
        switch(choice){
            case("Teams"):{
                List<String> teams = m_client.sendToServer("viewInformationAboutTeams");
                showListOnScreen("Teams",teams, l_viewPane, 2);
                break;
            }
            case("Players"):{
                List<String> players = m_client.sendToServer("viewInformationAboutPlayers");
                showListOnScreen("Players",players, l_viewPane, 2);
                break;
            }
            case("Coaches"):{
                List<String> coaches = m_client.sendToServer("viewInformationAboutCoaches");
                showListOnScreen("Coaches",coaches, l_viewPane, 2);
                break;
            }
            case("Leagues"):{
                List<String> leagues = m_client.sendToServer("viewInformationAboutLeagues");
                showListOnScreen("Leagues",leagues, l_viewPane, 2);
                break;
            }
            case("Seasons"):{
                List<String> seasons = m_client.sendToServer("viewInformationAboutSeasons");
                showListOnScreen("Seasons",seasons, l_viewPane, 2);
                break;
            }
            case("Referees"):{
                List<String> referees = m_client.sendToServer("viewInformationAboutReferees");
                showListOnScreen("Referees",referees, l_viewPane, 2);
                break;
            }
        }
    }

    public void buildSearchView(GridPane l_searchPane, HBox mainView, Client m_client, String userId) {
        TextField searchArea = new TextField();
        l_searchPane.add(searchArea, 0, 0);
        Button b_search = new Button("Search");
        b_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //do we want spelling correction?
                clearMainView(mainView);
                clearMainView(l_searchPane);
                buildSearchView(l_searchPane, mainView, m_client, userId);
                String searchWord = searchArea.getText();

                if(Checker.isValid(searchWord)){
                    List<String> correction = FootballSpellChecker.correct(searchWord);
                    if(correction.size()>0){
                        //suggest corrections to user
                    }
                    String request="";
                    if(userId.length()>0){
                        request = "userSearch|"+userId+"|"+searchWord;
                    }
                    else
                        request = "guestSearch|"+searchWord;

                    List<String> results = m_client.sendToServer(request);
                    showListOnScreen("Pages",results, l_searchPane, 3);
                }
                else
                    showAlert("Invalid search", Alert.AlertType.ERROR);

            }
        });

        l_searchPane.add(b_search,0,1);
        l_searchPane.setAlignment(Pos.TOP_CENTER);
        mainView.getChildren().add(l_searchPane);
    }

    private TextField tf_emailInForm;
    private TextField tf_passwordInForm;
    private TextField tf_passwordAgain;
    private TextField tf_firstName;
    private TextField tf_lastName;
    private TextField tf_phone;
    private TextField tf_address;
    private ChoiceBox<String> tf_role;
    private ChoiceBox<String> tf_training;
    private TextField tf_price;
    private DatePicker birthDatePicker;
    private CheckBox cb_manage;
    private CheckBox cb_finance;
    
    public void buildForm(String type, Pane view, Client m_client, String admin) {

        clearMainView(view);

        GridPane l_registerPane = new GridPane();
        Button b_register = new Button("Add");
        b_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registerToSystem(type,m_client, view,admin);
            }
        });
        l_registerPane.add(b_register, 1,9);
        Label mail = new Label("E-mail Address:");
        l_registerPane.add(mail, 0,0);
        Label First = new Label("First Name:");
        l_registerPane.add(First, 0,3);
        Label Last = new Label("Last Name:");
        l_registerPane.add(Last, 0,4);
        tf_emailInForm = new TextField();
        l_registerPane.add(tf_emailInForm, 2,0);
        tf_firstName = new TextField();;
        l_registerPane.add(tf_firstName, 2,3);
        tf_lastName = new TextField();;
        l_registerPane.add(tf_lastName, 2,4);
        //-----------all users need------------//
        switch(type){
            case "player":{
                Label birthDate = new Label("Birth Date:");
                l_registerPane.add(birthDate, 0,5);
                birthDatePicker = new DatePicker();
                l_registerPane.add(birthDatePicker, 2,5);
                addRoleField(type,l_registerPane,0,6);
                addPriceField(l_registerPane,0,7);
                break;
            }
            case "coach":{
                addTrainingField(type,l_registerPane, 0, 5);
                addRoleField(type,l_registerPane,0,6);
                addPriceField(l_registerPane,0,7);

                break;
            }
            case "teamManager":{
                addPriceField(l_registerPane, 0, 5);
                cb_manage = new CheckBox("Add Management Authorization");
                l_registerPane.add(cb_manage, 2, 6);
                cb_finance = new CheckBox("Add Finance Authorization");
                l_registerPane.add(cb_finance, 2, 7);
                //price, bool manageasset, bool finance
                break;
            }
            case "admin":{
                addPasswordFields(l_registerPane);
                break;
            }
            case "referee":{
                addTrainingField(type,l_registerPane, 0,5);
                break;
            }
            case "fan":{
                b_register.setText("Register");
                addPasswordFields(l_registerPane);
                Label Phone = new Label("Phone Number:");
                l_registerPane.add(Phone, 0,5);
                Label Address = new Label("Address:");
                l_registerPane.add(Address, 0,6);
                tf_phone = new TextField();;
                l_registerPane.add(tf_phone, 2,5);
                tf_address = new TextField();;
                l_registerPane.add(tf_address, 2,6);
                break;
            }
        }

        l_registerPane.setAlignment(Pos.CENTER);
        view.getChildren().add(l_registerPane);

    }
    public List<String> getRolesFromSplitedText(String[] split, int startIndex) {
        List<String> result = new LinkedList<>();
        new LinkedList<>();
        for (int i = startIndex; i < split.length; i++) {
            result.add(split[i]);
        }
        return result;
    }

    private void addTrainingField(String type, GridPane l_registerPane, int col, int row) {
        Label training = new Label("Training");
        l_registerPane.add(training, col, row);
        tf_training = new ChoiceBox<>();
        ObservableList<String> trainings = FXCollections.observableArrayList();
        switch(type){
            case "coach":{
                trainings.add("IFA_C");
                trainings.add("UEFA_A");
                trainings.add("UEFA_B");
                trainings.add("UEFA_PRO");
                break;
            }
            case "referee":{
                trainings.add("referees");
                trainings.add("linesman");
                trainings.add("var");
                break;
            }
        }
        tf_training.setItems(trainings);
        l_registerPane.add(tf_training, col+2, row);
    }

    private void addPriceField(GridPane l_registerPane, int col, int row) {

        Label price = new Label("Price:");
        l_registerPane.add(price, col,row);
        tf_price = new TextField();
        l_registerPane.add(tf_price, col+2,row);
    }

    private void addRoleField(String type, GridPane l_registerPane, int col, int row) {
        Label role = new Label("Role:");
        l_registerPane.add(role, col,row);
        tf_role = new ChoiceBox<>();
        ObservableList<String> roles = FXCollections.observableArrayList();
        switch(type){
            case "player":{
                roles.add("goalkeeper");
                roles.add("playerBack");
                roles.add("midfielderPlayer");
                roles.add("attackingPlayer");
                break;
            }
            case "coach":{
                roles.add("main");
                roles.add("assistantCoach");
                roles.add("fitness");
                roles.add("goalkeeperCoach");
                break;
            }
        }
        tf_role.setItems(roles);
        l_registerPane.add(tf_role, col+2,row);
    }

    private void addPasswordFields(GridPane l_registerPane) {
        Label Password = new Label("Password:");
        l_registerPane.add(Password, 0,1);
        Label VerifyPassword = new Label("Verify Password:");
        l_registerPane.add(VerifyPassword, 0,2);
        tf_passwordInForm = new TextField();;
        l_registerPane.add(tf_passwordInForm, 2,1);
        tf_passwordAgain = new TextField();;
        l_registerPane.add(tf_passwordAgain, 2,2);

    }

    private void registerToSystem(String type, Client m_client, Pane view, String admin) {
        String mail = tf_emailInForm.getText(),
                firstName = tf_firstName.getText(),
                lastName = tf_lastName.getText();
        List<String> register =null;
        String request = "";
        if(!Checker.isValidEmailAddress(mail)){
            showAlert("invalid Email Address!",Alert.AlertType.ERROR);
            return;
        }
        if(!(Checker.isValid(firstName) && Checker.isValid(lastName))){
            showAlert("Invalid first or last name!", Alert.AlertType.ERROR);
            return;
        }

        switch(type){
            case "fan":{
               String password = tf_passwordInForm.getText(),
                phone = tf_phone.getText(), address = tf_address.getText();

               if(checkPassword(password, tf_passwordAgain.getText())){
                   if(Checker.isValidNumber(phone) && Checker.isValid(address)) {
                       request = "register|"+mail+"|"+password+"|"+firstName+"|"+lastName+"|"+phone+"|"+address;
                   }
                   else
                       showAlert("Invalid phone (enter numbers only) or address!", Alert.AlertType.ERROR);
               }

                break;
            }
            case "player":{
                LocalDate birth = birthDatePicker.getValue();
                String role = tf_role.getValue(), price = tf_price.getText();
                if(birth!=null && Checker.isValidNumber(price)&&Checker.isValid(role)){
                    request = "addNewPlayer|"+admin+"|"+firstName+"|"+lastName+"|"+mail+"|"+birth+"|"+role+"|"+price;
                }
                else{
                    showAlert("Invalid date, role or price!", Alert.AlertType.ERROR);
                }
                break;
            }
            case "coach":{
                String role = tf_role.getValue(),training = tf_training.getValue() , price = tf_price.getText();
                if(Checker.isValid(role)&&Checker.isValid(training)&&Checker.isValidNumber(price)){
                    request = "addNewCoach|"+admin+"|"+firstName+"|"+lastName+"|"+mail+"|"+training+"|"+role+"|"+price;
                }
                else{
                    showAlert("Invalid training, role or price!", Alert.AlertType.ERROR);
                }
                break;
            }
            case "teamManager":{
                String price = tf_price.getText();
                if(Checker.isValidNumber(price)){
                    request = "addNewTeamManager|"+admin+"|"+firstName+"|"+lastName+"|"+mail+"|"+price+"|"+cb_manage.isSelected()+"|"+cb_finance.isSelected();
                }
                break;
            }
            case "teamOwner":{
                request = "addNewTeamOwner|"+admin+"|"+firstName+"|"+lastName+"|"+mail;
                break;
            }
            case "representative":{
                request ="addNewUnionRepresentative|"+admin+"|"+firstName+"|"+lastName+"|"+mail;
                break;
            }
            case "admin":{
                //fix first time problem, how to add system admin without an authorized admin?
                String password = tf_passwordInForm.getText();
                if(checkPassword(password, tf_passwordAgain.getText())){
                    request = "addNewAdmin|"+admin+"|"+password+"|"+firstName+"|"+lastName+"|"+mail;
                }
                break;
            }
            case "referee":{
                String training = tf_training.getValue();
                if(Checker.isValid(training))
                    request = "appointReferee|"+admin+"|"+firstName+"|"+lastName+"|"+mail+"|"+training;
                break;
            }
        }
        //if(!type.equals("fan") &&!type.equals("admin")&&!type.equals("representative"))
        //    FootballSpellChecker.addWord(firstName+" "+lastName);

        register = m_client.sendToServer(request);
        String[]split = register.get(0).split("\\|");
        if(type.equals("fan")) {
            String loggedUser = split[0];
            if(loggedUser.length()>0){
                showAlert("success Alert! - we are logging you in", Alert.AlertType.INFORMATION);
                register = getRolesFromSplitedText(split,1);
                setSceneByFXMLPath("UserView.fxml",register, loggedUser, m_client);
            }
            else
                showAlert("registration failed - user already exists or invalid arguments",Alert.AlertType.ERROR);
        }
        if(split[0].contains("Succeed")){
            showAlert(split[0], Alert.AlertType.INFORMATION);
            clearMainView(view);
            if(type.equals("player")||type.equals("coach"))
                FootballSpellChecker.addWord(firstName+" "+lastName);
        }
        else{
            showAlert(split[0], Alert.AlertType.INFORMATION);
        }
    }

    private boolean checkPassword(String password, String password2) {
        if(!password.equals(password2)){
            showAlert("cant verify password!",Alert.AlertType.ERROR);
            return false;
        }
        if(!Checker.isValidPassword(password)){
            showAlert("invalid password!",Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public void linkSelectionLists(ListView lv_players, ListView lv_selectedPlayers) {
        lv_players.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lv_players.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)-> {
            lv_selectedPlayers.setItems(lv_players.getSelectionModel().getSelectedItems());
        });
    }

    private TableColumn getRoleColumn() {
        TableColumn role = new TableColumn("Role");
        role.setCellValueFactory(new PropertyValueFactory("roleInTeam"));
        return role;
    }

    private TableColumn getTrainingColumn() {
        TableColumn training = new TableColumn("training");
        training.setCellValueFactory(new PropertyValueFactory("training"));
        return training;
    }

    private TableColumn getNameColumn() {
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory("name"));
        return name;
    }

    private TableColumn getTeamsColumn() {
        TableColumn teams = new TableColumn("Teams");
        teams.setCellValueFactory(new PropertyValueFactory("teams"));
        return teams;
    }


    public HashMap<String, String> initHashSet(List<String> received) {
        HashMap<String, String>  teams = new HashMap<>();
        for(String team: received){
            if(team.length()>0){
                String[] split = team.split(":");
                teams.put(split[0], split[1]);
            }
        }
        return teams;
    }

    public void addTeamsChoiceBox(GridPane pane, ChoiceBox cb_teams ,int rowIdx, Collection<String> teams) {
        Label l_teams = new Label("Teams: ");
        pane.add(l_teams,0,rowIdx);
        cb_teams = new ChoiceBox(FXCollections.observableArrayList(teams));
        pane.add(cb_teams,1,rowIdx);

    }

    public String getIdFromName(String teamName, HashMap<String, String> teams) {
        for(String id : teams.keySet()){
            if(teams.get(id).equals(teamName)){
                return id;
            }
        }
        return "";
    }
}
