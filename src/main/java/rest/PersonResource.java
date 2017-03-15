package rest;

import entity.Person;
import facade.Facade;
import facade.FacadeInterface;
import java.util.List;
import utility.JSONConverter;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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
     * Method to return a specific person object from the mySQL database.
     *
     * @return A list with every object in JSON format.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPerson(@PathParam("id") int id) {

       
        return converter.getJSONFromPerson(facade.getPerson(id));

    }

}
