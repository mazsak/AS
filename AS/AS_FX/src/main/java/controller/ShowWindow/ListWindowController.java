package controller.ShowWindow;

import controller.Main.MainController;
import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Date;
import java.util.List;
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
        team.setCellValueFactory(new PropertyValueFactory<Cattle,String>("teamEAT"));
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
    public void listCattlesActionListener(MouseEvent arg0) {
        Cattle cattle = HCattle.findByEarring(earring.getCellObservableValue(listCattles.getSelectionModel().getFocusedIndex()).getValue());
        infocon.setCattleInfo(cattle);
        if(arg0.getClickCount() == 2){
            cattleBar.getSelectionModel().select(1);
        }
    }

    @FXML
    public void cowshedActionListener(MouseEvent arg0) {
        ObservableList<String> teams = FXCollections.observableArrayList();
        ObservableList<Cattle> allCattleInCowshed = FXCollections.observableArrayList();
        if(listCowshed.getSelectionModel().getSelectedIndex() == 0){
            List<Cattle> cattles;
            cattles = HCattle.read();
            allCattleInCowshed.addAll(cattles);

        }else {
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

    public void setMc(MainController mc) {
        this.mc = mc;
    }
    
    public TabPane getTabPane(){
        return cattleBar;
    }
    
    public Tab getListAnimals(){
        return listAnimals;
    }
    
    public void switchBack(){
        getTabPane().getSelectionModel().select(listAnimals);
    }

}