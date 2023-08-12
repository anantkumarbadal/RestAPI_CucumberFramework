package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    //Hooks used to set the Pre-conditions and Post-Conditions to run the test cases independently

    //so it will run this before the DeletePlace scenario is executed by looking at the hooks
    @Before("@DeletePlace") //tags from feature file
    public void beforeScenario() throws IOException {
        //execute this code only when the place_id is null
        //write a code to get the place_id

        StepDefinition sd = new StepDefinition();

        if(StepDefinition.place_id==null) //calling static method with classname
        {
            sd.add_place_payload_with("Anant", "English", "India");
            sd.user_calls_with_http_request("AddPlaceAPI", "POST");
            sd.verify_the_place_id_created_maps_to_using("Anant", "GetPlaceAPI");
        }

    }



}
