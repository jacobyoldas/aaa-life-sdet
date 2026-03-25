package tests.api;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class AuthTests extends BaseAPI {

    @Test
    public void authPositive() {

        Map<String, String> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "password123");

        Response response =
                given()
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post("/auth");

        Assert.assertEquals( response.getStatusCode(),200,"Expected status code 200 but got different response");

        JsonPath json = response.jsonPath();

        String token = json.getString("token");

        Assert.assertTrue(!token.isEmpty(), "Auth token is missing or empty in response");
    }

    @Test
    public void authNegative() {

        Map<String, String> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "wrong");

        Response response =
                given()
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post("/auth");

        Assert.assertEquals(response.getStatusCode(), 200,"Expected 200 even for invalid login (API behavior)");

        JsonPath json = response.jsonPath();

        String reason = json.getString("reason");

        Assert.assertTrue(reason.contains("Bad credentials"),"Expected error message not found in response"
        );
    }
}