import java.util.List;

/**
 * The AdvancedGraphInterface is a boilerplate for the
 * AdvancedGraph class which extends on a normal graph
 * in that it can find the path between multiple points
 * and insert/remove multiple nodes in one method call.
 *
 * @param <NodeType> The class of the nodes within the graph
 * @param <EdgeType> The class of the edges that connect the nodes within the graph
 */
public interface AdvancedGraphInterface<NodeType, EdgeType extends Number> extends GraphADT<NodeType, EdgeType> {
//  public AdvancedGraph()

    /**
     * Inserts the nodes from the provided
     * list into the graph one-by-one.
     *
     * @param nodes The List of nodes to insert
     * @return true if all nodes could be added,
     * otherwise false
     */
    boolean insertNodes(List<NodeType> nodes);


    /**
     * Removes each node on the provided
     * list from the graph one-by-one.
     *
     * @param nodes The List of nodes to remove
     * @return true if all nodes could be removed,
     * otherwise false
     */
    boolean removeNodes(List<NodeType> nodes);

    /**
     * Finds the shortest path between multiple nodes from the
     * provided list. The node at the 0th index represents the
     * starting point in a journey and the node at the last
     * node represents the destination of the journey. Each
     * intermediary node must be
     *
     * @param nodes A List of nodes in the order they should be walked past
     * @return A 2D List, where each row is the shortest path between
     * the ith node and the i+1st node for 0<=i<n-2
     */
    List<List<NodeType>> shortestPathMultiple(List<NodeType> nodes);


    /**
     * Determines the minimum spanning tree on a provided node and returns
     * the traced graph. The MST will contain all nodes of this graph but
     * its edges will be a subset of this graph's edges. The implementation
     * MAY or MAY NOT be deterministic.
     *
     * @param node The node to begin the algorithm on
     * @return The MST on the given node
     */
    AdvancedGraphInterface<NodeType, EdgeType> findMST(NodeType node);


    /**
     * Generates a String representation of the AdvancedGraph.
     *
     * @return The String representation of the AdvancedGraph
     */
    String toString(); // @Override
}
