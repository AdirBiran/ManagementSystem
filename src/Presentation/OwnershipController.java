package Presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.HashMap;
import java.util.List;

public class OwnershipController {

    private HBox mainView1;
    private String loggedUser;
    private Client m_client;
    private HashMap<String,String> teams; //<id , name>

    private GeneralController m_general = new GeneralController();


    public OwnershipController(HBox mainView1, String loggedUser, Client m_client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.m_client = m_client;
        initHashSet(m_client.sendToServer("getTeams|"+loggedUser));;
    }

    private void initHashSet(List<String> received) {
        this.teams = new HashMap<>();
        for(String team: received){
            String[] split = team.split(":");
            this.teams.put(split[0], split[1]);
        }
    }

    public void openNewTeam(){
        m_general.clearMainView(mainView1);
        //let user select at least 11 players
        //let user select coach/coaches
        //let User select field/fields
        //send request to



        String teamName="";
        FootballSpellChecker.addWord(teamName);


    }
    //public void getTeamById(){}
    public void removeTeam()
    {
        GridPane gridPane = new GridPane();
        Label label = new Label("Please select team to remove");
        ObservableList<String> list = FXCollections.observableArrayList(teams.values());
        ChoiceBox<String> choiceBox = new ChoiceBox<>(list);
        gridPane.add(label, 0,0);
        gridPane.add(choiceBox,0,1);
        Button addBtn = new Button("Remove");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String teamName = choiceBox.getValue();
                for(String id: teams.keySet()){
                    if(teamName.equals(teams.get(id))){
                        List<String> receive =  m_client.sendToServer("removeTeam|"+loggedUser+"|"+id);
                        m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                    }
                }
            }
        });

        mainView1.getChildren().add(gridPane);
    }


    public void appointTeamOwner(){
        //let user select user to appoint
        //send request to appoint user
    }
    public void appointTeamManager(){}
    public void closeTeam(){}
    public void reopenTeam(){}
}
