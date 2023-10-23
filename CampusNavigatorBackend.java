import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Backend program for the Campus Navigator Application to make use of an
 * AdvancedGraph of type BuildingInterface
 * 
 * @author Nicholas Brigida
 * @version April 22, 2023
 */
public class CampusNavigatorBackend implements CampusNavigatorBackendInterface {
    private AdvancedGraphInterface<BuildingInterface, Double> graph;
    private BuildingGraphReaderInterface graphReader;
    private AdvancedGraphInterface<BuildingInterface, Double> MST;

    private List<List<BuildingInterface>> paths;

    /**
     * The constructor of the CampusNavigatorBackend that gives it an initial graph
     * and graphReader to utilize
     * 
     * @param graph       graph to be used
     * @param graphReader graphReader to be utilized
     */
    public CampusNavigatorBackend(
        AdvancedGraphInterface<BuildingInterface, Double> graph,
        BuildingGraphReaderInterface graphReader
    ) {
        this.graph = graph;
        this.graphReader = graphReader;
    }

    /**
     * Gets loads a graph from a file
     * 
     * @param fileName The file name to read from
     * @return true if the load was successful and false otherwise
     */
    @Override
    public boolean loadData(String fileName) {
        try {
            BuildingGraphReaderInterface.FileData dataRead = graphReader.readDataFromFile(fileName);
            if (!addBuildings(dataRead.buildings())) return false;
            for (BuildingGraphReaderInterface.Edge e : dataRead.edges()) {
                if (!graph.insertEdge(
                    new Building(e.srcName(), null), 
                    new Building(e.destName(), null), 
                    e.dist()
                )) return false;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Adds buildings to the graph
     * 
     * @param buildingNames the list of buildings to add
     * @return true if add was successful and false otherwise
     */
    @Override
    public boolean addBuildings(List<BuildingInterface> buildingNames) {
        return graph.insertNodes(buildingNames);
    }

    /**
     * Removes buildings from the graph
     * 
     * @param buildingNames the names of the buildings to remove
     * @return true if remove was successful and false otherwise
     */
    @Override
    public boolean removeBuildings(List<String> buildingNames) {
        for (String buildingName : buildingNames)
            if (!graph.removeNode(new Building(buildingName, null)))
                return false;

        return true;
    }

    @Override
    public boolean addEdge(String pred, String succ, double weight) {
        return graph.insertEdge(
            new Building(pred, null), 
            new Building(succ, null), 
            weight
        );
    }

    @Override
    public boolean removeEdge(String pred, String succ) {
        return graph.removeEdge(
            new Building(pred, null), 
            new Building(succ, null)
        );
    }

    /**
     * generates the path between certain buildings
     * 
     * @param buildingNames the buildings for the path to be generated between
     */
    @Override
    public void generatePaths(List<String> buildingNames) {
        List<BuildingInterface> buildings = new ArrayList<>(buildingNames.size());
        for (String buildingName : buildingNames)
            buildings.add(new Building(buildingName, null));

        paths = graph.shortestPathMultiple(buildings);
    }

    /**
     * returns the paths generated between multiple buildings
     * 
     * @return the path generated between multiple buildings
     */
    @Override
    public List<List<BuildingInterface>> getPaths() {
        return paths;
    }

    /**
     * Returns true or false depending on if a certain building is in the graph or
     * not
     * 
     * @param buildingName name of building to search for
     * @return true if building is in graph and false otherwise
     */
    @Override
    public boolean containsBuilding(String buildingName) {
        return graph.containsNode(new Building(buildingName, null));
    }

    /**
     * Returns true or false depending on if a certain edge is in the graph or not
     * 
     * @param srcName  the name of the source node of the edge searched for
     * @param destName the name of the destination node of the edge searched for
     * @return true if edge is in graph and false otherwise
     */
    @Override
    public boolean containsEdge(String srcName, String destName) {
        return graph.containsEdge(
            new Building(srcName, null), 
            new Building(destName, null)
        );
    }

    /**
     * Generates a Minimum Spanning Tree originating from a certain building
     * 
     * @param buildingName the building the spanning tree is originating from
     */
    @Override
    public void generateMST(String buildingName) {
        MST = graph.findMST(
            new Building(buildingName, null)
        );
    }

    /**
     * returns the Minimum Spanning Tree in a string format
     * 
     * @return the Minimum Spanning Tree in a string format
     */
    @Override
    public String getMstString() {
        if(MST == null)
        {
            return "MST is uninitialized";
        }
        return MST.toString();
    }
}
