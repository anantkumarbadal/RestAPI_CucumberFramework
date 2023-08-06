package utilities;

import pojoClasses.AddPlace;
import pojoClasses.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    //creating Test Data build file to create the JSON data using POJO classes and serialization concept

    public AddPlace addPlacePayload()

            //using serialization concept and importing the POJO classes
    {
        //creating the object for this class and passing all the POJO classes variables and values to set the data for creating JSON payload
        AddPlace ap =  new AddPlace();

        //setting up data
        ap.setAccuracy(50);
        ap.setAddress("29, side layout, cohen 09");
        ap.setLanguage("French-IN");
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setWebsite("https://rahulshettyacademy.com");
        ap.setName("Frontline house");

        //for types we have to pass the List of values - arrays of values
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park"); //adding all the list values
        myList.add("shop");

        ap.setTypes(myList);

        //for location , we need to create the object for the Location class
        Location lc = new Location();
        lc.setLat(-38.383494);
        lc.setLng(33.427362);
        //passing the Location Pojo class object to the AddPlace  Pojo class
        ap.setLocation(lc); //passing the location object where we have stored the values

        //as we are returning the object to AddPlace class
        return ap;
    }
}
