/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, description;
    private int NumEmployees, cvr;
    private double marketValue;
    
 
    
    
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private InfoEntity infoEntity;

    public Company() {
    }

    public Company(String name, String description, int cvr, int NumEmployees, double marketValue, InfoEntity infoEntity) {
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.NumEmployees = NumEmployees;
        this.marketValue = marketValue;
        this.infoEntity = infoEntity;
    }

    public Company(String name, String description, int NumEmployees, int cvr, double marketValue) {
        this.name = name;
        this.description = description;
        this.NumEmployees = NumEmployees;
        this.cvr = cvr;
        this.marketValue = marketValue;
    }
    
    

  

    public InfoEntity getInfoEntity() {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity) {
        this.infoEntity = infoEntity;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public int getNumEmployees() {
        return NumEmployees;
    }

    public void setNumEmployees(int NumEmployees) {
        this.NumEmployees = NumEmployees;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + ", description=" + description + ", cvr=" + cvr + ", NumEmployees=" + NumEmployees + ", marketValue=" + marketValue + ", infoEntity=" + infoEntity + '}';
    }

  
 
    
}
