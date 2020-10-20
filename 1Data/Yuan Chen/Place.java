// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// Role: Data Wrangler
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class defines the Place object and stores the information of zipcode
 * and related city/county/state
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
   
   /**
    * city getter
    * @return city name of the zipcode
    */
   public String getCity() {return city;}
   
   /**
    * city mutator
    * @param set the name of the city
    */
   public void setCity(String city) {this.city = city;}
   
   /**
    * county getter
    * @return county name of the zipcode
    */
   public String getCounty() {return county;}
   
   /**
    * county mutator
    * @param set the name of the county
    */
   public void setCounty(String county) {this.county = county;}
   
   /**
    * state getter
    * @return state name of the zipcode
    */
   public String getState() {return state;}
   
   /**
    * state mutator
    * @param set the name of the state
    */
   public void setState(String state) {this.state = state;}

   /**
    * zipcode getter
    * @return zipcode
    */
   public int getZipCode() {return zipcode;}
   
   /**
    * zipcode mutator
    * @param zipcode
    */
   public void setZipCode(int zipcode) {this.zipcode = zipcode;}
   
   /**
    * the method return a string contains the information of the zipcode
    */
   public String toString() {
      return "Zipcode: " + this.zipcode + ", City: " 
   + this.city + ", County: " + this.county 
   + ", State: " + this.state + ".";
   }
   
   /**
    * this method extends the function of Comparable()
    * and allows the program to compare two Place objects
    */
   @Override
   public int compareTo(Place p) {
      return this.zipcode - p.getZipCode();
   }
   
}
