// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// Role: Backend Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader:

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Justin Qiao
 *
 */
class TestZipCodeRBT {

  @Test
  void testConstructor() {
    try {
      ZipCodeRBT test = new ZipCodeRBT();
      if (test.size() != 38803)
        fail("The constructor failed to load the csv database correctly.");
    } catch (Exception e) {
      fail("Unexpected Exception occured when constructing a ZipCodeRBT instance.");
    }
  }

  @Test
  void testGet() {
    ZipCodeRBT test = new ZipCodeRBT();
    Place testPlace = test.get(53714);
    if (!testPlace.getCity().equals("MADISON") || !testPlace.getState().equals("WI")
        || !testPlace.getCounty().equals("DANE") || !test.contains(53714))
      fail("Failed to get data with zipcode 53714.");
    testPlace = test.get(99999);
    if (testPlace != null || test.contains(99999))
      fail("Failed to get null when zipcode entered does not exist.");
  }

  @Test
  void testAdd() {
    ZipCodeRBT test = new ZipCodeRBT();
    if (!test.add(54321, "test", "test", "test") || test.size() != 38804)
      fail("Failed to add a new zipcode.");
    System.out.println(test.get(501));
    if (test.add(501, "HOLTSVILLE", "SUFFOLK", "NY") || test.size() != 38804)
      fail("Readded exixting data.");
  }

  @Test
  void testRemove() {

  }
}
