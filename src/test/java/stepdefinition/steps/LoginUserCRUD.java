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
import org.hamcrest.Matchers;
import org.junit.Assert;
import stepdefinition.methods.CreateUserMethod;
import stepdefinition.methods.LoginUserMethod;



public class LoginUserCRUD {

    public Scenario scenario;
    LoginUserMethod loginUserMethod = new LoginUserMethod();
    public static String sessionid;
    public Response response;
    public RequestSpecification requestSpecification;
    public ValidatableResponse validatableResponse;
    Integer responseStatusCode;

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("I want to login to an user account at {string} with valid {string} and valid {string}")
    public void iWantToLoginToAnUserAccountAtWithValidAndValid(String url, String username, String password) {
        loginUserMethod.givenMethodLogin(url, username, password);}


    @When("I submit a get request")
    public void iSubmitAGetRequest()
    {
        String responseBody = loginUserMethod.submitGet();
        scenario.log("Response body" + responseBody);
    }

    @Then("I get {string}")
    public void iGet(String sc)
    {       responseStatusCode = loginUserMethod.getCode(sc);
            scenario.log("Response Status Code: " + responseStatusCode);
    }


    @And("with valid response {string}")
    public void withValidResponse(String message) {
        String responseMsg = loginUserMethod.responseMessageUser(message);
        scenario.log("response message:" + responseMsg);
        sessionid = responseMsg.substring(responseMsg.indexOf(':') + 1);
        scenario.log(sessionid);
    }


    @And("valid response {string}")
    public void validResponse(String message) {
        JsonPath jsonPath = response.jsonPath();
        String responseMsg = jsonPath.get("message");
        validatableResponse.body("message", Matchers.containsString(message));
        scenario.log("response message:" + responseMsg);
    }

    @Given("I want to create an user with {string} at {string} from logged in user")
    public void iWantToCreateAnUserWithAtFromLoggedInUser(String username, String path) {
        CreateUserMethod user = new CreateUserMethod();
        response = user.CreatePayload(
                8, username, "five", "user8@gmail.com",
                "password", "12345", "1", 1, path,sessionid);}

    @When("I submit post request")
    public void iSubmitPostRequest() {
        scenario.log("Response body" + response.getBody().asPrettyString());}


    @Then("I get the {string}")
    public void iGetThe(String sc) {
        validatableResponse = response.then();
        responseStatusCode = response.getStatusCode();
        validatableResponse.assertThat().statusCode(Integer.parseInt(sc));
        scenario.log("Response Status Code: " + responseStatusCode);
    }


    @Given("I want to update an {string} of an {string} at {string} for a logged in user")
    public void iWantToUpdateAnOfAnAtForALoggedInUser(String firstname, String username, String path) {

        CreateUserMethod user = new CreateUserMethod();
        response = user.UpdateUserPayload(
                8, username, firstname, "user8@gmail.com",
                "password", "12345", "1",1, path);

    }

    @When("I submit a put request")
    public void iSubmitAPutRequest() {
        scenario.log("Response body"+ response.getBody().asPrettyString());
    }
    

    @Given("I want to delete an order at {string} with an {string}")
    public void iWantToDeleteAnOrderAtWithAn(String url, String username)
    {
        //requestId = orderid;

        StringBuilder stringBuilder = new StringBuilder();
      String basepath = stringBuilder.toString();
        System.out.println(basepath);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2")
                .basePath(basepath)
                .accept(ContentType.JSON);
    }


    @Given("I want to logout from an logged in user account with {string}")
    public void iWantToLogoutFromAnLoggedInUserAccountWith(String path) {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/")
                .basePath(path)
                .accept(ContentType.JSON);
    }

    @Then("I get the success {string}")
    public void iGetTheSuccess(String sc) {

        validatableResponse = response.then();
        String responseStatusCode = String.valueOf(response.getStatusCode());
        Assert.assertEquals(sc,responseStatusCode);
        scenario.log("response status code: " +responseStatusCode);
    }

    @And("response body with  {string} must be displayed")
    public void responseBodyWithMustBeDisplayed(String message) {
        JsonPath jsonPath = response.jsonPath();
        String msg = jsonPath.get("message");
        scenario.log("response message:" + msg);
        Assert.assertEquals(message,msg);
    }

    @When("I submit Delete request")
    public void iSubmitDeleteRequest() {
        response = requestSpecification.when().delete();

    }

    @When("I submit get request")
    public void iSubmitGetRequest() {
        response = requestSpecification.when().get();
    }
}

