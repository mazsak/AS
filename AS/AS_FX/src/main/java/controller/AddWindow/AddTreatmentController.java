package controller.AddWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTreatmentController implements Initializable {

    @FXML
    private ComboBox<?> cowshed;

    @FXML
    private ComboBox<?> group;

    @FXML
    private ComboBox<?> cattle;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField disease;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextArea note;

    @FXML
    private ComboBox<?> medicine;

    @FXML
    private ListView<?> listMedicine;

    @FXML
    void addMedicineActionListener(ActionEvent event) {

    }

    @FXML
    void addTreatmentActionListener(ActionEvent event) {

    }

    @FXML
    void cowshedCheckedActionListener(ActionEvent event) {

    }

    @FXML
    void groupCheckedActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
