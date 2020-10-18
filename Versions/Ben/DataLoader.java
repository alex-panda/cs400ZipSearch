// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Data Loader class
 * @author Ben
 *
 */
public class DataLoader {
	private ArrayList<Place> zipcodeList;// arraylist of the places
	private String csvPath;

	/**
	 * Default constructor. Loads csv from same directory.
	 */
	public DataLoader() {
		this("zipcode_clean.csv");
	}

	/**
	 * Loads csv from specified directory
	 * @param csvPath The specified path
	 */
	public DataLoader(String csvPath) {
		this.csvPath = csvPath;
		this.zipcodeList = new ArrayList<>();
		this.readData(this.csvPath);
	}

	/**
	 * Reads data from csv into Place objects and adds them to arraylist field
	 * @param csvPath
	 */
	private void readData(String csvPath) {
		try {
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
			String row = csvReader.readLine(); // Assigns header row
			while((row = csvReader.readLine()) != null) {
				String[] arrayRow = row.split(",");
				this.zipcodeList.add(new Place(Integer.parseInt(arrayRow[0]), arrayRow[1], arrayRow[3], arrayRow[2]));
			}
			csvReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: The file path specified does not exist or cannot be opened.");
		} catch (IOException e) {
			System.out.println("ERROR: Read error.");
		}
	}

	/**
	 * Getter method for arraylist of places
	 * @return
	 */
	public ArrayList<Place> getData() {
		return this.zipcodeList;
	}
}
