package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;


@Entity
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private String firstName, lastName;
    
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Hobby> hobbies = new ArrayList();
    
    public Person() {
    }

    public Person(String firstName, String lastName, String email) {
        super(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }
    
    public void addHobby(Hobby hobby){
        
        this.hobbies.add(hobby);
        
    }
    
    
    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
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

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + '}';
    }
    
}
