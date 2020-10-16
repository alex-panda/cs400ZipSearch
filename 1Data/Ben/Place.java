
public class Place implements Comparable<Place> {
	private int zipcode;
	private String city;
	private String county;
	private String state;
	private Place left;// left child of the node
	private Place right;// right child of the node
	private boolean isBlack;// true if node is black, false if red

	public Place(int zipcode, String city, String county, String state) {
		this.zipcode = zipcode;
		this.city = city;
		this.county = county;
		this.state = state;
		this.left = null;
		this.right = null;
		this.isBlack = false;
	}

	@Override
	public String toString() {
		return "Zipcode " + this.zipcode + ": " + this.city + ", " + this.state + " (" + this.county
				+ " county)";
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Place getLeft() {
		return left;
	}

	public void setLeft(Place left) {
		this.left = left;
	}

	public Place getRight() {
		return right;
	}

	public void setRight(Place right) {
		this.right = right;
	}

	public boolean getIsBlack() {
		return isBlack;
	}

	public void isBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	@Override
	public int compareTo(Place o) {
		return this.zipcode - o.getZipcode();
	}
}
