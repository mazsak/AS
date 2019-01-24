package controller.AddWindow;

import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Cattle;
import models.Cowshed;
import models.Team;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;

public class AddCattleController implements Initializable {

    private List<Team> groups;
    private Team chosenTeam;

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
        if (!earring.getText().isEmpty() && !sex.getValue().isEmpty() && !cowshed.getValue().isEmpty()
                && !group.getValue().isEmpty() && !birthDate.getValue().toString().isEmpty()
                && !joinDate.getValue().toString().isEmpty()) {

            Cattle cattle = new Cattle();
            cattle.setName(name.getText());
            cattle.setEarring(earring.getText());
            cattle.setSex(sex.getValue());
            //numberCowshed
            if (numberCowshed.getText().equals("")) {
                
            } else {
                try {
                    cattle.setCowshedNumber(Integer.valueOf(numberCowshed.getText()));
                } catch (Exception e) {
                    cattle.setCowshedNumber(0);
                }
            }
            cattle.setBirthDate(java.sql.Date.valueOf(birthDate.getValue()));
            cattle.setJoinDate(java.sql.Date.valueOf(joinDate.getValue()));
            //leave date
            if (leaveDate.getValue() == null) {
                LocalDate lc = LocalDate.of(1901, Month.FEBRUARY, 28);
                cattle.setLeaveDate(java.sql.Date.valueOf(lc));
            } else {
                cattle.setLeaveDate(java.sql.Date.valueOf(leaveDate.getValue()));
            }
            if(leaveReason.getText().equals("")){
                cattle.setLevaReason("XD1337");
            }else{
                cattle.setLevaReason(leaveReason.getText());
            }
            cattle.setNotes(note.getText());
            cattle.addToTeamList(groups.get(group.getSelectionModel().getSelectedIndex()));
            HCattle.save(cattle);
            groups.get(group.getSelectionModel().getSelectedIndex()).addCattleToList(cattle);
            HTeam.update(groups.get(group.getSelectionModel().getSelectedIndex()));

            name.clear();
            sex.getSelectionModel().clearSelection();
            earring.clear();
            cowshed.getSelectionModel().clearSelection();
            group.getSelectionModel().clearSelection();
            birthDate.getEditor().clear();
            joinDate.getEditor().clear();
            leaveDate.getEditor().clear();
            numberCowshed.clear();
            leaveReason.clear();
            note.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sex.getItems().addAll("XX", "XY");

        List<Cowshed> csh = HCowshed.read();

        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }

        cowshed.setItems(cowsheds);
    }

    @FXML
    public void checkedActionListener(ActionEvent event) {
        groups = HTeam.getByCowshedName(cowshed.getSelectionModel().getSelectedItem(), "EAT");
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groups.size(); i++) {
            groups2.add(groups.get(i).getName());
        }
        group.setItems(groups2);
    }

    public Team getChosenTeam() {
        return chosenTeam;
    }

    public void setChosenTeam(Team chosenTeam) {
        this.chosenTeam = chosenTeam;

        cowshed.getSelectionModel().select(this.chosenTeam.getIdCowshed().getName());

        groups = HTeam.getByCowshedName(cowshed.getSelectionModel().getSelectedItem(), "EAT");
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groups.size(); i++) {
            groups2.add(groups.get(i).getName());
        }
        group.setItems(groups2);
        group.getSelectionModel().select(chosenTeam.getName());
    }
}
