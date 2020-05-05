package Presentation;

import Domain.User;
import Service.GuestSystem;

import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GuestController {


    private GuestSystem guestSystem = new GuestSystem();

    private User loggedUser = null;

    @FXML private HBox mainView;
    @FXML private TextField tf_email;
    @FXML private PasswordField tf_password;


    public void loginButtonPushed(ActionEvent action){

        String Email = tf_email.getText();
        String password = tf_password.getText();
        if(Checker.isValidPassword(password)&&Checker.isValidEmailAddress(Email)){
            loggedUser = guestSystem.logIn(Email, password);
            if(loggedUser == null){
                showAlert("wrong email or password!");
            }
            else{
                tf_email.setText("");
                tf_password.setText("");
                setSceneByFXMLPath("UserView.fxml");
            }
        }
        else
            showAlert("wrong email or password!");

    }

    private void setSceneByFXMLPath(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            if(path.equals("UserView.fxml")){
                ((UserController)loader.getController()).buildPresentation(loggedUser.getStringRoles());
                ((UserController)loader.getController()).setUser(loggedUser);
            }

            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private TextField tf_emailInForm;
    private TextField tf_passwordInForm;
    private TextField tf_passwordAgain;
    private TextField tf_firstName;
    private TextField tf_lastName;
    private TextField tf_phone;
    private TextField tf_address;
    private GridPane l_registerPane;

    public void buildForm(ActionEvent action) {

        clearMainView();
        l_registerPane = new GridPane();
        Label mail = new Label("E-mail Address:");
        l_registerPane.add(mail, 0,0);
        Label Password = new Label("Password:");
        l_registerPane.add(Password, 0,1);
        Label VerifyPassword = new Label("Verify Password:");
        l_registerPane.add(VerifyPassword, 0,2);
        Label First = new Label("First Name:");
        l_registerPane.add(First, 0,3);
        Label Last = new Label("Last Name:");
        l_registerPane.add(Last, 0,4);
        Label Phone = new Label("Phone Number:");
        l_registerPane.add(Phone, 0,5);
        Label Address = new Label("Address:");
        l_registerPane.add(Address, 0,6);

        tf_emailInForm = new TextField();
        l_registerPane.add(tf_emailInForm, 2,0);
        tf_passwordInForm = new TextField();;
        l_registerPane.add(tf_passwordInForm, 2,1);
        tf_passwordAgain = new TextField();;
        l_registerPane.add(tf_passwordAgain, 2,2);
        tf_firstName = new TextField();;
        l_registerPane.add(tf_firstName, 2,3);
        tf_lastName = new TextField();;
        l_registerPane.add(tf_lastName, 2,4);
        tf_phone = new TextField();;
        l_registerPane.add(tf_phone, 2,5);
        tf_address = new TextField();;
        l_registerPane.add(tf_address, 2,6);

        Button b_register = new Button("Register");
        b_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                registerInForm(action);
            }
        });
        l_registerPane.add(b_register, 1,7);
        l_registerPane.setAlignment(Pos.CENTER);
        mainView.getChildren().add(l_registerPane);
    }

    private void clearMainView() {
        mainView.getChildren().clear();
    }

    public void registerInForm(ActionEvent action){
        String mail = tf_emailInForm.getText(),
                password = tf_passwordInForm.getText(),
                firstName = tf_firstName.getText(),
                lastName = tf_lastName.getText(),
                phone = tf_phone.getText(),
                address = tf_address.getText();
        if(!Checker.isValidEmailAddress(mail)){
            showAlert("invalid Email Address!");
            return;
        }
        if(!password.equals(tf_passwordAgain.getText())){
            showAlert("cant verify password!");
            return;
        }
        if(!Checker.isValidPassword(password)){
            showAlert("invalid password!");

            return;
        }
        if(Checker.isValid(firstName) && Checker.isValid(lastName) && Checker.isValid(phone) && Checker.isValid(address)){
            loggedUser = guestSystem.registr(mail,password,firstName,lastName,phone,address);
            if(loggedUser!=null){
                setSceneByFXMLPath("UserView.fxml");
                l_registerPane = null;
            }
            else
                showAlert("registration failed :( ");

        }
        else
            showAlert("registration failed :( ");

    }

    private GridPane l_searchPane;

    public void searchButtonPushed(ActionEvent actionEvent){
        clearMainView();
        l_searchPane = new GridPane();
        TextField searchArea = new TextField();
        l_searchPane.add(searchArea, 3, 0);
        Button b_search = new Button("Search");
        b_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //do we want spelling correction?
                List<Object> results = guestSystem.search(searchArea.getText());//needs to return a list of String
                int i=3;
                for(Object result: results){
                    Label label = new Label(result.toString());
                    label.setAlignment(Pos.CENTER);
                    l_searchPane.add(label, 3, i);
                    i++;
                }
                //show results
            }
        });

        l_searchPane.add(b_search,3,1);
        l_searchPane.setAlignment(Pos.TOP_CENTER);
        mainView.getChildren().add(l_searchPane);

    }

    private GridPane l_viewPane;
    public void viewInfoButtonPushed(ActionEvent actionEvent){
        clearMainView();
        l_viewPane = new GridPane();
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
        ObservableList obList = FXCollections.observableList(values);
        ChoiceBox subjects = new ChoiceBox(obList);
        subjects.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String choice = (String)subjects.getValue();
                showInfo(choice);
            }
        });
        l_viewPane.add(subjects, 0,1);
        l_viewPane.setAlignment(Pos.TOP_CENTER);
        mainView.getChildren().add(l_viewPane);
    }

    private void showInfo(String choice) {
        switch(choice){
            case("Teams"):{
                guestSystem.viewInformationAboutTeams();
                break;
            }
            case("Players"):{
                guestSystem.viewInformationAboutPlayers();
                break;
            }
            case("Coaches"):{
                guestSystem.viewInformationAboutCoaches();
                break;
            }
            case("Leagues"):{
                guestSystem.viewInformationAboutLeagues();
                break;
            }
            case("Seasons"):{
                guestSystem.viewInformationAboutSeasons();
                break;
            }
            case("Referees"):{
                guestSystem.viewInformationAboutReferees();
                break;
            }
        }
    }


    private void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,s);
        alert.show();
    }




}
