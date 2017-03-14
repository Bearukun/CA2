/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street, AdditionalInfo;
    
    @OneToMany(mappedBy = "address")
    List<InfoEntity> infoEntity;
    
    @ManyToOne
    private CityInfo cityInfo;
    

    public Address() {
    }

    public Address(String street, String AdditionalInfo, List<InfoEntity> infoEntity, CityInfo cityInfo) {
        this.street = street;
        this.AdditionalInfo = AdditionalInfo;
        this.infoEntity = infoEntity;
        this.cityInfo = cityInfo;
    }

   

    public List<InfoEntity> getInfoEntity() {
        return infoEntity;
    }

    public void setInfoEntity(List<InfoEntity> infoEntity) {
        this.infoEntity = infoEntity;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }
    
    

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setAdditionalInfo(String AdditionalInfo) {
        this.AdditionalInfo = AdditionalInfo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street=" + street + ", AdditionalInfo=" + AdditionalInfo + ", infoEntity=" + infoEntity + ", cityInfo=" + cityInfo + '}';
    }

    

    
    
}
