// --== CS400 File Header Information ==--
// Name: Xiaohan Shen
// Email: xshen97@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * JUnit test Suite that tests whether DataWrangler's code works as expected
 *
 * @author Xiaohan Shen
 */
public class TestDataWranglerSuite {

    /**
     * Tests whether Place class functions as expected.
     */
    @Test
    public void testPlace() {
        Place place = null;
        try {
            place = new Place(00000, "someCity", "someCounty", "someState");
        } catch (Exception e) {
            fail("Place object construction threw an exception " + e.getMessage());
        }
        assertEquals("someCity", place.getCity(), "Place object is not working correctly");
        assertEquals("someCounty", place.getCounty(), "Place object is not working correctly");
        assertEquals("someState", place.getState(), "Place object is not working correctly");
        assertEquals(00000, place.getZipcode(), "Place object is not working correctly");
    }

    /**
     * Tests whether DataLoader correctly loads data from default csv file
     */
    @Test
    public void testLoadData() {
        try {
            DataLoader dataLoader = new DataLoader();
            assertNotNull(dataLoader, "DataLoader construction failed");
            ArrayList<Place> data = dataLoader.getData();
            for (int i = 0; i < data.size(); i++) {
                assertNotNull(data.get(i), "Data loaded contains null object");
            }
            assertEquals(501, data.get(0).getZipcode(),
                    "Data loaded is not " + " the same as the default zipcode csv file");
            assertEquals("HOLTSVILLE", data.get(0).getCity(),
                    "Data loaded is not " + " the same as the default zipcode csv file");
            assertEquals("SUFFOLK", data.get(0).getCounty(),
                    "Data loaded is not " + " the same as the default zipcode csv file");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
