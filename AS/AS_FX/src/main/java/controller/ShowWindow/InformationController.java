package controller.ShowWindow;

import controller.AddWindow.AddWindowController;
import controller.Main.MainController;
import hibernate.HCattle;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Calving;
import models.Cattle;
import models.Insemination;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class InformationController implements Initializable {

    private ListWindowController parentController;
    private MainController mc;
    
    @FXML
    private TableView<Calving> listCalving;

    @FXML
    private TableColumn<Calving, Integer> numberCalving;

    @FXML
    private TableColumn<Calving, String> calfCalving;

    @FXML
    private TableColumn<Calving, String> noteCalving;

    @FXML
    private TableColumn<Calving, Date> dateCalving;

    @FXML
    private TableView<Insemination> listInsemination;

    @FXML
    private TableColumn<Insemination, Integer> numberInsemination;

    @FXML
    private TableColumn<Insemination, String> resultInsemination;

    @FXML
    private TableColumn<Insemination, String> noteInsemination;

    @FXML
    private TableColumn<Insemination, Date> dataInsemination;

    @FXML
    private TableColumn<Insemination, String> bullInsemination;

    @FXML
    private Label nameCattle;

    @FXML
    private Label cowshedNumberCattle;

    @FXML
    private Label sexCattle;

    @FXML
    private Label birthDateCattle;

    @FXML
    private Label joinDateCattle;

    @FXML
    private Label leaveDateCattle;

    @FXML
    private Label leaveReasonCattle;

    @FXML
    private Button editNameCattle;

    @FXML
    private Button editNumberCowshedCattle;

    @FXML
    private Button editSexCattle;

    @FXML
    private Button editBirthDateCattle;

    @FXML
    private Button editJoinDateCattle;

    @FXML
    private Button editLeaveDateCattle;

    @FXML
    private Button editLeaveReasonCattle;

    @FXML
    private Label noteCattle;

    @FXML
    private Button editNoteCattle;

    @FXML
    private Label earringCattle;

    @FXML
    private TextField nameCattleText;

    @FXML
    private TextField noteCattleText;

    @FXML
    private TextField leaveReasonCattleText;

    @FXML
    private TextField cowshedNumberCattleText;

    @FXML
    void addCalvingActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddWindow.fxml"));

        BorderPane addWindow = null;

        try {
            addWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AddWindowController awc = loader.getController();
        if(!earringCattle.getText().equals("Nie wybrano krowy")){
            awc.setChosenCattle(HCattle.findByEarring(earringCattle.getText()));
        }
        awc.setChosen(4);
        
        parentController.getMc().getMainWindow().setCenter(addWindow);
    }

    @FXML
    void addInseminationActionListener(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddWindow/AddWindow.fxml"));

        BorderPane addWindow = null;

        try {
            addWindow = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        AddWindowController awc = loader.getController();
        if(!earringCattle.getText().equals("Nie wybrano krowy")){
            awc.setChosenCattle(HCattle.findByEarring(earringCattle.getText()));
        }
        awc.setChosen(3);

        parentController.getMc().getMainWindow().setCenter(addWindow);
    }
  
    @FXML
    void deleteCattleActionListener(ActionEvent event) throws IOException {
        String current = earringCattle.getText();
        if (!current.equals("Nie wybrano krowy")) {
            Cattle cattle = HCattle.findByEarring(earringCattle.getText());
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            alert.setTitle("Potwierdzenie usunięcia");
            alert.setContentText("Czy usunąć zwierzę: " + cattle.getEarring() +"?");
            ButtonType buttonTak = new ButtonType("Tak");
            ButtonType buttonNie = new ButtonType("Nie");

            alert.getButtonTypes().setAll(buttonTak, buttonNie);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTak) {
                HCattle.delete(cattle);
                birthDateCattle.setText("");
                nameCattle.setText("");
                joinDateCattle.setText("");
                leaveDateCattle.setText("");
                leaveReasonCattle.setText("");
                earringCattle.setText("Nie wybrano krowy");
                cowshedNumberCattle.setText("");
                sexCattle.setText("");
                noteCattle.setText("");
                parentController.switchBack();
            } else {

            }
        }
    }

    @FXML
    void editBirthDateCattleActionListener(ActionEvent event) {

    }

    @FXML
    void editJoinDateCattleActionListener(ActionEvent event) {

    }

    @FXML
    void editLeaveDateCattleActionListener(ActionEvent event) {

    }

    @FXML
    void editNameCattleActionListener(ActionEvent event) {
        if(nameCattle.isVisible()){
            nameCattle.setVisible(false);
            nameCattleText.setVisible(true);
            nameCattleText.setText(nameCattle.getText());
        }else{
            if(!nameCattleText.getText().equals(nameCattle.getText())) {
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setName(nameCattleText.getText());
                HCattle.update(cattle);
                nameCattle.setText(nameCattleText.getText());
            }
            nameCattleText.setVisible(false);
            nameCattle.setVisible(true);
        }
    }

    @FXML
    void editNoteCattleActionListener(ActionEvent event) {
        if(noteCattle.isVisible()){
            noteCattle.setVisible(false);
            noteCattleText.setVisible(true);
            noteCattleText.setText(noteCattle.getText());
        }else{
            if(!noteCattleText.getText().equals(noteCattle.getText())) {
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setNotes(noteCattleText.getText());
                HCattle.update(cattle);
                noteCattle.setText(noteCattleText.getText());
            }
            noteCattleText.setVisible(false);
            noteCattle.setVisible(true);
        }
    }

    @FXML
    void editNumberCowshedCattleActionListener(ActionEvent event) {
        if(cowshedNumberCattle.isVisible()){
            cowshedNumberCattle.setVisible(false);
            cowshedNumberCattleText.setVisible(true);
            cowshedNumberCattleText.setText(cowshedNumberCattle.getText());
        }else{
            if(!cowshedNumberCattleText.getText().equals(cowshedNumberCattle.getText())){
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setCowshedNumber(Integer.valueOf(cowshedNumberCattleText.getText()));
                HCattle.update(cattle);
                cowshedNumberCattle.setText(cowshedNumberCattleText.getText());
            }
            cowshedNumberCattleText.setVisible(false);
            cowshedNumberCattle.setVisible(true);
        }
    }

    @FXML
    void editSexCattleActionListener(ActionEvent event) {

    }

    @FXML
    void editLeaveReasonCattleActionListener(ActionEvent event) {
        if(leaveReasonCattle.isVisible()){
            leaveReasonCattle.setVisible(false);
            leaveReasonCattleText.setVisible(true);
            leaveReasonCattleText.setText(leaveReasonCattle.getText());
        }else{
            //TODO nie dziala gdy edytujesz '-'
            if(!leaveReasonCattleText.getText().equals(leaveReasonCattle.getText())){
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setLevaReason(leaveReasonCattleText.getText());
                HCattle.update(cattle);
                leaveReasonCattle.setText(leaveReasonCattleText.getText());
                System.out.println(leaveReasonCattleText.getText());
            }
            leaveReasonCattleText.setVisible(false);
            leaveReasonCattle.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCattleInfo(Cattle cattle) {

        birthDateCattle.setText(cattle.getBirthDate().toString());
        nameCattle.setText(cattle.getName());
        joinDateCattle.setText(cattle.getJoinDate().toString());
        if (cattle.getLeaveDate() != null) {
            leaveDateCattle.setText(cattle.getLeaveDate().toString());
            leaveReasonCattle.setText(cattle.getLevaReason());
        } else {
            leaveDateCattle.setText("-");
            leaveReasonCattle.setText("-");
        }
        earringCattle.setText(cattle.getEarring());
        if(cattle.getCowshedNumber()!=null){
            cowshedNumberCattle.setText(cattle.getCowshedNumber().toString());
        }else{
            cowshedNumberCattle.setText("-");
        }
        sexCattle.setText(cattle.getSex());
        noteCattle.setText(cattle.getNotes());

        ObservableList<Insemination> inseminations = FXCollections.observableArrayList();
        inseminations.addAll(cattle.getInseminationList());

        numberInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, Integer>("idInsemination"));
        dataInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, Date>("inseminationDate"));
        bullInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, String>("bull"));
        resultInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, String>("result"));
        noteInsemination.setCellValueFactory(new PropertyValueFactory<Insemination, String>("notes"));
        listInsemination.setItems(inseminations);

        ObservableList<Calving> calvings = FXCollections.observableArrayList();
        calvings.addAll(cattle.getCalvingList());

        numberCalving.setCellValueFactory(new PropertyValueFactory<Calving, Integer>("idCalving"));
        dateCalving.setCellValueFactory(new PropertyValueFactory<Calving, Date>("calvingDate"));
        calfCalving.setCellValueFactory(new PropertyValueFactory<Calving, String>("calf"));
        noteCalving.setCellValueFactory(new PropertyValueFactory<Calving, String>("notes"));
        listCalving.setItems(calvings);
    }
    
    public void setParentController(ListWindowController parentController) {
        this.parentController = parentController;
    }
    
    public MainController getMc() {
        return mc;
    }

    public void setMc(MainController mc) {
        this.mc = mc;
    }
}
