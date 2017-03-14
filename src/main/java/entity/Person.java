package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id;
    private String firstName, lastName;
    
    @ManyToMany(mappedBy = "persons", cascade = CascadeType.ALL)
    private List<Hobby> hobbies;
    
    
   
   
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private InfoEntity infoEntity;

    public Person() {
    }

    public Person(String firstName, String lastName, List<Hobby> hobbies, InfoEntity infoEntity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
        this.infoEntity = infoEntity;
    }

    public Person(String firstName, String lastName, List<Hobby> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }
    
    
    

    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public InfoEntity getInfoEntity() {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity) {
        this.infoEntity = infoEntity;
    }
    
    
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + ", infoEntity=" + infoEntity + '}';
    }

    

    
    
}
