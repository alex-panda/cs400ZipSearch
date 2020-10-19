// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// Role: Backend Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader:

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class models a Zipcode Red-Black Tree storing Place objects with their zipcode as keys.
 * Instance of this class is initialized with all existing US zipcodes provided by the data
 * wranglers. Instance from this class can perform RBT add, remove, getNode, getPlace, contains,
 * size, clear functions. It also provide in order traversal of the Place objects stored in the
 * tree. Some private methods are included to help with maintaining RBT properties, and some
 * protected methods are provided for test engineers to test the functionality of this class.
 * 
 * @author Justin Qiao
 */
public class ZipCodeRBT extends RedBlackTree<Place> {
  private int size; // number of zipCodes stored in the current ZipCodeRBT

  /**
   * Constructor of a ZipCodeRBT instance, automatically calls the initialization() method in this
   * class.
   */
  public ZipCodeRBT() {
    initialization(); // initialize with original csv file provided by the data wranglers
  }

  /**
   * Initializes the ZipCodeRBT instance with data from data wrangler classes.
   */
  private void initialization() {
    // get data from the dataLoader
    DataLoader dataLoader = new DataLoader();
    ArrayList<Place> data = dataLoader.getData();
    // insert every Place object from dataLoader to the RBT
    for (int i = 0; i < data.size(); i++)
      this.insert(data.get(i));
    size = data.size(); // track the initial size of the RBT
  }

  /**
   * Deletes all Objects stored in the ZipCodeRBT instance. Test engineers could take advantage of
   * this method to create and test this class with smaller trees.
   */
  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * Resets the tree by the original data from dataLoader.
   */
  public void reset() {
    initialization();
  }

  /**
   * Gets a specific Node in the ZipCodeRBT with the given zipCode, if any.
   * 
   * @param zipCode - the zipcode corresponding to the Node being acquired
   * @return A RedBlackTree.Node<Place> object which contains the given zipcode, null if the given
   *         zipcode does not exist in the ZipCodeRBT
   */
  protected Node<Place> getNode(int zipCode) {
    Node<Place> current = root; // the current node being compared with the zipCode
    while (current != null) {
      // return current node if zipcode matches
      if (current.data.getZipCode() == zipCode)
        return current;
      // search right subtree if current zipcode is smaller than the given one
      else if (current.data.getZipCode() < zipCode)
        current = current.rightChild;
      // search left subtree if current zipcode is greater than the given one
      else if (current.data.getZipCode() > zipCode)
        current = current.leftChild;
    }
    return null; // return null if zipCode does not exist in the ZipCodeRBT
  }

  /**
   * Gets a specific Place object in the ZipCodeRBT with the given zipCode, if any.
   * 
   * @param zipCode - the zipcode corresponding to the Place being acquired
   * @return A Place object which contains the given zipcode, null if the given zipcode does not
   *         exist in the ZipCodeRBT
   */
  public Place getPlace(int zipCode) {
    Node<Place> node = getNode(zipCode); // try to get the Node with the given zipcode
    if (node != null) // if there is any such Node
      return node.data; // return its Place object
    return null; // return null if zipCode does not exist in the ZipCodeRBT
  }

  /**
   * Checks whether a zipCode exists in the current ZipCodeRBT.
   * 
   * @param zipCode - the zipcode being checked
   * @return true if it exists, false otherwise
   */
  public boolean contains(int zipCode) {
    if (getNode(zipCode) != null)
      return true;
    return false;
  }

  /**
   * Returns the number of Place object stored in the ZipCodeRBT instance calling this method.
   * 
   * @return size of the ZipCodeRBT instance
   */
  public int size() {
    return size;
  }

  /**
   * Checks whether the ZipCodeRBT is empty.
   * 
   * @param zipCode - the zipcode being checked
   * @return true if it exists, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Adds a new Place to the current ZipCodeRBT.
   * 
   * @param zipCode - an int value of the new Place
   * @param city    - a String of the city name of the new Place
   * @param county  - a String of the county name of the new Place
   * @param state   - a String of the state name of the new Place
   * @return true if new Place added, false otherwise
   */
  public boolean add(int zipCode, String city, String county, String state) {
    Place newPlace = new Place(zipCode, city, county, state); // create a new Place object
    try {
      // if the current tree does not contain the new zipcode, add the new Place
      if (!contains(zipCode)) {
        this.insert(newPlace);
        size++;
        return true;
      }
      return false; // other wise not add the new Place
    } catch (Exception e) { // if any exception occurs, not add the new Place and return false
      return false;
    }
  }

