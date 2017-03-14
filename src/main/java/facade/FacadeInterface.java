package facade;

import entity.Company;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface FacadeInterface {

    public void addEntityManagerFactory(EntityManagerFactory emf);
    
    public Person getPerson(int id);

    public List<Person> getPersons();

    public List<Person> getPersons(int zipCode);

    public Company getCompany(int cvr);

}
