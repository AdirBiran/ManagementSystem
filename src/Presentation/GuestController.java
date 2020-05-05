package Presentation;

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
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GuestController {


    private Client m_client = new Client(7567);//split by |

    private GeneralController m_general = new GeneralController();

    @FXML private HBox mainView;
    @FXML private TextField tf_email;
    @FXML private PasswordField tf_password;


    public void loginButtonPushed(ActionEvent action){

        String Email = tf_email.getText();
        String password = tf_password.getText();
        if(Checker.isValidPassword(password)&&Checker.isValidEmailAddress(Email)){
            List<String> user = m_client.sendToServer("logIn"+"|"+Email+"|"+password);
            String[] split = (user.get(0).split("\\|"));
            String loggedUser = split[0];
            if(loggedUser.length()==0){
                m_general.showAlert("wrong email or password!",Alert.AlertType.ERROR);
            }
            else{
                tf_email.setText("");
                tf_password.setText("");
                user = getRolesFromSplitedText(split,1);

                m_general.setSceneByFXMLPath("UserView.fxml", user, loggedUser, m_client);
            }
        }
        else
            m_general.showAlert("wrong email or password!",Alert.AlertType.ERROR);

    }



    private TextField tf_emailInForm;
    private TextField tf_passwordInForm;
    private TextField tf_passwordAgain;
    private TextField tf_firstName;
    private TextField tf_lastName;
    private TextField tf_phone;
    private TextField tf_address;

    public void buildRegistrationForm(ActionEvent action) {

        m_general.clearMainView(mainView);
        GridPane l_registerPane = new GridPane();
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



    private void registerInForm(ActionEvent action){
        String mail = tf_emailInForm.getText(),
                password = tf_passwordInForm.getText(),
                firstName = tf_firstName.getText(),
                lastName = tf_lastName.getText(),
                phone = tf_phone.getText(),
                address = tf_address.getText();
        if(!Checker.isValidEmailAddress(mail)){
            m_general.showAlert("invalid Email Address!",Alert.AlertType.ERROR);
            return;
        }
        if(!password.equals(tf_passwordAgain.getText())){
            m_general.showAlert("cant verify password!",Alert.AlertType.ERROR);
            return;
        }
        if(!Checker.isValidPassword(password)){
            m_general.showAlert("invalid password!",Alert.AlertType.ERROR);

            return;
        }
        if(Checker.isValid(firstName) && Checker.isValid(lastName) && Checker.isValid(phone) && Checker.isValid(address)){
            List<String> register = m_client.sendToServer("register|"+mail+"|"+password+"|"+firstName+"|"+lastName+"|"+phone+"|"+address);
            String[]split = register.get(0).split("\\|");
            String loggedUser = split[0];
            if(loggedUser.length()>0){
                register = getRolesFromSplitedText(split,1);
                m_general.setSceneByFXMLPath("UserView.fxml",register, loggedUser, m_client);
                m_general.clearMainView(mainView);
            }
            else
                m_general.showAlert("registration failed - user already exists",Alert.AlertType.ERROR);

        }
        else
            m_general.showAlert("registration failed - invalid first name, last name, phone ot address",Alert.AlertType.ERROR);

    }

    public void searchButtonPushed(ActionEvent actionEvent){
        m_general.clearMainView(mainView);
        GridPane l_searchPane = new GridPane();
        m_general.buildSearchView(l_searchPane, mainView, m_client,"");


    }

    public void viewInfoButtonPushed(ActionEvent actionEvent){
        m_general.clearMainView(mainView);
        GridPane l_viewPane = new GridPane();
        m_general.buildViewInfoScene(l_viewPane, mainView, m_client);

    }


    private List<String> getRolesFromSplitedText(String[] split, int startIndex) {
        List<String> result = new LinkedList<>();
        new LinkedList<>();
        for (int i = startIndex; i < split.length; i++) {
            result.add(split[i]);
        }
        return result;
    }



}
