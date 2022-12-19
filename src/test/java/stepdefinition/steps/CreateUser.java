package stepdefinition.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import stepdefinition.methods.CreateUserMethod;

public class CreateUser {

    public Scenario scenario;
    public Response response;
    public ValidatableResponse validatableResponse;

    @Before
    public void before(Scenario scenarioVal)
    {
        this.scenario = scenarioVal;
    }

    @Given("I want to create an user at {string}")
    public void iWantToCreateAnUser(String path)
    {
        CreateUserMethod user = new CreateUserMethod();
        response = user.CreateUserPayload(
                5,"user5","user5","five",
                "user5@gmail.com","password","1234",1,path);


    }

    @Given("I want to create an user at {string} with list")
    public void iWantToCreateAnUserWithList(String path)
    {
        CreateUserMethod user = new CreateUserMethod();
        response = user.CreateUserPayload(
                7,"user7","user", "two","user2@gmail.com",
                "password","1234",1,path);

    }


    @When("I submit a post request")
    public void iSubmitAPostRequest()
    {
        scenario.log("Response body"+ response.getBody().asPrettyString());

    }


    @Then("should get the success {string}")
    public void shouldGetTheSuccess(String sc)
    {
        validatableResponse = response.then();
        String responseStatusCode = String.valueOf(response.getStatusCode());
        Assert.assertEquals(sc,responseStatusCode);
        scenario.log("response status code: " +responseStatusCode);
    }

    @And("the valid response {string}")
    public void theValidResponse(String message)
    {
        JsonPath jsonPath = response.jsonPath();
        String msg = jsonPath.get("message");
        scenario.log("response message:" + msg);
        Assert.assertEquals(message,msg);

    }


    }


