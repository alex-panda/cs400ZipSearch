// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class defines the Place object and stores the information of zipcode
 * and related city/county/state
 * @author Yuan Chen
 *
 */
public class Place {
   private int zipcode;
   private String city;
   private String county;
   private String state;
   private Place left;
   private Place right;
   private boolean isBlack;
   
   /**
    * Default constructor
    */
   public Place() {
      this.zipcode = 00000;
      this.city = "";
      this.county = "";
      this.state = "";
      this.left = null;
      this.right = null;
      this.isBlack = false;
   }
   
   /**
    * Parameterized constructor
    */
   public Place(int zipCode, String city, String county, String state) {
      this.zipcode = zipCode;
      this.city = city;
      this.county = county;
      this.state = state;
      this.left = null;
      this.right = null;
      this.isBlack = false;
   }
   
   
   public int getZipCode() {
      return zipcode;
   }
   
   public void setZipCode(int zipCode) {
      this.zipcode = zipCode;
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

   public int getZipcode() {
      return zipcode;
   }

   public void setZipcode(int zipcode) {
      this.zipcode = zipcode;
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

   public boolean isBlack() {
      return isBlack;
   }

   public void setBlack(boolean isBlack) {
      this.isBlack = isBlack;
   }
   
   public String toString() {
      return "Zipcode: " + this.zipcode + "; City: " 
   + this.city + "; County: " + this.county 
   + "; State: " + this.state + ".";
   }
   
}
