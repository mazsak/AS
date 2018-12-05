/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.ShowWindow.ListWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author root
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane MainWindow;

    public BorderPane getMainWindow() {
        return MainWindow;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ShowWindow/ListWindow.fxml"));

        BorderPane showWindow = null;

        try {
            showWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ListWindowController mac = loader.getController();

        mac.setMc(this);

        MainWindow.setCenter(showWindow);
    }    

    @FXML
    private void OnActionGrup(ActionEvent event) {
    }

    @FXML
    private void OnActionTreatment(ActionEvent event) {
    }

    @FXML
    private void OnActionList(ActionEvent event) {
    }

    @FXML
    private void OnActionAdd(ActionEvent event) {
    }
    
}
