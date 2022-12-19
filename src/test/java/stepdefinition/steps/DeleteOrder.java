package stepdefinition.steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import stepdefinition.pojo.PayloadManager;


public class DeleteOrder {

    public Scenario scenario;
    static RequestSpecification requestSpecification;
    static Response response;
    static ValidatableResponse validatableResponse;
    Integer resrponseStatusCode;
    String requestId;

    @Before(order = 0)
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Before(order = 1)
    public void beforeDelete() {
        String uri = "/store/order";
        PayloadManager pm = new PayloadManager();
        String payload = pm.PlaceOrderValidPayload();
        RequestSpecification requestSpecification1 = RestAssured.given();
        requestSpecification1.baseUri("https://petstore.swagger.io/v2")
                .basePath(uri)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload);
        Response response1 = requestSpecification1.when().post();
    }


    @Given("I want to delete an order at {string} with a valid {string}")
    public void iWantToDeleteAnOrderAtWithAValid(String uri, String orderid) {

        requestId = orderid;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(uri).append(orderid);
        String basepath = stringBuilder.toString();
        System.out.println(basepath);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2")
                .basePath(basepath)
                .accept(ContentType.JSON);
    }


    @When("I submit a Delete request")
    public void iSubmitADeleteRequest() {
        response = requestSpecification.when().delete();

    }

    @Then("I should get code {string}")
    public void iShouldGetCode(String sc) {
        try{
        validatableResponse = response.then();
        resrponseStatusCode = response.getStatusCode();
        validatableResponse.assertThat().statusCode(Integer.parseInt(sc));
        scenario.log("Response Status Code: " + resrponseStatusCode);
        scenario.log("Response body " + response.getBody().asPrettyString());
             }
        catch(Exception e){e.printStackTrace();}

    }



    @And("response body {string} should be displayed")
    public void responseBodyShouldBeDisplayed(String message) throws Exception
    {
        JsonPath jsonPath = response.jsonPath();
        if(response.getStatusCode()!=200)
        {
            String msg = jsonPath.get("message");
            scenario.log("response message:" + msg);
            Assert.assertEquals(message,msg);
        }
    }
}



