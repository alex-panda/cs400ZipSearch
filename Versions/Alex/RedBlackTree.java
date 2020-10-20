// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
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

        /**
         * Constructs a new Node Object.
         * @param data the data that this node will hold.
         * */
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
                //System.out.println("output: " + output);
                //Thread.dumpStack();
            }
            return output + "]";
        }

        /**
         * Sets the left child of this node to the given node.
         * @param n the node to set as the leftchild of this node
         * */
        public void setLeftChild(Node<T> n) {
            this.leftChild = n;
            if (n != null) n.parent = this;
        }

        /**
         * Sets the right child of this node to the given node.
         * @param n the node to set as the rightchild of this node
         * */
        public void setRightChild(Node<T> n) {
            this.rightChild = n;
            if (n != null) n.parent = this;
        }

        /**
         * Returns a string for testing purposes that labels every node with what color the node is
         *  and its data's value. It lists these in a level order traversal of the tree.
         */
        public String toTestString() {
                String output = "[";
                LinkedList<Node<T>> q = new LinkedList<>();
                q.add(this);
                while(!q.isEmpty()) {
                    Node<T> next = q.removeFirst();
                    if(next.leftChild != null) q.add(next.leftChild);
                    if(next.rightChild != null) q.add(next.rightChild);

                    if(next.isBlack) output += "B:";
                    else output += "R:";

                    output += next.data.toString();
                    if(!q.isEmpty()) output += ", ";
                }
                return output + "]";
        }
    }

    /**
     * Gets a string for testing purposes that labels every node with what color the node is
     *  and its data's value. It lists these in a level order traversal of the tree.
     */
    public String toTestString() {
        if (this.root == null)
            return "[]";
        else
            return this.root.toTestString();
    }

    protected Node<T> root; // reference to root node of tree, null when empty

    /**
     * Sets the given node to the root of the RedBlackTree
     * @param node the node you want to set as the root of the RedBlackTree
     * */
    private void setRoot(Node<T> node) {
        this.root = node;
        node.parent = null;
    }

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree.  After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(this.root == null) {
            this.root = newNode; // add first node to an empty tree
        } else {
            insertHelper(newNode,root); // recursively insert into subtree
        }
        this.root.isBlack = true; // make the root always be black
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

        // Make sure that the tree, after the node is inserted, is a valid red
        //  black tree, fixing it if necessary
        //System.out.println("fulltree Before: " + this.toTestString());
        //System.out.println("newNode: " + newNode.toString());
        enforceRBTreePropertiesAfterInsert(newNode);
        //System.out.println("fulltree After: " + this.toTestString());
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
     * rotation). When the provided child is a rightChild of the provided
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
    public void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if (child == parent.leftChild) { // If child is the left child
            // Perform right or left-right rotation
            rotateRight(child, parent);

        } else if (child == parent.rightChild) { // if child is the right child
            // Perform a left or right-left rotation
            rotateLeft(child, parent);

        } else {
            throw new IllegalArgumentException("\"Child\" node was not a child of" +
                    " the given parent node. \nParent Node Hierarchy: " + parent);
        }
    }

    /**
     * The job of this method is to resolve red child under red parent red
     *  black tree property violations that are introduced by inserting new
     *  nodes into a red black tree. While doing so, all other red black tree
     *  properties must also be preserved.
     * @param newNode the node just Naively inserted into the BST
     * */
    private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
        while (root != newNode && newNode.parent.isBlack == false) {
            Node<T> uncle = null;
            if (newNode.parent == newNode.parent.parent.leftChild) {
                uncle = newNode.parent.parent.rightChild;

                // If the uncle is Red
                if (uncle != null && !uncle.isBlack) {
                    // Just need to recolor the nodes
                    newNode.parent.isBlack = true;
                    uncle.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    // Advance up the tree
                    newNode = newNode.parent.parent;
                    continue;
                }

                Node<T> nodeToRotateRight = newNode;
                // If the newnode is the rightChild of its parent
                if (newNode == newNode.parent.rightChild) {
                    // Left-Right rotation needed so do left rotation here, right
                    //  rotation will happen after
                    nodeToRotateRight = newNode.parent;
                    // Perform left rotation
                    rotate(newNode, newNode.parent);
                }

                // Recolor node for right rotation
                nodeToRotateRight.parent.isBlack = true;
                nodeToRotateRight.parent.parent.isBlack = false;
                // Perform Right rotation
                rotate(nodeToRotateRight.parent, nodeToRotateRight.parent.parent);

            } else {
                uncle = newNode.parent.parent.leftChild;

                // If the uncle is Red
                if (uncle != null && uncle.isBlack == false) {
                    // Just need to recolor the nodes
                    newNode.parent.isBlack = true;
                    uncle.isBlack = true;
                    newNode.parent.parent.isBlack = false;
                    // Advance up the tree
                    newNode = newNode.parent.parent;
                    continue;
                }

                Node<T> nodeToRotateLeft = newNode;
                // If the newnode is the leftChild of its parent
                if (newNode == newNode.parent.leftChild) {
                    // Right-Left rotation needed so do right rotation here, left
                    //  rotation will happen after
                    nodeToRotateLeft = newNode.parent;
                    // Perform right rotation
                    rotate(newNode, newNode.parent);
                }
                // Recolor node for left rotation
                nodeToRotateLeft.parent.isBlack = true;
                nodeToRotateLeft.parent.parent.isBlack = false;
                // Perform left rotation
                rotate(nodeToRotateLeft.parent, nodeToRotateLeft.parent.parent);
            }
        }
    }

    //
    // Helper Methods

    /**
     * Helper method that rotates the given nodes right in the tree.
     * @param child the child of the parent that you are rotating
     * @param parent the parent of the child you are rotating
     * */
    private void rotateRight(Node<T> child, Node<T> parent) {
        parent.setLeftChild(child.rightChild);
        if (parent.parent == null)
            this.setRoot(child);
        else if (parent.parent.leftChild == parent)
            parent.parent.setLeftChild(child);
        else
            parent.parent.setRightChild(child);
        child.setRightChild(parent);
    }

    /**
     * Helper method that rotates the given nodes left in the tree.
     * @param child the child of the parent that you are rotating
     * @param parent the parent of the child you are rotating
     * */
    private void rotateLeft(Node<T> child, Node<T> parent) {
        parent.setRightChild(child.leftChild);
        if (parent.parent == null)
            this.setRoot(child);
        else if (parent.parent.leftChild == parent)
            parent.parent.setLeftChild(child);
        else
            parent.parent.setRightChild(child);
        child.setLeftChild(parent);
    }
}


