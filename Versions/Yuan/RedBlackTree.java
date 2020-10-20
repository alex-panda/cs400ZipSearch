// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// Role: Front end developer
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes within a binary search tree.  You can use this class' insert
 * method to build a binary search tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always be maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild; 
        public Node<T> rightChild;
        public boolean isBlack;
        
        public Node(T data) { 
           this.data = data; 
           this.isBlack = false;
        }
        
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree.  After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException,
				      IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) { 
           root = newNode; 
           } // add first node to an empty tree
        else insertHelper(newNode,root); // recursively insert into subtree
        root.isBlack = true;
    }
    
    /**
     * This method enforce the red black rules of the red black tree
     * @param n is the node just inserted
     */
    private void enforceRBTreePropertiesAfterInsert(Node<T> n) {
       Node<T> s; // sibling node
       Node<T> p = n.parent; //parent node
       Node<T> g = n.parent.parent;//grandpa node
       if (n == root) {//if node is root, color it black and done
          n.isBlack = true;
       }
       else if (!p.isBlack) {// parent is red
          if (p == g.leftChild) {//parent is left child
             s = g.rightChild;
             if (s == null || s.isBlack) {//sibling is black or null&parent is red
                if (n.isLeftChild()) {//n is left child & p is left child
                   rotate(p,g);
                   p.isBlack = true;
                   g.isBlack = false;
                } else {//n is right child & p is left child
                   rotate(n,p);
                   rotate(n,g);
                   n.isBlack = true;
                   g.isBlack = false;
                }
                //recursively enforce RBTree rules
                if (p.parent != null) {
                   if (p.parent.parent != null) {
                      enforceRBTreePropertiesAfterInsert(p);
                   }
                }
             }
             else {//sibling is red&parent is red
                p.isBlack = true;
                s.isBlack = true;
                g.isBlack = false;
                //recursively enforce RBTree rules
                if (g.parent != null) {
                   if (g.parent.parent != null) {
                      enforceRBTreePropertiesAfterInsert(g);
                   }
                }
             } 
             
          }
          if (p == g.rightChild) {//parent is right child
             s = g.leftChild;
             if (s == null || s.isBlack) {//sibling is black or null&parent is red
                if (!n.isLeftChild()) {//n is right child & p is right child
                   rotate(p,g);
                   p.isBlack = true;
                   g.isBlack = false;
                } else {//n is left child & p is right child
                   rotate(n,p);
                   rotate(n,g);
                   n.isBlack = true;
                   g.isBlack = false;                   
                }
                //recursively enforce RBTree rules
                if (p.parent != null) {
                   if (p.parent.parent != null) {
                      enforceRBTreePropertiesAfterInsert(p);
                   }
                }
             }
             else {//sibling is red&parent is red
                p.isBlack = true;
                s.isBlack = true;
                g.isBlack = false;
                //recursively enforce RBTree rules
                if (g.parent != null) {
                   if (g.parent.parent != null) {                
                      enforceRBTreePropertiesAfterInsert(g);
                   }
                }
             } 
             
          }
       }
    }

    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");

        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
        if (newNode.parent != null) {
           if (newNode.parent.parent != null) {
              enforceRBTreePropertiesAfterInsert(newNode);
           }
        }
        
    }
    
    

    /**
     * This method performs a level order traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { return root.toString(); }

    /**
     * Performs the rotation operation on the provided nodes within this BST.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right 
     * rotation).  When the provided child is a rightChild of the provided 
     * parent, this method will perform a left rotation (sometimes called a 
     * right-left rotation).  When the provided nodes are not related in one 
     * of these ways, this method will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent)
	throws IllegalArgumentException {
       if (child.equals(parent.leftChild)) {
          if (parent.equals(root)) {
             root = child;
             rotateRight(child, parent);             
          }
          else if (parent.isLeftChild()){
             parent.parent.leftChild = child;
             rotateRight(child, parent);   
          }
          else {
             parent.parent.rightChild = child;
             rotateRight(child, parent); 
          }
       }
       else if(child.equals(parent.rightChild)) {
          if (parent.equals(root)) {
             root = child;
             rotateLeft(child, parent);             
          }
          else if (parent.isLeftChild()){
             parent.parent.leftChild = child;
             rotateLeft(child, parent);   
          }
          else {
             parent.parent.rightChild = child;
             rotateLeft(child, parent); 
          }
       }
       else {
          throw new IllegalArgumentException();
       }
    }
    
    /**
     * This method help left rotate the two nodes 
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     */
    private void rotateLeft(Node<T> child, Node<T> parent) {
       Node<T> temp = child.leftChild;
       child.parent = parent.parent;
       parent.parent = child;
       child.leftChild = parent;
       parent.rightChild = temp;
       parent.parent = child;
    }
    
    /**
     * This method help right rotate the two nodes 
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     */
    private void rotateRight(Node<T> child, Node<T> parent) {
       Node<T> temp = child.rightChild;
       child.parent = parent.parent;
       parent.parent = child;
       child.rightChild = parent;
       parent.leftChild = temp;
       parent.parent = child;
    }

}
