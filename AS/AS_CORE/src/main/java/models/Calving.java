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
@Table(name = "CALVING")
public class Calving {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_CALVING", nullable = false)
    private Integer idCalving;
    @Column(name = "CALVING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calvingDate;
    @Column(name = "NOTES")
    private String notes;
    @JoinColumn(name = "ID_CATTLE", referencedColumnName = "ID_CATTLE")
    @ManyToOne
    private Cattle idCattle;
    @JoinColumn(name = "ID_CALF", referencedColumnName = "ID_CATTLE")
    @ManyToOne
    private Cattle idCalf;

    public Calving() {
    }

    public Calving(Integer idCalving) {
        this.idCalving = idCalving;
    }

    public Integer getIdCalving() {
        return idCalving;
    }

    public void setIdCalving(Integer idCalving) {
        this.idCalving = idCalving;
    }

    public String getCalvingDate() {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormatter.format(calvingDate);
    }

    public void setCalvingDate(Date calvingDate) {
        this.calvingDate = calvingDate;
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

    public Cattle getIdCalf() {
        return idCalf;
    }

    public String getCalf(){
        return idCalf.getEarring();
    }

    public void setIdCalf(Cattle idCalf) {
        this.idCalf = idCalf;
    }

    @Override
    public String toString() {
        return "Calving[ idCalving=" + idCalving + " ]";
    }

}
