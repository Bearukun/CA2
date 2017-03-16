package facade;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public class PersonFacade implements PersonFacadeInterface {

    //Interface used to interact with the entity manager facotry for the persistence unit. 
    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        
        //Adding the entityManagerFactory to PersonFacade. 
        this.emf = emf;

    }

    /**
     * Method used to get a person from the database.
     * @param id Id of the person to be fetched.
     * @return Returns a Person object corresponding to the given id.
     */
    @Override
    public Person getPerson(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);

    }

    /**
     * Method to retrieve all persons from the database.
     * @return Returns a list of Person(s).
     */
    @Override
    public List<Person> getPersons() {

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();

    }

    /**
     * Method used to get persons with a specific zip code.
     * @param zipCode int used to specify what zipcode we want persons from.
     * @return Returns a list of Person(s) with the specified zipCode.
     */
    @Override
    public List<Person> getPersons(int zipCode) {

        EntityManager em = emf.createEntityManager();
        //Create a query, that selects Persons where the zipcode matches with the given zipcode.
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address .city.zipCode = :zipCode", Person.class);
        //Set parameter
        query.setParameter("zipCode", zipCode);
        //Execute and return
        return query.getResultList();

    }

    /**
     * Method to retrieve all persons contactinfo.
     * @return Returns a list of custom Json object with contact information.
     */
    @Override
    public List<JsonObject> getPersonsContactinfo() {

        List<JsonObject> contactinfo = new ArrayList();

        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);

        for (int i = 0; i < query.getResultList().size(); i++) {

            JsonObject jo = new JsonObject();

            jo.addProperty("id", query.getResultList().get(i).getId());
            jo.addProperty("firstName", query.getResultList().get(i).getFirstName());
            jo.addProperty("lastName", query.getResultList().get(i).getLastName());
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

    /**
     * Method used to get the contact information from a specific user.
     * @param id id used to specify what user we want.
     * @return Returns a custom JsonObject with the desired information.
     */
    @Override
    public JsonObject getPersonsContactinfo(int id) {

        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);

        JsonObject jo = new JsonObject();

        jo.addProperty("id", person.getId());
        jo.addProperty("firstName", person.getFirstName());
        jo.addProperty("lastName", person.getLastName());
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

    /**
     * Method used to delete a person from the databse.
     * @param id Id of the persons that needs to be removed. 
     * @return Returns a json object that tells that the user has been removed.
     */
    @Override
    public JsonObject deletePerson(int id) {

        EntityManager em = emf.createEntityManager();

        Person person = null;

        try {

            em.getTransaction().begin();
            person = em.merge(em.find(Person.class, id));
            em.remove(person);
            em.getTransaction().commit();

        } catch (PersistenceException e) {

            em.getTransaction().rollback();

        } finally {

            em.close();

        }

        JsonObject jo = new JsonObject();

        jo.addProperty("message", "Person: " + person.getFirstName() + " with id: " + id + " has been removed.");

        return jo;

    }

    /**
     * Method to add a person
     *
     * @param person Person object to be added
     * @return Returns added person
     */
    @Override
    public String addPerson(Person person) {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

        } catch (PersistenceException ex) {

            em.getTransaction().rollback();
            return ex.getMessage();

        } finally {

            em.close();

        }

        return "OK";

    }

    /**
     * Method to edit a person.
     * @param person Person object that has been edited.
     * @return Returns a custom jsonObject that tells the person has been edited.
     */
    @Override
    public JsonObject editPerson(Person person) {

        EntityManager em = emf.createEntityManager();
        JsonObject jo = new JsonObject();

        try {

            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();

        } catch (PersistenceException ex) {

            em.getTransaction().rollback();
            jo.addProperty("error", ex.getMessage());

        }

        jo.addProperty("message", "Person has been edited");

        return jo;

    }

}
