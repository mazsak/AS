package controller.AddWindow;

import controller.Main.MainController;
import hibernate.HCalving;
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
import models.Calving;
import models.Cattle;
import models.Cowshed;
import models.Team;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCalvingController implements Initializable {

    private List<Team> groups;
    private List<Cattle> cattleList;
    private MainController mc;
    private Cattle chosenCattle;

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
        if(!cattle.getValue().isEmpty() && !calf.getValue().isEmpty() && !calvingDate.getValue().toString().isEmpty()){
            Calving calving = new Calving();
            calving.setCalvingDate(java.sql.Date.valueOf(calvingDate.getValue()));
            calving.setIdCattle(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));
            calving.setIdCalf(cattleList.get(calf.getSelectionModel().getSelectedIndex()));
            calving.setNotes(note.getText());
            HCalving.save(calving);

            groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(cattle.getSelectionModel().getSelectedIndex())
                    .addToCalvingListCalfForMother(calving);
            HCattle.update(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));

            cattleList.get(calf.getSelectionModel().getSelectedIndex()).addToCalvingListMotherForCalf(calving);
            HCattle.update(cattleList.get(calf.getSelectionModel().getSelectedIndex()));
            
            note.clear();
            calvingDate.getEditor().clear();
            calf.getSelectionModel().clearSelection();
            cowshedCalf.getSelectionModel().clearSelection();
            cattle.getSelectionModel().clearSelection();
            group.getSelectionModel().clearSelection();
            cowshed.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void cowshedCheckedActionListener(ActionEvent event) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        groups = HTeam.getByCowshedName(cowshed.getSelectionModel().getSelectedItem(), "EAT");
        for (int i = 0; i < groups.size(); i++) {
            teams.add(groups.get(i).getName());
        }
        group.setItems(teams);
    }

    @FXML
    void groupCheckedActionListener(ActionEvent event) {
        ObservableList<String> cattles = FXCollections.observableArrayList();

        if(!group.getSelectionModel().isEmpty()){
            for (int i = 0; i < groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
                cattles.add(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
            }
        }
        cattle.setItems(cattles);
    }

    @FXML
    void cowshedCalfCheckedActionListener(ActionEvent event) {
        ObservableList<String> calfs = FXCollections.observableArrayList();

        List<Team> groupsCalf = HTeam.getByCowshedName(cowshedCalf.getSelectionModel().getSelectedItem(), "EAT");


        cattleList = new ArrayList<>();
        for (int i = 0; i < groupsCalf.size(); i++) {
            cattleList.addAll(groupsCalf.get(i).getCattleList());
        }

        for(int i = 0; i < cattleList.size(); i++){
            if(!cattleList.get(i).getEarring().equals(cattle.getValue())) {
                calfs.add(cattleList.get(i).getEarring());
            }else{
                cattleList.remove(i);
                i--;
            }
        }
        calf.setItems(calfs);
    }

    @FXML
    void cattleCheckedActionListener(ActionEvent event){
        if(!calf.getItems().isEmpty()){
            ObservableList<String> calfs = FXCollections.observableArrayList();

            List<Team> groupsCalf = HTeam.getByCowshedName(cowshedCalf.getSelectionModel().getSelectedItem(), "EAT");


            cattleList = new ArrayList<>();
            for (int i = 0; i < groupsCalf.size(); i++) {
                cattleList.addAll(groupsCalf.get(i).getCattleList());
            }

            for(int i = 0; i < cattleList.size(); i++){
                if(!cattleList.get(i).getEarring().equals(cattle.getValue())) {
                    calfs.add(cattleList.get(i).getEarring());
                }else{
                    cattleList.remove(i);
                    i--;
                }
            }
            calf.setItems(calfs);
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
        cowshedCalf.setItems(cowsheds);
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
