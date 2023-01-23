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
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    //StepDefinition should have only core logical codes lines
    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    @Given("Add Place Payload")
    public void add_place_payload() throws FileNotFoundException {

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload());
    }
    @When("User calls {string} with post http request")
    public void user_calls_with_post_http_request(String string) {

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    response = res.when()
                .post("/maps/api/place/add/json")
                .then().spec(resSpec) //here used the ResponseSpecification object where we stored the Assertions and validations
                .extract().response();
    }
    @Then("The API call got success with Status code is {int}")
    public void the_api_call_got_success_with_status_code_is(Integer int1) {

        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}") //both the values will come from the feature file to validate the responses
    public void in_response_body_is(String keyValue, String expectedValue) {

        System.out.println(keyValue);
        System.out.println(expectedValue);

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
      //  assertEquals(js.get(keyValue).toString(),expectedValue); //here have to pass two arguments to validate the response
        assertEquals(js.get(keyValue).toString(),expectedValue);
    }

}
