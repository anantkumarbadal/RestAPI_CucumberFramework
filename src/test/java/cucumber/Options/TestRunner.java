package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/features",
        glue = {"stepDefinitions"},
        plugin = "json:target/jsonReports/cucumber-report.json")

// tags = "@DeletePlace"

//using this tags we can run the particular test from the feature file

public class TestRunner {

}

//compile test verify in maven- mvn test verify= for html report