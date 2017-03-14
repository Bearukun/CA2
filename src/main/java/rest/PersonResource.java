package rest;

import entity.Person;
import facade.Facade;
import facade.FacadeInterface;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utility.JSONConverter;

/**
 * REST Web Service
 *
 */
@Path("Person")
public class PersonResource {

    private FacadeInterface facade = new Facade();
    private JSONConverter converter = new JSONConverter();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {

        facade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

    }

    /**
     * Method to return every person object on the mySQL database.
     *
     * @return A list with every object in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {

        List<Person> people = facade.getPersons();
        return converter.getJSONFromPerson(people);

    }

}
