package rest;

import com.google.gson.Gson;
import entity.Person;
import exception.PersonException;
import facade.PersonFacade;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import facade.PersonFacadeInterface;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 */
@Path("Person")
public class PersonResource {

    private PersonFacadeInterface facade = new PersonFacade();
    private static Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {

        facade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {

        return gson.toJson(facade.getPersons());

    }

    /**
     * Method to return a specific person object from the mySQL database.
     *
     * @param id
     * @return A list with every object in JSON format.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) throws PersonException {

        Person person = facade.getPerson(id);
        System.out.println("Hello! " + person);
        System.out.println(facade.getPersons().size());
        if (person == null) {

            throw new PersonException("404");

        }

        try {

            return gson.toJson(facade.getPerson(id));

        } catch (NumberFormatException e) {

            return "";

        }

    }

    /**
     * Method to return a specific person object from the mySQL database.
     *
     * @return A list with every object in JSON format.
     * @throws exception.PersonException
     */
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactinfo() throws PersonException {
        int pSize = facade.getPersons().size();
        if( pSize < 0){
        throw new PersonException("500");
        }
        return gson.toJson(facade.getPersonsContactinfo());

    }

    /**
     * Method to return a specific person object from the mySQL database.
     *
     * @return A list with every object in JSON format.
     */
    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactinfo(@PathParam("id") int id) throws PersonException{

        Person person = facade.getPerson(id);
        System.out.println("Hello! " + person);
        if (person == null) {

            throw new PersonException("404");

        }

        try {

            return gson.toJson(facade.getPersonsContactinfo(id));

        } catch (NumberFormatException e) {

            return "";
        

    }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPerson(String json) throws PersonException{
        
        Person person = gson.fromJson(json, Person.class);
        
        //check if the required name and email information has been added
        if (person.getFirstName() == null || 
                person.getLastName() == null ||
                person.getEmail() == null) {
            System.out.println("Person not added: Failed Step 1");
            throw new PersonException("400" + "addStep1");
        } 
        //Check if the required adress informatino has been added
        else if ( person.getAddress().getAdditionalInfo() == null || 
                person.getAddress().getCityInfo() == null ||
                person.getAddress().getStreet() == null){
             System.out.println("Person not added: Failed Step 2");
            throw new PersonException("400" + "addStep2");
            
        } 
        //Check if any phones has been added
        else if (person.getPhones().get(0) == null){
             System.out.println("Person not added: Failed Phone Step");
            throw new PersonException("400" + "addStepPhone");
        } else
        
        System.out.println("Person succesfully added");
        facade.addPerson(person);
        return gson.toJson(person);
        
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String editPerson(String json) {
        
        Person person = gson.fromJson(json, Person.class);
        facade.editPerson(person);
        return gson.toJson(person);
        
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) {

        return gson.toJson(facade.deletePerson(id));

    }
}
