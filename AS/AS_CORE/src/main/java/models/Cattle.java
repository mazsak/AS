/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.List;

/**
 * @author Mateusz
 */
@Entity
@Table(name = "CATTLE")
public class Cattle {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_CATTLE", nullable = false)
    private Integer idCattle;
    @Column(name = "NAME_")
    private String name;
    @Column(name = "EARRING")
    private String earring;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "COWSHED_NUMBER")
    private Integer cowshedNumber;
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.DATE)
    private Date joinDate;
    @Column(name = "LEAVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date leaveDate;
    @Column(name = "LEAVE_REASON")
    private String leaveReason;
    @Column(name = "NOTES")
    private String notes;
    @ManyToMany(mappedBy = "cattleList")
    private List<Team> teamList;
    @OneToMany(mappedBy = "idCattle")
    private List<Insemination> inseminationList;
    @OneToMany(mappedBy = "idCattle")
    private List<Calving> calvingList;
    @OneToMany(mappedBy = "idCalf")
    private List<Calving> calvingList1;
    @OneToMany(mappedBy = "idCattle")
    private List<StatsDaily> statsDailyList;
    @OneToMany(mappedBy = "idCattle")
    private List<Treatment> treatmentList;
    @OneToMany(mappedBy = "idCattle")
    private List<StatsMonthly> statsMonthlyList;

    public Cattle() {
    }

    public Cattle(Integer idCattle) {
        this.idCattle = idCattle;
    }

    public Integer getIdCattle() {
        return idCattle;
    }

    public void setIdCattle(Integer idCattle) {
        this.idCattle = idCattle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEarring() {
        return earring;
    }

    public void setEarring(String earring) {
        this.earring = earring;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getCowshedNumber() {
        return cowshedNumber;
    }

    public void setCowshedNumber(Integer cowshedNumber) {
        this.cowshedNumber = cowshedNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLevaReason() {
        return leaveReason;
    }

    public void setLevaReason(String levaReason) {
        this.leaveReason = levaReason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    @XmlTransient
    public List<Insemination> getInseminationList() {
        return inseminationList;
    }

    public void setInseminationList(List<Insemination> inseminationList) {
        this.inseminationList = inseminationList;
    }

    @XmlTransient
    public List<Calving> getCalvingList() {
        return calvingList;
    }

    public void setCalvingList(List<Calving> calvingList) {
        this.calvingList = calvingList;
    }

    @XmlTransient
    public List<Calving> getCalvingList1() {
        return calvingList1;
    }

    public void setCalvingList1(List<Calving> calvingList1) {
        this.calvingList1 = calvingList1;
    }

    @XmlTransient
    public List<StatsDaily> getStatsDailyList() {
        return statsDailyList;
    }

    public void setStatsDailyList(List<StatsDaily> statsDailyList) {
        this.statsDailyList = statsDailyList;
    }

    @XmlTransient
    public List<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    @XmlTransient
    public List<StatsMonthly> getStatsMonthlyList() {
        return statsMonthlyList;
    }

    public void setStatsMonthlyList(List<StatsMonthly> statsMonthlyList) {
        this.statsMonthlyList = statsMonthlyList;
    }

    @Override
    public String toString() {
        return "Cattle[ idCattle=" + idCattle + " ]";
    }

}
