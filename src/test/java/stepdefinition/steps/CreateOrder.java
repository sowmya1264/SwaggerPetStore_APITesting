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
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import stepdefinition.pojo.PayloadManager;


@Owner(value = "Sowmya")
@Severity(SeverityLevel.CRITICAL)
public class CreateOrder {

    public Scenario scenario;
    String payload;

    PayloadManager pm = new PayloadManager();

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    static RequestSpecification requestSpecification;
    static Response response1;
    static Response response2;
    static ValidatableResponse validatableResponse;

    @Given("I want to place an order for pet at {string}")

    public void iWantToPlaceAnOrderForPetAt(String uri)
    {

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2")
                .basePath(uri)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON);

    }
    @And("with the valid request body")
    public void withTheValidRequestBody()
    {
        payload = pm.PlaceOrderValidPayload();
        requestSpecification.body(payload);
        response1 = requestSpecification.when().post();
        scenario.log("Request body"+payload);
    }

    @When("I submit a Post request")
    public void iSubmitAPostRequest() {
        scenario.log("Response body " + String.valueOf(response1.getBody().asPrettyString()));

    }

    @Then("I should get the success {string}")
    public void iShouldGetTheSuccess(String statuscode)
    {
        validatableResponse = response1.then();
        validatableResponse.assertThat().statusCode(Integer.parseInt(statuscode));
        scenario.log("valid request status code: " +String.valueOf(response1.getStatusCode()));
    }

    @And("the valid response body")
    public void theValidResponseBody()
    {
        JsonPath jsonPath = response1.jsonPath();
        Integer petid = jsonPath.get("petId");
        Integer id = jsonPath.get("id");

        scenario.log("Response Pet Id:" + String.valueOf(petid));
        scenario.log("Request Id:" + String.valueOf(id));
        Assert.assertNotNull(petid);
        Assert.assertNotNull(id);

    }

    @And("with the invalid request body request body")
    public void withTheInvalidRequestBodyRequestBody() {

        String invalidpayload = " ";
        requestSpecification.body(invalidpayload);
        response2 = requestSpecification.when().post();

    }

    @Then("I should get the bad request {string}")
    public void iShouldGetTheBadRequest(String statuscode) {
        validatableResponse = response2.then();
        validatableResponse.assertThat().statusCode(Integer.parseInt(statuscode));
        scenario.log("Invalid request status code:" + String.valueOf(response2.getStatusCode()));
    }
}
