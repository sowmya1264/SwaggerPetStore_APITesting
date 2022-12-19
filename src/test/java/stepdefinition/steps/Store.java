package stepdefinition.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


@Owner(value = "Sowmya")
@Severity(SeverityLevel.CRITICAL)
public class Store {

    public Scenario scenario;

    static RequestSpecification requestSpecification;
    static Response response;
    static ValidatableResponse validatableResponse;

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("I want to make a get request {string}")
    public void i_want_to_make_a_get_request(String uri)
    {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2")
                .basePath(uri)
                .accept(ContentType.JSON);

    }

    @When("I submit a GET request")
    public void iSubmitAGETRequest()
    {
        response = requestSpecification.when().get();

    }

    @Then("I should get success status code {string}")
    public void i_should_get_success_status_code(String sc)
    {
        validatableResponse = response.then().log().all();
        validatableResponse.assertThat().statusCode(Integer.parseInt(sc));
        scenario.log("Response Code:" + String.valueOf(response.getStatusCode()));
        scenario.log("Response Body:" + response.getBody().asPrettyString());

    }



}
