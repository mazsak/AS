package controller.AddWindow;

import controller.Main.MainController;
import hibernate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import models.Bull;
import models.Cowshed;
import models.Insemination;
import models.Team;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import models.Cattle;

public class AddInseminationController implements Initializable {
    private List<Team> groups;
    private List<Bull> bulls;
    private MainController mc;
    private Cattle chosenCattle;

    @FXML
    private ComboBox<String> result;

    @FXML
    private TextArea note;

    @FXML
    private DatePicker inseminationDate;

    @FXML
    private ComboBox<String> cowshed;

    @FXML
    private ComboBox<String> cattle;

    @FXML
    private ComboBox<String> bull;

    @FXML
    private ComboBox<String> group;

    @FXML
    void addInseminationActionListener(ActionEvent event) {
        //TODO zapisywanie byka
        if (!cattle.getValue().isEmpty()) {

            Insemination insemination = new Insemination();
            if (HBull.searchByName(bull.getSelectionModel().getSelectedItem())) {
                Bull bbll = new Bull();
                bbll.setName(bull.getSelectionModel().getSelectedItem());
                HBull.save(bbll);

                insemination.setInseminationDate(java.sql.Date.valueOf(inseminationDate.getValue()));
                insemination.setResult(result.getValue());
                insemination.setNotes(note.getText());
                insemination.setIdCattle(groups.get(group.getSelectionModel().getSelectedIndex())
                        .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));
                insemination.setIdBull(bbll);
                HInsemination.save(insemination);

                bbll.addToInseminationList(insemination);
                HBull.update(bbll);
            } else {
                insemination.setInseminationDate(java.sql.Date.valueOf(inseminationDate.getValue()));
                insemination.setResult(result.getValue());
                insemination.setNotes(note.getText());
                insemination.setIdCattle(groups.get(group.getSelectionModel().getSelectedIndex())
                        .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));
                insemination.setIdBull(bulls.get(bull.getSelectionModel().getSelectedIndex()));
                HInsemination.save(insemination);

                bulls.get(bull.getSelectionModel().getSelectedIndex()).addToInseminationList(insemination);
                HBull.update(bulls.get(bull.getSelectionModel().getSelectedIndex()));
            }
            groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()).addToInseminationList(insemination);
            HCattle.update(groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));

            result.getSelectionModel().clearSelection();
            cowshed.getSelectionModel().clearSelection();
            cattle.getSelectionModel().clearSelection();
            bull.getSelectionModel().clearSelection();
            group.getSelectionModel().clearSelection();
            inseminationDate.getEditor().clear();
            note.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cowshed> csh = HCowshed.read();
        ObservableList<String> cowsheds = FXCollections.observableArrayList();
        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }
        cowshed.setItems(cowsheds);
        result.getItems().addAll("Powodzenie", "Niepowodzenie", "Nieznane");

        bulls = HBull.read();
        ObservableList<String> bulls4 = FXCollections.observableArrayList();
        for (int i = 0; i < bulls.size(); i++) {
            bulls4.add(bulls.get(i).getName());
        }
        bull.setItems(bulls4);
    }

    @FXML
    public void cowshedCheckedActionListener(ActionEvent event) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCowshedName(cowshed.getSelectionModel().getSelectedItem(), "EAT");
        for (int i = 0; i < groups.size(); i++) {
            teams.add(groups.get(i).getName());
        }
        group.setItems(teams);
    }

    @FXML
    public void groupCheckedActionListener(ActionEvent event) {
        ObservableList<String> cattles = FXCollections.observableArrayList();

        for (int i = 0; i < groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattle.setItems(cattles);
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    public Cattle getChosenCattle() {
        return chosenCattle;
    }

    public void setChosenCattle(Cattle chosenCattle) {
        this.chosenCattle = chosenCattle;

        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCattleInEm(chosenCattle);
        Team currentTeam = null;
        for (int i = 0; i < groups.size(); i++) {
            teams.add(groups.get(i).getName());
             currentTeam = groups.get(i);
        }
        ObservableList<String> groupsInCowshed = FXCollections.observableArrayList();
        groups = currentTeam.getIdCowshed().getTeamList();
        for (int i = 0; i < groups.size(); i++) {
            groupsInCowshed.add(groups.get(i).getName());
        }
        group.setItems(groupsInCowshed);
        group.getSelectionModel().select(teams.get(0));

        cowshed.getSelectionModel().select(currentTeam.getIdCowshed().getName());
        cattle.getSelectionModel().select(this.chosenCattle.getEarring());
        ObservableList<String> cattles = FXCollections.observableArrayList();

        for (int i = 0; i < groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattle.setItems(cattles);
    }
}

