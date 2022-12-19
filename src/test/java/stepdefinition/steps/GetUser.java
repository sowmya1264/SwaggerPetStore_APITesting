package stepdefinition.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefinition.methods.GetRequestMethod;

public class GetUser
{

    public static Scenario scenario;
    @Before()
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    GetRequestMethod getRequestMethod = new GetRequestMethod();

    @Given("I want to get details of an user at {string} with an {string}")
    public void iWantToGetDetailsOfAnUserAtWithAn(String url, String username) {
        getRequestMethod.givenMethod(url,username);}

    @When("I want submit a get request")
    public void iWantSubmitAGetRequest() {
        String responseBody = getRequestMethod.submitGet();
        scenario.log("Response body"+responseBody);
    }

    @Then("I should get  code {string}")
    public void iShouldGetCode(String sc) {

        Integer responseSc = getRequestMethod.getCode(sc);
        scenario.log("Response Status Code: " + responseSc);
    }

    @And("response body with {string} must be displayed")
    public void responseBodyWithMustBeDisplayed(String message) {
        //getRequestMethod.responseMessageUser(message);
        String responseMsg = getRequestMethod.responseMessageUser(message);
        scenario.log("response message:" + responseMsg);
    }
}


