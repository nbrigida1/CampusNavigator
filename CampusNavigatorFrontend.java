import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CampusNavigatorFrontend implements CampusNavigatorFrontendInterface {
  private final Scanner userInput;
  private final CampusNavigatorBackendInterface backend;
  private final List<String> paths = new ArrayList<>();

  /**
   * Constructor for frontend to make use of Scanner and LibraryCheckoutBackend
   *
   * @param userInput A Scanner to pull user input from
   * @param backend   The backend to use for interacting with the Library
   */
  public CampusNavigatorFrontend(
    Scanner userInput, 
    CampusNavigatorBackendInterface backend
  ) {
    this.userInput = userInput;
    this.backend = backend;
  }

  /**
   * Private helper to display lines above and below welcome and exit messages
   */
  private void hr() {
    System.out.println("-".repeat(50));
  }

  /**
   * Repeatedly prompts user for input and display the correct operation for each.
   * Q to stop
   */
  @Override
  public void runCommandLoop() {
    hr(); // lines to separate message
    System.out.println("Welcome To Group CM's Campus Navigator!");
    hr();

    String command = "";
    while (!command.equals("Q")) { // continues until user inputs "Q"
      command = this.mainMenuPrompt();
      switch (command) {
        case "LB":
          loadDataCommand();
          break;
        case "AB":
          System.out.println(
              "Add Buildings To Dataset (Type \"done\" When Finished)...");
          System.out.print("Building Name: ");
          String building = userInput.nextLine().trim();
          addBuildingCommand(building); // calls the add building mathod
          break;
        case "RB":
          System.out.println("Remove Buildings From Dataset (Type \"done\" When Finished)...");
          System.out.print("Building Name: ");
          String removeBuilding = userInput.nextLine().trim();
          removeBuildingFromPath(removeBuilding);
          break;
        case "AE":
          addEdge();
          break;
        case "RE":
          removeEdge();
          break;
        case "GP":
          searchBuildingDistancesCommand();
          break;
        case "PP":
          displayBuildingsCommand();
          break;
        case "GM":
          GenerateMst();
          break;
        case "PM":
          printMst();
          break;
        case "Q":
          break;
        default:
          System.out.println("That's Not a Valid Input!!!");
          break;
      }
    }
    hr();
    System.out.println("Thank You For Using Group CM's Campus Navigator!");
    hr();

  }

  /**
   * Prints the command options to System.out and reads user's choice through
   * userInput scanner
   * 
   * @return the input the user wants to access
   */
  @Override
  public String mainMenuPrompt() {
    // display menu
    System.out.println("Command Reference:");
    System.out.println("  [LB]: Load Building Data");
    System.out.println("  [AB]: Add Building");
    System.out.println("  [RB]: Remove Building");
    System.out.println("  [AE]: Add Edge");
    System.out.println("  [RE]: Remove Edge");
    System.out.println("  [GP]: Generate Paths");
    System.out.println("  [PP]: Print Paths");
    System.out.println("  [GM]: Generate MST");
    System.out.println("  [PM]: Print MST");
    System.out.println("  [ Q]: Quit");

    // read in and trim user's input
    System.out.print("Enter Command: ");
    String input = userInput.nextLine().trim();
    if (input.length() == 0) { // blank input results in invalid string returned
      return "a";
    }
    // return uppercase version of input
    return input.toUpperCase();
  }

  /**
   * a private helper method to get the MST from backend
   */
  private void printMst() {
    // TODO Auto-generated method stub
    if (backend.getMstString() == null) {
      System.out.println("ERROR — You Must Generate An MST First");
    }
    System.out.print(backend.getMstString());
  }

  /**
   * a private helper method to Generate MST from backend
   */
  private void GenerateMst() {
    // TODO Auto-generated method stub
    System.out.print("Building Name: ");
    String buildingName = userInput.nextLine().trim();
    if (!backend.containsBuilding(buildingName)) {
      System.out.printf("[ERROR] Building \"%s\" Doesn't Exist\n", buildingName);
      hr();
      return;
    }
    backend.generateMST(buildingName); // calls backend to generate mst
    System.out.println("MST Generated!");
    hr();
  }

  /**
   * private helper method to ask user the info about which edge he wants to
   * remove from data and
   * then we call backend
   */
  private void removeEdge() {
    System.out.print("Building A: ");
    String buildingA = userInput.nextLine().trim();
    System.out.print("Building B: ");
    String buildingB = userInput.nextLine().trim();
    
    if (backend.removeEdge(buildingA, buildingB))
      System.out.println("Removed Edge!");
    else System.out.println("[ERROR] Could Not Remove Edge!");
    hr();
  }

  /**
   * private helper method to ask user the info about which edge he wants to add
   * to data and then we
   * call backend to do just that
   */
  private void addEdge() {
    System.out.print("Building A: ");
    String buildingA = userInput.nextLine().trim();
    System.out.print("Building B: ");
    String buildingB = userInput.nextLine().trim();
    System.out.print("Distance (Miles): ");
    String weight = userInput.nextLine().trim();

    if (backend.addEdge(
      buildingA, 
      buildingB, 
      Integer.parseInt(weight)
    )) System.out.println("Added Edge!");
    else System.out.println("[ERROR] Could Not Add Edge!");
    hr();
  }

  /**
   * Prompts user to load in a file and displays an error message when filename is
   * invalid If valid
   * uses bckend to load file
   */
  @Override
  public void loadDataCommand() {
    System.out.print("File Name: ");
    String filename = userInput.nextLine().trim();
    if (backend.loadData(filename)) System.out.println("Loaded Data!");
    else System.out.printf("Could Not Load File Name \"%s\"\n", filename);
    hr();
  }

  /**
   * Returns the lowest distance to the buildings added to the path is called to
   * generate the
   * shortest path in backend
   */
  @Override
  public void searchBuildingDistancesCommand() {
    System.out.println("Generate Path (Type \"done\" When Finished)...");
    System.out.print("Building Name: ");
    String buildingName = userInput.nextLine().trim();
    while (!buildingName.equalsIgnoreCase("done")) {
      if (!backend.containsBuilding(buildingName)) { // if building isnt in data base
        System.out.printf("[ERROR] Building \"%s\" Not Found!\n", buildingName);
        hr();
        return;
      }

      if (!buildingName.equalsIgnoreCase("done"))
        paths.add(buildingName);
      System.out.print("Building Name: ");

      buildingName = userInput.nextLine().trim();
    }
    if (paths.size() < 2) {
      System.out.println("[ERROR] Must Have At Least 2 Buildings");
      hr();
      return;
    }
    try {
      backend.generatePaths(paths); // calls the method from backend
      System.out.println("Path Generated!");
      hr();
    } catch (NoSuchElementException e) {
      e.printStackTrace();
    }
  }

  /**
   * Adds building to the data list
   * 
   * @param name of the building you want to add to the path
   */
  @Override
  public void addBuildingCommand(String buildingName) {
    if (buildingName.equalsIgnoreCase("done"))
      return;

    String addressStr = "";
    List<BuildingInterface> buildings = new ArrayList<>();
    while (!buildingName.equalsIgnoreCase("done")) { // user enters done to end
      System.out.printf("Building Address: ", buildingName);
      addressStr = userInput.nextLine().trim();

      if (backend.containsBuilding(buildingName)) { // if building is in database
        System.out.printf("[ERROR] Building With Name \"%s\" Already Exists\n", buildingName);
        hr();
        return;
      }
      buildings.add(new Building(buildingName, addressStr));

      System.out.print("Building Name: ");
      buildingName = userInput.nextLine().trim();
    }
    
    backend.addBuildings(buildings);
    hr();
  }

  /**
   * Prints the path generated to the console and clears the path for the next
   * path to be generated
   */
  @Override
  public void displayBuildingsCommand() {
    String answer = "";
    // TODO Auto-generated method stub
    List<List<BuildingInterface>> PrintPaths = backend.getPaths();
    for (int i = 0; i < PrintPaths.size(); i++) {

      for (int j = 0; j < PrintPaths.get(i).size(); j++) {
        answer += PrintPaths.get(i).get(j).getName() + "=>";
      }
      answer = answer.substring(0, answer.length() - 2); // removes => in the end
      answer += "\n";
    }
    System.out.println(answer.substring(0, answer.length() - 1)); // making the path end without =>

    clearPath();
  }

  /**
   * clears the path history
   */
  @Override
  public void clearPath() {
    paths.clear();

  }

  /**
   * Removes the building from data list by calling Backend
   * 
   * @param name of the building you want to remove from the data list
   */
  @Override
  public void removeBuildingFromPath(String buildingName) {
    List<String> toRemove = new ArrayList<>();
    toRemove.add(buildingName);// list of building names to remove

    while (!buildingName.equalsIgnoreCase("done")) {
      if (!backend.containsBuilding(buildingName)) {
        System.out.printf("[ERROR] Building \"%s\" Not Found\n", buildingName);
        hr();
        return;
      }

      System.out.print("Building Name: ");
      buildingName = userInput.nextLine().trim(); // user input
      toRemove.add(buildingName);
    }

    backend.removeBuildings(toRemove);
    System.out.println("Buildings Removed!");
    hr();
  }

  /**
   * start the app
   * 
   * @param args
   */
  public static void main(String[] args) {
    AdvancedGraph<BuildingInterface, Double> graph = new AdvancedGraph<>();
    BuildingGraphReader reader = new BuildingGraphReader();
    CampusNavigatorBackend backend = new CampusNavigatorBackend(graph, reader);
    CampusNavigatorFrontend fd = new CampusNavigatorFrontend(new Scanner(System.in), backend);
    fd.runCommandLoop();
  }
}
