package controller.ShowWindow;

import controller.Main.MainController;
import hibernate.FactoryHibernate;
import hibernate.HCattle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Calving;
import models.Cattle;
import models.Insemination;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationController implements Initializable {

    private EntityManager em;

    @FXML
    private TableColumn<Calving, Integer> numberCalving;

    @FXML
    private Label birthDateCattle;

    @FXML
    private TableColumn<Insemination, Integer> numberInsemination;

    @FXML
    private TableColumn<Insemination, String> resultInsemination;

    @FXML
    private TableView<Calving> listCalving;

    @FXML
    private TableColumn<Calving, String> calfCalving;

    @FXML
    private TableColumn<Calving, String> noteCalving;

    @FXML
    private Label cowshedNumberCattle;

    @FXML
    private TableColumn<Insemination, String> noteInsemination;

    @FXML
    private Label noteCattle;

    @FXML
    private Label nameCattle;

    @FXML
    private Label joinDateCattle;

    @FXML
    private Label leaveReasonCattle;

    @FXML
    private TableColumn<Insemination, Date> dataInsemination;

    @FXML
    private TableView<Insemination> listInsemination;

    @FXML
    private TableColumn<Insemination, String> bullInsemination;

    @FXML
    private Label earringCattle;

    @FXML
    private Label sexCattle;

    @FXML
    private Label leaveDateCattle;

    @FXML
    private TableColumn<Calving, Date> dateCalving;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        em = FactoryHibernate.getEm();
    }

    public void setCattleInfo(Cattle cattle) {
        birthDateCattle.setText(cattle.getBirthDate().toString());
        nameCattle.setText(cattle.getName());
        joinDateCattle.setText(cattle.getJoinDate().toString());
        if(cattle.getLeaveDate()!=null){
            leaveDateCattle.setText(cattle.getLeaveDate().toString());
            leaveReasonCattle.setText(cattle.getLevaReason());
        }else{
            leaveDateCattle.setText("-");
            leaveReasonCattle.setText("-");
        }
        earringCattle.setText(cattle.getEarring());
        cowshedNumberCattle.setText(cattle.getCowshedNumber().toString());
        sexCattle.setText(cattle.getSex());
        noteCattle.setText(cattle.getNotes());

        ObservableList<Insemination> inseminations = FXCollections.observableArrayList();
        inseminations.addAll(cattle.getInseminationList());

        numberInsemination.setCellValueFactory(new PropertyValueFactory<Insemination,Integer>("idInsemination"));
        dataInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, Date>("inseminationDate"));
        bullInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, String>("bull"));
        resultInsemination.setCellValueFactory(new PropertyValueFactory<Insemination,String>("result"));
        noteInsemination.setCellValueFactory(new PropertyValueFactory<Insemination,String>("notes"));
        listInsemination.setItems(inseminations);

        ObservableList<Calving> calvings = FXCollections.observableArrayList();
        calvings.addAll(cattle.getCalvingList());

        numberCalving.setCellValueFactory(new PropertyValueFactory<Calving,Integer>("idCalving"));
        dateCalving.setCellValueFactory(new PropertyValueFactory<Calving, Date>("calvingDate"));
        calfCalving.setCellValueFactory(new PropertyValueFactory<Calving, String>("calf"));
        noteCalving.setCellValueFactory(new PropertyValueFactory<Calving,String>("notes"));
        listCalving.setItems(calvings);
    }
}
