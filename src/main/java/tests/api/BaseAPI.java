package tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import tests.utils.ConfigReader;

public class BaseAPI {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.get("api_url");
    }
}