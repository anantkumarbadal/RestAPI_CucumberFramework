package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    //all ReUsable methods

    public static RequestSpecification reqSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if (reqSpec == null) //writting this to avoid the repeat run and replace the previous value in the logging file
        {
            //for logging all the details
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

            reqSpec = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log)) //to log all the requests
                    .addFilter(ResponseLoggingFilter.logResponseTo(log)) //to log all the response
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }
        return reqSpec;
    }

    public static String getGlobalValue(String key) throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
        properties.load(fis);
        return properties.getProperty(key);

    }

    public String getJsonPath(Response response, String key) {

        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();

    }


}
