package Presentation;

import Data.DataAccess;
import Data.Database;
import Domain.Referee;
import Domain.Team;
import Domain.User;
import Domain.UserFactory;
import Service.FootballManagementSystem;
import Service.Server;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {


    final static String SYSTEM_NAME = "Football Five";
    private static Server server  = new Server(7567, 10);

    private static Stage stage;

    public static Stage getStage(){return stage;}

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Opener.fxml"));

        primaryStage.setTitle(SYSTEM_NAME);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(650);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        server.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //FootballManagementSystem.systemInit(true);
        //FootballManagementSystem.dataReboot();
        //UserFactory.getNewAdmin("aA123456", "adminush", "ush", "m@m.com");
        launch(args);
    }
}