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
@Table(name = "STATS_DAILY")
public class StatsDaily {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_DAILY", nullable = false)
    private Integer idDaily;
    @Column(name = "MILK_AMOUNT")
    private Integer milkAmount;
    @Column(name = "MILKING_DATE")
    @Temporal(TemporalType.DATE)
    private Date milkingDate;
    @Column(name = "MILKING_TIME")
    private String milkingTime;
    @JoinColumn(name = "ID_CATTLE", referencedColumnName = "ID_CATTLE")
    @ManyToOne
    private Cattle idCattle;

    public StatsDaily() {
    }

    public StatsDaily(Integer idDaily) {
        this.idDaily = idDaily;
    }

    public Integer getIdDaily() {
        return idDaily;
    }

    public void setIdDaily(Integer idDaily) {
        this.idDaily = idDaily;
    }

    public Integer getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(Integer milkAmount) {
        this.milkAmount = milkAmount;
    }

    public Date getMilkingDate() {
        return milkingDate;
    }

    public void setMilkingDate(Date milkingDate) {
        this.milkingDate = milkingDate;
    }

    public String getMilkingTime() {
        return milkingTime;
    }

    public void setMilkingTime(String milkingTime) {
        this.milkingTime = milkingTime;
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
        hash += (idDaily != null ? idDaily.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "StatsDaily[ idDaily=" + idDaily + " ]";
    }

}
