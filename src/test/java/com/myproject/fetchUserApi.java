package com.myproject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class fetchUserApi {

    private String token;

    static final String userName = "kowshik.bikkina@skillsoft.com";
    static final String password = "P@ssw0rd123@";
    static final String orgId = "5cbc7202-4149-4fb3-9363-4ef8ae1a25e7";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://presales1.percipio.com";
    }

    public static String getAuthRequestBody(String userName, String password, String orgId) {
        return String.format("""
                {
                  "login": "%s",
                  "password": "%s",
                  "organizationId": "%s"
                }
                """, userName, password, orgId);
    }

    public static String getUsersRequestBody(String q, int max, int offset, String sort, List<String> rolesJson) {
        String rolesString = rolesJson.stream()
                .map(role -> "\"" + role + "\"")
                .collect(Collectors.joining(", "));

        return String.format("""
                {
                  "q": "%s",
                  "max": %d,
                  "offset": %d,
                  "sort": "%s",
                  "roles": [%s]
                }
                """, q, max, offset, sort, rolesString);
    }

    public static String getAuthToken(String userName, String password, String orgId) {
        String jsonBody = getAuthRequestBody(userName, password, orgId);

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .post("/api/public/auth/authenticate");

        return response.jsonPath().getString("id_token");
    }

    public static Response callUserAPI(String token) {
        String jsonBody = """
                {
                  "q": "",
                  "max": 10,
                  "offset": 0,
                  "sort": "lastName,firstName",
                  "roles": []
                }
                """;

        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(jsonBody)
                .post("/api/ucm/users")
                .then()
                .extract()
                .response();
    }

    @Test
    public void testGetAuthToken() {
        token = getAuthToken(userName, password, orgId);
        System.out.println("Token: " + token);
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");
    }

    @Test(dependsOnMethods = { "testGetAuthToken" })
    public void testCallUserAPI() {
        Response response = callUserAPI(token);

        List<String> firstNames = response.path("users.firstName");
        for (String name : firstNames) {
            System.out.println(name);
        }

        String firstName = response.path("users[0].firstName");
        Assert.assertEquals(firstName, "Andrew", "First name of first user should be Andrew");

        List<Map<String, Object>> users = response.path("users");
        for (Map<String, Object> user : users) {
            System.out.println("Name: " + user.get("loginName"));
            System.out.println("Email: " + user.get("email"));
            System.out.println("Role: " + user.get("role"));
            System.out.println("--------------");
        }

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
    }
}
