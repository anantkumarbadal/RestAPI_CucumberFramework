package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    //all ReUsable methods

    RequestSpecification reqSpec;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        //Google MAP- Add API Example
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //for logging all the details
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log)) //to log all the requests
                .addFilter(ResponseLoggingFilter.logResponseTo(log)) //to log all the response
                .setContentType(ContentType.JSON).build();
        return reqSpec;
    }

    public void getGlobalValue() throws IOException {
        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
        prop.load(fis);
        prop.getProperty("baseUrl");

    }

}
