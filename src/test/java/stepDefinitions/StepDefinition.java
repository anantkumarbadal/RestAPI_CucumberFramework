package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.junit.Assert;
import utilities.ApiResources;
import utilities.TestDataBuild;
import utilities.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    //StepDefinition should have only core logical codes lines
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    static Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id; //making it static bcz this variable will be shared across the test case with the same copy and will refer the same
    //if we do not make it static then it will throw null pointer exception or the place_id in payload will be null
    @Given("Add Place Payload with {string}  {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        reqSpec = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address)).log().all();
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        //calling resources from enum
        //constructor will be called with value of resource which you pass
        ApiResources apiResources = ApiResources.valueOf(resource); //resource is coming from the feature file and invoking constructor to fall in this valueOf() method and get different API resources from enum
        System.out.printf("API Resource: " + apiResources.getResource());

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        //making the http method most generic to call
        if (method.equalsIgnoreCase("POST"))
            response = reqSpec.when().post(apiResources.getResource());// use Enum class to declare the constants for the Resources-calling according to the feature file
        else if (method.equalsIgnoreCase("GET"))
            response = reqSpec.when().get(apiResources.getResource());
    }

    @Then("The API call got success with Status code is {int}")
    public void the_api_call_got_success_with_status_code_is(Integer int1) {

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("{string} in response body is {string}")
    //both the values will come from the feature file to validate the responses
    public void in_response_body_is(String keyValue, String expectedValue) {

    //    System.out.println(keyValue);
    //    System.out.println(expectedValue);

    //    assertEquals(js.get(keyValue).toString(), expectedValue); //here have to pass two arguments to validate the response
    //using Utils class
        Assert.assertEquals(getJsonPath(response, keyValue), expectedValue);

    }

    @Then("verify the place_id created maps to {string} using {string}")
    public void verify_the_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        /* prepare request specification
           hit getAPI call
         */
        //End to End functionality here--POST request to GET request
        place_id = getJsonPath(response, "place_id");
        reqSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET"); //using this method to call the http method
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

        reqSpec = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id)); //getting the place_id from the previous test case and deleting here

    }



}