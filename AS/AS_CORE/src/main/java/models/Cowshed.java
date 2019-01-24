/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mateusz
 */
@Entity
@Table(name = "COWSHED")
public class Cowshed {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_COWSHED", nullable = false)
    private Integer idCowshed;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "NAME_")
    private String name;
    @Column(name = "INFO")
    private String info;
    @OneToMany(mappedBy = "idCowshed", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)

    private List<Team> teamList;

    public Cowshed() {
        this.teamList = new ArrayList<>();
    }

    public Cowshed(Integer idCowshed) {
        this.idCowshed = idCowshed;
        this.teamList = new ArrayList<>();
    }

    public Integer getIdCowshed() {
        return idCowshed;
    }

    public void setIdCowshed(Integer idCowshed) {
        this.idCowshed = idCowshed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @XmlTransient
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void addTeam(Team team){
        this.teamList.add(team);
    }

    @Override
    public String toString() {
        return "Cowshed[ idCowshed=" + idCowshed + " ]";
    }

}
