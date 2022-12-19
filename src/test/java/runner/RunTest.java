package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = {"src/test/java/features"},
            glue= "stepdefinition/steps",
            monochrome = true,
            dryRun = false
            )
    public class RunTest
{
    private static final Logger logger = LogManager.getLogger(RunTest.class);


}
