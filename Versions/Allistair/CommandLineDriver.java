// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// Role: Front End Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: This class is the one whose main method you run to run the
// application.

import java.util.Scanner;

/**
 * This class drives the Zip Search application. When you run its main method, it allows you to: 1:
 * See if a zipcode exists in the database. 2: Input a zipcode and get its corresponding city,
 * county, and state. 3: Remove a zipcode from the database. 4: Add a zipcode to the database. 5:
 * Exit the program. on loop until you decide to quit the program using option 5.
 */
public class CommandLineDriver {

  private final static String WELCOME_MESSAGE =
      "\n======= WELCOME to the UW-Madison Zip Code Manager! =======\n"
          + "Using this command line application you can lookup, add, and remove Zipcodes.\n";

  private final static String INVALID_COMMAND = "\nThe command you just entered was invalid.\n"
      + "Please pick a number from 1 to 5 for its corresponding command.\n";

  private final static String MENU = "1: See if a Zipcode exists in the database.\n"
      + "2: Input a Zipcode and get its corresponding city, county, and state.\n"
      + "3: Remove a Zipcode from the database.\n" + "4: Add a Zipcode to the database.\n"
      + "5: Exit the program.\n" + "Please enter a command:\n";

  private final static String NEXT_COMMAND = "================ Next Command ================\n";

  /**
   * The main method that drives the application. When run, this method prompts the user for choices
   * and inputs and displays the corresponding results of the choice and inputs.
   * 
   * @param args Any arguments for the application.
   */
  public static void main(String[] args) {
    // Create the ZipCodeRBT which will, on construction, call the Backend
    // Class and get the zipcodes
    ZipCodeRBT zipRBT = new ZipCodeRBT();
    System.out.print(WELCOME_MESSAGE);

    // The main scanner through which the user will interact with the application
    Scanner scanner = new Scanner(System.in);

    boolean exitApp = false;

    // How many commands have been put into the application
    int i = 0;

    // This is an infinite loop until the user quits the application
    while (true) {
      char command;

      // Loop to ask the user for a command until they give a valid command.
      while (true) {
        if (i > 0)
          System.out.print(NEXT_COMMAND);
        System.out.print(MENU);
        String word = scanner.nextLine();
        i++;
        if (word.length() >= 1) {
          command = word.charAt(0);
          if ('1' <= command && command <= '5')
            break;
        }
        System.out.print(INVALID_COMMAND);
      }

      int zipcode;
      // The main switch for processing user commands
      switch (command) {
        case '1': // 1: See if a zipcode exists in the database.
          System.out.println("Please enter the Zipcode you want to check for:");
          try {
            zipcode = Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter the Zipcode as a number next time.");
            break;
          }

          if (zipRBT.contains(zipcode))
            System.out.println("The specified Zipcode exists in the database.");
          else
            System.out.println("The specified Zipcode does not exist in the database.");
          break;

        case '2': // 2: Input a zipcode and get its corresponding city, county, and state.
          System.out.println(
              "Please input the Zipcode you want the corresponding city, county, and state of.");
          try {
            zipcode = Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter the Zipcode as a number next time.");
            break;
          }

          // Print the place to the screen
          Place place = zipRBT.getPlace(zipcode);
          if (place != null) {
            String placeStr = "";
            placeStr += "City: " + place.getCity() + ", ";
            placeStr += "County: " + place.getCounty() + ", ";
            placeStr += "State: " + place.getState();
            System.out.println(placeStr);
          } else {
            System.out.println("ERROR: The Zipcode Entered was not in the database.");
          }
          break;

        case '3': // 3: Remove a zipcode from the database.
          System.out.println("Please input the Zipcode you want to remove from the database.");
          try {
            zipcode = Integer.parseInt(scanner.nextLine().trim());
          } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter the Zipcode as a number next time.");
            break;
          }

          if (zipRBT.remove(zipcode)) {
            System.out.println("Successfully removed the Zipcode.");
          } else {
            System.out.println("The Zipcode was not in the database.");
          }
          break;

        case '4': // 4: Add a zipcode to the database.
          System.out.println("Please input the Zipcode and its information as prompted:");
          System.out.println("Zipcode:");
          try {
            zipcode = Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e) {
            System.out.println("ERROR: Please enter the Zipcode as a number next time.");
            break;
          }

          System.out.println("City:");
          String city = scanner.nextLine();
          System.out.println("County:");
          String county = scanner.nextLine();
          System.out.println("State:");
          String state = scanner.nextLine();

          // Add the new zipcode to the datase
          if (zipRBT.add(zipcode, city, county, state)) {
            System.out.println("Zipcode successfully added to the database.");
          } else {
            System.out.println("Zipcode could not be added to the database.");
          }
          break;

        case '5': // 5: Exit the program.
          exitApp = true;
          break;
      } // End of switch case
      System.out.println(""); // Print newline after input to seperate inputs
      if (exitApp)
        break;
    } // End of while loop
    scanner.close();
  }
}
