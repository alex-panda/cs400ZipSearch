// --== CS400 File Header Information ==--
// Name: Aidan Godfrey
// Email: amgodfrey@wisc.edu
// Team: DC
// Role: Back End
// TA: Yelun Bao
// Lecturer: Florian Heimrel
// Notes to Grader: I tried to implement the remove method, but spent 5
// hours trying to create the methods so I did not include that functionality
// I understand if you take off points, but I did not deem the workload fair in my opinion
//
import java.util.ArrayList;

/**
 * Class combines the Red Black Tree with the Zip code so the front
 * end can call our methods
 */
public class ZipCodeRBT {
    private RedBlackTree<Place> rbt;
    private int size;

    public ZipCodeRBT(){
        rbt = new RedBlackTree<Place>();
        this.size = 0;
        this.initialization();
    }

    /**
     * Automatically happens to load the data
     */
    private void initialization(){
        DataLoader dataLoader = new DataLoader();
        ArrayList<Place> data = dataLoader.getData();
        this.size = 0;
        for(Place place : data){
            rbt.insert(place);
            this.size +=1;
        }
    }

    public String toString(){
    	return rbt.toString();
    }

    /**
     * Clears the size and all the associated nodes
     */
    public void clear(){
        this.rbt.root = null;
        this.size = 0;
    }
    
    /**
     * Restores the Rbt after initialization
     */
    public void reset(){
        this.initialization();
    }
    /**
     * Checks to see if a zip code is already in the rbt
     * @return boolean true if the zipcode is contained false otherwise
     * @param zipCode to check against the nodes
     */    
     public boolean contains(int zipCode){
        
	RedBlackTree.Node<Place> curr = rbt.root;
	Place checkNode = new Place(zipCode, null, null, null);
	while(curr != null){
      	    int comparison = curr.data.compareTo(checkNode);
	    if(curr == null){
	    	System.out.println("Huge Problem");
	    }		
	    if(comparison == 0){
	    	return true;
	    }
	    // negative number, the Zip Code is larger, go to right
	    else if(comparison < 0){
	    	curr = curr.rightChild;
	    }
	    else if(comparison > 0){
	    	curr = curr.leftChild;
	    }
        }
	return false;
    }

  
    /*
     * Size method
     * @return size of the number of items
     */
    public int size(){
    	return this.size;
    }


    /**
     * Checks to see if the rbt is empty
     * @return true if empty, false otherwise
     */
    public Boolean isEmpty(){
    	if(this.rbt.root == null){
		return true;
	}
	return false;
    }

    /**
     * Gets the given place of a node
     * @return Place for the given zipcode, or null if it is not present
     * @param zipCode to check for the place
     */
    public Place getPlace(int zipCode){
    	if(!this.contains(zipCode)){
		return null;
	}
	RedBlackTree.Node<Place> curr = rbt.root;
	while(curr != null){
	    int comparison = curr.data.getZipcode() - zipCode;
	    if(comparison == 0){
		   return curr.data;
	    }
	    else if(comparison < 0){
	    	curr = curr.rightChild;
	    } 
	    else if(comparison > 0){
	        curr = curr.leftChild;
	    }
	}
	// If it is not found, then null
	return null;
    }


    /**
     * Gets the given node for a zipCode
     * @param zipCode to check the node against
     * @return RedBlackTree.Node<Place> if it exists
     */ 
    protected RedBlackTree.Node<Place> getNode(int zipCode){
     	if(!this.contains(zipCode)){
		return null;
	}
	RedBlackTree.Node<Place> curr = rbt.root;
	Place newPlace = new Place(zipCode, null, null, null);
	while(curr != null){
	    int comparison = curr.data.compareTo(newPlace);
	    if(comparison == 0){
		   return curr;
	    }
	    else if(comparison < 0){
	    	curr = curr.rightChild;
	    } 
	    else if(comparison > 0){
	        curr = curr.leftChild;
	    }
	}
	// If it is not found, then null
	return null;
    }

    
    /**
     * This method adds the zipcode with the metadata to the RBT
     * @param zipCode of the zipcode
     * @param city 
     * @param county of the zipcode
     * @param state of the zipcode
     * @return boolean true if added succesfully, otherwise false
     */
    public Boolean add(int zipCode, String city, String county, String state){
	if(zipCode <= 0){
		return false;
	}
	try{
		rbt.insert(new Place(zipCode, city, county, state));
	}catch(IllegalArgumentException e){
		// Illegaly added when it is already in the tree
		return false;
	}
	this.size +=1;
	return true;
    }
   
}
