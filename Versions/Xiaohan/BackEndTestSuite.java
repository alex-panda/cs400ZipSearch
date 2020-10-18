import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Back end test suite that checks whether back end code functions correctly.
 *
 * @author Xiaohan Shen
 */
public class BackEndTestSuite {
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
            if (test.add(501, "HOLTSVILLE", "SUFFOLK", "NY")
                    || test.size() != 38804 && test.contains(501))
                fail("Readded exixting data.");
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

    /**
     * This method checks whether the ZipCodeRBT remove functions properly on a sample small tree. The
     * test trees comes from the class note from Florian's lectures with slight modifications, the
     * initial ZipCodeRBT has been cleared just from testing purposes. Test fails when the RBT
     * deletion happens not as expected, or any unexpected Exceptions occurs.
     */
    @Test
    void testSmallTreeRemove() {
        // case 1.1: D is black, R is red
        ZipCodeRBT test = new ZipCodeRBT();
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(11, "NULL", "NULL", "NULL");
        test.add(18, "NULL", "NULL", "NULL");
        test.add(23, "NULL", "NULL", "NULL");
        test.add(29, "NULL", "NULL", "NULL");
        test.remove(7);
        // store the expected in order traversal
        String ans = "[Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 11, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 20, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 18, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 23, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 29, City: NULL, County: NULL, State: NULL.]";
        // store the expected color in order traversal
        String color = "[Black, Black, Red, Black, Black, Red]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 7.");
        // case 1.2: D is red, R is black
        test.remove(20);
        ans = "[Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 11, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 23, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 18, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 29, City: NULL, County: NULL, State: NULL.]";
        color = "[Black, Black, Red, Black, Black]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 20.");
        // case 2.1 S is black and has at least 1 red child
        // sub-case right-right
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(1, "NULL", "NULL", "NULL");
        test.add(11, "NULL", "NULL", "NULL");
        test.add(18, "NULL", "NULL", "NULL");
        test.add(23, "NULL", "NULL", "NULL");
        test.add(29, "NULL", "NULL", "NULL");
        test.remove(18);
        ans = "[Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 7, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 23, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 1, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 11, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 20, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 29, City: NULL, County: NULL, State: NULL.]";
        // resulting color is different from class note but still valid
        color = "[Black, Black, Red, Red, Red, Black, Black]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 18.");
        // sub-case right-left
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(1, "NULL", "NULL", "NULL");
        test.add(11, "NULL", "NULL", "NULL");
        test.add(19, "NULL", "NULL", "NULL");
        test.add(23, "NULL", "NULL", "NULL");
        test.add(21, "NULL", "NULL", "NULL");
        test.remove(19);
        ans = "[Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 7, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 21, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 1, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 11, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 20, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 23, City: NULL, County: NULL, State: NULL.]";
        // resulting color is different from class note but still valid
        color = "[Black, Black, Red, Red, Red, Black, Black]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 19.");
        // sub-case left-left
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(1, "NULL", "NULL", "NULL");
        test.add(11, "NULL", "NULL", "NULL");
        test.add(16, "NULL", "NULL", "NULL");
        test.add(23, "NULL", "NULL", "NULL");
        test.add(15, "NULL", "NULL", "NULL");
        test.remove(23);
        ans = "[Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 7, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 16, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 1, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 11, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 15, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 20, City: NULL, County: NULL, State: NULL.]";
        // resulting color is different from class note but still valid
        color = "[Black, Black, Red, Red, Red, Black, Black]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 23.");
        // sub-case left-left
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(1, "NULL", "NULL", "NULL");
        test.add(11, "NULL", "NULL", "NULL");
        test.add(16, "NULL", "NULL", "NULL");
        test.add(24, "NULL", "NULL", "NULL");
        test.add(19, "NULL", "NULL", "NULL");
        test.remove(24);
        ans = "[Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 7, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 19, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 1, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 11, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 16, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 20, City: NULL, County: NULL, State: NULL.]";
        // resulting color is different from class note but still valid
        color = "[Black, Black, Red, Red, Red, Black, Black]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 17.");
        // case 2.2: S and both its children are black
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(1, "NULL", "NULL", "NULL");
        test.remove(1); // make the tree all black
        test.remove(14);
        ans = "[Zipcode: 20, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 7, City: NULL, County: NULL, State: NULL.]";
        color = "[Black, Red]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 14.");
        // case 2.3: S is red
        test.clear();
        test.add(14, "NULL", "NULL", "NULL");
        test.add(7, "NULL", "NULL", "NULL");
        test.add(20, "NULL", "NULL", "NULL");
        test.add(23, "NULL", "NULL", "NULL");
        test.add(6, "NULL", "NULL", "NULL");
        test.add(8, "NULL", "NULL", "NULL");
        test.add(5, "NULL", "NULL", "NULL");
        test.remove(23);
        test.remove(20);
        ans = "[Zipcode: 7, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 6, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 14, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 5, City: NULL, County: NULL, State: NULL.,"
                + " Zipcode: 8, City: NULL, County: NULL, State: NULL.]";
        color = "[Black, Black, Black, Red, Red]";
        if (!test.toString().equals(ans) || !test.colorInorderTraversal().equals(color))
            fail("Failed to remove 20.");
    }

    /**
     * This method checks whether the ZipCodeRBT remove functions properly on the initialized tree
     * with 38803 Place objected from the data wranglers. Test fails when failed to remove any node
     * that should be removed, or change any part of the tree when a node should not be removed was
     * passed to the remove method, or any unexpected Exceptions occurs.
     */
    @Test
    void testZipcodeRemove() {
        ZipCodeRBT test = new ZipCodeRBT();
        // case 1: remove a node in the middle of the tree
        if (!test.remove(53706))
            fail("Failed to remove zipcode 53706.");
        if (test.size() != 38802 || test.contains(53706))
            fail("Failed to remove zipcode 53706.");
        // case 2: remove a node that does not really exist
        if (test.remove(1))
            fail("Removed non-existing zipcode successfully when it should fail");
        if (test.size() != 38802)
            fail("Removed non-existing zipcode successfully when it should fail");
        // case 3: remove the smallest node
        if (!test.remove(501))
            fail("Failed to remove zipcode 501.");
        if (test.size() != 38801 || test.contains(501))
            fail("Failed to remove zipcode 53706.");
        // case 4: remove the largest node
        if (!test.remove(99950))
            fail("Failed to remove zipcode 501.");
        if (test.size() != 38800 || test.contains(99950))
            fail("Failed to remove zipcode 53706.");
    }
}
