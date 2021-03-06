package exception;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import facade.PersonFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PersonExceptionMapper implements ExceptionMapper<Exception> {

    public PersonExceptionMapper() {
        
         fc.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
         
    }
    
    Gson gson = new Gson();
    PersonFacade fc = new PersonFacade();

    @Override
    public Response toResponse(Exception e) {
        JsonObject jo = new JsonObject();

        //400 - Basic 400, will not work
        if (e.getMessage().equalsIgnoreCase("400")) {
            jo.addProperty("HTTP Status", "400");
            jo.addProperty("Reason", "The server cannot or will not process the request due to an apparent client error");
            return Response.status(400).entity(gson.toJson(jo)).build();

        }//400 - if firstname, lastname, email is not filled-out correctly.
        else if (e.getMessage().equalsIgnoreCase("400" + "addStep1")) {
            jo.addProperty("HTTP Status", "400");
            jo.addProperty("Reason", "The server cannot or will not process the request due to an apparent client error");
            jo.addProperty("Solution", "Remember to make sure that the input in"
                    + " either First Name, Last name or email is not 'null' or empty.");
            return Response.status(400).entity(gson.toJson(jo)).build();

        } //400 - if Address is not filled-out correctly
        else if (e.getMessage().equalsIgnoreCase("400" + "addStep2")) {
            jo.addProperty("HTTP Status", "400");
            jo.addProperty("Reason", "The server cannot or will not process the request due to an apparent client error");
            jo.addProperty("Solution", "Remember to make sure that the inputfields in"
                    + " the Address are not 'null' or empty.");
            return Response.status(400).entity(gson.toJson(jo)).build();

        }//400 - if no phone has been added
        else if (e.getMessage().equalsIgnoreCase("400" + "addStepPhone")) {
            jo.addProperty("HTTP Status", "400");
            jo.addProperty("Reason", "The server cannot or will not process the request due to an apparent client error");
            jo.addProperty("Solution", "Remember to make sure that you input atleast one phone and suiting"
                    + "description, and that the phone field is not 'null' or empty.");
            return Response.status(400).entity(gson.toJson(jo)).build();

        } //404 - Page Not found
        else if (e.getMessage().equalsIgnoreCase("404")) {
            jo.addProperty("HTTP Status", "404");
            jo.addProperty("Reason", "The requested resource could not be found with the given parameter!");
            jo.addProperty("Solution", "Try using a parameter between 1 and " + fc.getPersons().size());
//Following for-loop prints the Stack Trace 
//            for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) {
//            jo.addProperty("StackTrace" + i, Thread.currentThread().getStackTrace()[i].toString());
//            
//            }
//Following for-loop prints the Stack Trace 
//            for (int i = 0; i < e.getStackTrace().length; i++) {
//            jo.addProperty("StackTrace" + i, e.getStackTrace()[i].toString());
//            
//            }
            return Response.status(404).entity(gson.toJson(jo)).build();

        } //405 - Method Not Allowed
        else if (e.getMessage().equalsIgnoreCase("405")) {
            jo.addProperty("HTTP Status", "405");
            jo.addProperty("Reason", "Method not Allowed ");
            return Response.status(405).entity(gson.toJson(jo)).build();

        } //406 - Not Acceptable
        else if (e.getMessage().equalsIgnoreCase("406")) {
            jo.addProperty("HTTP Status", "406");
            jo.addProperty("Reason", "The requested resource is capable of "
                    + "generating only content not acceptable according to the "
                    + "Accept headers sent in the request.");
            return Response.status(406).entity(gson.toJson(jo)).build();
        } //418 - Teapot!
        else if (e.getMessage().equalsIgnoreCase("418")) {
            jo.addProperty("HTTP Status", "418");
            jo.addProperty("Reason", "I am a TeaPot!");
            return Response.status(418).entity(gson.toJson(jo)).build();
        } //500 - Internal Server Error 
        else if (e.getMessage().equalsIgnoreCase("500")) {
            jo.addProperty("HTTP Status", "500");
            jo.addProperty("Reason", "An unexpected condition was encountered on"
                    + " the Server");
            return Response.status(500).entity(gson.toJson(jo)).build();
        } //501 - Not Implemented
        else if (e.getMessage().equalsIgnoreCase("501")) {
            jo.addProperty("HTTP Status", "501");
            jo.addProperty("Reason", "The server does not recognize the request method");
            return Response.status(501).entity(gson.toJson(jo)).build();
        } //503 - Not available
        else if (e.getMessage().equalsIgnoreCase("503")) {
            jo.addProperty("HTTP Status", "503");
            jo.addProperty("Reason", "The server is currently unavailable");
            return Response.status(503).entity(gson.toJson(jo)).build();
        } //505- HTTP not supported
         else if (e.getMessage().equalsIgnoreCase("505")) {
            jo.addProperty("HTTP Status", "505");
            jo.addProperty("Reason", "The server does not support the HTTP "
                    + "protocol version used in the request.");
            return Response.status(505).entity(gson.toJson(jo)).build();
        }

        jo.addProperty("HTTP Status", "404");
        jo.addProperty("Reason", "The requested resource could not be found.");
        return Response.status(404).entity(gson.toJson(jo)).build();
        
    }

}
