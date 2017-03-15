package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "IE_ID")
    List<Phone> phones = new ArrayList<>();

    @ManyToOne
    private Address address;

    public InfoEntity() {
    }

    public InfoEntity(String email) {
        this.email = email;
    }

    public void addPhone(Phone phone) {


        this.phones.add(phone);

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "InfoEntity{" + "id=" + id + ", email=" + email + ", phones="
                + phones + ", address=" + address + '}';
    }

}
