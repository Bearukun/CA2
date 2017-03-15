package facade;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Company;
import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class PersonFacade implements PersonFacadeInterface {

    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {

        this.emf = emf;

    }

    @Override
    public Person getPerson(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);

    }

    @Override
    public List<Person> getPersons() {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();

    }

    @Override
    public List<Person> getPersons(int zipCode) {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address .city.zipCode = :zipCode", Person.class);
        query.setParameter("zipCode", zipCode);
        return query.getResultList();

    }
    
    @Override
    public List<JsonObject> getPersonsContactinfo() {

        List<JsonObject> contactinfo = new ArrayList();
        
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        
        for (int i = 0; i < query.getResultList().size(); i++) {
            
            JsonObject jo = new JsonObject();
            
            jo.addProperty("id", query.getResultList().get(i).getId());
            jo.addProperty("firstName", query.getResultList().get(i).getFirstName());
            jo.addProperty("firstName", query.getResultList().get(i).getLastName());
            jo.addProperty("email", query.getResultList().get(i).getEmail());
            
            JsonArray phones = new JsonArray();
            
            for (int j = 0; j < query.getResultList().get(i).getPhones().size(); j++) {
                
                JsonObject phone = new JsonObject();
                
                phone.addProperty("id", query.getResultList().get(i).getPhones().get(j).getId());
                phone.addProperty("number", query.getResultList().get(i).getPhones().get(j).getNumber());
                phone.addProperty("description", query.getResultList().get(i).getPhones().get(j).getDescription());
                
                phones.add(phone);
                                
            }
            
            jo.add("phones", phones);
            
            
            contactinfo.add(jo);
            
            
            
        }
        
        return contactinfo;

    }
    
    @Override
    public JsonObject getPersonsContactinfo(int id) {

        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        
        
        
        JsonObject jo = new JsonObject();
            
            jo.addProperty("id", person.getId());
            jo.addProperty("firstName", person.getFirstName());
            jo.addProperty("firstName", person.getLastName());
            jo.addProperty("email", person.getEmail());
            
            JsonArray phones = new JsonArray();
            
            for (int j = 0; j < person.getPhones().size(); j++) {
                
                JsonObject phone = new JsonObject();
                
                phone.addProperty("id", person.getPhones().get(j).getId());
                phone.addProperty("number", person.getPhones().get(j).getNumber());
                phone.addProperty("description", person.getPhones().get(j).getDescription());
                
                phones.add(phone);
                                
            }
            
            jo.add("phones", phones);
            
        return jo;   

    }

 

}
