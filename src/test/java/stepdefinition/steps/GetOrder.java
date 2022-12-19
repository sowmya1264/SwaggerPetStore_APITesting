package stepdefinition.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import runner.RunTest;
import stepdefinition.methods.GetRequestMethod;

@Owner("Sowmya")
@Severity(SeverityLevel.CRITICAL)
public class GetOrder{

        public static Scenario scenario;
        @Before(order=0)
        public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }


        GetRequestMethod getRequestMethod = new GetRequestMethod();


        private static final Logger logger = LogManager.getLogger(RunTest.class);

         @Given("I want to get details of an order at {string} with an {string}")
         public void iWantToGetDetailsOfAnOrderAtWithAn(String url, String orderid) throws Exception{
             getRequestMethod.givenMethod(url,orderid);}

        @When("I submit a Get request")
        public void iSubmitAGetRequest() {
            String responseBody = getRequestMethod.submitGet();
            scenario.log("Response body"+responseBody);
         }

        @Then("I should get {string}")
        public void iShouldGet(String sc) throws Exception
        {
            Integer responseSc = getRequestMethod.getCode(sc);
            scenario.log("Response Status Code: " + responseSc);
        }

        @And("response body with {string} should be displayed")
        public void responseBodyWithShouldBeDisplayed(String message) throws Exception
        {
            String responseMsg = getRequestMethod.responseMessageId(message);
            scenario.log("response message:" + responseMsg);
        }
    }


