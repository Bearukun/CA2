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

    /**
     * Method to answer to a GET request on ../api/Person
     *
     * @return Returns a JSON object with every person from the database.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {

        return gson.toJson(facade.getPersons());

    }

    /**
     * Method to return a specific person object from the mySQL database from
     * the REST-call.
     *
     * @param id The requested ID
     * @return A JSON object consisting of the desired person.
     * @throws PersonException Throws a the exception
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) throws PersonException {

        Person person = facade.getPerson(id);
//        System.out.println("Hello! " + person);
//        System.out.println(facade.getPersons().size());
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
     * Method to answer on a GET call, and return the contactinformation for all
     * persons
     *
     * @return json object containing contactinfo from all persons
     * @throws PersonException The exception
     */
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactinfo() throws PersonException {

        int pSize = facade.getPersons().size();
        if (pSize < 0) {
            throw new PersonException("500");
        }
        return gson.toJson(facade.getPersonsContactinfo());

    }

    /**
     * Method to answer on a GET-call, and return the contactinfo for a specific
     * user.
     *
     * @param id The id of the person that the user wants
     * @return json object containing the information
     * @throws PersonException the exception
     */
    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactinfo(@PathParam("id") int id) throws PersonException {

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

    /**
     * Method to answer a POST call, adding a person.
     *
     * @param json the json object containing the person-data
     * @return returns the person that has been added
     * @throws PersonException the exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addPerson(String json) throws PersonException {

        Person person = gson.fromJson(json, Person.class);

        //check if the required name and email information has been added
        if (person.getFirstName() == null
                || person.getLastName() == null
                || person.getEmail() == null) {
            System.out.println("Person not added: Failed Step 1");
            throw new PersonException("400" + "addStep1");
        }
//        //Check if the required adress informatino has been added
//        else if (person.getAddress().getAdditionalInfo() == null
//                || person.getAddress().getCityInfo() == null
//                || person.getAddress().getStreet() == null) {
//            System.out.println("Person not added: Failed Step 2");
//            throw new PersonException("400" + "addStep2");
//
//        } //Check if any phones has been added
//        else if (person.getPhones().get(0) == null) {
//            System.out.println("Person not added: Failed Phone Step");
//            throw new PersonException("400" + "addStepPhone");
//        } else {
//            System.out.println("Person succesfully added");
//        }
        facade.addPerson(person);
      
        return gson.toJson(person);

    }

    /**
     * Method to accept a PUT call and edit a person.
     *
     * @param json the person object in json format.
     * @return returns the json object from the facade.
     * @throws PersonException The exception.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String editPerson(String json) throws PersonException {

        Person person = gson.fromJson(json, Person.class);
        //check if the required name and email information has been edited correctly
        if (person.getFirstName() == null
                || person.getLastName() == null
                || person.getEmail() == null) {
            System.out.println("Person not added: Failed Step 1");
            throw new PersonException("400" + "addStep1");
        } //Check if the required adress informatino has been edited correctly
        else if (person.getAddress().getAdditionalInfo() == null
                || person.getAddress().getCityInfo() == null
                || person.getAddress().getStreet() == null) {
            System.out.println("Person not added: Failed Step 2");
            throw new PersonException("400" + "addStep2");

        } //Check if the basic phone has been edited correctly
        else if (person.getPhones().get(0) == null) {
            System.out.println("Person not added: Failed Phone Step");
            throw new PersonException("400" + "addStepPhone");
        }

        try {

            return gson.toJson(facade.editPerson(person));

        } catch (NumberFormatException e) {

            return "";

        }

    }

    /**
     * Method that accepts a DELETE call and deletes a person.
     * @param id the id of the person that is going to be deleted.
     * @return returns a json object from the facade.
     * @throws PersonException the exception.
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) throws PersonException {

        Person person = facade.getPerson(id);

        if (person == null) {
            
            throw new PersonException("404");
            
        }
        try {

            return gson.toJson(facade.deletePerson(id));
            
        } catch (Exception e) {
            
            return "";
            
        }

    }

}
