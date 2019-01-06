package controller.AddWindow;

import hibernate.FactoryHibernate;
import hibernate.HBull;
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
import models.Bull;
import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCalvingController implements Initializable {

    private EntityManager em;
    private List<Team> groups;

    @FXML
    private TextArea note;

    @FXML
    private DatePicker calvingDate;

    @FXML
    private ComboBox<String> cowshedCalf;

    @FXML
    private ComboBox<String> calf;

    @FXML
    private ComboBox<String> cowshed;

    @FXML
    private ComboBox<String> cattle;

    @FXML
    private ComboBox<String> group;

    @FXML
    void addCalvingActionListener(ActionEvent event) {

    }

    @FXML
    void cowshedCheckedActionListener(ActionEvent event) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCowshedName(em, cowshed.getSelectionModel().getSelectedItem());
        for (int i = 0; i < groups.size(); i++) {
            teams.add(groups.get(i).getName());
        }
        group.setItems(teams);
    }

    @FXML
    void groupCheckedActionListener(ActionEvent event) {
        ObservableList<String> cattles = FXCollections.observableArrayList();

        for (int i = 0; i < groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattle.setItems(cattles);
    }

    @FXML
    void cowshedCalfCheckedActionListener(ActionEvent event) {
        ObservableList<String> calfs = FXCollections.observableArrayList();

        groups = HTeam.getByCowshedName(em, cowshedCalf.getSelectionModel().getSelectedItem());

        List<Cattle> allCattleInCowshed = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            allCattleInCowshed.addAll(groups.get(i).getCattleList());
        }

        for(int i = 0; i < allCattleInCowshed.size(); i++){
            calfs.add(allCattleInCowshed.get(i).getEarring());
        }
        calf.setItems(calfs);
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
        cowshedCalf.setItems(cowsheds);
    }
}
