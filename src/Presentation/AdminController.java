package Presentation;

import Domain.User;
import javafx.scene.layout.HBox;

public class AdminController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;

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
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewCoach(){
        //show registration form for player
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewTeamOwner(){
        //show registration form for player
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewTeamManager(){
        //show registration form for player
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewUnionRepresentative(){
        //show registration form for player
        //check arguments
        //send request to server
        //return ack - success or failure
    }
    public void addNewAdmin(){
        //show registration form for player
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
