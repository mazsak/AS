package controller.ShowWindow;

import controller.Main.MainController;
import hibernate.FactoryHibernate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Cattle;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationController implements Initializable {

    private EntityManager em;

    @FXML
    private Label birthDateCattle;

    @FXML
    private Label nameCattle;

    @FXML
    private Label joinDateCattle;

    @FXML
    private Label leaveReasonCattle;

    @FXML
    private Label earringCattle;

    @FXML
    private Label cowshedNumberCattle;

    @FXML
    private Label sexCattle;

    @FXML
    private Label leaveDateCattle;

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
    }
}
