package rest;

import com.google.gson.Gson;
import entity.Person;
import facade.PersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import facade.PersonFacadeInterface;
import javax.ws.rs.DELETE;

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
     * @return A list with every object in JSON format.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) {

        return gson.toJson(facade.getPerson(id));

    }

    /**
     * Method to return a specific person object from the mySQL database.
     *
     * @return A list with every object in JSON format.
     */
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactinfo() {

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
    public String getPersonContactinfo(@PathParam("id") int id) {

        return gson.toJson(facade.getPersonsContactinfo(id));

    }




    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("id") int id) {
        
        return gson.toJson(facade.deletePerson(id));
        
    }
}
