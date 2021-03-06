/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Main;

import controller.ShowWindow.ListWindowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private void OnActionTreatment(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ShowWindow/ListWindowTreatment.fxml"));

        BorderPane showWindowTreatment = null;

        try {
            showWindowTreatment = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MainWindow.setCenter(showWindowTreatment);
    }

    @FXML
    private void OnActionList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/EditWindow/EditGroup.fxml"));

        ScrollPane editGroupWindow = null;

        try {
            editGroupWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MainWindow.setCenter(editGroupWindow);
    }

    @FXML
    private void OnActionAdd(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddWindow.fxml"));

        BorderPane addWindow = null;

        try {
            addWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MainWindow.setCenter(addWindow);
    }
    
}
