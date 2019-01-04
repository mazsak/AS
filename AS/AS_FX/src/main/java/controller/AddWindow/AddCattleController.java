package controller.AddWindow;

import hibernate.FactoryHibernate;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCattleController implements Initializable {
    private EntityManager em;

    @FXML
    private TextField earring;

    @FXML
    private TextArea note;

    @FXML
    private DatePicker joinDate;

    @FXML
    private TextField leaveReason;

    @FXML
    private ComboBox<String> sex;

    @FXML
    private TextField name;

    @FXML
    private TextField numberCowshed;

    @FXML
    private DatePicker leaveDate;

    @FXML
    private ComboBox<String> cowshed;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<String> group;

    @FXML
    void addCattleActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        em = FactoryHibernate.getEm();
        sex.getItems().addAll("XX", "XY");

        List<Cowshed> csh = HCowshed.read(em);

        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }

        cowshed.setItems(cowsheds);
    }

    @FXML
    public void checkedActionListener(ActionEvent event) {
        List<Team> tm = HTeam.getByCowshedName(em, cowshed.getSelectionModel().getSelectedItem());
        ObservableList<String> groups = FXCollections.observableArrayList();
        for (int i = 0; i < tm.size(); i++) {
            groups.add(tm.get(i).getName());
        }
        group.setItems(groups);
    }
}