  /**
   * Adds a Place with the given zipCode from the current ZipCodeRBT if it exists.
   * 
   * @param zipCode - an int value of the Place being removed
   * @return true if new Place removed, false otherwise
   */
  public boolean remove(int zipCode) {
    // if the current tree does not contain the given zipcode, return false
    if (!contains(zipCode))
      return false;
    // if the given zipCode exists, goto the tree nodes and begin to remove the node
    Node<Place> delete = getNode(zipCode); // the node being deleted
    Node<Place> replace; // helper reference, it points to delete's successor if delete
                         // have two children, otherwise it points to delete
    Node<Place> childToReconnect; // the replace node's child, it may hold the black
                                  // weight holder node as well
    boolean clearHolder = false; // when this equals true, clear black weight holder after removing
                                 // the node from the tree
    // set the replace reference as described above
    if (delete.leftChild == null || delete.rightChild == null)
      replace = delete;
    else
      replace = successor(delete);
    // set the childToReconnect reference as descirbed above
    if (replace.leftChild != null)
      childToReconnect = replace.leftChild;
    else
      childToReconnect = replace.rightChild;
    // reconnect childToReconnect to the parent of replace, if childToReconnect is null, replace the
    // null by a blackWeightHolder node
    if (childToReconnect != null)
      childToReconnect.parent = replace.parent;
    else if (replace.isBlack && replace.leftChild == null && replace.rightChild == null) {
      // create the blackWeightHolder if replace is black with no child
      Place blackWeighHolder = new Place(-1, "NULL", "NULL", "NULL");
      childToReconnect = new RedBlackTree.Node<Place>(blackWeighHolder);
      childToReconnect.isBlack = true;
      childToReconnect.parent = replace.parent;
      clearHolder = true; // make a not to clear the holder after removal
    }
    // reconnect childToReconnect to the parent of replace
    if (replace.parent == null)
      root = childToReconnect;
    else if (replace.isLeftChild())
      replace.parent.leftChild = childToReconnect;
    else
      replace.parent.rightChild = childToReconnect;
    // replace delete's data by replace's data
    if (replace != delete)
      delete.data = replace.data;
    // fix RBT color properties if replace is Black
    if (replace.isBlack)
      fixColor(childToReconnect);
    // clear black weight holders if needed
    if (clearHolder)
      clearHolder(root);
    // decrease size by 1 after removal
    size--;
    return true;
  }

  /**
   * Helper method to find the in order successor of a node in the ZipCodeRBT.
   * 
   * @param node - a reference to the node asking for its successor
   * @return a reference to the node's in order successor, null if the node has the largest key in
   *         the ZipCodeRBT, or the given node reference is null
   */
  private Node<Place> successor(Node<Place> node) {
    // return null if node is null
    if (node == null)
      return null;
    if (node.rightChild != null) { // if node has right subtree
      Node<Place> successor = node.rightChild;
      // find the left most node in its right subtree
      while (successor.leftChild != null)
        successor = successor.leftChild;
      return successor;
    } else {
      Node<Place> successor = node.parent;
      // otherwise, find its nearest ancestor that is larger than itself
      while (successor != null && node == successor.rightChild) {
        node = successor;
        successor.parent = successor;
      }
      return successor;
    }
  }

  /**
   * Helper method to restore the RBT color properties of the ZipCodeRBT. The idea of this method
   * comes from class reading: http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/chap14.htm
   * 
   * @param node - a reference to the node we begin to restore
   */
  private void fixColor(Node<Place> node) {
    while (node != root && node.isBlack) {
      if (node.isLeftChild()) {
        // the node's parent's other child
        Node<Place> silbling = node.parent.rightChild;
        // case 1: sibling is red
        if (silbling != null && !silbling.isBlack) {
          silbling.isBlack = true;
          node.parent.isBlack = false;
          rotate_Copy(node.parent.rightChild, node.parent);
          silbling = node.parent.rightChild;
        }
        // case 2: sibling is black and sibling's children are both black
        else if (silbling != null && (silbling.leftChild == null || silbling.leftChild.isBlack)
            && (silbling.rightChild == null || silbling.rightChild.isBlack)) {
          silbling.isBlack = false;
          node = node.parent;
        } else {
          // case 3: sibling is black sibling's rightChild is black, and its leftChild is red
          if (silbling != null && (silbling.rightChild == null || silbling.rightChild.isBlack)) {
            silbling.leftChild.isBlack = true;
            silbling.isBlack = false;
            rotate_Copy(silbling.leftChild, silbling);
            silbling = node.parent.rightChild;
          }
          // case 4: sibling is black sibling's rightChild is red, and its leftChild is black
          else if (silbling != null) {
            silbling.isBlack = node.parent.isBlack;
            node.parent.isBlack = true;
            silbling.rightChild.isBlack = true;
            rotate_Copy(node.parent.rightChild, node.parent);
            node = root;
          }
        }
      } else { // the content of this else block is symmetric to the if block above, with "left" and
               // "right" exchanged
        Node<Place> silbling = node.parent.leftChild;
        if (silbling != null && !silbling.isBlack) {
          silbling.isBlack = true;
          node.parent.isBlack = false;
          rotate_Copy(node.parent.leftChild, node.parent);
          silbling = node.parent.leftChild;
        } else if ((silbling.leftChild == null || silbling.leftChild.isBlack)
            && (silbling.rightChild == null || silbling.rightChild.isBlack)) {
          silbling.isBlack = false;
          node = node.parent;
        } else {
          if (silbling.leftChild == null || silbling.leftChild.isBlack) {
            silbling.rightChild.isBlack = true;
            silbling.isBlack = false;
            rotate_Copy(silbling.rightChild, silbling);
            silbling = node.parent.leftChild;
          } else if (silbling != null) {
            silbling.isBlack = node.parent.isBlack;
            node.parent.isBlack = true;
            silbling.leftChild.isBlack = true;
            rotate_Copy(node.parent.leftChild, node.parent);
            node = root;
          }
        }
      }
    }
    node.isBlack = true; // fix root property
  }

