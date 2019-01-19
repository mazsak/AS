package controller.AddWindow;

import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Cowshed;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddGroupController implements Initializable {
    
    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> cowshed;

    @FXML
    private ComboBox<String> type;

    @FXML
    void addGroupActionListener(ActionEvent event) {
        if(!name.getText().isEmpty() && !cowshed.getValue().isEmpty() && !type.getValue().isEmpty()){
            String typeString;
            if(type.getValue().equals("Żywieniowe"))
                typeString = "EAT";
            else
                typeString = "SICK";
            HTeam.save(typeString, name.getText(), HCowshed.findByName(cowshed.getValue()));
            name.clear();
            type.getSelectionModel().clearSelection();
            cowshed.getSelectionModel().clearSelection();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("Żywieniowe", "Leczone");

        List<Cowshed> csh = HCowshed.read();

        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }

        cowshed.setItems(cowsheds);
    }
}
