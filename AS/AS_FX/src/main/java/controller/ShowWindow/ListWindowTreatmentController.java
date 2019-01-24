package controller.ShowWindow;

import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HMedicine;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Cattle;
import models.Cowshed;
import models.Medicine;
import models.Team;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListWindowTreatmentController implements Initializable {

    private List<Team> groups;

    @FXML
    private ListView<String> listCowshed;

    @FXML
    private ListView<String> listTeam;

    @FXML
    private TableView<Cattle> listCattles;

    @FXML
    private TableColumn<Cattle, CheckBox> number;

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
    private ComboBox<Medicine> medicineGiven;

    @FXML
    void cowshedActionListener(MouseEvent event) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        ObservableList<Cattle> allCattleInCowshed = FXCollections.observableArrayList();
        if (listCowshed.getSelectionModel().getSelectedIndex() == 0) {
            List<Cattle> cattles;
            cattles = HCattle.read();
            allCattleInCowshed.addAll(cattles);

        } else {
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

        ContextMenu contextMenuGroup = new ContextMenu();

        MenuItem editGroup = new MenuItem("Edytuj grupę");
        editGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO przejscie do edycji wybrana grupą
            }
        });
        contextMenuGroup.getItems().add(editGroup);
        MenuItem deleteGroup = new MenuItem("Usuń grupę");
        deleteGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Team team : groups) {
                    if (team.getName().equals(listTeam.getSelectionModel().getSelectedItem())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setGraphic(null);
                        alert.setTitle("Potwierdzenie usunięcia");
                        alert.setContentText("Czy usunąć grupę: " + team.getName() + "?");
                        ButtonType buttonTak = new ButtonType("Tak");
                        ButtonType buttonNie = new ButtonType("Nie");

                        alert.getButtonTypes().setAll(buttonTak, buttonNie);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTak) {
                            groups.remove(team);
                            HTeam.delete(team);
                            listTeam.getItems().remove(listTeam.getSelectionModel().getSelectedItem());
                        }
                    }
                }
            }
        });
        contextMenuGroup.getItems().add(deleteGroup);

        listTeam.setContextMenu(contextMenuGroup);

        ContextMenu contextMenuCowshed = new ContextMenu();

        MenuItem addGroup = new MenuItem("Dodaj grupę");
        addGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO dodawnaie do tej obory grupy SICK
            }
        });
        contextMenuCowshed.getItems().add(addGroup);
        MenuItem deleteAllGroupCowshed = new MenuItem("Usuń grupy");
        deleteAllGroupCowshed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO usuniecie wszystkiech grupy SICK
            }
        });
        contextMenuCowshed.getItems().add(deleteAllGroupCowshed);
        MenuItem deleteCowshed = new MenuItem("Usuń oborę");
        deleteCowshed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Cowshed cowshed : HCowshed.read()) {
                    if (cowshed.getName().equals(listCowshed.getSelectionModel().getSelectedItem())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setGraphic(null);
                        alert.setTitle("Potwierdzenie usunięcia");
                        alert.setContentText("Usunięcie obory \"" + cowshed.getName() + "\" usunie wszystkie obiekty zwiazane znią!");
                        ButtonType buttonTak = new ButtonType("Tak");
                        ButtonType buttonNie = new ButtonType("Nie");

                        alert.getButtonTypes().setAll(buttonTak, buttonNie);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == buttonTak) {
                            listCowshed.getItems().remove(cowshed.getName());
                            listTeam.getItems().clear();
                            listCattles.getItems().clear();
                            HCowshed.delete(cowshed);
                        }
                    }
                }
            }
        });
        contextMenuCowshed.getItems().add(deleteCowshed);

        listCowshed.setContextMenu(contextMenuCowshed);

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

        //number.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("idCattle"));
        earring.setCellValueFactory(new PropertyValueFactory<Cattle, String>("earring"));
        cowshedNumber.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("cowshedNumber"));
        cowshed.setCellValueFactory(new PropertyValueFactory<Cattle, String>("cowshed"));
        team.setCellValueFactory(new PropertyValueFactory<Cattle, String>("teamSICK"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Cattle, Date>("birthDate"));
        medicineGivenColumn.setCellValueFactory(new PropertyValueFactory<Medicine, String>("name"));
        number.setCellValueFactory(new PropertyValueFactory<Cattle, CheckBox>("Select"));

        listCattles.setItems(cattles);

        List<Medicine> csh2 = HMedicine.read();
        ObservableList<Medicine> cowsheds2 = FXCollections.observableArrayList();
        cowsheds2.addAll(csh2);
        medicineGiven.setItems(cowsheds2);
    }
}
