package stepdefinition.pojo;


import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PayloadManager {

    public String PlaceOrderValidPayload() {

        PlaceOrderPojo plpojo = new PlaceOrderPojo();
        plpojo.setId(4);
        plpojo.setPetId(20);
        plpojo.setQuantity(1);
        plpojo.setShipDate("2022-12-16T06:37:13.192Z");
        plpojo.setStatus("placed");
        plpojo.setComplete(true);

        Gson gson = new Gson();
        String payloadstring = gson.toJson(plpojo);
        return payloadstring;

    }

}




