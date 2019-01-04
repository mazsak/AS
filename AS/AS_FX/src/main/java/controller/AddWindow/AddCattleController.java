package controller.AddWindow;

import hibernate.FactoryHibernate;
import hibernate.HCattle;
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
import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCattleController implements Initializable {
    private EntityManager em;
    private List<Team> groups;

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
        em = FactoryHibernate.getEm();

        if(!name.getText().isEmpty()) {
            Cattle cattle = new Cattle();
            cattle.setName(name.getText());
            cattle.setEarring(earring.getText());
            cattle.setSex(sex.getValue());
            cattle.setCowshedNumber(Integer.valueOf(numberCowshed.getText()));
            cattle.setBirthDate(java.sql.Date.valueOf(birthDate.getValue()));
            cattle.setJoinDate(java.sql.Date.valueOf(joinDate.getValue()));
            cattle.setLeaveDate(java.sql.Date.valueOf(leaveDate.getValue()));
            cattle.setLevaReason(leaveReason.getText());
            cattle.setNotes(note.getText());
            cattle.addToTeamList(groups.get(group.getSelectionModel().getSelectedIndex()));
            HCattle.save(em, cattle);
            groups.get(group.getSelectionModel().getSelectedIndex()).addCattleToList(cattle);
            HTeam.update(em, groups.get(group.getSelectionModel().getSelectedIndex()));
        }
        name.clear();
        earring.clear();
        numberCowshed.clear();
        leaveReason.clear();
        note.clear();
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
        groups = HTeam.getByCowshedName(em, cowshed.getSelectionModel().getSelectedItem());
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groups.size(); i++) {
            groups2.add(groups.get(i).getName());
        }
        group.setItems(groups2);
    }
}

