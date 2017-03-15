package facade;

import com.google.gson.JsonObject;
import entity.Company;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface PersonFacadeInterface {

    public void addEntityManagerFactory(EntityManagerFactory emf);

    public Person getPerson(int id);

    public List<Person> getPersons();

    public List<Person> getPersons(int zipCode);

    public List<JsonObject> getPersonsContactinfo();

    public JsonObject getPersonsContactinfo(int id);

}
