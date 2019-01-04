package controller.ShowWindow;

import controller.Main.MainController;
import hibernate.FactoryHibernate;
import hibernate.HCattle;
import hibernate.HCowshed;
import hibernate.HTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListWindowController implements Initializable {

    private MainController mc;
    private EntityManager em;
    private InformationController infocon;

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
    //private ListView<Cowshed> listCowshed;

    @FXML
    private ListView<String> listTeam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FactoryHibernate fh = new FactoryHibernate();
        em = FactoryHibernate.getEm();

        List<Cowshed> csh = HCowshed.read(em);

        //ObservableList<Cowshed> cowsheds = FXCollections.observableArrayList();
        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        for (int i = 0; i < csh.size(); i++) {
            //cowsheds.add(new Cowshed(csh.get(i).getIdCowshed()));
            cowsheds.add(csh.get(i).getName());
        }

        listCowshed.setItems(cowsheds);

        List<Cattle> ctl = HCattle.read(em);
        ObservableList<Cattle> cattles = FXCollections.observableArrayList();
        cattles.addAll(ctl);

        number.setCellValueFactory(new PropertyValueFactory<Cattle,Integer>("idCattle"));
        earring.setCellValueFactory(new PropertyValueFactory<Cattle,String>("earring"));
        cowshedNumber.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("cowshedNumber"));
        cowshed.setCellValueFactory(new PropertyValueFactory<Cattle,String>("notes"));
        team.setCellValueFactory(new PropertyValueFactory<Cattle,String>("teamList"));
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

    }

    @FXML
    public void listCattlesActionListener(MouseEvent arg0) {
        Cattle cattle = HCattle.findByEarring(em, earring.getCellObservableValue(listCattles.getSelectionModel().getFocusedIndex()).getValue());
        infocon.setCattleInfo(cattle);
        if(arg0.getClickCount() == 2){
            cattleBar.getSelectionModel().select(1);
        }
    }

    @FXML
    public void cowshedActionListener(MouseEvent arg0) {
        List<Team> tms = HTeam.getByCowshedName(em, listCowshed.getSelectionModel().getSelectedItem());
        ObservableList<String> teams = FXCollections.observableArrayList();

        for (int i = 0; i < tms.size(); i++) {
            teams.add(tms.get(i).getName());
        }

        listTeam.setItems(teams);
    }

    @FXML
    public void teamActionListener(MouseEvent arg0) {
        List<Cattle> ctl = HTeam.getCattlesFromTeam(em, listTeam.getSelectionModel().getSelectedItem());
        ObservableList<Cattle> cattles = FXCollections.observableArrayList();

        cattles.addAll(ctl);

        number.setCellValueFactory(new PropertyValueFactory<Cattle,Integer>("idCattle"));
        earring.setCellValueFactory(new PropertyValueFactory<Cattle,String>("earring"));
        cowshedNumber.setCellValueFactory(new PropertyValueFactory<Cattle, Integer>("cowshedNumber"));
        cowshed.setCellValueFactory(new PropertyValueFactory<Cattle,String>("notes"));
        team.setCellValueFactory(new PropertyValueFactory<Cattle,String>("teamList"));
        birthDate.setCellValueFactory(new PropertyValueFactory<Cattle, Date>("birthDate"));
        listCattles.setItems(cattles);
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

}