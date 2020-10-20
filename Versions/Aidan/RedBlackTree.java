// --== CS400 File Header Information ==--
// Name: Aidan Godfrey
// Email: amgodfrey@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimrel
// Notes to Grader: Had to change the insert method because of stack overflow
import java.util.LinkedList;


/**
 * Red Black Tree implementation with a Node inner class for representing
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
        public Node<T> leftChild = null; 
        public Node<T> rightChild = null;
        public String color = "red"; // By default the color is red	
        public boolean isBlack = false;
	public Node(T data) { this.data = data; }
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

       /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        public String toStringColor() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
		if(next.isBlack){
			output += "Black";
		}
		else{
			output += "Red";
		}
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }

	/*
	 * Getter method to find the color of this node
	 */
	public String getColor(){
	    return this.color;
	}

	/* 
	 * Sets the color of the node to either black or red
	 */
	public void setColor(String color){
	    this.color = color;
	    if(color.equals("black")){
		this.isBlack = true;
	    }
	    else{
		this.isBlack=false;
	    }
	}

	/*
	 * This method is meant to check if this node is black
	 * If it is, then true, otherwise false
	 */
	public boolean isBlack(){
	    if(this.color.equals("black")){
		return true;
	    }
	    else{
 	 	return false;
	    }
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
	else{
	    Node<T> curr = root;
	    while(curr != null){
            	if(curr.data.compareTo(newNode.data) == 0){
	            throw new IllegalArgumentException("Messed Up");
		}
		else if(curr.data.compareTo(newNode.data) < 0){
		    if(curr.rightChild == null){
			    curr.rightChild = newNode;
			    newNode.parent = curr;
			    enforceRBTreePropertiesAfterInsert(newNode);
			    break;
		    }
		    else if(curr.rightChild != null){
		    	curr = curr.rightChild;
		    }

		}
		else if(curr.data.compareTo(newNode.data) > 0){
		    if(curr.leftChild == null){
			    curr.leftChild = newNode;
			    newNode.parent = curr;
			    
			    enforceRBTreePropertiesAfterInsert(newNode);
			    
			    break;
		    }
		    else if(curr.leftChild != null){
		    	curr = curr.leftChild;
		    }
		}
	    }
	}
	root.setColor("black");
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

    public String colorLevelOrderTraversal(){ return root.toStringColor();}

    /*
     * Takes a reference to a new added red node as it
     * only param to recrusively see if the red child
     * and red parent porperty is violated
     */
    public void enforceRBTreePropertiesAfterInsert(Node<T> newNode){
	if(newNode.parent.color.equals("black")){
	    return;
	}
	
	if(newNode == root){
		return;
	}
	// Now, the parent must be red, two cases
	// Let use check if the node is the left child and go forward	
	if(newNode == newNode.parent.leftChild && newNode.color.equals("red") && newNode.parent.color.equals("red")){
	    // Case 1: if the NewNode's sibling is black or null
	    	
	    if(newNode.parent.parent.rightChild == null || newNode.parent.parent.rightChild.color.equals("black") ||
			    newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.color.equals("black")){
	        // Then we must rotate right, and recolor
		// Let us recolor first since rotate does not affect color

		// Case 1: right rotation, in line going down left
		if(newNode.parent == newNode.parent.parent.leftChild){
		    newNode.parent.parent.setColor("red");
		    newNode.parent.setColor("black");
		    newNode.setColor("red");
		    rotate(newNode.parent, newNode.parent.parent);
		}
		else{ // Case 2:  Right Left  rotation
		    rotate(newNode, newNode.parent);
		    rotate(newNode, newNode.parent);
		    newNode.leftChild.setColor("red");
		    newNode.rightChild.setColor("red");
		    newNode.setColor("black");
		
		}
		
	    }
	    // Case 2: The newNode's sibling is red, just recolor
	    else{ 
		// Set G to red, p and s to black, c to red
		newNode.setColor("red");
		newNode.parent.setColor("black");
		newNode.parent.parent.setColor("red");
		
		if(newNode.parent == newNode.parent.parent.leftChild){
		    if(newNode.parent.parent.rightChild != null){
		        newNode.parent.parent.rightChild.setColor("black");
		    }
		}
		else{
		    if(newNode.parent.parent.leftChild != null){
		        newNode.parent.parent.leftChild.setColor("black");
		    }
		}
	    	enforceRBTreePropertiesAfterInsert(newNode.parent);
	    }
	
	}
	// Now, the parent must be red, two cases
	// Let use check if the node is the right child and go forward	
	else if(newNode == newNode.parent.rightChild && newNode.color.equals("red") && newNode.parent.color.equals("red")){
	    // Case 1: if the NewNode's sibling is black or null
	    if(newNode.parent.parent.leftChild == null || newNode.parent.parent.leftChild.color.equals("black")||
			     newNode.parent.parent.rightChild == null || newNode.parent.parent.rightChild.color.equals("black")){

		// Case 1: left rotation, in line going down right
		if(newNode.parent == newNode.parent.parent.rightChild){
		   
		    newNode.parent.parent.setColor("red");
		    newNode.parent.setColor("black");
		    newNode.setColor("red");
		    rotate(newNode.parent, newNode.parent.parent);
		}
		else{ // Case 2:  Right Left  rotation
   		    rotate(newNode, newNode.parent);
		    rotate(newNode, newNode.parent);
		    newNode.leftChild.setColor("red");
		    newNode.rightChild.setColor("red");
		    newNode.setColor("black");
		    	
		}
		
	    }
	    // Case 2: The newNode's sibling is red, just recolor
	    else{ 
            	// Set G to red, p and s to black, c to red
		newNode.setColor("red");
		newNode.parent.setColor("black");
		newNode.parent.parent.setColor("red");
		if(newNode.parent == newNode.parent.parent.leftChild){
		    if(newNode.parent.parent.rightChild != null){
			newNode.parent.parent.rightChild.setColor("black");
		    }
		}
		else{
		    if(newNode.parent.parent.leftChild!= null){
		        newNode.parent.parent.leftChild.setColor("black");
		    }
		}
	    	enforceRBTreePropertiesAfterInsert(newNode.parent);
	    }
	
	}


		
    }



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
	if(child != parent.leftChild && child != parent.rightChild){
		throw new IllegalArgumentException("The child provided to rotate is no child of the parent");
	}

	// If the child is the left child of the parent, perfrom a right rotation!
	if(child == parent.leftChild){
		// Create a temporary node to collect the data for parent node 
		Node<T> grandParent = parent.parent; // Grandparent needs to alter the child refrence to the child
	
		parent.parent = child; // Change the parent to the child position
		parent.leftChild = child.rightChild;
		if(child.rightChild != null){
			child.rightChild.parent = parent;
		}
		// Parent.right is already set for a right rotation


		child.parent = grandParent; // Now, traverse child up to the parent spot
		//Child.left is already correct
		child.rightChild = parent;
		
		if(grandParent != null){
			if(grandParent.leftChild == parent){
				grandParent.leftChild = child;
			}
			else if(grandParent.rightChild == parent){
				grandParent.rightChild = child;
			}
		}
		else{
			root = child;
		}
	}	// If the grandparent is null, then we are at the root so do not change anythin

	// If the child is the right child of the parent, perform a left rotation!
	else if(child == parent.rightChild){
		// Create a temporary node to collect the data for parent node 
		Node<T> grandParent = parent.parent; // Grandparent needs to alter the child refrence to the child
	
		parent.parent = child; // Change the parent to the child position
		parent.rightChild = child.leftChild;
		if(child.leftChild != null){
			child.leftChild.parent = parent;
		}
		// Parent.right is already set for a right rotation


		child.parent = grandParent; // Now, traverse child up to the parent spot
		//Child.left is already correct
		child.leftChild = parent;
		
		if(grandParent != null){
			if(grandParent.leftChild == parent){
				grandParent.leftChild = child;
			}
			else if(grandParent.rightChild == parent){
				grandParent.rightChild = child;
			}
		}
		else{
			root = child;
		}
		// If the grandparent is null, then we are at the root so do not change anythin

	}
       
    }
   
}
