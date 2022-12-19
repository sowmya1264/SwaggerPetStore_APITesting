package stepdefinition.methods;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BaseTest {

    public static Scenario scenario;
    @Before()
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }
}
