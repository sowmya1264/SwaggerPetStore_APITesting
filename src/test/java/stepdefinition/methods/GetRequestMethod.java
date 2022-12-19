package stepdefinition.methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class GetRequestMethod
{
    static RequestSpecification requestSpecification;
    static Response response;
    static ValidatableResponse validatableResponse;

    String requestString;
    Integer responseStatusCode;



    public void givenMethod(String url, String path) {
        requestString= path;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append(path);
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
            String responseUserName = jsonPath.get("username");
            Assert.assertEquals(requestString,responseUserName);
            return responseUserName;

        }
        else
        {
            String msg = jsonPath.get("message");
            //scenario.log("response message:" + msg);
            Assert.assertEquals(message,msg);
            return msg;

        }}

    public String responseMessageId(String message) {

        JsonPath jsonPath = response.jsonPath();
            if(response.getStatusCode()==200)
            {
                String responseid = jsonPath.get("id").toString();
                Assert.assertEquals(requestString,responseid);
                return  responseid;

            }
            else {
                String msg = jsonPath.get("message");
                //scenario.log("response message:" + msg);
                Assert.assertEquals(message,msg);
                return msg;
            }
    }


}
