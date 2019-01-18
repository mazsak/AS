package controller.ShowWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ListWindowTreatmentController implements Initializable {

    @FXML
    private ListView<?> listCowshed;

    @FXML
    private ListView<?> listTeam;

    @FXML
    private TableView<?> listCattles;

    @FXML
    private TableColumn<?, ?> number;

    @FXML
    private TableColumn<?, ?> earring;

    @FXML
    private TableColumn<?, ?> cowshed;

    @FXML
    private TableColumn<?, ?> cowshedNumber;

    @FXML
    private TableColumn<?, ?> team;

    @FXML
    private TableColumn<?, ?> birthDate;

    @FXML
    private TableColumn<?, ?> medicineGivenColumn;

    @FXML
    private DatePicker medicineGivenDate;

    @FXML
    private ComboBox<?> medicineGiven;

    @FXML
    void cowshedActionListener(MouseEvent event) {

    }

    @FXML
    void listCattlesActionListener(MouseEvent event) {

    }

    @FXML
    void medicineGivenActionListener(ActionEvent event) {

    }

    @FXML
    void medicineGivenDateActionListener(ActionEvent event) {

    }

    @FXML
    void teamActionListener(MouseEvent event) {

    }

    @FXML
    void saveChangeActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
