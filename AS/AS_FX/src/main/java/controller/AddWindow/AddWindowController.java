package controller.AddWindow;

import controller.Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddWindowController {

    @FXML
    private ScrollPane addWindow;

    @FXML
    void addCowshedActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddCowshed.fxml"));

        GridPane addCowshed = null;

        try {
            addCowshed = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindow.setContent(addCowshed);
    }

    @FXML
    void addGroupActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddGroup.fxml"));

        GridPane addGroup = null;

        try {
            addGroup = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindow.setContent(addGroup);
    }

    @FXML
    void addCattleActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddCattle.fxml"));

        GridPane addCattle = null;

        try {
            addCattle = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindow.setContent(addCattle);
    }

    @FXML
    void addInseminationActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddInsemination.fxml"));

        GridPane addInsemination = null;

        try {
            addInsemination = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindow.setContent(addInsemination);
    }

    @FXML
    void addCalvingActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddCalving.fxml"));

        GridPane addCalving = null;

        try {
            addCalving = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindow.setContent(addCalving);
    }

}

