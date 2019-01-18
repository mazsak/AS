package controller.EditWindow;

import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import models.Cowshed;
import models.Team;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EditGroupController implements Initializable {

    private List<Team> groupsFirstList;
    private List<Team> groupsSecondList;

    @FXML
    private ComboBox<String> groupSecond;

    @FXML
    private ComboBox<String> groupFirst;

    @FXML
    private ComboBox<String> cowshedFirst;

    @FXML
    private ComboBox<String> cowshedSecond;

    @FXML
    private ComboBox<String> type;

    @FXML
    private ComboBox<String> nameMedicine;

    @FXML
    private DatePicker dateStartTreatment;

    @FXML
    void cowshedFirstActionListener(ActionEvent event) {
        groupsFirstList = HTeam.getByCowshedName(cowshedFirst.getSelectionModel().getSelectedItem());
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groupsFirstList.size(); i++) {
            groups2.add(groupsFirstList.get(i).getName());
        }
        groupFirst.setItems(groups2);
        if (cowshedSecond.getSelectionModel().isEmpty()) {
            cowshedSecond.getSelectionModel().select(cowshedFirst.getSelectionModel().getSelectedIndex());
            groupsSecondList.addAll(groupsFirstList);
            groupSecond.setItems(groups2);
        }
    }

    @FXML
    void cowshedSecondActionListener(ActionEvent event) {
        groupsSecondList = HTeam.getByCowshedName(cowshedSecond.getSelectionModel().getSelectedItem());
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groupsSecondList.size(); i++) {
            groups2.add(groupsSecondList.get(i).getName());
        }
        groupSecond.setItems(groups2);
        if (groupFirst.getSelectionModel().isEmpty()) {
            cowshedFirst.getSelectionModel().select(cowshedSecond.getSelectionModel().getSelectedIndex());
            groupsFirstList.addAll(groupsSecondList);
            groupFirst.setItems(groups2);
        }

    }

    @FXML
    void fromFirstToSecondActionListener(ActionEvent event) {

    }

    @FXML
    void fromSecondToFirstActionListener(ActionEvent event) {

    }

    @FXML
    void saveListener(ActionEvent event) {

    }

    @FXML
    void typeActionListener(ActionEvent event) {
        if (type.getSelectionModel().getSelectedItem().equals("Leczone")) {
            nameMedicine.setDisable(false);
            dateStartTreatment.setDisable(false);
        } else {
            nameMedicine.setDisable(true);
            dateStartTreatment.setDisable(true);
            nameMedicine.getSelectionModel().clearSelection();
            dateStartTreatment.setValue(LocalDate.now());
        }
    }

    @FXML
    void groupFirstActionListener(ActionEvent event) {
    }

    @FXML
    void groupSecondActionListener(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateStartTreatment.setValue(LocalDate.now());
        type.getItems().addAll("Żywieniowe", "Leczone");
        type.getSelectionModel().select(0);

        List<Cowshed> csh = HCowshed.read();
        ObservableList<String> cowsheds = FXCollections.observableArrayList();
        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }

        cowshedFirst.setItems(cowsheds);
        cowshedSecond.setItems(cowsheds);
    }
}
