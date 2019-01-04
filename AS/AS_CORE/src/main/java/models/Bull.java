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
@Table(name = "BULL")
public class Bull {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "ID_BULL", nullable = false)
    private Integer idBull;
    @Column(name = "NAME_")
    private String name;
    @OneToMany(mappedBy = "idBull")
    private List<Insemination> inseminationList = new ArrayList<>();

    public Bull() {
    }

    public Bull(Integer idBull) {
        this.idBull = idBull;
    }

    public Integer getIdBull() {
        return idBull;
    }

    public void setIdBull(Integer idBull) {
        this.idBull = idBull;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Bull[ idBull=" + idBull + " ]";
    }

}
