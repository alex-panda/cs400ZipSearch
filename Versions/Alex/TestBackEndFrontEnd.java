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

/**
 * A Test Suite used to test the BackEnd and FrontEnd Developers' classes
 * 
 * @author Allistair
 *
 */
public class TestBackEndFrontEnd {

  /**
   * Tests the default constructor of ZipCodeRBT. Passes if the data has been initialized and added
   * correctly to the Red Black Tree, fails otherwise.
   */
  @Test
  public void testConstructor() {
    try {
      ZipCodeRBT test = new ZipCodeRBT();
      assertEquals(test.size(), 38803,
          "ZipCodeRBT's constructor failed to load the data correctly.");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests the clear and reset methods of ZipCodeRBT. Passes if both methods work as intended, fails
   * otherwise.
   */
  @Test
  public void testClearAndReset() {
    try {
      ZipCodeRBT test = new ZipCodeRBT();
      test.clear();
      if (!(test.root == null && test.size() == 0)) {
        fail("ZipCodeRBT's clear method failed to clear the RBT.");
      }
      test.reset();
      Place place = new Place(501, "HOLTSVILLE", "SUFFOLK", "NY");
      assertEquals(test.getPlace(501).toString(), place.toString(),
          "ZipCodeRBT's constructor failed to load the data correctly.");
      assertEquals(test.size(), 38803,
          "ZipCodeRBT's constructor failed to load the data correctly.");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests the getNode and getPlace methods in ZipCodeRBT. Test passes if the get functions return
   * the right Place object with the zipcode is passed and fails otherwise
   */
  @Test
  public void testGet() {
    try {
      ZipCodeRBT test = new ZipCodeRBT();
      assertEquals(test.getNode(53715).data.toString(),
          "Zipcode: 53715, City: MADISON, County: DANE, State: WI.",
          "getNode method in ZipCodeRBT failed to get Node with zipcode 53715.");
      Place testPlace = test.getPlace(99999);
      if (testPlace != null || test.contains(99999)) {
        fail("getPlace method failed to return null when a zipcode that doesn't exist is passed.");
      }
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests whether the add method in ZipCodeRBT inserts a node correctly into the RBT.
   */
  @Test
  public void testAdd() {
    try {
      ZipCodeRBT test = new ZipCodeRBT();
      if (!test.add(1, "City", "County", "State") || test.size() != 38804 && test.contains(1))
        fail("add method in ZipCodeRBT failed to add a new zipcode.");

      // adding an existing node
      if (test.add(501, "HOLTSVILLE", "SUFFOLK", "NY")
          || test.size() != 38804 && test.contains(501))
        fail("add method in ZipCodeRBT added data that already existed in the RBT");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  /**
   * This method checks whether the ZipCodeRBT remove functions properly on a sample small tree. An
   * empty RBT tree with only a few values is used to test the functionality of the remove method.
   * Test fails when the RBT deletion happens not as expected, or any unexpected Exceptions occurs.
   */
  @Test
  public void testSmallTreeRemove() {
    ZipCodeRBT test = new ZipCodeRBT();
    test.clear();
    test.add(30, "", "", "");
    test.add(20, "", "", "");
    test.add(1, "", "", "");
    test.add(14, "", "", "");
    test.add(50, "", "", "");
    test.add(25, "", "", "");

    // remove red node - 0 children
    // 20,1,30,14,50
    test.remove(25);
    String expected = "[Zipcode: 20, City: , County: , State: .,"
        + " Zipcode: 1, City: , County: , State: .," + " Zipcode: 30, City: , County: , State: .,"
        + " Zipcode: 14, City: , County: , State: .," + " Zipcode: 50, City: , County: , State: .]";
    String expectedColor = "[Black, Black, Black, Red, Red]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove red node with no children.");

    // remove black node with 1 child
    test.remove(1);
    expected = "[Zipcode: 20, City: , County: , State: .,"
        + " Zipcode: 14, City: , County: , State: .," + " Zipcode: 30, City: , County: , State: .,"
        + " Zipcode: 50, City: , County: , State: .]";
    expectedColor = "[Black, Black, Black, Red]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove black node with 1 child.");

    // remove node with 2 children
    test.remove(20);
    expected = "[Zipcode: 30, City: , County: , State: .,"
        + " Zipcode: 14, City: , County: , State: .," + " Zipcode: 50, City: , County: , State: .]";
    expectedColor = "[Black, Black, Black]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove node with 2 children.");

    // remove black node with no children (use of double-black)

    // sibling of double-black is red
    test.add(40, "", "", "");
    test.add(35, "", "", "");
    test.add(33, "", "", "");
    test.remove(14);
    expected = "[Zipcode: 40, City: , County: , State: .,"
        + " Zipcode: 33, City: , County: , State: .," + " Zipcode: 50, City: , County: , State: .,"
        + " Zipcode: 30, City: , County: , State: .," + " Zipcode: 35, City: , County: , State: .]";
    expectedColor = "[Black, Red, Black, Black, Black]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove black node with no child, when sibling is red.");

    // sibling of double-black is black, sibling's children are also black
    test.clear();
    test.add(20, "", "", "");
    test.add(10, "", "", "");
    test.add(30, "", "", "");
    test.add(5, "", "", "");
    test.remove(5);
    test.remove(10);
    expected =
        "[Zipcode: 20, City: , County: , State: .," + " Zipcode: 30, City: , County: , State: .]";
    expectedColor = "[Black, Red]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove black node with no child,"
          + " when sibiling and sibling's children are all black.");

    // sibling of double-black is black, same side child of sibling is red, other child is black
    test.clear();
    test.add(45, "", "", "");
    test.add(22, "", "", "");
    test.add(72, "", "", "");
    test.add(68, "", "", "");
    test.remove(22);
    expected = "[Zipcode: 68, City: , County: , State: .,"
        + " Zipcode: 45, City: , County: , State: .," + " Zipcode: 72, City: , County: , State: .]";
    expectedColor = "[Black, Black, Black]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove black node with no child, when sibling is black,"
          + " same side child of sibling is red and other child is black.");

    // sibling of double-black is black, and other side child is red
    test.clear();
    test.add(45, "", "", "");
    test.add(22, "", "", "");
    test.add(72, "", "", "");
    test.add(68, "", "", "");
    test.add(91, "", "", "");
    test.remove(22);
    expected = "[Zipcode: 72, City: , County: , State: .,"
        + " Zipcode: 45, City: , County: , State: .," + " Zipcode: 91, City: , County: , State: .,"
        + " Zipcode: 68, City: , County: , State: .]";
    expectedColor = "[Black, Black, Black, Red]";
    if (!test.toString().equals(expected) || !test.colorLevelOrderTraversal().equals(expectedColor))
      fail("Failed to remove black node with no child,"
          + " when sibling is black and other side child is red.");
  }

  /**
   * This method checks whether the ZipCodeRBT remove functions properly on the initialized tree
   * with 38803 Place objects from the data wranglers. Test fails when failed to remove any node
   * that should be removed, or change any part of the tree when a node should not be removed was
   * passed to the remove method, or any unexpected exceptions occur.
   */
  @Test
  public void testZipcodeRemove() {
    try {
      ZipCodeRBT test = new ZipCodeRBT();
      // remove smallest node
      if (!test.remove(501))
        fail("Failed to remove existing zipcode 501.");
      if (test.size() != 38802 || test.contains(501))
        fail("Failed to remove existing zipcode 501.");

      // remove middle node
      if (!test.remove(53715))
        fail("Failed to remove existing zipcode 53715.");
      if (test.size() != 38801 || test.contains(53715))
        fail("Failed to remove existing zipcode 53715.");


      // remove largest node
      if (!test.remove(99950))
        fail("Failed to remove existing zipcode 99950.");
      if (test.size() != 38800 || test.contains(99950))
        fail("Failed to remove existing zipcode 99950.");

      // remove non-existent node
      if (test.remove(1))
        fail("Removed a non-existent node from the RBT.");
      if (test.size() != 38800)
        fail("Removed a non-existant node from the RBT.");
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
