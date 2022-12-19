package stepdefinition.methods;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateUserMethod {

    public Response CreateUserPayload(Integer id,String username,String firstName, String lastName,
                                  String email, String password, String phone, Integer userStatus,String path)
    {
        LinkedHashMap<String,Object> hashmap = new LinkedHashMap<>();
        hashmap.put("id", id);
        hashmap.put("username", username);
        hashmap.put("firstName", firstName);
        hashmap.put("lastName", lastName);
        hashmap.put("email", email);
        hashmap.put("password", password);
        hashmap.put("phone", phone);
        hashmap.put("userStatus", userStatus);

        ArrayList<Map> payload = new ArrayList<>();
        payload.add(hashmap);
        System.out.println(payload);
        RequestSpecification requestSpecification1 = RestAssured.given();
        requestSpecification1.baseUri("https://petstore.swagger.io/v2/")
                .basePath(path)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload);
        Response response1 = requestSpecification1.when().post();

        return response1;

    }

    public Response CreatePayload(Integer id,String username,String firstName, String lastName,
                                      String email, String password, String phone, Integer userStatus,
                                      String path,String session)
    {

        LinkedHashMap<String,Object> hashmap = new LinkedHashMap<>();
        hashmap.put("id", id);
        hashmap.put("username", username);
        hashmap.put("firstName", firstName);
        hashmap.put("lastName", lastName);
        hashmap.put("email", email);
        hashmap.put("password", password);
        hashmap.put("phone", phone);
        hashmap.put("userStatus", userStatus);

        ArrayList<Map> payload = new ArrayList<>();
        payload.add(hashmap);
        System.out.println(payload);
        System.out.println(session);
        RequestSpecification requestSpecification1 = RestAssured.given();
        requestSpecification1.baseUri("https://petstore.swagger.io/v2")
                .basePath(path)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload);
                //.header("authorization","key" + session);

        Response response2 = requestSpecification1.when().post();

        return response2;

    }

    public Response UpdateUserPayload(Integer id,String username,String firstName, String lastName,
                                      String email, String password, String phone, Integer userStatus,String path)
    {
        LinkedHashMap<String,Object> hashmap = new LinkedHashMap<>();
        hashmap.put("id", id);
        hashmap.put("username", username);
        hashmap.put("firstName", firstName);
        hashmap.put("lastName", lastName);
        hashmap.put("email", email);
        hashmap.put("password", password);
        hashmap.put("phone", phone);
        hashmap.put("userStatus", userStatus);

        ArrayList<Map> payload = new ArrayList<>();
        payload.add(hashmap);
        System.out.println(payload);
        RequestSpecification requestSpecification1 = RestAssured.given();
        requestSpecification1.baseUri("https://petstore.swagger.io/v2/")
                .basePath(path)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload);
        Response response = requestSpecification1.when().put();

        return response;

    }


}
