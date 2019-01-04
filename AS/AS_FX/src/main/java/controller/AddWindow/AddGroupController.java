package controller.AddWindow;

import hibernate.FactoryHibernate;
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

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddGroupController implements Initializable {

    private EntityManager em;

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
            HTeam.save(em, typeString, name.getText(), HCowshed.findByName(em, cowshed.getValue()));
            name.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        em = FactoryHibernate.getEm();
        type.getItems().addAll("Żywieniowe", "Leczone");

        List<Cowshed> csh = HCowshed.read(em);

        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }

        cowshed.setItems(cowsheds);
    }
}
