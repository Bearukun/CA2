package facade;

import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;


public class PersonFacadeTest {
    
    private PersonFacadeInterface facade = new PersonFacade();
  
        
    public PersonFacadeTest() {
        
        facade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        
    }

    /**
     * Method to test if the facade can fetch a list of persons on the database.
     */
    @Test
    public void testRecieve() {
        
        List<Person> persons = new ArrayList();
        
        //Get a list of all person objects in the databse
        persons = facade.getPersons();
        
        assertTrue("Expected a list of 1 or more", persons.size() >= 1);
        
    }
    
    /**
     * Method to test if the facades add method is working.
     */
    @Test
    public void testAdd(){
        
        List<Person> persons = new ArrayList();
        
        //Get a list of all person objects in the databse
        persons = facade.getPersons();
        
        int previousSize = persons.size();
        
        Person clone = persons.get(0);
        
        clone.setId(null);
        clone.setFirstName("Test");
        
        facade.addPerson(clone);
        
        List<Person> newPersonsList = new ArrayList();
        
        //Get a list of all person objects in the databse
        newPersonsList = facade.getPersons();
        
        assertTrue("Expected new list to be larger than the old", persons.size() < newPersonsList.size());
        
        //remove the person that has been added
        facade.deletePerson(newPersonsList.get(newPersonsList.size()-1).getId());
        
    }
    
}
