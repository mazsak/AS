package controller.AddWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCattleController implements Initializable {

    @FXML
    private TextField earring;

    @FXML
    private TextArea note;

    @FXML
    private DatePicker joinDate;

    @FXML
    private TextField leaveReason;

    @FXML
    private ComboBox<?> sex;

    @FXML
    private TextField name;

    @FXML
    private TextField numberCowshed;

    @FXML
    private DatePicker leaveDate;

    @FXML
    private ComboBox<?> cowshed;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<?> group;

    @FXML
    void addCattleActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

