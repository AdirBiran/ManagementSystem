package Presentation;

import Data.Database;
import Domain.Team;
import Domain.UserFactory;
import Service.FootballManagementSystem;
import Service.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final String SYSTEM_NAME = "SystemName";

    private static Stage stage;

    public static Stage getStage(){return stage;}

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("GuestView.fxml"));
        primaryStage.setTitle(SYSTEM_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Server server  = new Server(7567, 10);
        server.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FootballManagementSystem.systemInit(true);
        FootballManagementSystem.dataReboot();
        UserFactory.getNewAdmin("aA123456", "adminush", "ush", "m@m.com");
        launch(args);
    }
}