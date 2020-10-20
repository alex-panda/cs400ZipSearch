// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// Role: Data Wrangler
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

/**
 * Place class for Data Loader
 * 
 * @author Ben
 *
 */
public class Place implements Comparable<Place> {
	private int zipcode;
	private String city;
	private String county;
	private String state;
	private Place left;// left child of the node
	private Place right;// right child of the node
	private boolean isBlack;// true if node is black, false if red
	/**
	 * Constructor to initialize place fields
	 * 
	 * @param zipcode
	 * @param city
	 * @param county
	 * @param state
	 */	
	public Place(int zipcode, String city, String county, String state) {
		this.zipcode = zipcode;
		this.city = city;
		this.county = county;
		this.state = state;
		this.left = null;
		this.right = null;
		this.isBlack = false;
	}
	/**
	 * ToString() implementation
	 */
	@Override
	public String toString() {
		return "Zipcode " + this.zipcode + ": " + this.city + ", " + this.state + " (" + this.county
				+ " county)";
	}
	/**
	 * Getter for the zipcode
	 * @return zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * Setter for the zipcode
	 * @param zipcode 
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Getter for the city
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Setter for the city
	 * @param city to be set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Country getter
	 * 
	 * @return country
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * country setter
	 * 
	 * @param county
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * state getter
	 * 
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * state setter
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * left getter
	 * 
	 * @return left
	 */
	public Place getLeft() {
		return left;
	}

	/**
	 * left setter
	 * 
	 * @param left
	 */
	public void setLeft(Place left) {
		this.left = left;
	}

	/**
	 * right getter
	 * 
	 * @return right
	 */
	public Place getRight() {
		return right;
	}

	/**
	 * right setter
	 * 
	 * @param right
	 */
	public void setRight(Place right) {
		this.right = right;
	}

	/**
	 * isBlack getter
	 * 
	 * @return isBlack
	 */
	public boolean getIsBlack() {
		return isBlack;
	}

	/**
	 * isBlack setter
	 * 
	 * @param isBlack
	 */
	public void isBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	/**
	 * comapareTo method override
	 */
	@Override
	public int compareTo(Place o) {
		return this.zipcode - o.getZipcode();
	}
}

