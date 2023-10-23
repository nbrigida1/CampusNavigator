import java.util.List;


/**
 * The CampusNavigatorBackendInterface is a boilerplate for
 * the CampusNavigatorBackend class which has the ability to
 * add multiple Buildings, remove multiple Buildings, generate
 * the most efficient contiguous paths between multiple Buildings,
 * check whether the Graph contains a Building, check whether the
 * Graph contains an Edge, and generates MSTs.
 * @author Nicholas Brigida
 * @version April 22, 2023
 */
public interface CampusNavigatorBackendInterface {



    /**
     * Uses a BuildingGraphReader to read in the
     * Buildings and their Edges from a file name.
     *
     * @param fileName The file name to read from
     * @return true if the file could be successfully read,
     *         otherwise false
     */
    boolean loadData(String fileName);


    boolean addBuildings(List<BuildingInterface> buildingNames);
    boolean removeBuildings(List<String> buildingNames);

    boolean addEdge(String pred, String succ, double weight);
    boolean removeEdge(String pred, String succ);


    // Must maintain internal 2D List within Backend
    // Allows FD to print path without recalculating each time
    //   generatePaths(A, D, F) =>
    //   paths = [
    //      [A, B, C, D],
    //      [D, E, F]
    //   ];
    void generatePaths(List<String> buildingNames);
    List<List<BuildingInterface>> getPaths();


    boolean containsBuilding(String buildingName);
    boolean containsEdge(String srcName, String destName);


    // Must maintain internal AdvancedGraph for MST
    void generateMST(String buildingName);
    String getMstString();
}
