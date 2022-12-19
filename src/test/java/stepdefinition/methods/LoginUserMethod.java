package stepdefinition.methods;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class LoginUserMethod {

    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;

    String requestString;
    Integer responseStatusCode;

    public void givenMethodLogin(String url,String username,String password)
    {
        String parameter ="?username="+username+"&password="+password;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append(parameter);
        String basepath =stringBuilder.toString();
        System.out.println(basepath);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://petstore.swagger.io/v2/")
                .basePath(basepath)
                .accept(ContentType.JSON);
    }


    public String submitGet() {
        response = requestSpecification.when().get();
        return response.getBody().asPrettyString();
    }

    public Integer getCode(String sc) {
        validatableResponse = response.then();
        responseStatusCode = response.getStatusCode();
        validatableResponse.assertThat().statusCode(Integer.parseInt(sc));
        return  responseStatusCode;

    }

    public String responseMessageUser(String message) {
        JsonPath jsonPath = response.jsonPath();
        if(response.getStatusCode()==200)
        {
            String responseUserName = jsonPath.get("message");
            validatableResponse.body("message", Matchers.containsString(message));
            return responseUserName;
        }
        else
        {
            String msg = jsonPath.get("message");
            Assert.assertEquals(message,msg);
            return msg;

        }}


}
