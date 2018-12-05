package controller.ShowWindow;

import controller.Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ListWindowController implements Initializable {

    private MainController mc;

    @FXML
    private BorderPane rootListWindow;

    @FXML
    private ListView<?> listCattles;

    @FXML
    private ListView<?> listCowshed;

    @FXML
    private ListView<?> listTeam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void listCattlesActionListener(ActionEvent event) {

    }

    @FXML
    void cowshedActionListener(ActionEvent event) {

    }

    @FXML
    void teamActionListener(ActionEvent event) {

    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

}