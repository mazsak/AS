package controller.ShowWindow;

import controller.Main.MainController;
import hibernate.FactoryHibernate;
import hibernate.HCowshed;
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
import models.Cowshed;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListWindowController implements Initializable {

    private MainController mc;

    @FXML
    private TableColumn<?, ?> number;

    @FXML
    private TableColumn<?, ?> earring;

    @FXML
    private BorderPane rootListWindow;

    @FXML
    private TableColumn<?, ?> cowshedNumber;

    @FXML
    private TableColumn<?, ?> cowshed;

    @FXML
    private TableColumn<?, ?> team;

    @FXML
    private Tab listAnimals;

    @FXML
    private TableColumn<?, ?> birthDate;

    @FXML
    private ScrollPane informationCattle;

    @FXML
    private TableView<?> listCattles;

    @FXML
    private ListView<String> listCowshed;
    //private ListView<Cowshed> listCowshed;

    @FXML
    private ListView<?> listTeam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FactoryHibernate fh = new FactoryHibernate();
        EntityManager em = FactoryHibernate.getEm();

        List<Cowshed> csh = HCowshed.read(em);

        //ObservableList<Cowshed> cowsheds = FXCollections.observableArrayList();
        ObservableList<String> cowsheds = FXCollections.observableArrayList();

        for(int i = 0; i < csh.size(); i++){
            //cowsheds.add(new Cowshed(csh.get(i).getIdCowshed()));
            cowsheds.add(csh.get(i).getName());
        }

        listCowshed.setItems(cowsheds);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ShowWindow/InformationCattle.fxml"));

        GridPane informationWindow = null;

        try {
            informationWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        informationCattle.setContent(informationWindow);

    }

    @FXML
    void listCattlesActionListener(ActionEvent event) {

    }

    @FXML
    void cowshedActionListener(ActionEvent event) {

    }

    @FXML
    void teamActionListener(ActionEvent event) {

    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }

}