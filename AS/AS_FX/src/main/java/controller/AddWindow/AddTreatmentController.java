package controller.AddWindow;

import hibernate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Cowshed;
import models.Medicine;
import models.Team;
import models.Treatment;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;

public class AddTreatmentController implements Initializable {
    private List<Team> groups;

    @FXML
    private ComboBox<String> cowshed;

    @FXML
    private ComboBox<String> group;

    @FXML
    private ComboBox<String> cattle;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField disease;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextArea note;

    @FXML
    private ComboBox<String> medicine;

    @FXML
    private ListView<String> listMedicine;

    @FXML
    void addMedicineActionListener(ActionEvent event) {
        listMedicine.getItems().add(medicine.getSelectionModel().getSelectedItem());
    }

    @FXML
    void addTreatmentActionListener(ActionEvent event) {
        if (!cattle.getValue().isEmpty() && startDate.getValue() != null && !disease.getText().isEmpty()) {
            Treatment treatment = new Treatment();
            treatment.setDisease(disease.getText());
            treatment.setStartDate(java.sql.Date.valueOf(startDate.getValue()));
            if (endDate.getValue() == null) {
                LocalDate lc = LocalDate.of(1901, Month.FEBRUARY, 28);
                LocalDate lc2 = lc.plusDays(1);
                final long hours12 = 12L * 60L * 60L * 1000L;
                Date date = new Date(1901, 2, 28);
                treatment.setEndDate(java.sql.Date.valueOf(lc));
            } else {
                treatment.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
            }
            groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()).addTreatmentList(treatment);
            treatment.setIdCattle(groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));

            HTreatment.save(treatment);
            HCattle.update(groups.get(group.getSelectionModel().getSelectedIndex())
                    .getCattleList().get(cattle.getSelectionModel().getSelectedIndex()));


            for (String medicineItem : listMedicine.getItems()) {
                if (HMedicine.searchByName(medicineItem)) {
                    Medicine m = new Medicine();
                    m.setName(medicineItem);
                    treatment.addMedicine(m);
                    m.addTreatment(treatment);
                    HMedicine.save(m);
                    HTreatment.update(treatment);
                }
            }
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

        for (int i = 0; i < groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().size(); i++) {
            cattles.add(groups.get(group.getSelectionModel().getSelectedIndex()).getCattleList().get(i).getEarring());
        }
        cattle.setItems(cattles);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cowshed> csh = HCowshed.read();
        ObservableList<String> cowsheds = FXCollections.observableArrayList();
        for (int i = 0; i < csh.size(); i++) {
            cowsheds.add(csh.get(i).getName());
        }
        cowshed.setItems(cowsheds);

        List<Medicine> medi = HMedicine.read();
        ObservableList<String> medicines = FXCollections.observableArrayList();
        for (int i = 0; i < csh.size(); i++) {
            medicines.add(medi.get(i).getName());
        }
        medicine.setItems(medicines);
    }
}
