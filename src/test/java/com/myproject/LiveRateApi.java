package com.myproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LiveRateApi {

    private final String BASE_URL = "http://viewbcastgold.dpgold.in:8811";
    private final String API_PATH = "/VOTSBroadcast/Services/xml/GetLiveRate";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(enabled = true)
    public void testGetLiveRateBasic() {

        // Basic test to check if API returns a 200 status
        Response response = given()
                .queryParam("_", System.currentTimeMillis())
                .get(API_PATH)
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Response: " + response.print());
        Assert.assertNotNull(response.asString(), "Response should not be null");
    }
}
