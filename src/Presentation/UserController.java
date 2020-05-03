package Presentation;

import Domain.User;
import Service.UserSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UserController {


    @FXML private MenuBar mb_mainMenu1;
    private UserSystem userSystem = new UserSystem();
    private User loggedUser;




    public void searchButtonPushed(ActionEvent actionEvent){
        //open dialog with user?
        userSystem.search("");
        //show somehow
    }

    public void viewInfoButtonPushed(ActionEvent actionEvent){
        //open dialog with user?
        //ask what information to show
    }

    public void viewSearchHistoryButtonPushed(ActionEvent actionEvent){
        userSystem.viewSearchHistory(loggedUser);
    }

    public void logoutButtonPushed(ActionEvent actionEvent) {
        userSystem.logOut();
        loggedUser = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GuestView.fxml"));
            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.loggedUser = user;
    }

    public void buildPresentation(List<String> roles) {
        for(String role : roles){
            switch(role){
                case("Fan"):{
                    Menu fanMenu = new Menu("Fan Actions");
                    MenuItem followPage = new MenuItem("followPage");
                    fanMenu.getItems().add(followPage);
                    MenuItem editPersonalInfo = new MenuItem("editPersonalInfo");
                    fanMenu.getItems().add(editPersonalInfo);
                    MenuItem followGames = new MenuItem("followGames");
                    fanMenu.getItems().add(followGames);
                    MenuItem submitComplaint = new MenuItem("submitComplaint");
                    fanMenu.getItems().add(submitComplaint);
                    mb_mainMenu1.getMenus().add(fanMenu);
                    break;
                }
                case("Admin"):{
                    Menu adminMenu = new Menu("Admin Actions");
                    MenuItem closeTeamPermanently = new MenuItem("closeTeamPermanently");
                    adminMenu.getItems().add(closeTeamPermanently);
                    MenuItem addNewPlayer = new MenuItem("addNewPlayer");
                    adminMenu.getItems().add(addNewPlayer);
                    MenuItem addNewCoach = new MenuItem("addNewCoach");
                    adminMenu.getItems().add(addNewCoach);
                    MenuItem addNewTeamOwner = new MenuItem("addNewTeamOwner");
                    adminMenu.getItems().add(addNewTeamOwner);
                    MenuItem addNewTeamManager = new MenuItem("addNewTeamManager");
                    adminMenu.getItems().add(addNewTeamManager);
                    MenuItem addNewUnionRepresentative = new MenuItem("addNewUnionRepresentative");
                    adminMenu.getItems().add(addNewUnionRepresentative);
                    MenuItem addNewAdmin = new MenuItem("addNewAdmin");
                    adminMenu.getItems().add(addNewAdmin);
                    MenuItem removeUser = new MenuItem("removeUser");
                    adminMenu.getItems().add(removeUser);
                    MenuItem viewLog = new MenuItem("viewLog");
                    adminMenu.getItems().add(viewLog);
                    MenuItem responseToComplaint = new MenuItem("responseToComplaint");
                    adminMenu.getItems().add(responseToComplaint);
                    mb_mainMenu1.getMenus().add(adminMenu);
                    break;
                }
                //case("Coach"):{
                //    Menu coachMenu = new Menu("Coach Actions");
                //    break;
                //}
                //case("Player"):{
                //    break;
                //}
                case("TeamOwner"):{
                    Menu ownerMenu = new Menu("Ownership Actions");
                    MenuItem addTeam = new MenuItem("addTeam");
                    ownerMenu.getItems().add(addTeam);
                    MenuItem getTeamById = new MenuItem("getTeamById");
                    ownerMenu.getItems().add(getTeamById);
                    MenuItem removeTeam = new MenuItem("removeTeam");
                    ownerMenu.getItems().add(removeTeam);
                    MenuItem appointTeamOwner = new MenuItem("appointTeamOwner");
                    ownerMenu.getItems().add(appointTeamOwner);
                    MenuItem appointTeamManager = new MenuItem("appointTeamManager");
                    ownerMenu.getItems().add(appointTeamManager);
                    MenuItem closeTeam = new MenuItem("closeTeam");
                    ownerMenu.getItems().add(closeTeam);
                    MenuItem reopenTeam = new MenuItem("reopenTeam");
                    ownerMenu.getItems().add(reopenTeam);
                    mb_mainMenu1.getMenus().add(ownerMenu);
                    //manage:
                    addManagement();
                    break;
                }
                case("TeamManager"):{
                    addManagement();
                    break;
                }
                case("UnionRepresentative"):{
                    Menu unionMenu = new Menu("Union Actions");
                    MenuItem configureNewLeague = new MenuItem("configureNewLeague");
                    unionMenu.getItems().add(configureNewLeague);
                    MenuItem configureNewSeason = new MenuItem("configureNewSeason");
                    unionMenu.getItems().add(configureNewSeason);
                    MenuItem configureLeagueInSeason = new MenuItem("configureLeagueInSeason");
                    unionMenu.getItems().add(configureLeagueInSeason);
                    MenuItem assignGames = new MenuItem("assignGames");
                    unionMenu.getItems().add(assignGames);
                    MenuItem appointReferee = new MenuItem("appointReferee");
                    unionMenu.getItems().add(appointReferee);
                    MenuItem addRefereeToLeague = new MenuItem("addRefereeToLeague");
                    unionMenu.getItems().add(addRefereeToLeague);
                    MenuItem changeScorePolicy = new MenuItem("changeScorePolicy");
                    unionMenu.getItems().add(changeScorePolicy);
                    MenuItem changeAssignmentPolicy = new MenuItem("changeAssignmentPolicy");
                    unionMenu.getItems().add(changeAssignmentPolicy);
                    MenuItem addTUTUPaymentToTeam = new MenuItem("addTUTUPaymentToTeam");
                    unionMenu.getItems().add(addTUTUPaymentToTeam);
                    MenuItem addPaymentsFromTheTUTU = new MenuItem("addPaymentsFromTheTUTU");
                    unionMenu.getItems().add(addPaymentsFromTheTUTU);
                    MenuItem addTeamToLeague = new MenuItem("addTeamToLeague");
                    unionMenu.getItems().add(addTeamToLeague);
                    MenuItem calculateLeagueScore = new MenuItem("calculateLeagueScore");
                    unionMenu.getItems().add(calculateLeagueScore);
                    MenuItem calculateGameScore = new MenuItem("calculateGameScore");
                    unionMenu.getItems().add(calculateGameScore);
                    MenuItem changeRegistrationFee = new MenuItem("changeRegistrationFee");
                    unionMenu.getItems().add(changeRegistrationFee);
                    mb_mainMenu1.getMenus().add(unionMenu);
                    break;
                }
                case("Referee"):{
                    Menu refMenu = new Menu("Referee Actions");
                    MenuItem addEventToGame = new MenuItem("addEventToGame");
                    refMenu.getItems().add(addEventToGame);
                    MenuItem setScoreInGame = new MenuItem("setScoreInGame");
                    refMenu.getItems().add(setScoreInGame);
                    MenuItem getEventReport = new MenuItem("getEventReport");
                    refMenu.getItems().add(getEventReport);
                    MenuItem changeEvent = new MenuItem("changeEvent");
                    refMenu.getItems().add(changeEvent);
                    mb_mainMenu1.getMenus().add(refMenu);
                    break;
                }
                case("HasPage"):{
                    Menu pageMenu = new Menu("Page Management");
                    MenuItem viewPage = new MenuItem("viewPage");
                    pageMenu.getItems().add(viewPage);
                    MenuItem uploadToPage = new MenuItem("uploadToPage");
                    pageMenu.getItems().add(uploadToPage);
                    mb_mainMenu1.getMenus().add(pageMenu);
                    break;
                }

            }
        }
    }

    private void addManagement() {
        Menu manageMenu = new Menu("Management Actions");
        MenuItem addPlayerToTeam = new MenuItem("addPlayerToTeam");
        manageMenu.getItems().add(addPlayerToTeam);
        MenuItem addCoachToTeam = new MenuItem("addCoachToTeam");
        manageMenu.getItems().add(addCoachToTeam);
        MenuItem addFieldToTeam = new MenuItem("addFieldToTeam");
        manageMenu.getItems().add(addFieldToTeam);
        MenuItem updateAsset = new MenuItem("updateAsset");
        manageMenu.getItems().add(updateAsset);
        MenuItem reportIncome = new MenuItem("reportIncome");
        manageMenu.getItems().add(reportIncome);
        MenuItem reportExpanse = new MenuItem("reportExpanse");
        manageMenu.getItems().add(reportExpanse);
        MenuItem getBalance = new MenuItem("getBalance");
        manageMenu.getItems().add(getBalance);
        mb_mainMenu1.getMenus().add(manageMenu);
    }

}
