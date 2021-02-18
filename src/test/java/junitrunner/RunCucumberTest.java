package junitrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber-report.json",
                 "de.monochromata.cucumber.report.PrettyReports:target/cucumber"
        },
		 features = {"src/test/resources/features"}, 
        glue = {"stepdefs"},
        strict = true
)

/**
 * Entry point for tests
 * @author tanujkumarvishnoi
 *
 */
public class RunCucumberTest {

}
