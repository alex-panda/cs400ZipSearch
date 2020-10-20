// --== CS400 File Header Information ==--
// Name: Allistair Nathan Mascarenhas
// Email: anmascarenha@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

/**
 * Red black tree(RBT) implementation with a Node inner class for representing the nodes within a
 * RBT. This class has an insert method which can be used to insert nodes into a RBT. It also
 * enforces all the properties and automatically balances the RBT after each node is inserted. This
 * class also has a toString method to display the level order (breadth first) traversal of values
 * in that tree.
 *
 * @author Allistair
 *
 * @param <T> Generic type parameter denoting the type of nodes stored in the RBT
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;

    // stores if a node if black or red
    public boolean isBlack;

    public Node(T data) {
      this.data = data;
      this.isBlack = false;
      this.parent = null;
      this.leftChild = null;
      this.rightChild = null;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     *
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { // display subtree in order traversal
      String output = "[";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty

  /**
   * Performs a naive insertion into a RBT: adding the input data value to a new node in a leaf
   * position within the tree. After this insertion, an attempt is made to restructure or balance
   * the tree by calling insertHelper() which then calls enforceRBTreePropertiesAfterInsert(). This
   * tree will not hold null references, nor duplicate data values. The root node is always set to
   * black at the end of the insert method.
   *
   * @param data to be added into this RBT
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the tree already contains data
   */
  public void insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
    } // add first node to an empty tree
    else
      insertHelper(newNode, root); // recursively insert into subtree
    root.isBlack = true;
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   *
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descendant beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
   */
  private void insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      throw new IllegalArgumentException("This RedBlackTree already contains that value.");

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        // calls method to balance RBT after inserting a node
        // enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        // calls method to balance RBT after inserting a node
        // enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }
    enforceRBTreePropertiesAfterInsert(newNode);
  }

  /**
   * Enforces all the RBT properties by rotating or recolouring nodes. The method checks for all the
   * cases mentioned below and carries out the required actions to balance the tree.
   *
   * Case 0: if parent is black -> do nothing
   *
   * Case 1: if parent and sibling is red -> Set parent and sibling to black and set parent's parent
   * to red. Call the below method once again on parent's parent, propagating the enforcing upwards
   *
   * Case 2: if parent is red, sibling is black and sibling is on the same side as newNode -> call
   * rotate method on newNode and parent and then proceed to "Case 3"
   *
   * Case 3: if parent is red, sibling is black and sibling is on the opposite side as newNode ->
   * call rotate method on parent and parent's parent. Set parent's parent to red and change parent
   * to black
   *
   * @param newNode is the new node that was just added to this tree
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    Node<T> parentNode = newNode.parent;

    // checks if parent isn't null and is red
    if (parentNode != null && !parentNode.isBlack) {

      Node<T> siblingNode = getSibling(parentNode);
      boolean sameSide = newNode.isLeftChild() == !parentNode.isLeftChild();

      // checks if sibling is red
      if (siblingNode != null && !siblingNode.isBlack) {

        // Case 1: parent = red, sibling = red
        parentNode.isBlack = true;
        siblingNode.isBlack = true;
        parentNode.parent.isBlack = false;
        enforceRBTreePropertiesAfterInsert(parentNode.parent);
      } else {
        Node<T> gParentNode = parentNode.parent;
        if (sameSide) {
          // Case 2: parent = red, sibling = black, sameSide = true
          rotate(newNode, parentNode);
          // Case 3: parent = red, sibling = black, sameSide = false
          rotate(newNode, gParentNode);
          newNode.isBlack = true;
          gParentNode.isBlack = false;
        } else {
          // Case 3: parent = red, sibling = black, sameSide = false
          rotate(parentNode, gParentNode);
          gParentNode.isBlack = false;
          parentNode.isBlack = true;
        }
      }
    }
  }

  /**
   * Private helper method used to get the sibling of the current node.
   *
   * @param currNode is the current Node we want to find the sibling of
   * @return Node<T> - sibling of current node, null is returned if currNode is the root node or if
   *         currNode doesn't have a sibling
   */
  private Node<T> getSibling(Node<T> currNode) {
    if (currNode == root) {
      return null;
    } else {
      if (currNode.isLeftChild()) {
        return currNode.parent.rightChild;
      } else {
        return currNode.parent.leftChild;
      }
    }
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   *
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this RBT. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   *
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) {
    if (child != null && parent != null) {
      if (child.parent == parent && (parent.leftChild == child || parent.rightChild == child)) {
        boolean parentLeftChild = parent.isLeftChild();
        if (child.isLeftChild()) {
          // Do a right rotation
          Node<T> gChildRight = child.rightChild;
          child.rightChild = parent;
          parent.leftChild = gChildRight;
          if (gChildRight != null) {
            gChildRight.parent = parent;
          }
        } else {
          // Do a left rotation
          Node<T> gChildLeft = child.leftChild;
          child.leftChild = parent;
          parent.rightChild = gChildLeft;
          if (gChildLeft != null) {
            gChildLeft.parent = parent;
          }
        }

        if (parent.parent == null) {
          root = child;
          child.parent = null;
          parent.parent = child;
        } else {
          Node<T> gParent = parent.parent;
          child.parent = gParent;
          parent.parent = child;
          if (parentLeftChild) {
            gParent.leftChild = child;
          } else {
            gParent.rightChild = child;
          }
        }
      } else {
        throw new IllegalArgumentException();
      }
    }
  }
}

