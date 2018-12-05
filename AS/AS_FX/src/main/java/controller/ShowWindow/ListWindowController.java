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
    private ListView<?> listCowshed;

    @FXML
    private ListView<?> listTeam;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO zaladownaie obor
    }

    @FXML
    void cowshedActionListener(ActionEvent event) {
        //TODO akcja nacisniecia obory
    }

    @FXML
    void teamActionListener(ActionEvent event) {
        //TODO akcja nacisniecia grupy
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

}