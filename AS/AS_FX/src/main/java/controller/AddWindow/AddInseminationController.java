package controller.AddWindow;

import hibernate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import models.*;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddInseminationController implements Initializable {
    private EntityManager em;
    private List<Team> groups;
    private List<Bull> bulls;

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
            if (HBull.searhByName(em, bull.getSelectionModel().getSelectedItem())) {
                Bull bbll = new Bull();
                bbll.setName(bull.getSelectionModel().getSelectedItem());
                HBull.save(em, bbll);

                insemination.setInseminationDate(java.sql.Date.valueOf(inseminationDate.getValue()));
                insemination.setResult(result.getValue());
                insemination.setNotes(note.getText());
                insemination.setIdCattle(groups.get(group.getSelectionModel().getSelectedIndex())
                        .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));
                insemination.setIdBull(bbll);
                HInsemination.save(em, insemination);

                bbll.addToInseminationList(insemination);
                HBull.update(em, bbll);
            } else {
                insemination.setInseminationDate(java.sql.Date.valueOf(inseminationDate.getValue()));
                insemination.setResult(result.getValue());
                insemination.setNotes(note.getText());
                insemination.setIdCattle(groups.get(group.getSelectionModel().getSelectedIndex())
                        .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));
                insemination.setIdBull(bulls.get(bull.getSelectionModel().getSelectedIndex()));
                HInsemination.save(em, insemination);

                bulls.get(bull.getSelectionModel().getSelectedIndex()).addToInseminationList(insemination);
                HBull.update(em, bulls.get(bull.getSelectionModel().getSelectedIndex()));
            }
            groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()).addToInseminationList(insemination);
            HCattle.update(em, groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));

            note.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        em = FactoryHibernate.getEm();

        List<Cowshed> csh = HCowshed.read(em);
        ObservableList<String> cowsheds = FXCollections.observableArrayList();
        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }
        cowshed.setItems(cowsheds);
        result.getItems().addAll("Powodzenie", "Niepowodzenie", "Nieznane");

        bulls = HBull.read(em);
        ObservableList<String> bulls4 = FXCollections.observableArrayList();
        for (int i = 0; i < bulls.size(); i++) {
            bulls4.add(bulls.get(i).getName());
        }
        bull.setItems(bulls4);
    }

    @FXML
    public void cowshedCheckedActionListener(ActionEvent event) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCowshedName(em, cowshed.getSelectionModel().getSelectedItem());
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
}

