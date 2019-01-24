/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import hibernate.HCattle;
import hibernate.HTeam;
import javafx.scene.control.CheckBox;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;
    @Column(name = "LEAVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;
    @Column(name = "LEVA_REASON")
    private String leaveReason;
    @Column(name = "NOTES")
    private String notes;
    @ManyToMany(mappedBy = "cattleList")
    private List<Team> teamList = new ArrayList<>();
    @OneToMany(mappedBy = "idCattle")
    private List<Insemination> inseminationList = new ArrayList<>();
    @OneToMany(mappedBy = "idCattle")
    private List<Calving> calvingList = new ArrayList<>();
    @OneToMany(mappedBy = "idCalf")
    private List<Calving> calvingList1 = new ArrayList<>();
    @OneToMany(mappedBy = "idCattle")
    private List<StatsDaily> statsDailyList = new ArrayList<>();
    @OneToMany(mappedBy = "idCattle")
    private List<Treatment> treatmentList = new ArrayList<>();
    @OneToMany(mappedBy = "idCattle")
    private List<StatsMonthly> statsMonthlyList = new ArrayList<>();
    @Transient
    private CheckBox select;

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

    public Cattle() {
        this.select = new CheckBox();
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

    public String getBirthDate() {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormatter.format(birthDate);
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getJoinDate() {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormatter.format(joinDate);
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getLeaveDate() {
        DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return outputFormatter.format(leaveDate);
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

    public String getCowshed(){
        String tmp = null;
        StringBuilder result = new StringBuilder();
        for (Team team : teamList) {
            if (!team.getIdCowshed().getName().equals(tmp)) {
                tmp = team.getIdCowshed().getName();
                result.append(team.getIdCowshed().getName()).append(" ");
            }
        }
        return result.toString();
    }

    public String getTeamEAT(){
        StringBuilder result = new StringBuilder();
        for (Team team : teamList) {
            if (team.getType().equals("EAT")) {
                result.append(team.getName()).append(" ");
            }
        }
        return result.toString();
    }

    public void replaceTeamEAT(Team teamNew) {
        for (Team team : teamList) {
            if (team.getType().equals("EAT")) {

                team.removeCattle(this);
                teamList.remove(team);
                HTeam.update(team);

                teamNew.addCattleToList(this);
                teamList.add(teamNew);
                HTeam.update(teamNew);

                HCattle.update(this);
                return;
            }
        }
    }

    public void deleteTeamSICK(Team team) {
        team.removeCattle(this);
        HTeam.update(team);
        HCattle.update(this);
    }

    public void addTeamSICK(Team team) {
        team.addCattleToList(this);
        addToTeamList(team);
        HTeam.update(team);
        HCattle.update(this);
    }
    
    public String getTeamSICK(){
        StringBuilder result = new StringBuilder();
        for (Team team : teamList) {
            if (team.getType().equals("SICK")) {
                result.append(team.getName()).append(" ");
            }
        }
        return result.toString();
    }

    @XmlTransient
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void addToTeamList(Team team){
        teamList.add(team);
    }

    @XmlTransient
    public List<Insemination> getInseminationList() {
        return inseminationList;
    }

    public void setInseminationList(List<Insemination> inseminationList) {
        this.inseminationList = inseminationList;
    }

    public void addToInseminationList(Insemination insemination){
        inseminationList.add(insemination);
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

    public void addToCalvingListMotherForCalf(Calving calving){
        calvingList.add(calving);
    }

    public void setCalvingList1(List<Calving> calvingList1) {
        this.calvingList1 = calvingList1;
    }

    public void addToCalvingListCalfForMother(Calving calving){
        calvingList1.add(calving);
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

    public void addTreatmentList(Treatment treatment) {
        treatmentList.add(treatment);
    }

    @Override
    public String toString() {
        return "Cattle[ idCattle=" + idCattle + " ]";
    }

}
