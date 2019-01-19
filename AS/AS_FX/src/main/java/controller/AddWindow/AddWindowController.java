package controller.AddWindow;

import controller.Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import models.Cattle;

public class AddWindowController implements Initializable{

    private int chosen = 0;
    private Cattle chosenCattle;
    
    @FXML
    private ScrollPane addWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    
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
        
        if(chosenCattle!=null){
            AddInseminationController aic = loader.getController();
            aic.setChosenCattle(chosenCattle);
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
        
        if(chosenCattle!=null){
            AddCalvingController acc = loader.getController();
            //acc.setMc();
        }

        addWindow.setContent(addCalving);
    }

    public int getChosen() {
        return chosen;
    }

    public void setChosen(int chosen) {
        this.chosen = chosen;
        switch(this.chosen){
            case 0:
                addCowshedActionListener(null);
                break;
            case 1:
                addGroupActionListener(null);
                break;
            case 2:
                addCattleActionListener(null);
                break;
            case 3:
                addInseminationActionListener(null);
                break;
            case 4:
                addCalvingActionListener(null);
                break;
        }
    }

    public Cattle getChosenCattle() {
        return chosenCattle;
    }

    public void setChosenCattle(Cattle chosenCattle) {
        this.chosenCattle = chosenCattle;
    }
}

