/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mateusz
 */
@Entity
@Table(name = "MEDICINE")
public class Medicine {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_MEDICINE", nullable = false)
    private Integer idMedicine;
    @Column(name = "NAME_")
    private String name;
    @JoinTable(name = "MEDS_USED", joinColumns = {
            @JoinColumn(name = "ID_MEDICINE", referencedColumnName = "ID_MEDICINE")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_TREATMENT", referencedColumnName = "ID_TREATMENT")})
    @ManyToMany
    private List<Treatment> treatments;

    public Medicine() {
    }

    public Medicine(Integer idMedicine) {
        this.idMedicine = idMedicine;
    }

    public Integer getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(Integer idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    @Override
    public String toString() {
        return "Medicine[ idMedicine=" + idMedicine + " ]";
    }

}
