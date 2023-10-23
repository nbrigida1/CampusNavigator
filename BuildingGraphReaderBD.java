import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of BuildingGraphReader class for backend development
 * @author Nicholas Brigida
 * @version 4/22/22
 */
@SuppressWarnings("unchecked")
public class BuildingGraphReaderBD implements  BuildingGraphReaderInterface{
    /**
     * Mock implementation of readDataFromFile method with test graph
     * @param fileName not implemented in this version
     * @return an example of a graph that would be returned from the file
     * @throws FileNotFoundException not implemented in this version
     * @throws IllegalArgumentException not implemented in this version
     */
    @Override
    public FileData readDataFromFile(String fileName) throws FileNotFoundException, IllegalArgumentException {

        List buildings = new ArrayList<BuildingInterface>();
        BuildingInterface A = new BuildingBD("A", "a");
        BuildingInterface B = new BuildingBD("B", "b");
        BuildingInterface C = new BuildingBD("C", "c");
        BuildingInterface D = new BuildingBD("D", "d");
        buildings.add(A);
        buildings.add(B);
        buildings.add(C);
        buildings.add(D);
        List edges = new ArrayList<Edge>();
        Edge AB = new Edge("A", "B" , 3.0);
        Edge BC = new Edge("B", "C" , 6.0);
        Edge CD = new Edge("C", "D" , 4.0);
        edges.add(AB);
        edges.add(BC);
        edges.add(CD);

        FileData data = new FileData(buildings, edges);
        return data;
    }
}
