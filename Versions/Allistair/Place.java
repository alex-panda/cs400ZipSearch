// --== CS400 File Header Information ==--
// Name: Yuan Chen
// Email: chen2243@wisc.edu
// Team: DC
// Role: Front end developer
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class defines the Place object and stores the information of zipcode and related
 * city/county/state
 * 
 * @author Yuan Chen
 *
 */
public class Place implements Comparable<Place> {
  private int zipcode;
  private String city;
  private String county;
  private String state;

  /**
   * Default constructor
   */
  public Place() {
    this.zipcode = 00000;
    this.city = "";
    this.county = "";
    this.state = "";
  }

  /**
   * Parameterized constructor
   */
  public Place(int zipCode, String city, String county, String state) {
    this.zipcode = zipCode;
    this.city = city;
    this.county = county;
    this.state = state;
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

  public int getZipCode() {
    return zipcode;
  }

  public void setZipCode(int zipcode) {
    this.zipcode = zipcode;
  }

  public String toString() {
    return "Zipcode: " + this.zipcode + ", City: " + this.city + ", County: " + this.county
        + ", State: " + this.state + ".";
  }

  @Override
  public int compareTo(Place p) {
    return this.zipcode - p.getZipCode();
  }

}
