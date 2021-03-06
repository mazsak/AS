package controller.AddWindow;

import hibernate.HCowshed;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCowshedController implements Initializable {

    @FXML
    private TextArea note;

    @FXML
    private TextField address;

    @FXML
    private TextField name;

    @FXML
    void addCowshedActionListener(ActionEvent event) {
        if(!name.getText().isEmpty()){
            HCowshed.save(address.getText(), note.getText(), name.getText());
            name.clear();
            address.clear();
            note.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
