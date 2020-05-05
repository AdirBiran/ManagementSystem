package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GeneralController {

    public void setSceneByFXMLPath(String path, List<String> roles, String loggedUser, Client m_client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();

            if(path.equals("UserView.fxml")){
                ((UserController)loader.getController()).buildPresentation(roles);
                ((UserController)loader.getController()).setUser(loggedUser);
                ((UserController)loader.getController()).setClient(m_client);
            }

            Scene scene = new Scene(root);
            Main.getStage().setScene(scene);
            Main.getStage().show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clearMainView(Pane view)
    {
        view.getChildren().clear();
    }

    public void showListOnScreen(List<String> list, GridPane gridPane, int startIndex) {

        for(String string: list){
            Label label = new Label(string);
            gridPane.add(label,0,startIndex);
            startIndex++;
        }

    }

    public void showAlert(String s, Alert.AlertType type) {
        Alert alert = new Alert(type,s);
        alert.show();
    }

    public void buildViewInfoScene(GridPane l_viewPane, HBox mainView, Client client) {
        Label label = new Label("Please select subject:");
        label.setAlignment(Pos.CENTER_LEFT);
        l_viewPane.add(label, 0,0);
        List<String> values = new LinkedList<>();
        values.add("Teams");
        values.add("Players");
        values.add("Coaches");
        values.add("Leagues");
        values.add("Seasons");
        values.add("Referees");
        ObservableList obList = FXCollections.observableList(values);
        ChoiceBox subjects = new ChoiceBox(obList);
        subjects.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearGridPaneRows(l_viewPane,2);
                String choice = (String)subjects.getValue();
                showInfo(choice, client, l_viewPane);
            }
        });
        l_viewPane.add(subjects, 0,1);
        l_viewPane.setAlignment(Pos.TOP_CENTER);
        mainView.getChildren().add(l_viewPane);
    }

    /**
     * check this function!!!!!!!
     * @param gridPane
     * @param startRowIndex
     */
    private void clearGridPaneRows(GridPane gridPane, int startRowIndex) {
        RowConstraints constraints;
        do{
            constraints = gridPane.getRowConstraints().remove(startRowIndex);
        }while (constraints!=null);

    }

    private void showInfo(String choice, Client m_client, GridPane l_viewPane) {
        switch(choice){
            case("Teams"):{
                List<String> teams = m_client.sendToServer("viewInformationAboutTeams");
                showListOnScreen(teams, l_viewPane, 2);
                break;
            }
            case("Players"):{
                List<String> players = m_client.sendToServer("viewInformationAboutPlayers");
                showListOnScreen(players, l_viewPane, 2);
                break;
            }
            case("Coaches"):{
                List<String> coaches = m_client.sendToServer("viewInformationAboutCoaches");
                showListOnScreen(coaches, l_viewPane, 2);
                break;
            }
            case("Leagues"):{
                List<String> leagues = m_client.sendToServer("viewInformationAboutLeagues");
                showListOnScreen(leagues, l_viewPane, 2);
                break;
            }
            case("Seasons"):{
                List<String> seasons = m_client.sendToServer("viewInformationAboutSeasons");
                showListOnScreen(seasons, l_viewPane, 2);
                break;
            }
            case("Referees"):{
                List<String> referees = m_client.sendToServer("viewInformationAboutReferees");
                showListOnScreen(referees, l_viewPane, 2);
                break;
            }
        }
    }

    public void buildSearchView(GridPane l_searchPane, HBox mainView, Client m_client, String userId) {
        TextField searchArea = new TextField();
        l_searchPane.add(searchArea, 3, 0);
        Button b_search = new Button("Search");
        b_search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //do we want spelling correction?
                clearGridPaneRows(l_searchPane,3);
                List<String> results = m_client.sendToServer("search"+"|"+userId+"|"+searchArea.getText());
                showListOnScreen(results, l_searchPane, 3);
            }
        });

        l_searchPane.add(b_search,3,1);
        l_searchPane.setAlignment(Pos.TOP_CENTER);
        mainView.getChildren().add(l_searchPane);
    }
}
