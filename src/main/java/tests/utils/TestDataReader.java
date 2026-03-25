package tests.utils;;

import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.InputStream;

public class TestDataReader {

    public static JSONArray getBookingData() {

        try {
            InputStream input = TestDataReader.class
                    .getClassLoader()
                    .getResourceAsStream("bookingData.json");

            JSONTokener tokener = new JSONTokener(input);

            return new JSONArray(tokener);

        } catch (Exception e) {
            throw new RuntimeException("JSON file not found");
        }
    }
}