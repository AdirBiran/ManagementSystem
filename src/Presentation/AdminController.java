package Presentation;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
        m_general.clearMainView(mainView1);
        //show all teams with selection option maybe choicebox
        String request = "getAllOpenTeams_Admin|"+loggedUser;

        //let user select a team to close
        //sent request to close team
        //return ack - success or failure
    }
    public void addNewPlayer(){
        //show registration form for player
        m_general.buildForm("player", mainView1, client,loggedUser);

    }

    public void addNewCoach(){
        //show registration form for coach
        m_general.buildForm("coach", mainView1, client,loggedUser);

    }
    public void addNewTeamOwner(){
        //show registration form for teamOwner
        m_general.buildForm("teamOwner", mainView1, client,loggedUser);

    }
    public void addNewTeamManager(){
        //show registration form for teamManager
        m_general.buildForm("teamManager", mainView1, client,loggedUser);
    }
    public void addNewUnionRepresentative(){
        //show registration form for representative
        m_general.buildForm("representative", mainView1, client,loggedUser);

    }
    public void addNewAdmin(){
        //show registration form for admin
        m_general.buildForm("admin", mainView1, client,loggedUser);
    }
    public void removeUser(){
        m_general.clearMainView(mainView1);
        String request = "getAllUsers_Admin|"+loggedUser;
        //show all users?? - table view?
        //let user select a user to remove
        //send request to remove user
        //return ack - success or failure
    }
    public void viewLog(){
        m_general.clearMainView(mainView1);
        //send request to get log
        //show log? open in a new window?
        //maybe in table view?
    }
    public void responseToComplaint(){
        m_general.clearMainView(mainView1);
        //show all active complaints
        //let user select a complaint to response to
        //create text area for the user to write his response
        //send response to server
    }


}
