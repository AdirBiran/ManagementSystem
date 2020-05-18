package Presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.List;

public class HasPageController {


    private HBox mainView1;
    private String loggedUser;
    private Client client;
    private GeneralController m_general = new GeneralController();

    public HasPageController(HBox mainView1, String loggedUser, Client client) {
        this.mainView1 = mainView1;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public void viewPage(){
        m_general.clearMainView(mainView1);
        List<String> page = client.sendToServer("viewPage|"+loggedUser);
        Label l_page = new Label(page.get(0));
        mainView1.getChildren().add(l_page);

    }
    public void uploadToPage(){
        m_general.clearMainView(mainView1);
        GridPane pane = new GridPane();
        Label l_upload = new Label("Please enter text to upload:");
        pane.add(l_upload, 0, 0);
        TextArea uploadArea = new TextArea();
        pane.add(uploadArea, 0, 1);
        Button uploadBtn = new Button("Upload");
        uploadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String upload = uploadArea.getText();
                if(Checker.isValid(upload)){
                    List<String> receive = client.sendToServer("uploadToPage|"+loggedUser+"|"+upload);
                    m_general.showAlert(receive.get(0), Alert.AlertType.INFORMATION);
                }

            }
        });
        pane.add(uploadBtn, 0, 2);
        mainView1.getChildren().add(pane);
    }
}
