package Presentation;

import Domain.User;
import Service.UserSystem;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UserController {



    @FXML private HBox mainView1;
    @FXML private MenuBar mb_mainMenu1;

    //private UserSystem userSystem = new UserSystem();
    private Client m_client;

    private String loggedUser;

    private AdminController admin;
    private FanController fan;
    private HasPageController page;
    private ManagementController management;
    private OwnershipController ownership;
    private RefereeController referee;
    private UnionController union;


    public void searchButtonPushed(ActionEvent actionEvent){
        //find a way to reuse code in guest controller
    }

    public void viewInfoButtonPushed(ActionEvent actionEvent){
        //find a way to reuse code in guest controller
    }

    public void viewSearchHistoryButtonPushed(ActionEvent actionEvent){

        List<String> history = m_client.sendToServer("viewSearchHistory|"+loggedUser);
        //show list - find a way to reuse code in guest controller
    }

    public void logoutButtonPushed(ActionEvent actionEvent) {
        m_client.sendToServer("logOut|"+loggedUser);
        loggedUser = "";
        try {
            Parent root = FXMLLoader.load(getClass().getResource("GuestView.fxml"));
            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String user){
        this.loggedUser = user;
    }

    public void setClient(Client client) {
        this.m_client = client;
    }

    public void buildPresentation(List<String> roles) {
        for(String role : roles){
            switch(role){
                case("Fan"):{
                    fan = new FanController(mainView1, loggedUser, m_client);
                    Menu fanMenu = new Menu("Fan Actions");
                    MenuItem followPage = new MenuItem("followPage");
                    followPage.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            fan.followPage();
                        }
                    });
                    fanMenu.getItems().add(followPage);
                    MenuItem followGames = new MenuItem("followGames");
                    followGames.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            fan.followGames();
                        }
                    });
                    fanMenu.getItems().add(followGames);
                    MenuItem editPersonalInfo = new MenuItem("editPersonalInfo");
                    editPersonalInfo.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            fan.editPersonalInfo();
                        }
                    });
                    fanMenu.getItems().add(editPersonalInfo);
                    MenuItem submitComplaint = new MenuItem("submitComplaint");
                    submitComplaint.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            fan.submitComplaint();
                        }
                    });
                    fanMenu.getItems().add(submitComplaint);
                    mb_mainMenu1.getMenus().add(fanMenu);
                    break;
                }
                case("Admin"):{
                    admin = new AdminController(mainView1, loggedUser, m_client);
                    Menu adminMenu = new Menu("Admin Actions");
                    MenuItem closeTeamPermanently = new MenuItem("closeTeamPermanently");
                    closeTeamPermanently.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.closeTeamPermanently();
                        }
                    });
                    adminMenu.getItems().add(closeTeamPermanently);
                    MenuItem addNewPlayer = new MenuItem("addNewPlayer");
                    addNewPlayer.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.addNewPlayer();
                        }
                    });
                    adminMenu.getItems().add(addNewPlayer);
                    MenuItem addNewCoach = new MenuItem("addNewCoach");
                    addNewCoach.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.addNewCoach();
                        }
                    });
                    adminMenu.getItems().add(addNewCoach);
                    MenuItem addNewTeamOwner = new MenuItem("addNewTeamOwner");
                    addNewTeamOwner.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.addNewTeamOwner();
                        }
                    });
                    adminMenu.getItems().add(addNewTeamOwner);
                    MenuItem addNewTeamManager = new MenuItem("addNewTeamManager");
                    addNewTeamManager.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.addNewTeamManager();
                        }
                    });
                    adminMenu.getItems().add(addNewTeamManager);
                    MenuItem addNewUnionRepresentative = new MenuItem("addNewUnionRepresentative");
                    addNewUnionRepresentative.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.addNewUnionRepresentative();
                        }
                    });
                    adminMenu.getItems().add(addNewUnionRepresentative);
                    MenuItem addNewAdmin = new MenuItem("addNewAdmin");
                    addNewAdmin.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.addNewAdmin();
                        }
                    });
                    adminMenu.getItems().add(addNewAdmin);
                    MenuItem removeUser = new MenuItem("removeUser");
                    removeUser.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.removeUser();
                        }
                    });
                    adminMenu.getItems().add(removeUser);
                    MenuItem viewLog = new MenuItem("viewLog");
                    viewLog.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.viewLog();
                        }
                    });
                    adminMenu.getItems().add(viewLog);
                    MenuItem responseToComplaint = new MenuItem("responseToComplaint");
                    responseToComplaint.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            admin.responseToComplaint();
                        }
                    });
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
                    ownership = new OwnershipController(mainView1, loggedUser, m_client);
                    Menu ownerMenu = new Menu("Ownership Actions");
                    MenuItem addTeam = new MenuItem("addTeam");
                    addTeam.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.addTeam();
                        }
                    });
                    ownerMenu.getItems().add(addTeam);
                    MenuItem getTeamById = new MenuItem("getTeamById");
                    getTeamById.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.getTeamById();
                        }
                    });
                    ownerMenu.getItems().add(getTeamById);
                    MenuItem removeTeam = new MenuItem("removeTeam");
                    removeTeam.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.removeTeam();
                        }
                    });
                    ownerMenu.getItems().add(removeTeam);
                    MenuItem appointTeamOwner = new MenuItem("appointTeamOwner");
                    appointTeamOwner.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.appointTeamOwner();
                        }
                    });
                    ownerMenu.getItems().add(appointTeamOwner);
                    MenuItem appointTeamManager = new MenuItem("appointTeamManager");
                    appointTeamManager.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.appointTeamManager();
                        }
                    });
                    ownerMenu.getItems().add(appointTeamManager);
                    MenuItem closeTeam = new MenuItem("closeTeam");
                    closeTeam.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.closeTeam();
                        }
                    });
                    ownerMenu.getItems().add(closeTeam);
                    MenuItem reopenTeam = new MenuItem("reopenTeam");
                    reopenTeam.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ownership.reopenTeam();
                        }
                    });
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
                    union = new UnionController(mainView1, loggedUser, m_client);
                    Menu unionMenu = new Menu("Union Actions");
                    MenuItem configureNewLeague = new MenuItem("configureNewLeague");
                    configureNewLeague.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.configureNewLeague();
                        }
                    });
                    unionMenu.getItems().add(configureNewLeague);
                    MenuItem configureNewSeason = new MenuItem("configureNewSeason");
                    configureNewSeason.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.configureNewSeason();
                        }
                    });
                    unionMenu.getItems().add(configureNewSeason);
                    MenuItem configureLeagueInSeason = new MenuItem("configureLeagueInSeason");
                    configureLeagueInSeason.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.configureLeagueInSeason();
                        }
                    });
                    MenuItem addTeamToLeague = new MenuItem("addTeamToLeague");
                    addTeamToLeague.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.addTeamToLeague();
                        }
                    });
                    unionMenu.getItems().add(addTeamToLeague);
                    unionMenu.getItems().add(configureLeagueInSeason);
                    MenuItem assignGames = new MenuItem("assignGames");
                    assignGames.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.assignGames();
                        }
                    });
                    unionMenu.getItems().add(assignGames);
                    MenuItem appointReferee = new MenuItem("appointReferee");
                    appointReferee.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.appointReferee();
                        }
                    });
                    unionMenu.getItems().add(appointReferee);
                    MenuItem addRefereeToLeague = new MenuItem("addRefereeToLeague");
                    addRefereeToLeague.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.addRefereeToLeague();
                        }
                    });
                    unionMenu.getItems().add(addRefereeToLeague);
                    MenuItem changeScorePolicy = new MenuItem("changeScorePolicy");
                    changeScorePolicy.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.changeScorePolicy();
                        }
                    });
                    unionMenu.getItems().add(changeScorePolicy);
                    MenuItem changeAssignmentPolicy = new MenuItem("changeAssignmentPolicy");
                    changeAssignmentPolicy.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.changeAssignmentPolicy();
                        }
                    });
                    unionMenu.getItems().add(changeAssignmentPolicy);
                    MenuItem addTUTUPaymentToTeam = new MenuItem("addTUTUPaymentToTeam");
                    addTUTUPaymentToTeam.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.addTUTUPaymentToTeam();
                        }
                    });
                    unionMenu.getItems().add(addTUTUPaymentToTeam);
                    MenuItem addPaymentsFromTheTUTU = new MenuItem("addPaymentsFromTheTUTU");
                    addPaymentsFromTheTUTU.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.addPaymentsFromTheTUTU();
                        }
                    });
                    unionMenu.getItems().add(addPaymentsFromTheTUTU);
                    MenuItem calculateLeagueScore = new MenuItem("calculateLeagueScore");
                    calculateLeagueScore.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.calculateLeagueScore();
                        }
                    });
                    unionMenu.getItems().add(calculateLeagueScore);
                    MenuItem calculateGameScore = new MenuItem("calculateGameScore");
                    calculateGameScore.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.calculateGameScore();
                        }
                    });
                    unionMenu.getItems().add(calculateGameScore);
                    MenuItem changeRegistrationFee = new MenuItem("changeRegistrationFee");
                    changeRegistrationFee.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            union.changeRegistrationFee();
                        }
                    });
                    unionMenu.getItems().add(changeRegistrationFee);
                    mb_mainMenu1.getMenus().add(unionMenu);
                    break;
                }
                case("Referee"):{
                    referee = new RefereeController(mainView1, loggedUser, m_client);
                    Menu refMenu = new Menu("Referee Actions");
                    MenuItem addEventToGame = new MenuItem("addEventToGame");
                    addEventToGame.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            referee.addEventToGame();
                        }
                    });
                    refMenu.getItems().add(addEventToGame);
                    MenuItem setScoreInGame = new MenuItem("setScoreInGame");
                    setScoreInGame.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            referee.setScoreInGame();
                        }
                    });
                    refMenu.getItems().add(setScoreInGame);
                    MenuItem getEventReport = new MenuItem("getEventReport");
                    getEventReport.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            referee.getEventReport();
                        }
                    });
                    refMenu.getItems().add(getEventReport);
                    MenuItem changeEvent = new MenuItem("changeEvent");
                    changeEvent.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            referee.changeEvent();
                        }
                    });
                    refMenu.getItems().add(changeEvent);
                    mb_mainMenu1.getMenus().add(refMenu);
                    break;
                }
                case("HasPage"):{
                    page = new HasPageController(mainView1, loggedUser, m_client);
                    Menu pageMenu = new Menu("Page Management");
                    MenuItem viewPage = new MenuItem("viewPage");
                    viewPage.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            page.viewPage();
                        }
                    });
                    pageMenu.getItems().add(viewPage);
                    MenuItem uploadToPage = new MenuItem("uploadToPage");
                    uploadToPage.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            page.uploadToPage();
                        }
                    });
                    pageMenu.getItems().add(uploadToPage);
                    mb_mainMenu1.getMenus().add(pageMenu);
                    break;
                }

            }
        }
    }

    private void addManagement() {
        management = new ManagementController(mainView1, loggedUser, m_client);
        Menu manageMenu = new Menu("Management Actions");
        MenuItem addPlayerToTeam = new MenuItem("addPlayerToTeam");
        addPlayerToTeam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.addPlayerToTeam();
            }
        });
        manageMenu.getItems().add(addPlayerToTeam);
        MenuItem addCoachToTeam = new MenuItem("addCoachToTeam");
        addCoachToTeam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.addCoachToTeam();
            }
        });
        manageMenu.getItems().add(addCoachToTeam);
        MenuItem addFieldToTeam = new MenuItem("addFieldToTeam");
        addFieldToTeam.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.addFieldToTeam();
            }
        });
        manageMenu.getItems().add(addFieldToTeam);
        MenuItem updateAsset = new MenuItem("updateAsset");
        updateAsset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.updateAsset();
            }
        });
        manageMenu.getItems().add(updateAsset);
        MenuItem reportIncome = new MenuItem("reportIncome");
        reportIncome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.reportIncome();
            }
        });
        manageMenu.getItems().add(reportIncome);
        MenuItem reportExpanse = new MenuItem("reportExpanse");
        reportExpanse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.reportExpanse();
            }
        });
        manageMenu.getItems().add(reportExpanse);
        MenuItem getBalance = new MenuItem("getBalance");
        getBalance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                management.getBalance();
            }
        });
        manageMenu.getItems().add(getBalance);
        mb_mainMenu1.getMenus().add(manageMenu);
    }

}
