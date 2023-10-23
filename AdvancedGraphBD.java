import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Mock implementation of AdvancedGraph for Backend development
 * @author Nicholas Brigida
 * @version 4/22/23
 */
@SuppressWarnings("unchecked")
public class AdvancedGraphBD<NodeType, EdgeType> implements AdvancedGraphInterface{
    Hashtable nodeList = new Hashtable<String, BuildingInterface>();
    Hashtable<String , String> edgeList = new Hashtable<String , String>();


    /**
     * Inserts nodes into mock-hashtable
     * @param nodes The List of nodes to insert
     * @return true if insert is successful and false otherwise
     */
    @Override
    public boolean insertNodes(List nodes) {
        if(nodes.isEmpty())
            return false;
        for (Object o: nodes)
        {
            try{
                nodeList.put(o.toString(),o);
            }
            catch (Exception e)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * removes nodes from a mock-hashtable
     * @param nodes The List of nodes to remove
     * @return true if remove is successful and false otherwise
     */
    @Override
    public boolean removeNodes(List nodes) {
        for (Object o: nodes)
        {
            try
            {
                nodeList.remove(o.toString());
            }
            catch (Exception e)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of set list paths
     * @param nodes A List of nodes in the order they should be walked past
     * @return a list of paths
     */
    @Override
    public List<List> shortestPathMultiple(List nodes) {
        List<BuildingInterface> AC = new ArrayList<>();
        List<BuildingInterface> DF = new ArrayList<>();
        List<BuildingInterface> GI = new ArrayList<>();
        AC.add(new BuildingBD("A", "a"));
        AC.add(new BuildingBD("B", "b"));
        AC.add(new BuildingBD("C", "c"));
        DF.add(new BuildingBD("D", "d"));
        DF.add(new BuildingBD("E", "e"));
        DF.add(new BuildingBD("F", "f"));
        GI.add(new BuildingBD("G", "g"));
        GI.add(new BuildingBD("H", "h"));
        GI.add(new BuildingBD("I", "i"));
        List<List> returned = new ArrayList<>();
        returned.add(AC);
        returned.add(DF);
        returned.add(GI);
        return returned;
    }

    /**
     * Returns a minimum spanning tree from an origin node
     * @param node The node to begin the algorithm on
     * @return an MST originating from parameter node
     */
    @Override
    public AdvancedGraphInterface findMST(Object node) {
        return new AdvancedGraphBD();
    }

    /**
     * Inserts data into the mock-graph
     * @param data is the data item stored in the new node
     * @return true if insert is successful and false otherwise
     */
    @Override
    public boolean insertNode(Object data) {
        try{
            nodeList.put(data.toString(), new BuildingBD((String)data, null));
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * removes a node with cetain data from mock-array
     * @param data is the data item stored in the node to be removed
     * @return true if remove is successful and false otherwise
     */
    @Override
    public boolean removeNode(Object data) {
        String dataString = data.toString();
        String name = dataString.split(" ")[0];
        try{
            nodeList.remove(name);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a node is in the node list and false otherwise
     * @param data the node contents to check for
     * @return true if a node is in the node list and false otherwise
     */
    @Override
    public boolean containsNode(Object data) {
        String dataString = data.toString();
        String name = dataString.split(" ")[0];
        for(Object o :nodeList.keySet()) {
            {
                String s = o.toString();
                int n = name.compareTo(s);
                if (n == 0)
                {
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * returns the number of nodes as an int
     * @return the number of nodes
     */
    @Override
    public int getNodeCount() {
        return nodeList.size();
    }

    /**
     * Inserts an edge into mock edge list
     * @param pred is the data item contained in the new edge's predecesor node
     * @param succ is the data item contained in the new edge's successor node
     * @param weight is the non-negative data item stored in the new edge
     * @return true if insert is successful and false otherwise
     */
    @Override
    public boolean insertEdge(Object pred, Object succ, Number weight) {
        try{
            edgeList.put(pred.toString(),succ.toString());
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * Attempts to remove an edge from the edge list
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if remove was successful and false otherwise
     */
    @Override
    public boolean removeEdge(Object pred, Object succ) {
        try{
            edgeList.remove(pred,succ);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * Returns boolean value based on whether edgelist contains an edge or not
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if an edge is present and false otherwise
     */
    @Override
    public boolean containsEdge(Object pred, Object succ) {
        try
        {
            String e = edgeList.get(pred.toString());
            if(e.equals(succ.toString()))
                return true;
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Not needed for this implementation
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return 0
     */
    @Override
    public Number getEdge(Object pred, Object succ) {
        return 0;
    }

    /**
     * Returns the number of edges
     * @return number of edges
     */
    @Override
    public int getEdgeCount() {
        return edgeList.size();
    }

    /**
     * Mock implementation with set path
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return
     */
    @Override
    public List shortestPathData(Object start, Object end) {
        return null;
    }

    /**
     * Not
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return
     */
    @Override
    public double shortestPathCost(Object start, Object end) {
        return 0;
    }

    /**
     * returns an example string to test on
     * @return an example mst
     */
    @Override
    public String toString() {
        return "A -> B -> C";
    }
}
