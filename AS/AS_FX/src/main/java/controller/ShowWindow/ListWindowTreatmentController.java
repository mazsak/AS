package controller.ShowWindow;

import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Cattle;
import models.Cowshed;
import models.Medicine;
import models.Team;

public class ListWindowTreatmentController implements Initializable {

    private List<Team> groups;
    
    @FXML
    private ListView<String> listCowshed;

    @FXML
    private ListView<String> listTeam;

    @FXML
    private TableView<Cattle> listCattles;

    @FXML
    private TableColumn<Cattle, Integer> number;

    @FXML
    private TableColumn<Cattle, String> earring;

    @FXML
    private TableColumn<Cattle, String> cowshed;

    @FXML
    private TableColumn<Cattle, Integer> cowshedNumber;

    @FXML
    private TableColumn<Cattle, String> team;

    @FXML
    private TableColumn<Cattle, Date> birthDate;

    @FXML
    private TableColumn<Medicine, String> medicineGivenColumn;

    @FXML
    private DatePicker medicineGivenDate;

    @FXML
    private ComboBox<?> medicineGiven;

    @FXML
    void cowshedActionListener(MouseEvent event) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        ObservableList<Cattle> allCattleInCowshed = FXCollections.observableArrayList();
        if(listCowshed.getSelectionModel().getSelectedIndex() == 0){
            List<Cattle> cattles;
            cattles = HCattle.read();
            allCattleInCowshed.addAll(cattles);

        }else {
            groups = HTeam.getByCowshedName(listCowshed.getSelectionModel().getSelectedItem(), "SICK");

            for (int i = 0; i < groups.size(); i++) {
                teams.add(groups.get(i).getName());
                allCattleInCowshed.addAll(groups.get(i).getCattleList());
            }

        }
        listTeam.setItems(teams);
        listCattles.setItems(allCattleInCowshed);
    }

    @FXML
    void listCattlesActionListener(MouseEvent event) {
        
    }

    @FXML
    void medicineGivenActionListener(ActionEvent event) {

    }

    @FXML
    void medicineGivenDateActionListener(ActionEvent event) {

    }

    @FXML
    void teamActionListener(MouseEvent event) {
        ObservableList<Cattle> cattles = FXCollections.observableArrayList();

        cattles.addAll(groups.get(listTeam.getSelectionModel().getSelectedIndex()).getCattleList());
        listCattles.setItems(cattles);
    }

    @FXML
    void saveChangeActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cowshed> csh = HCowshed.read();

        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        cowsheds.add("Wszystkie obory");
        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }

        listCowshed.setItems(cowsheds);

        List<Cattle> ctl = HCattle.read();
        ObservableList<Cattle> cattles = FXCollections.observableArrayList();
        cattles.addAll(ctl);

        number.setCellValueFactory(new PropertyValueFactory<Cattle,Integer>("idCattle"));
        earring.setCellValueFactory(new PropertyValueFactory<Cattle,String>("earring"));
        cowshedNumber.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("cowshedNumber"));
        cowshed.setCellValueFactory(new PropertyValueFactory<Cattle,String>("cowshed"));
        team.setCellValueFactory(new PropertyValueFactory<Cattle,String>("teamSICK"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Cattle, Date>("birthDate"));
        medicineGivenColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        listCattles.setItems(cattles);
    }
}
