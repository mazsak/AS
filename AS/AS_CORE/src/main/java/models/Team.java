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
@Table(name = "TEAM")
public class Team {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_GROUP", nullable = false)
    private Integer idGroup;
    @Column(name = "NAME_")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @JoinTable(name = "TEAM_CATTLE", joinColumns = {
            @JoinColumn(name = "ID_GROUP", referencedColumnName = "ID_GROUP")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_CATTLE", referencedColumnName = "ID_CATTLE")})
    @ManyToMany
    private List<Cattle> cattleList = new ArrayList<>();
    @JoinColumn(name = "ID_COWSHED", referencedColumnName = "ID_COWSHED")
    @ManyToOne
    private Cowshed idCowshed;

    public Team() {
    }

    public Team(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public List<Cattle> getCattleList() {
        return cattleList;
    }

    public void setCattleList(List<Cattle> cattleList) {
        this.cattleList = cattleList;
    }

    public void addCattleToList(Cattle cattle){
        cattleList.add(cattle);
    }

    public Cowshed getIdCowshed() {
        return idCowshed;
    }

    public void setIdCowshed(Cowshed idCowshed) {
        this.idCowshed = idCowshed;
    }

    @Override
    public String toString() {
        return "Team[ idGroup=" + idGroup + " ]";
    }

}
