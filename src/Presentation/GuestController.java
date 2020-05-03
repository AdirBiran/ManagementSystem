package Presentation;

import Domain.User;
import Service.GuestSystem;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.stage.Stage;

public class GuestController {


    private GuestSystem guestSystem = new GuestSystem();

    private User loggedUser = null;


    @FXML private TextField tf_email;
    @FXML private TextField tf_password;


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

    @FXML private TextField tf_emailInForm;
    @FXML private TextField tf_passwordInForm;
    @FXML private TextField tf_passwordAgain;
    @FXML private TextField tf_firstName;
    @FXML private TextField tf_lastName;
    @FXML private TextField tf_phone;
    @FXML private TextField tf_address;

    public void registerButtonPushed(ActionEvent action){
        Stage formStage = new Stage();
        formStage.setTitle("Registration Form");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RegistrationForm.fxml"));
            Scene scene = new Scene(root);
            formStage.setScene(scene);
            formStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            hideWindow(action);
            return;
        }
        if(!password.equals(tf_passwordAgain.getText())){
            showAlert("cant verify password!");
            hideWindow(action);
            return;
        }
        if(!Checker.isValidPassword(password)){
            showAlert("invalid password!");
            hideWindow(action);
            return;
        }
        if(Checker.isValid(firstName) && Checker.isValid(lastName) && Checker.isValid(phone) && Checker.isValid(address)){
            loggedUser = guestSystem.registr(mail,password,firstName,lastName,phone,address);
            if(loggedUser!=null){
                hideWindow(action);
                setSceneByFXMLPath("UserView.fxml");
            }
            else
                showAlert("registration failed :( ");

        }
        else
            showAlert("registration failed :( ");
        hideWindow(action);
    }

    public void searchButtonPushed(ActionEvent actionEvent){
        //open dialog with user?
        guestSystem.search("");
        //show somehow
    }

    public void viewInfoButtonPushed(ActionEvent actionEvent){
        //open dialog with user?
        //ask what information to show
    }

    private void hideWindow(ActionEvent action) {
        Node node = (Node)action.getSource();
        node.getScene().getWindow().hide();
    }


    private void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,s);
        alert.show();
    }




}
