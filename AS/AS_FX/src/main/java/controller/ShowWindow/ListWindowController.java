package controller.ShowWindow;

import controller.AddWindow.AddWindowController;
import controller.Main.MainController;
import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Cattle;
import models.Cowshed;
import models.Team;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListWindowController implements Initializable {

    private MainController mc;
    private InformationController infocon;
    private List<Team> groups;

    @FXML
    private TableColumn<Cattle, Integer> number;

    @FXML
    private TableColumn<Cattle, String> earring;

    @FXML
    private BorderPane rootListWindow;

    @FXML
    private TableColumn<Cattle, Integer> cowshedNumber;

    @FXML
    private TableColumn<Cattle, String> cowshed;

    @FXML
    private TableColumn<Cattle, String> team;

    @FXML
    private TabPane cattleBar;

    @FXML
    private Tab listAnimals;

    @FXML
    private TableColumn<Cattle, Date> birthDate;

    @FXML
    private ScrollPane informationCattle;

    @FXML
    private TableView<Cattle> listCattles;

    @FXML
    private ListView<String> listCowshed;

    @FXML
    private ListView<String> listTeam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ContextMenu contextMenuGroup = new ContextMenu();

        MenuItem addCattle = new MenuItem("Dodaj zwierzę");
        addCattle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddWindow.fxml"));

                BorderPane addWindow = null;

                try {
                    addWindow = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

                AddWindowController awc = loader.getController();
                awc.setChosenTeam(HTeam.getByName(listTeam.getSelectionModel().getSelectedItem()));
                awc.setChosen(2);

                mc.getMainWindow().setCenter(addWindow);
            }
        });
        contextMenuGroup.getItems().add(addCattle);
        MenuItem deleteAllCattleGroup = new MenuItem("Usuń zwierzęta");
        deleteAllCattleGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setGraphic(null);
                alert.setTitle("Potwierdzenie usunięcia");
                alert.setContentText("Czy usunąć wszystkie zwierzęta z grupy \"" + listTeam.getSelectionModel().getSelectedItem() + "\"?");
                ButtonType buttonTak = new ButtonType("Tak");
                ButtonType buttonNie = new ButtonType("Nie");

                alert.getButtonTypes().setAll(buttonTak, buttonNie);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTak) {
                    Team team = HTeam.getByName(listTeam.getSelectionModel().getSelectedItem());
                    int i = 0;
                    while (i < team.getCattleList().size()) {
                        HCattle.delete(team.getCattleList().get(i));
                    }
                    listCattles.getItems().clear();
                }
            }
        });
        contextMenuGroup.getItems().add(deleteAllCattleGroup);
        MenuItem deleteGroup = new MenuItem("Usuń grupę");
        deleteGroup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Team team : groups) {
                    if (team.getName().equals(listTeam.getSelectionModel().getSelectedItem())) {
                        if (team.getCattleList().isEmpty()) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setGraphic(null);
                            alert.setTitle("Potwierdzenie usunięcia");
                            alert.setContentText("Czy usunąć grupę \"" + listTeam.getSelectionModel().getSelectedItem() + "\"?");
                            ButtonType buttonTak = new ButtonType("Tak");
                            ButtonType buttonNie = new ButtonType("Nie");

                            alert.getButtonTypes().setAll(buttonTak, buttonNie);

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == buttonTak) {
                                groups.remove(team);
                                HTeam.delete(team);
                                listTeam.getItems().remove(listTeam.getSelectionModel().getSelectedItem());
                                return;
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setGraphic(null);
                            alert.setTitle("Infromacja");
                            alert.setContentText("Przed usunięciem grupy \"" + team.getName() + "\" zmienić grupę zwierząt!");
                            ButtonType buttonTak = new ButtonType("Ok");
                            alert.getButtonTypes().setAll(buttonTak);

                            alert.showAndWait();
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
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddWindow.fxml"));

                BorderPane addWindow = null;

                try {
                    addWindow = loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

                AddWindowController awc = loader.getController();
                awc.setChosenCowshed(HCowshed.findByName(listCowshed.getSelectionModel().getSelectedItem()));
                awc.setChosen(1);

                mc.getMainWindow().setCenter(addWindow);
            }
        });
        contextMenuCowshed.getItems().add(addGroup);
        MenuItem deleteAllGroupCowshed = new MenuItem("Usuń grupy");
        deleteAllGroupCowshed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Cowshed cowshed = HCowshed.findByName(listCowshed.getSelectionModel().getSelectedItem());
                int i = 0;
                while (i < cowshed.getTeamList().size()) {
                    HTeam.delete(cowshed.getTeamList().get(i));
                }
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

        number.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("idCattle"));
        earring.setCellValueFactory(new PropertyValueFactory<Cattle, String>("earring"));
        cowshedNumber.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("cowshedNumber"));
        cowshed.setCellValueFactory(new PropertyValueFactory<Cattle, String>("cowshed"));
        team.setCellValueFactory(new PropertyValueFactory<Cattle, String>("teamEAT"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Cattle, Date>("birthDate"));
        listCattles.setItems(cattles);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ShowWindow/InformationCattle.fxml"));

        GridPane informationWindow = null;

        try {
            informationWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        infocon = loader.getController();
        informationCattle.setContent(informationWindow);

        infocon.setParentController(this);
    }

    @FXML
    public void listCattlesActionListener(MouseEvent arg0) throws ParseException {
        Cattle cattle = HCattle.findByEarring(earring.getCellObservableValue(listCattles.getSelectionModel().getFocusedIndex()).getValue());
        infocon.setCattleInfo(cattle);
        if (arg0.getClickCount() == 2) {
            cattleBar.getSelectionModel().select(1);
        }
    }

    @FXML
    public void cowshedActionListener(MouseEvent arg0) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        ObservableList<Cattle> allCattleInCowshed = FXCollections.observableArrayList();
        if (listCowshed.getSelectionModel().getSelectedIndex() == 0) {
            List<Cattle> cattles;
            cattles = HCattle.read();
            allCattleInCowshed.addAll(cattles);

        } else {
            groups = HTeam.getByCowshedName(listCowshed.getSelectionModel().getSelectedItem(), "EAT");

            for (int i = 0; i < groups.size(); i++) {
                teams.add(groups.get(i).getName());
                allCattleInCowshed.addAll(groups.get(i).getCattleList());
            }

        }
        listTeam.setItems(teams);
        listCattles.setItems(allCattleInCowshed);
    }

    @FXML
    public void teamActionListener(MouseEvent arg0) {
        ObservableList<Cattle> cattles = FXCollections.observableArrayList();

        cattles.addAll(groups.get(listTeam.getSelectionModel().getSelectedIndex()).getCattleList());
        listCattles.setItems(cattles);
    }

    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

    public TabPane getTabPane() {
        return cattleBar;
    }

    public Tab getListAnimals() {
        return listAnimals;
    }

    public void switchBack() {
        getTabPane().getSelectionModel().select(listAnimals);
        ObservableList<Cattle> allCattleInCowshed = FXCollections.observableArrayList();
        if (listCowshed.getSelectionModel().isEmpty() || listCowshed.getSelectionModel().getSelectedIndex() == 0) {
            List<Cattle> cattles;
            cattles = HCattle.read();
            allCattleInCowshed.addAll(cattles);
        } else {
            groups = HTeam.getByCowshedName(listCowshed.getSelectionModel().getSelectedItem(), "EAT");
            for (int i = 0; i < groups.size(); i++) {
                allCattleInCowshed.addAll(groups.get(i).getCattleList());
            }
        }
        listCattles.setItems(allCattleInCowshed);
    }

}