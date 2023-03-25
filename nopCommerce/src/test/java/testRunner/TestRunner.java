package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features/Customers.feature",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:reports/cucumber.html", "json:reports/cucumber.json"},
        tags = "@regression"
)
public class TestRunner {

}

// Run All Features: "Features/"
// Run Single Features: "Features/Customers.feature"
// Run Multi Features: {"Features/Customers.feature", "Features/Login.feature"}
// Run Multi Tag: "@regression", "@sanity"