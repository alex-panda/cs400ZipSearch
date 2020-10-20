// --== CS400 File Header Information ==--
// Name: Allistair Mascarenhas
// Email: anmascarenha@wisc.edu
// Team: DC
// Role: Test Engineer
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * A Test Suite used to test the DataWranglers' classes
 * 
 * @author Allistair
 */
public class TestData {

  /**
   * Checks if the Place class functions as intended. Tests the constructor and all the accessor and
   * mutator methods.
   */
  @Test
  public void testPlace() {
    try {
      Place test = new Place(12300, "Chicago", "Cook", "Illinois");
      String tExpected = "Zipcode: 12300, City: Chicago, County: Cook, State: Illinois.";
      String tActual = test.toString();
      assertEquals(tActual, tExpected, "Place's constructor doesn't initialize fields correctly.");
      Place test1 = new Place(12345, "Madison", "Dane", "Wisconsin");
      String t1Expected = "Zipcode: 12345, City: Madison, County: Dane, State: Wisconsin.";
      String t1Actual = test1.toString();
      assertEquals(t1Actual, t1Expected,
          "Place's constructor doesn't initialize fields correctly.");
      assertEquals(test1.compareTo(test), 45,
          "compareTo() in Place class returned an incorrect value.");
      test.setZipCode(test1.getZipCode());
      test.setCity(test1.getCity());
      test.setCounty(test1.getCounty());
      test.setState(test1.getState());
      assertEquals(test1.compareTo(test), 0,
          "compareTo() in Place class returned an incorrect value.");
      assertEquals(test.toString(), test1.toString(),
          "Accessor and/or mutator methods in Place don't function as intended.");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests whether the DataLoader class correctly loads the data from the default file.
   */
  @Test
  public void testDataLoader() {
    try {
      DataLoader test = new DataLoader();
      ArrayList<Place> data = test.getData();
      String actual = data.get(0).toString() + data.get(30).toString();
      String expected = "Zipcode: 501, City: HOLTSVILLE, County: SUFFOLK, State: NY."
          + "Zipcode: 1039, City: HAYDENVILLE, County: HAMPSHIRE, State: MA.";
      if (!(actual.equals(expected) && data.size() == 38803)) {
        fail("The data from the csv file was not loaded corrected into the ArrayList.");
      }
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
