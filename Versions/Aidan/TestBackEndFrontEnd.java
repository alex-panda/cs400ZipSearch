// --== CS400 File Header Information ==--
// Name: Xiaohan Shen
// Email: xshen97@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Front end and back end test suite that checks
 * whether the methods in back end called by front end code
 * functions correctly.
 *
 * @author Xiaohan Shen
 */
public class TestBackEndFrontEnd {
    /**
     * This method checks whether the ZipCodeRBT constructor functions properly. Test fails when the
     * tree is not initialized properly, or any unexpected Exceptions occurs.
     */
    @Test
    void testConstructor() {
        try {
            ZipCodeRBT test = new ZipCodeRBT();
            if (test.size() != 38803) // the default size shold be 38803 since there are 38803 valid US
                // zipcodes now
                fail("The constructor failed to load the csv database correctly.");
        } catch (Exception e) {
            fail("Unexpected Exception occured when constructing a ZipCodeRBT instance.");
        }
    }

    /**
     * Tests whether contains() functions correctly.
     */
    @Test
    public void testContains(){
        try {
            ZipCodeRBT testRBT = new ZipCodeRBT();
            // testRBT contains these zipcodes
            if (!testRBT.contains(1050)){
                fail("contains() failed to find existing zip code 1050");
            }
            if (!testRBT.contains(63338)){
                fail("contains() failed to find existing zip code 63338");
            }
            if (!testRBT.contains(90078)){
                fail("contains() failed to find existing zip code 90078");
            }
            // testRBT does not contain these zipcodes
            if (testRBT.contains(1)){
                fail("contains() returns true when zip code 1 does not exist");
            }
            if (testRBT.contains(99951)){
                fail("contains() returns true when zip code 99951 does not exist");
            }
            if (testRBT.contains(99952)){
                fail("contains() returns true when zip code 99952 does not exist");
            }
        } catch (Exception e) {
            fail("contains() threw an unexpected exception " + e.getMessage());
        }
    }
    /**
     * This method checks whether the ZipCodeRBT getters functions properly. Test fails when the
     * getters fail to get the expected data, or any unexpected Exceptions occurs.
     */
    @Test
    void testGet() {
        try {
            ZipCodeRBT test = new ZipCodeRBT();
            Place testPlace = test.getPlace(53714);
            if (!testPlace.getCity().equals("MADISON") || !testPlace.getState().equals("WI")
                    || !testPlace.getCounty().equals("DANE") || !test.contains(53714))
                fail("Failed to get data with zipcode 53714.");
            testPlace = test.getPlace(99999);
            if (testPlace != null || test.contains(99999))
                fail("Failed to get null when zipcode entered does not exist.");
        } catch (Exception e) {
            fail("Unexpected Exception occured when the getters are called.");
        }
    }

    /**
     * This method checks whether the ZipCodeRBT insertion functions properly. Test fails when the
     * resulting tree is not as expected, or any unexpected Exceptions occurs.
     */
    @Test
    void testAdd() {
        try {
            ZipCodeRBT test = new ZipCodeRBT();
            // 54321 should be successfully added
            if (!test.add(54321, "test", "test", "test") || test.size() != 38804 && test.contains(54321))
                
		fail("Failed to add a new zipcode.");
            // 501 should not be re-added
	    if(test.add(501, "HOLTSVILLE", "SUFFOLK", "NY")){
	    	fail("Readded existing 501");
	    }
	    if(test.size() != 38804){
	    	fail("Size Changed when it should not have");
	    }
           
        } catch (Exception e) {
            fail("Unexpected Exception occured during insertion.");
        }
    }

    /**
     * This method checks whether the ZipCodeRBT reset functions properly. Test fails when the clear
     * or reset functionalities is not working as expected, or any unexpected Exceptions occurs.
     */
    @Test
    void testReset() {
        try {
            ZipCodeRBT test = new ZipCodeRBT();
            test.clear();
            if (!test.isEmpty())
                fail("failed to clear ZipCodeRBT.");
            test.reset();
            if (!test.contains(53714) || test.size() != 38803)
                fail("failed to reset ZipCodeRBT.");
        } catch (Exception e) {
            fail("Unexpected Exception during reset.");
        }
    }
}
