// --== CS400 File Header Information ==--
//            Name: Aryav Bharali
//           Email: bharali@wisc.edu
//  Group and Team: CM Blue
//        Group TA: Karan Grover
//        Lecturer: Florian Heimerl
// Notes to Grader: None

import java.util.*;
import java.util.function.Function;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method represents the end of the
     * shortest path that is found: its cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references to represent
     * all the nodes along that shortest path (ordered from end to start).
     *
     * @param startData the data item in the starting node for the path
     * @param endData   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType startData, NodeType endData) {
        // Illegal Argument Check
        if (startData == null || nodes.get(startData) == null)
            throw new NoSuchElementException("Node With startData Doesn't Exist");
        if (endData == null || nodes.get(endData) == null)
            throw new NoSuchElementException("Node With endData Doesn't Exist");

        // Initialize Hash Table (node.data => isVisited)
        Hashtable<NodeType, Boolean> visitedData = new Hashtable<>();
        Function<NodeType, Boolean> isVisited = nodeData -> visitedData.getOrDefault(nodeData, false);

        // Initialize Priority Queue
        PriorityQueue<SearchNode> queue = new PriorityQueue<>();
        Node startNode = nodes.get(startData);
        SearchNode startSearchNode = new SearchNode(startNode, 0, null);
        queue.add(startSearchNode);

        // Main Algorithm
        while (!queue.isEmpty()) {
            SearchNode curr = queue.poll();
            NodeType currData = curr.node.data;

            // CASE I: Found Destination
            if (endData.equals(currData)) return curr;

            // CASE II: Found Unvisited Node
            if (!isVisited.apply(currData)) {
                visitedData.put(currData, true);

                // Iterate Through All Edges
                for (Edge edgeLeaving : curr.node.edgesLeaving) {
                    Node succ = edgeLeaving.successor;

                    // If Edge Destination Is Unvisited, Add To PQueue
                    if (!isVisited.apply(succ.data))
                        queue.add(new SearchNode(
                            succ,
                            curr.cost + edgeLeaving.data.doubleValue(),
                            curr
                        ));
                }
            }
        }

        throw new NoSuchElementException("Path From Nodes With startData to endData Could Not Be Found");
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shortest path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        LinkedList<NodeType> pathData = new LinkedList<>();

        SearchNode curr = computeShortestPath(start, end);
        while (curr != null) {
            pathData.addFirst(curr.node.data);
            curr = curr.predecessor;
        }

        return pathData;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path from the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        SearchNode lastNode = computeShortestPath(start, end);
        return lastNode.cost;
    }

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in its node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referenced by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     * <p>
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost) return +1;
            if (cost < other.cost) return -1;
            return 0;
        }
    }
}
