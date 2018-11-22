/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mateusz
 */
@Entity
@Table(name = "STATS_MONTHLY")
public class StatsMonthly {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_MONTHLY", nullable = false)
    private Integer idMonthly;
    @Column(name = "TEST_DATE")
    @Temporal(TemporalType.DATE)
    private Date testDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PROTEIN_CONTENT")
    private Double proteinContent;
    @Column(name = "FAT_CONTENT")
    private Double fatContent;
    @Column(name = "BACTERIA_CONTENT")
    private Integer bacteriaContent;
    @JoinColumn(name = "ID_CATTLE", referencedColumnName = "ID_CATTLE")
    @ManyToOne
    private Cattle idCattle;

    public StatsMonthly() {
    }

    public StatsMonthly(Integer idMonthly) {
        this.idMonthly = idMonthly;
    }

    public Integer getIdMonthly() {
        return idMonthly;
    }

    public void setIdMonthly(Integer idMonthly) {
        this.idMonthly = idMonthly;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Double getProteinContent() {
        return proteinContent;
    }

    public void setProteinContent(Double proteinContent) {
        this.proteinContent = proteinContent;
    }

    public Double getFatContent() {
        return fatContent;
    }

    public void setFatContent(Double fatContent) {
        this.fatContent = fatContent;
    }

    public Integer getBacteriaContent() {
        return bacteriaContent;
    }

    public void setBacteriaContent(Integer bacteriaContent) {
        this.bacteriaContent = bacteriaContent;
    }

    public Cattle getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(Cattle idCattle) {
        this.idCattle = idCattle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonthly != null ? idMonthly.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "StatsMonthly[ idMonthly=" + idMonthly + " ]";
    }

}
