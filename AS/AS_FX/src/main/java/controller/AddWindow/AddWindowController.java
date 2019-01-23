package controller.AddWindow;

import controller.Main.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import models.Cattle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        showWindow(loader);
    }

    private void showWindow(FXMLLoader loader) {
        GridPane grid = null;
        try {
            grid = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindow.setContent(grid);
    }

    @FXML
    void addGroupActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddGroup.fxml"));
        showWindow(loader);
    }

    @FXML
    void addCattleActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddCattle.fxml"));
        showWindow(loader);
    }

    @FXML
    void addInseminationActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddInsemination.fxml"));
        showWindow(loader);
    }

    @FXML
    void addCalvingActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddCalving.fxml"));
        showWindow(loader);
    }

    public int getChosen() {
        return chosen;
    }

    @FXML
    void addStatsActionListener(ActionEvent event) {

    }

    @FXML
    void addTreatmentActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddTreatment.fxml"));
        showWindow(loader);
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
            case 5:
                addTreatmentActionListener(null);
                break;
            case 6:
                addStatsActionListener(null);
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

