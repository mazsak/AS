package controller.ShowWindow;

import controller.AddWindow.AddStatsController;
import controller.AddWindow.AddWindowController;
import controller.Main.MainController;
import hibernate.HCattle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ComboBox<String> sexCattleBox;

    @FXML
    private DatePicker birthDateCattleDate;

    @FXML
    private DatePicker joinDateCattleDate;

    @FXML
    private DatePicker leaveDateCattleDate;

    @FXML
    private TableView<Treatment> listTreatment;

    @FXML
    private TableColumn<Treatment, Integer> numberTreatment;

    @FXML
    private TableColumn<Treatment, Date> startDateTreatment;

    @FXML
    private TableColumn<Treatment, Date> endDateTreatment;

    @FXML
    private TableColumn<Treatment, String> diseaseTreatment;

    @FXML
    private TableColumn<Treatment, String> medicineTreatment;

    @FXML
    private TableColumn<Treatment, String> noteTreatment;

    @FXML
    private TableView<StatsDaily> listStatsDaily;

    @FXML
    private TableColumn<StatsDaily, Integer> numberStatsDaily;

    @FXML
    private TableColumn<StatsDaily, Date> milkingDate;

    @FXML
    private TableColumn<StatsDaily, String> milkingTime;

    @FXML
    private TableColumn<StatsDaily, String> milkAmount;

    @FXML
    private TableView<StatsMonthly> listStatsMonthly;

    @FXML
    private TableColumn<StatsMonthly, Integer> numberStatsMonthly;

    @FXML
    private TableColumn<StatsMonthly, Date> dateStatsMonthly;

    @FXML
    private TableColumn<StatsMonthly, String> proteinStatsMonthly;

    @FXML
    private TableColumn<StatsMonthly, String> fatStatsMonthly;

    @FXML
    private TableColumn<StatsMonthly, String> bacteriaStatsMonthly;

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
        if(birthDateCattle.isVisible()){
            birthDateCattle.setVisible(false);
            birthDateCattleDate.setVisible(true);
            birthDateCattleDate.setValue(LocalDate.parse(birthDateCattle.getText()));
        }else{
            if(!birthDateCattleDate.getValue().toString().equals(birthDateCattle.getText())) {
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setBirthDate(java.sql.Date.valueOf(birthDateCattleDate.getValue()));
                HCattle.update(cattle);
                birthDateCattle.setText(birthDateCattleDate.getValue().toString());
            }
            birthDateCattleDate.setVisible(false);
            birthDateCattle.setVisible(true);
        }
    }

    @FXML
    void editJoinDateCattleActionListener(ActionEvent event) {
        if(joinDateCattle.isVisible()){
            joinDateCattle.setVisible(false);
            joinDateCattleDate.setVisible(true);
            joinDateCattleDate.setValue(LocalDate.parse(joinDateCattle.getText()));
        }else{
            if(!joinDateCattleDate.getValue().toString().equals(joinDateCattle.getText())) {
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setJoinDate(java.sql.Date.valueOf(joinDateCattleDate.getValue()));
                HCattle.update(cattle);
                joinDateCattle.setText(joinDateCattleDate.getValue().toString());
            }
            joinDateCattleDate.setVisible(false);
            joinDateCattle.setVisible(true);
        }
    }

    @FXML
    void editLeaveDateCattleActionListener(ActionEvent event) {
        if(leaveDateCattle.isVisible()){
            leaveDateCattle.setVisible(false);
            leaveDateCattleDate.setVisible(true);
            if(leaveDateCattle.getText().equals("-")){
                leaveDateCattleDate.setValue(LocalDate.now());
            }else{
                leaveDateCattleDate.setValue(LocalDate.parse(leaveDateCattle.getText()));
            }
        }else{
            if(!leaveDateCattleDate.getValue().toString().equals(leaveDateCattle.getText())) {
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setLeaveDate(java.sql.Date.valueOf(leaveDateCattleDate.getValue()));
                HCattle.update(cattle);
                leaveDateCattle.setText(leaveDateCattleDate.getValue().toString());
            }
            leaveDateCattleDate.setVisible(false);
            leaveDateCattle.setVisible(true);
        }
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
        if(sexCattle.isVisible()){
            sexCattle.setVisible(false);
            sexCattleBox.setVisible(true);
            ObservableList<String> sexes = FXCollections.observableArrayList();
            sexes.addAll("XX", "YY");
            sexCattleBox.setItems(sexes);
            sexCattleBox.getSelectionModel().select(sexCattle.getText());
        }else{
            if(!sexCattleBox.getSelectionModel().getSelectedItem().equals(sexCattle.getText())){
                Cattle cattle = HCattle.findByEarring(earringCattle.getText());
                cattle.setSex(sexCattleBox.getSelectionModel().getSelectedItem());
                HCattle.update(cattle);
                sexCattle.setText(sexCattleBox.getSelectionModel().getSelectedItem());
            }
            sexCattleBox.setVisible(false);
            sexCattle.setVisible(true);
        }
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
            }
            leaveReasonCattleText.setVisible(false);
            leaveReasonCattle.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCattleInfo(Cattle cattle) throws ParseException {

        birthDateCattle.setText(cattle.getBirthDate());
        nameCattle.setText(cattle.getName());
        joinDateCattle.setText(cattle.getJoinDate());
        if (cattle.getLeaveDate().equals(java.sql.Date.valueOf(LocalDate.of(1901, Month.FEBRUARY, 28)).toString())){
            leaveDateCattle.setText("-");
        }
        else {
            leaveDateCattle.setText(cattle.getLeaveDate());
        }
        if (cattle.getLevaReason().equals("XD1337")){
            leaveReasonCattle.setText("-");
        }
        else {
            leaveReasonCattle.setText(cattle.getLevaReason());
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

        ObservableList<Treatment> treatments = FXCollections.observableArrayList();
        treatments.addAll(cattle.getTreatmentList());

        numberTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("idTreatment"));
        startDateTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, Date>("startDate"));
        endDateTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, Date>("endDate"));
        diseaseTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, String>("disease"));
        noteTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, String>("note"));
        medicineTreatment.setCellValueFactory(new PropertyValueFactory<Treatment, String>("medicines"));
        listTreatment.setItems(treatments);

        ObservableList<StatsDaily> statsDaily = FXCollections.observableArrayList();
        statsDaily.addAll(cattle.getStatsDailyList());

        numberStatsDaily.setCellValueFactory(new PropertyValueFactory<StatsDaily, Integer>("idDaily"));
        milkingDate.setCellValueFactory(new PropertyValueFactory<StatsDaily, Date>("milkingDate"));
        milkingTime.setCellValueFactory(new PropertyValueFactory<StatsDaily, String>("milkingTime"));
        milkAmount.setCellValueFactory(new PropertyValueFactory<StatsDaily, String>("milkAmount"));
        listStatsDaily.setItems(statsDaily);

        ObservableList<StatsMonthly> statsMothly = FXCollections.observableArrayList();
        statsMothly.addAll(cattle.getStatsMonthlyList());

        numberStatsMonthly.setCellValueFactory(new PropertyValueFactory<StatsMonthly, Integer>("idMonthly"));
        fatStatsMonthly.setCellValueFactory(new PropertyValueFactory<StatsMonthly, String>("fatContent"));
        proteinStatsMonthly.setCellValueFactory(new PropertyValueFactory<StatsMonthly, String>("proteinContent"));
        bacteriaStatsMonthly.setCellValueFactory(new PropertyValueFactory<StatsMonthly, String>("bacteriaContent"));
        dateStatsMonthly.setCellValueFactory(new PropertyValueFactory<StatsMonthly, Date>("testDate"));
        listStatsMonthly.setItems(statsMothly);
    }


    @FXML
    void addStatsActionListener(ActionEvent event) {
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
        awc.setChosen(6);

        parentController.getMc().getMainWindow().setCenter(addWindow);
    }

    @FXML
    void addTreatmentActionListener(ActionEvent event) {
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
        awc.setChosen(5);

        parentController.getMc().getMainWindow().setCenter(addWindow);
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
