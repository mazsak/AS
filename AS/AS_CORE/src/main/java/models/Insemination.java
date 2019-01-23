/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mateusz
 */
@Entity
@Table(name = "INSEMINATION")
public class Insemination {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_INSEMINATION", nullable = false)
    private Integer idInsemination;
    @Column(name = "INSEMINATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inseminationDate;
    @Column(name = "RESULT")
    private String result;
    @Column(name = "NOTES")
    private String notes;
    @JoinColumn(name = "ID_CATTLE", referencedColumnName = "ID_CATTLE")
    @ManyToOne
    private Cattle idCattle;
    @JoinColumn(name = "ID_BULL", referencedColumnName = "ID_BULL")
    @ManyToOne
    private Bull idBull;

    public Insemination() {
    }

    public Insemination(Integer idInsemination) {
        this.idInsemination = idInsemination;
    }

    public Integer getIdInsemination() {
        return idInsemination;
    }

    public void setIdInsemination(Integer idInsemination) {
        this.idInsemination = idInsemination;
    }

    public String getInseminationDate() {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormatter.format(inseminationDate);
    }

    public void setInseminationDate(Date inseminationDate) {
        this.inseminationDate = inseminationDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Cattle getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(Cattle idCattle) {
        this.idCattle = idCattle;
    }

    public Bull getIdBull() {
        return idBull;
    }

    public String getBull(){
        return idBull.getName();
    }

    public void setIdBull(Bull idBull) {
        this.idBull = idBull;
    }

    @Override
    public String toString() {
        return "Insemination[ idInsemination=" + idInsemination + " ]";
    }

}
