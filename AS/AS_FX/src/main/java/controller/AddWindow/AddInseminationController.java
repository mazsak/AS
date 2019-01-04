package controller.AddWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AddInseminationController implements Initializable {

    @FXML
    private ComboBox<?> result;

    @FXML
    private TextArea note;

    @FXML
    private DatePicker inseminationDate;

    @FXML
    private ComboBox<?> cattle;

    @FXML
    private ComboBox<?> bull;

    @FXML
    void addInseminationActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

