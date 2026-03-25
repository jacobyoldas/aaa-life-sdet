package tests.api;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.utils.TestDataReader;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class BookingTests extends BaseAPI {

    @Test
    public void createBookingFromJson() {

        JSONArray data = TestDataReader.getBookingData();

        for (int i = 0; i < data.length(); i++) {

            JSONObject obj = data.getJSONObject(i);

            Map<String, Object> bookingDates = new HashMap<>();
            bookingDates.put("checkin", "2024-01-01");
            bookingDates.put("checkout", "2024-01-05");

            Map<String, Object> body = new HashMap<>();
            body.put("firstname", obj.getString("firstname"));
            body.put("lastname", obj.getString("lastname"));
            body.put("totalprice", obj.getInt("totalprice"));
            body.put("depositpaid", obj.getBoolean("depositpaid"));
            body.put("bookingdates", bookingDates);
            body.put("additionalneeds", "Breakfast");

            Response response = given()
                    .contentType("application/json")
                    .body(body)
                    .when()
                    .post("/booking");

            response.prettyPrint();

            Assert.assertEquals(response.getStatusCode(), 200, "Create booking failed");

            JsonPath json = response.jsonPath();

            Assert.assertEquals(json.getString("booking.firstname"), obj.getString("firstname"), "Firstname mismatch");
            Assert.assertEquals(json.getString("booking.lastname"), obj.getString("lastname"), "Lastname mismatch");
            Assert.assertEquals(json.getInt("booking.totalprice"), obj.getInt("totalprice"), "Price mismatch");
        }
    }

    @Test
    public void createBookingNegativeMissingFields() {

        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Test");

        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/booking");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode() , 500, "Expected failure for missing required fields");
    }

    @Test
    public void createBookingBoundaryZeroPrice() {

        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2024-01-05");

        Map<String, Object> body = new HashMap<>();
        body.put("firstname", "Boundary");
        body.put("lastname", "Test");
        body.put("totalprice", 0);
        body.put("depositpaid", true);
        body.put("bookingdates", bookingDates);

        Response response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("/booking");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Boundary test failed for zero price");
    }

    @Test
    public void getBookingPositive() {

        Response response = given()
                .when()
                .get("/booking/1");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 for valid booking");

        JsonPath json = response.jsonPath();

        Assert.assertTrue(!json.getString("firstname").isEmpty(), "Firstname missing in response");
    }

    @Test
    public void getBookingNegative() {

        Response response = given()
                .when()
                .get("/booking/999999");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 404, "Expected 404 for invalid booking");
    }
}