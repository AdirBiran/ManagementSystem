package Presentation;

import Domain.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GeneralController m_general = new GeneralController();

    public AdminController(HBox mainView1, String loggedUser, Client m_client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = m_client;
    }

    public void closeTeamPermanently(){
        //show all teams with selection option maybe choicebox
        //let user select a team to close
        //sent request to close team
        //return ack - success or failure
    }
    public void addNewPlayer(){
        //show registration form for player
        m_general.buildForm("player", mainView1, client,loggedUser);
        //check arguments
        //send request to server
        //return ack - success or failure
    }



    public void addNewCoach(){
        //show registration form for player
        m_general.buildForm("coach", mainView1, client,loggedUser);
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewTeamOwner(){
        //show registration form for player
        m_general.buildForm("teamOwner", mainView1, client,loggedUser);
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewTeamManager(){
        //show registration form for player
        m_general.buildForm("teamManager", mainView1, client,loggedUser);
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewUnionRepresentative(){
        //show registration form for player
        m_general.buildForm("representative", mainView1, client,loggedUser);
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewAdmin(){
        //show registration form for player
        m_general.buildForm("admin", mainView1, client,loggedUser);
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void removeUser(){
        
        //show all users?? - table view?
        //let user select a user to remove
        //send request to remove user
        //return ack - success or failure
    }
    public void viewLog(){
        //send request to get log
        //show log? open in a new window?
        //maybe in table view?
    }
    public void responseToComplaint(){
        //show all active complaints
        //let user select a complaint to response to
        //create text area for the user to write his response
    }




}
