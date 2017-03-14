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
import javax.persistence.OneToOne;


@Entity
public class InfoEntity implements Serializable {

    
   
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    
    @OneToOne
    private Person person;
    @OneToOne
    private Company company;
    
    @OneToMany(mappedBy = "infoEntity")
    List<Phone> phones;
    
    @ManyToOne
    private Address address;
    
    

    public InfoEntity() {
    }

    public InfoEntity(Integer id, String email) {
        this.id = id;
        this.email = email;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InfoEntity{" + "id=" + id + ", email=" + email + '}';
    }
    
    
    
    

    
}
