package controller.EditWindow;

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
import javafx.scene.control.ListView;
import models.Cattle;
import models.Cowshed;
import models.Team;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditGroupController implements Initializable {

    private List<Team> groupsFirstList = new ArrayList<>();
    private List<Team> groupsSecondList = new ArrayList<>();
    private List<String> cattlesFirstList = new ArrayList<>();
    private List<String> cattlesSecondList = new ArrayList<>();

    @FXML
    private ListView<String> cattleFirst;

    @FXML
    private ListView<String> cattleSecond;

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
        String typeName;
        if (type.getSelectionModel().getSelectedItem().equals("Żywieniowe")) {
            typeName = "EAT";
        } else {
            typeName = "SICK";
        }
        if (!groupsFirstList.isEmpty())
            groupsFirstList.clear();
        groupsFirstList = HTeam.getByCowshedName(cowshedFirst.getSelectionModel().getSelectedItem(), typeName);
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groupsFirstList.size(); i++) {
            groups2.add(groupsFirstList.get(i).getName());
        }
        groupFirst.setItems(groups2);
        if (cowshedSecond.getSelectionModel().isEmpty()) {
            cowshedSecond.getSelectionModel().select(cowshedFirst.getSelectionModel().getSelectedIndex());
            if (!groupsSecondList.isEmpty())
                groupsSecondList.clear();
            groupsSecondList.addAll(groupsFirstList);
            groupSecond.setItems(groups2);
        }
    }

    @FXML
    void cowshedSecondActionListener(ActionEvent event) {
        String typeName;
        if (type.getSelectionModel().getSelectedItem().equals("Żywieniowe")) {
            typeName = "EAT";
        } else {
            typeName = "SICK";
        }
        if (!groupsSecondList.isEmpty())
            groupsSecondList.clear();
        groupsSecondList = HTeam.getByCowshedName(cowshedSecond.getSelectionModel().getSelectedItem(), typeName);
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groupsSecondList.size(); i++) {
            groups2.add(groupsSecondList.get(i).getName());
        }
        groupSecond.setItems(groups2);
        if (cowshedFirst.getSelectionModel().isEmpty()) {
            cowshedFirst.getSelectionModel().select(cowshedSecond.getSelectionModel().getSelectedIndex());
            if (!groupsFirstList.isEmpty())
                groupsFirstList.clear();
            groupsFirstList.addAll(groupsSecondList);
            groupFirst.setItems(groups2);
        }

    }

    @FXML
    void fromFirstToSecondActionListener(ActionEvent event) {
        if (!groupSecond.getSelectionModel().isEmpty() && !cattleFirst.getItems().isEmpty()) {
            cattlesSecondList.add(cattleFirst.getSelectionModel().getSelectedItem());
            cattlesFirstList.remove(cattleFirst.getSelectionModel().getSelectedItem());
            cattleSecond.getItems().add(cattleFirst.getItems().get(cattleFirst.getSelectionModel().getSelectedIndex()));
            cattleSecond.getSelectionModel().selectFirst();
            cattleFirst.getItems().remove(cattleFirst.getSelectionModel().getSelectedIndex());
            cattleFirst.getSelectionModel().selectFirst();
            if (!groupSecond.isDisable()) {
                type.setDisable(true);
                cowshedFirst.setDisable(true);
                groupFirst.setDisable(true);
                cowshedSecond.setDisable(true);
                groupSecond.setDisable(true);
            }
        }
    }

    @FXML
    void fromSecondToFirstActionListener(ActionEvent event) {
        if (((type.getSelectionModel().getSelectedItem().equals("Żywieniowe") && !groupFirst.getSelectionModel().isEmpty()) && !groupSecond.getItems().isEmpty()) || (type.getSelectionModel().getSelectedItem().equals("Leczone") && !groupSecond.getItems().isEmpty())) {
            cattlesFirstList.add(cattleSecond.getSelectionModel().getSelectedItem());
            cattlesSecondList.remove(cattleSecond.getSelectionModel().getSelectedItem());
            cattleFirst.getItems().add(cattleSecond.getItems().get(cattleSecond.getSelectionModel().getSelectedIndex()));
            cattleFirst.getSelectionModel().selectFirst();
            cattleSecond.getItems().remove(cattleSecond.getSelectionModel().getSelectedIndex());
            cattleSecond.getSelectionModel().selectFirst();
            if (!groupSecond.isDisable()) {
                type.setDisable(true);
                cowshedFirst.setDisable(true);
                groupFirst.setDisable(true);
                cowshedSecond.setDisable(true);
                groupSecond.setDisable(true);
            }
        }
    }

    @FXML
    void saveListener(ActionEvent event) {
        if (!cattleFirst.getItems().isEmpty() || !cattleSecond.getItems().isEmpty()) {
            if (type.getSelectionModel().getSelectedItem().equals("Żywieniowe")) {
                cowshedFirst.setDisable(false);
                groupFirst.setDisable(false);
                for (String cattleEarring : cattlesFirstList) {
                    Cattle cattle = HCattle.findByEarring(cattleEarring);
                    Team team = HTeam.getByName(groupFirst.getSelectionModel().getSelectedItem());
                    cattle.replaceTeamEAT(team);
                }
                for (String cattleEarring : cattlesSecondList) {
                    Cattle cattle = HCattle.findByEarring(cattleEarring);
                    Team team = HTeam.getByName(groupSecond.getSelectionModel().getSelectedItem());
                    cattle.replaceTeamEAT(team);
                }
            } else {
                for (String cattleEarring : cattlesFirstList) {
                    Cattle cattle = HCattle.findByEarring(cattleEarring);
                    Team team = HTeam.getByName(groupSecond.getSelectionModel().getSelectedItem());
                    cattle.deleteTeamSICK(team);
                }
                for (String cattleEarring : cattlesSecondList) {
                    Cattle cattle = HCattle.findByEarring(cattleEarring);
                    Team team = HTeam.getByName(groupSecond.getSelectionModel().getSelectedItem());
                    cattle.addTeamSICK(team);
                }
            }
        }
        if (type.getSelectionModel().getSelectedItem().equals("Żywieniowe")) {
            cowshedFirst.setDisable(false);
            groupFirst.setDisable(false);
        }
        cowshedSecond.setDisable(false);
        groupSecond.setDisable(false);
        type.setDisable(false);
    }

    @FXML
    void typeActionListener(ActionEvent event) {
        String typeName;
        if (type.getSelectionModel().getSelectedItem().equals("Leczone")) {
            nameMedicine.setDisable(false);
            dateStartTreatment.setDisable(false);
            groupsFirstList.clear();
            cowshedFirst.getSelectionModel().clearSelection();
            cowshedFirst.setDisable(true);
            groupFirst.getItems().clear();
            groupFirst.setDisable(true);

            typeName = "SICK";
        } else {
            typeName = "EAT";
            cowshedFirst.setDisable(false);
            groupFirst.setDisable(false);
            nameMedicine.setDisable(true);
            dateStartTreatment.setDisable(true);
            nameMedicine.getSelectionModel().clearSelection();
            dateStartTreatment.setValue(LocalDate.now());
            cattleFirst.getItems().clear();
        }
        if (!cowshedFirst.getSelectionModel().isEmpty() && typeName.equals("EAT")) {
            groupsFirstList = HTeam.getByCowshedName(cowshedFirst.getSelectionModel().getSelectedItem(), typeName);
            ObservableList<String> groups2 = FXCollections.observableArrayList();
            for (int i = 0; i < groupsFirstList.size(); i++) {
                groups2.add(groupsFirstList.get(i).getName());
            }
            groupFirst.setItems(groups2);
        }
        if (!cowshedSecond.getSelectionModel().isEmpty()) {
            groupsSecondList = HTeam.getByCowshedName(cowshedSecond.getSelectionModel().getSelectedItem(), typeName);
            ObservableList<String> groups2 = FXCollections.observableArrayList();
            for (int i = 0; i < groupsSecondList.size(); i++) {
                groups2.add(groupsSecondList.get(i).getName());
            }
            groupSecond.setItems(groups2);
        }
    }

    @FXML
    void groupFirstActionListener(ActionEvent event) {
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groupsSecondList.size(); i++) {
            groups2.add(groupsSecondList.get(i).getName());
        }
        groupSecond.setItems(groups2);
        groupSecond.getItems().remove(groupFirst.getSelectionModel().getSelectedItem());

        ObservableList<String> cattlesFirst = FXCollections.observableArrayList();
        List<Cattle> cattles = new ArrayList<>();
        for (int i = 0; i < groupsFirstList.size(); i++) {
            if (groupsFirstList.get(i).getName().equals(groupFirst.getSelectionModel().getSelectedItem())) {
                cattles.addAll(groupsFirstList.get(i).getCattleList());
            }
        }
        for (int i = 0; i < cattles.size(); i++) {
            cattlesFirst.add(cattles.get(i).getEarring());
        }
        if (!cattlesFirstList.isEmpty()) {
            cattlesFirstList.clear();
        }
        cattlesFirstList.addAll(cattlesFirst);
        cattleFirst.setItems(cattlesFirst);
        cattleFirst.getSelectionModel().selectFirst();
    }

    @FXML
    void groupSecondActionListener(ActionEvent event) {
        ObservableList<String> groups2 = FXCollections.observableArrayList();
        for (int i = 0; i < groupsFirstList.size(); i++) {
            groups2.add(groupsFirstList.get(i).getName());
        }
        groupFirst.setItems(groups2);
        groupFirst.getItems().remove(groupSecond.getSelectionModel().getSelectedItem());

        ObservableList<String> cattlesSecond = FXCollections.observableArrayList();
        List<Cattle> cattles = new ArrayList<>();
        for (int i = 0; i < groupsSecondList.size(); i++) {
            if (groupsSecondList.get(i).getName().equals(groupSecond.getSelectionModel().getSelectedItem())) {
                cattles.addAll(groupsSecondList.get(i).getCattleList());
            }
        }
        for (int i = 0; i < cattles.size(); i++) {
            cattlesSecond.add(cattles.get(i).getEarring());
        }
        if (!cattlesSecondList.isEmpty()) {
            cattlesSecondList.clear();
        }
        cattlesSecondList.addAll(cattlesSecond);
        cattleSecond.setItems(cattlesSecond);
        cattleSecond.getSelectionModel().selectFirst();

        if (type.getSelectionModel().getSelectedItem().equals("Leczone")) {
            ObservableList<String> cattlesFirst = FXCollections.observableArrayList();
            Cowshed cowshed = HCowshed.findByName(cowshedSecond.getSelectionModel().getSelectedItem());
            for (Team team : cowshed.getTeamList()) {
                if (team.getType().equals("EAT")) {
                    for (Cattle cattle : team.getCattleList()) {
                        cattlesFirst.addAll(cattle.getEarring());
                    }
                }
            }
            cattlesFirst.removeAll(cattlesSecond);
            if (!cattlesFirstList.isEmpty()) {
                cattlesFirstList.clear();
            }
            cattlesFirstList.addAll(cattlesFirst);
            cattleFirst.setItems(cattlesFirst);
            cattleFirst.getSelectionModel().selectFirst();
        }
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
