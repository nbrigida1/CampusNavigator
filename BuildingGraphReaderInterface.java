import java.io.FileNotFoundException;
import java.util.List;


/**
 * The BuildingReaderInterface is a boilerplate for the BuildingReader
 * class which loads in a list of Buildings and their Edges from a
 * file in a DOT format.
 */
public interface BuildingGraphReaderInterface {
//  public BuildingGraphReader();


    /**
     * Represents the Edge between two Buildings with a weight of their
     * distance in miles. You can create a new Edge with `new Edge()`
     * and access its properties with `edge.srcName` and `edge.destName`.
     *
     * @param srcName The name of the 1st Building
     * @param destName The name of the 2nd Building
     * @param dist The distance in miles between the two Buildings
     */
    record Edge(String srcName, String destName, double dist) { }


    /**
     * Represents the List of all Buildings (regardless of whether it is connected)
     * and List of Edges between those buildings from the file. You can create a
     * new FileData with `new FileData()` and access buildings and edges
     * with `fileData.buildings` and `fileData.edges` respectfully.
     *
     * @param buildings A List of all Buildings
     * @param edges A List of all Edges (between the Buildings with their distance)
     */
    record FileData(List<BuildingInterface> buildings, List<Edge> edges) { }


    /**
     * Reads in all Buildings and their Edges of the graph
     * from a DOT file at the provided file name.
     *
     * @param fileName The file name at which data should be read from
     * @return All Buildings and Edges from the file as a Record
     * @throws FileNotFoundException if the file does not exist
     */
    FileData readDataFromFile(String fileName) throws FileNotFoundException;
}

