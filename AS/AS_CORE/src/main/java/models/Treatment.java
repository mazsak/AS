/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mateusz
 */
@Entity
@Table(name = "TREATMENT")
public class Treatment {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_TREATMENT", nullable = false)
    private Integer idTreatment;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;//
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;//
    @Column(name = "DISEASE")
    private String disease;//
    @Column(name = "NOTES")
    private String notes;//
    @ManyToMany(mappedBy = "treatments")
    private List<Medicine> medicines = new ArrayList<>();//
    @JoinColumn(name = "ID_CATTLE", referencedColumnName = "ID_CATTLE")
    @ManyToOne
    private Cattle idCattle;

    public Treatment() {
    }

    public Treatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Integer getIdTreatment() {
        return idTreatment;
    }

    public void setIdTreatment(Integer idTreatment) {
        this.idTreatment = idTreatment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Medicine> getMedsUsedList() {
        return medicines;
    }

    public void setMedsUsedList(List<Medicine> medsUsedList) {
        this.medicines = medsUsedList;
    }

    public String getMedicines() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<medicines.size(); i++){
            sb.append(medicines.get(i).getName());
            sb.append(" ");
        }
        String result = sb.toString();
        return result;
    }

    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public Cattle getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(Cattle idCattle) {
        this.idCattle = idCattle;
    }

    @Override
    public String toString() {
        return "Treatment[ idTreatment=" + idTreatment + " ]";
    }

}
