import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// --== CS400 File Header Information ==--
// Name: Yuan Chen
// Email: chen2243@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class reads a csv file and store the data in an array list of Place objects
 * 
 * @author Yuan Chen
 *
 */
public class DataLoader {
  private ArrayList<Place> zipcodeList;
  private String csvPath;

  public DataLoader() {
    // this.csvPath = "zipcode_clean.csv";
    this.csvPath =
        "C:\\Users\\Allistair\\eclipse-workspace\\Project2_ZipSearch\\src\\zipcode_clean.csv";
    this.zipcodeList = new ArrayList<>();
    readData(csvPath);
  }

  public DataLoader(String csvPath) {
    this.csvPath = csvPath;
    this.zipcodeList = new ArrayList<>();
    readData(csvPath);
  }

  public void readData(String csvPath) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(csvPath));
      String line = "";
      @SuppressWarnings("unused")
      String header = br.readLine();
      while ((line = br.readLine()) != null) {
        String[] zipcodeInfo = line.split(",");
        int zipcode = Integer.parseInt(zipcodeInfo[0]);
        String city = zipcodeInfo[1];
        String state = zipcodeInfo[2];
        String county = zipcodeInfo[3];
        zipcodeList.add(new Place(zipcode, city, county, state));
      }
      br.close();
    } catch (FileNotFoundException e) {
      System.out.println("WARNING: zipcode data file " + csvPath + " was not found!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public ArrayList<Place> getData() {
    return this.zipcodeList;
  }

}
