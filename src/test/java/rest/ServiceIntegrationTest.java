package rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import org.junit.Test;
import org.junit.BeforeClass;

public class ServiceIntegrationTest {

    @BeforeClass
    public static void setUpBeforeAll() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/CA2";
        RestAssured.defaultParser = Parser.JSON;

    }

    public ServiceIntegrationTest() {
    }

    /**
     * Test if server is running, and that it returns status code 200.
     */
    @Test
    public void serverIsRunning() {

        given().
                when().get().
                then().
                statusCode(200);

    }

    /**
     * We know that we'll get a 404 response with and a JSON object back when
     * trying to get a Person that from an ID that doesn't exist.
     */
    @Test
    public void getPersonThatDoesNotExist() {
        given().pathParam("n1", 2000).
                when().get("/api/Person/{n1}").
                then().
                statusCode(404);
    }

    /**
     * We know that we'll get a 200 response with and a JSON object back when
     * trying to get a Person that from an ID that exist.
     */
    @Test
    public void getPerson() {
        given().pathParam("n1", 1).
                when().get("/api/Person/{n1}").
                then().
                statusCode(200);
    }

}