  /**
   * Helper method to perform BST rotation for the ZipCodeRBT. re-coloring is not involved in this
   * helper method. This method is very similar to that in RedBlackTree.java.
   * 
   * @param child  - a reference to the child node involved in the rotation
   * @param parent - a reference to the child's parent node involved in the rotation
   */
  protected void rotate_Copy(Node<Place> child, Node<Place> parent) {
    // get the parent not of parent, null if parent is the root
    Node<Place> grandParent = parent.parent;
    // store this information for later use to link child with grandParent
    boolean parentIsLeftChild = parent.isLeftChild();
    // perform right rotation if child is the left child of parent
    if (child.isLeftChild()) {
      Node<Place> rightGrandChild = child.rightChild;
      // fix connection between original parent and child
      child.rightChild = parent;
      parent.parent = child;
      // re-attach original grandchild
      parent.leftChild = rightGrandChild;
      if (rightGrandChild != null)
        rightGrandChild.parent = parent;
    } else { // otherwise perform left rotation
      Node<Place> leftGrandChild = child.leftChild;
      // fix connection between original parent and child
      child.leftChild = parent;
      parent.parent = child;
      // re-attach original grandchild
      parent.rightChild = leftGrandChild;
      if (leftGrandChild != null)
        leftGrandChild.parent = parent;
    }
    // put the child in its parent's position, and update its connection to the grandParent node
    child.parent = grandParent;
    if (parent != root) {
      if (parentIsLeftChild)
        grandParent.leftChild = child;
      else
        grandParent.rightChild = child;
    } else // if parent is the root, replace the root by child
      root = child;
  }

  /**
   * Recursive helper method to clear any black weight holder created be the remove method.
   * 
   * @param child  - a reference to the child node involved in the rotation
   * @param parent - a reference to the child's parent node involved in the rotation
   */
  private void clearHolder(Node<Place> current) {
    // find and clear any black weight holder node in the left subtree of the current node, if any
    if (current.leftChild != null) {
      if (current.leftChild.data.getZipCode() == -1) {
        current.leftChild.parent = null;
        current.leftChild = null;
      } else
        clearHolder(current.leftChild); // make recursive call on the left subtree, if any
    }
    // find and clear any black weight holder node in the right subtree of the current node, if any
    if (current.rightChild != null) {
      if (current.rightChild.data.getZipCode() == -1) {
        current.rightChild.parent = null;
        current.rightChild = null;
      } else
        clearHolder(current.rightChild); // make recursive call on the right subtree, if any
    }
  }

  /**
   * Displays the current tree node colors in a level order traversal. The color can be either Black
   * or Red, not both. For example: "[Black, Black, Red, Red, Red, Black, Black]".
   * 
   * @return a String object with the color of the ZipCodeRBT nodes in level order traversal
   */
  protected String colorLevelOrderTraversal() {
    String output = "[";
    LinkedList<Node<Place>> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      Node<Place> next = q.removeFirst();
      if (next.leftChild != null)
        q.add(next.leftChild);
      if (next.rightChild != null)
        q.add(next.rightChild);
      if (next.isBlack)
        output += "Black";
      else
        output += "Red";
      if (!q.isEmpty())
        output += ", ";
    }
    return output + "]";
  }

}
