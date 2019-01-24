package controller.AddWindow;

import controller.Main.MainController;
import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HStats;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddStatsController implements Initializable {

    private List<Team> groups;
    private MainController mc;
    private Cattle chosenCattle;

    @FXML
    private ComboBox<String> cowshedDaily;

    @FXML
    private ComboBox<String> groupDaily;

    @FXML
    private ComboBox<String> cattleDaily;

    @FXML
    private DatePicker milkingDate;

    @FXML
    private ComboBox<String> milkingTime;

    @FXML
    private TextField milkAmount;

    @FXML
    private ComboBox<String> cowshedMonthly;

    @FXML
    private ComboBox<String> groupMonthly;

    @FXML
    private ComboBox<String> cattleMonthly;

    @FXML
    private DatePicker date;

    @FXML
    private TextField proteinContent;

    @FXML
    private TextField fatContent;

    @FXML
    private TextField bacteriaContent;

    @FXML
    void addStatsDailyActionListener(ActionEvent event) {
        if (!cattleDaily.getSelectionModel().isEmpty() && !milkAmount.getText().isEmpty() && milkingDate.getValue() != null && !milkingTime.getSelectionModel().isEmpty()) {
            StatsDaily statsDaily = new StatsDaily();
            statsDaily.setMilkAmount(Integer.parseInt(milkAmount.getText()));
            statsDaily.setMilkingDate(java.sql.Date.valueOf(milkingDate.getValue()));
            statsDaily.setMilkingTime(milkingTime.getSelectionModel().getSelectedItem());

            groups.get(groupDaily.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattleDaily.getSelectionModel().getSelectedIndex()).addStatsDaily(statsDaily);
            statsDaily.setIdCattle(groups.get(groupDaily.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattleDaily.getSelectionModel().getSelectedIndex()));

            HStats.saveDaily(statsDaily);
            HCattle.update(groups.get(groupDaily.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattleDaily.getSelectionModel().getSelectedIndex()));

            milkAmount.clear();
            milkingDate.getEditor().clear();
            cowshedDaily.getSelectionModel().clearSelection();
            groupDaily.getItems().clear();
            cattleDaily.getItems().clear();
            milkingTime.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void addStatsMonthlyActionListener(ActionEvent event) {
        if (!cattleMonthly.getSelectionModel().isEmpty() && !proteinContent.getText().isEmpty() && date.getValue() != null && !fatContent.getText().isEmpty() && !bacteriaContent.getText().isEmpty()) {
            StatsMonthly statsMonthly = new StatsMonthly();
            statsMonthly.setBacteriaContent(Integer.parseInt(bacteriaContent.getText()));
            statsMonthly.setFatContent(Double.parseDouble(fatContent.getText()));
            statsMonthly.setProteinContent(Double.parseDouble(proteinContent.getText()));
            statsMonthly.setTestDate(java.sql.Date.valueOf(date.getValue()));

            groups.get(groupMonthly.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattleMonthly.getSelectionModel().getSelectedIndex()).addStatsMonthly(statsMonthly);
            statsMonthly.setIdCattle(groups.get(groupMonthly.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattleMonthly.getSelectionModel().getSelectedIndex()));

            HStats.saveMonthly(statsMonthly);
            HCattle.update(groups.get(groupMonthly.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattleMonthly.getSelectionModel().getSelectedIndex()));

            bacteriaContent.clear();
            date.getEditor().clear();
            cowshedMonthly.getSelectionModel().clearSelection();
            groupMonthly.getItems().clear();
            cattleMonthly.getItems().clear();
            fatContent.clear();
            proteinContent.clear();
        }
    }

    @FXML
    void cowshedDailyCheckedActionListener(ActionEvent event) {
        cattleDaily.getItems().clear();
        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCowshedName(cowshedDaily.getSelectionModel().getSelectedItem(), "EAT");
        for (int i = 0; i < groups.size(); i++) {
            teams.add(groups.get(i).getName());
        }
        groupDaily.setItems(teams);
    }

    @FXML
    void cowshedMonthlyCheckedActionListener(ActionEvent event) {
        cattleMonthly.getItems().clear();
        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCowshedName(cowshedMonthly.getSelectionModel().getSelectedItem(), "EAT");
        for (int i = 0; i < groups.size(); i++) {
            teams.add(groups.get(i).getName());
        }
        groupMonthly.setItems(teams);
    }

    @FXML
    void groupDailyCheckedActionListener(ActionEvent event) {
        ObservableList<String> cattles = FXCollections.observableArrayList();
        for (int i = 0; i < groups.get(groupDaily.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(groupDaily.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattleDaily.setItems(cattles);
    }

    @FXML
    void groupMonthlyCheckedActionListener(ActionEvent event) {
        ObservableList<String> cattles = FXCollections.observableArrayList();

        for (int i = 0; i < groups.get(groupMonthly.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(groupMonthly.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattleMonthly.setItems(cattles);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cowshed> csh = HCowshed.read();
        ObservableList<String> cowsheds = FXCollections.observableArrayList();
        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }
        cowshedDaily.setItems(cowsheds);
        cowshedMonthly.setItems(cowsheds);
        milkingTime.getItems().addAll("rano", "wieczorem");
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
        groupDaily.setItems(groupsInCowshed);
        groupDaily.getSelectionModel().select(teams.get(0));

        cowshedDaily.getSelectionModel().select(currentTeam.getIdCowshed().getName());
        cattleDaily.getSelectionModel().select(this.chosenCattle.getEarring());
        ObservableList<String> cattles = FXCollections.observableArrayList();

        for (int i = 0; i < groups.get(groupDaily.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(groupDaily.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattleDaily.setItems(cattles);

        groupMonthly.setItems(groupsInCowshed);
        groupMonthly.getSelectionModel().select(teams.get(0));

        cowshedMonthly.getSelectionModel().select(currentTeam.getIdCowshed().getName());
        cattleMonthly.getSelectionModel().select(this.chosenCattle.getEarring());
        ObservableList<String> cattles2 = FXCollections.observableArrayList();

        for (int i = 0; i < groups.get(groupMonthly.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles2.add(groups.get(groupMonthly.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattleMonthly.setItems(cattles2);
    }

}
