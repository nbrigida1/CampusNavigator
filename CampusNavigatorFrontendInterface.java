/**
 * The CampusNavigatorFrontendInterface is a boilerplate for the class 
 * CampusNavigatorFrontend class which is the class that creates the UI.
 * @author Aditya 
 *
 */
public interface CampusNavigatorFrontendInterface { 
//public LibraryCheckoutFrontendXX(Scanner userInput, MadisonPathsBackendInterface backend);
  
/**
 * Repeatedly prompts user for input and display the correct operation for each.
 * Q to stop
 */
public void runCommandLoop();




/**
 * Prints the command options to System.out and reads user's choice through
 * userInput scanner
 * @return
 */
public String mainMenuPrompt();


/**
 * Prompts user to load in a file and displays an error message when filename is invalid
 * If valid uses bckend to load file
 */
public void loadDataCommand(); 


/**
 * Returns the lowest distance to the buildings added to the path 
 */
public void searchBuildingDistancesCommand();


/**
 * Adds building to the path list
 * @param name of the building you want to add to the path
 */
public void addBuildingCommand(String name); 


/**
 * Returns the buildings presently on the path list 
 */
public void displayBuildingsCommand();


/**
 *  clears the path list
 */
public void clearPath(); 


/**
 * 
 * @param name of the building you want to remove from the path list
 */
public void removeBuildingFromPath(String name); 
}