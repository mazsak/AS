package controller.EditWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import models.Team;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EditGroupController implements Initializable {

    private List<Team> groups;

    @FXML
    private ComboBox<String> groupSecond;

    @FXML
    private ComboBox<String> groupFirst;

    @FXML
    private ComboBox<?> cowshedFirst;

    @FXML
    private ComboBox<?> cowshedSecond;

    @FXML
    private ComboBox<String> type;

    @FXML
    private ComboBox<String> nameMedicine;

    @FXML
    private DatePicker dateStartTreatment;

    @FXML
    void cowshedFirstActionListener(ActionEvent event) {

    }

    @FXML
    void cowshedSecondActionListener(ActionEvent event) {

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
            nameMedicine.getItems().addAll("Żywieniowe", "Leczone");
            nameMedicine.setDisable(false);
            dateStartTreatment.setDisable(false);
        } else {
            nameMedicine.setDisable(true);
            dateStartTreatment.setDisable(true);
            nameMedicine.getSelectionModel().clearSelection();
            dateStartTreatment.setValue(LocalDate.now());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateStartTreatment.setValue(LocalDate.now());
        type.getItems().addAll("Żywieniowe", "Leczone");
    }
}
