package Presentation;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminController {

    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GridPane mainPain;
    private GeneralController m_general = new GeneralController();

    public AdminController(HBox mainView1, String loggedUser, Client m_client, GridPane mainPain) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = m_client;
        this.mainPain = mainPain;
    }


    public void closeTeamPermanently(){
        m_general.clearMainView(mainView1);
        m_general.clearMainView(mainPain);
        //show all teams with selection option maybe choicebox
        String request = "getAllOpenTeams_Admin|"+loggedUser;

        //let user select a team to close
        //sent request to close team
        //return ack - success or failure
    }
    public void addNewPlayer(){
        m_general.clearMainView(mainPain);
        //show registration form for player
        m_general.buildForm("player", mainView1, client,loggedUser, mainPain);

    }

    public void addNewCoach(){
        m_general.clearMainView(mainPain);
        //show registration form for coach
        m_general.buildForm("coach", mainView1, client,loggedUser, mainPain);

    }
    public void addNewTeamOwner(){
        m_general.clearMainView(mainPain);
        //show registration form for teamOwner
        m_general.buildForm("teamOwner", mainView1, client,loggedUser, mainPain);

    }
    public void addNewTeamManager(){
        m_general.clearMainView(mainPain);
        //show registration form for teamManager
        m_general.buildForm("teamManager", mainView1, client,loggedUser, mainPain);
    }
    public void addNewUnionRepresentative(){
        m_general.clearMainView(mainPain);
        //show registration form for representative
        m_general.buildForm("representative", mainView1, client,loggedUser, mainPain);

    }
    public void addNewAdmin(){
        m_general.clearMainView(mainPain);
        //show registration form for admin
        m_general.buildForm("admin", mainView1, client,loggedUser, mainPain);
    }
    public void removeUser(){
        m_general.clearMainView(mainPain);
        m_general.clearMainView(mainView1);
        String request = "getAllUsers_Admin|"+loggedUser;
        //show all users?? - table view?
        //let user select a user to remove
        //send request to remove user
        //return ack - success or failure
    }
    public void viewLog(){
        m_general.clearMainView(mainView1);
        m_general.clearMainView(mainPain);
        //send request to get log
        //show log? open in a new window?
        //maybe in table view?
    }
    public void responseToComplaint(){
        m_general.clearMainView(mainView1);
        m_general.clearMainView(mainPain);
        //show all active complaints
        //let user select a complaint to response to
        //create text area for the user to write his response
        //send response to server
    }


}
