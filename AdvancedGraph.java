// --== CS400 File Header Information ==--
//            Name: Aryav Bharali
//           Email: bharali@wisc.edu
//  Group and Team: CM Blue
//        Group TA: Karan Grover
//        Lecturer: Florian Heimerl
// Notes to Grader: None


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * The AdvancedGraph class extends upon DijkstraGraph—which features
 * inserting multiple nodes, removing multiple nodes, inserting an
 * undirected edge, removing an undirected edge, finding the
 * shortest path between multiple nodes, finding the MST,
 * and generating a String representation of the graph.
 *
 * @param <NodeType> The type of the Nodes stored in the AdvancedGraph
 * @param <EdgeType> The type of the Edges stored in the AdvancedGraph
 * @author Aryav Bharali
 * @version April 25, 2023
 */
public class AdvancedGraph<NodeType, EdgeType extends Number>
    extends DijkstraGraph<NodeType, EdgeType>
    implements AdvancedGraphInterface<NodeType, EdgeType> {

    /**
     * Inserts the provided Nodes one-by-one.
     *
     * @param nodes The List of Nodes to insert
     * @return true if all Nodes were successfully inserted,
     *         otherwise false
     */
    @Override
    public boolean insertNodes(List<NodeType> nodes) {
        for (NodeType node : nodes)
            if (!insertNode(node))
                return false;

        return true;
    }

    /**
     * Removes the provided Nodes one-by-one.
     *
     * @param nodes The List of nodes to remove
     * @return true if all Nodes were successfully removed,
     *         otherwise false
     */
    @Override
    public boolean removeNodes(List<NodeType> nodes) {
        for (NodeType node : nodes)
            if (!removeNode(node))
                return false;

        return true;
    }

    /**
     * Inserts an edge from the pred Node to the succ Node with the
     * given weight as well as an edge in the reverse direction.
     *
     * @param pred The Edge's pred Node
     * @param succ The Edge's succ Node
     * @param weight The Edge's weight
     * @return true if the Edge and its reverse could be inserted,
     *         otherwise false
     */
    @Override
    public boolean insertEdge(NodeType pred, NodeType succ, EdgeType weight) {
        boolean status1 = super.insertEdge(pred, succ, weight);
        boolean status2 = super.insertEdge(succ, pred, weight);
        return status1 && status2;
    }

    /**
     * Removes the edge from the pred Node to the
     * succ Node as well as the reverse Edge.
     *
     * @param pred the data item contained in the source node for the edge
     * @param succ the data item contained in the target node for the edge
     * @return true if the Edge and its reverse could be inserted,
     *         otherwise false
     */
    @Override
    public boolean removeEdge(NodeType pred, NodeType succ) {
        boolean status1 = super.removeEdge(pred, succ);
        boolean status2 = super.removeEdge(succ, pred);
        return status1 && status2;
    }

    /**
     * Finds the shortest path that must go through the provided Nodes.
     *
     * @param nodes A List of nodes in the order they should be walked past
     * @return A 2D List, where each row is the shortest path between
     *         the ith node and the i+1st node for 0<=i<n-2
     */
    @Override
    public List<List<NodeType>> shortestPathMultiple(List<NodeType> nodes) {
        int numPaths = nodes.size() - 1;
        List<List<NodeType>> paths = new ArrayList<>(numPaths);

        for (int pathI = 0; pathI < numPaths; pathI++) {
            NodeType srcNode = nodes.get(pathI);
            NodeType destNode = nodes.get(pathI + 1);
            paths.add(shortestPathData(srcNode, destNode));
        }

        return paths;
    }

    /**
     * Determines the minimum spanning tree on a provided node and returns
     * the traced graph. The MST will contain all nodes of this graph, but
     * its edges will be a subset of this graph's edges. The implementation
     * MAY or MAY NOT be deterministic.
     *
     * @param startData The node to begin the algorithm on
     * @return The MST on the given node
     */
    @Override
    public AdvancedGraphInterface<NodeType, EdgeType> findMST(NodeType startData) {
        AdvancedGraph<NodeType, EdgeType> mst = new AdvancedGraph<>();

        mst.insertNode(nodes.get(startData).data);
        while (mst.getNodeCount() != this.getNodeCount()) {
            double minWeight = 0;
            Edge minEdge = null;

            Enumeration<NodeType> mstNodes = mst.nodes.keys();
            while (mstNodes.hasMoreElements()) {
                NodeType mstNode = mstNodes.nextElement();
                for (Edge edge : nodes.get(mstNode).edgesLeaving) {
                    if (!mst.containsNode(edge.successor.data)) {
                        if (minEdge == null || edge.data.doubleValue() < minWeight) {
                            minWeight = edge.data.doubleValue();
                            minEdge = edge;
                        }
                    }
                }
            }

            mst.insertNode(minEdge.successor.data);
            mst.insertEdge(minEdge.predecessor.data, minEdge.successor.data, minEdge.data);
        }

        return mst;
    }

    /**
     * Generates a String representation of the AdvancedGraph following the following format:
     * ——————————————————————————————————————————————
     * NODES:
     * Node1
     * Node2
     * ——————————————————————————————————————————————
     * EDGES:
     * Node1 ==[Weight]=> Node2
     * Node2 ==[Weight]=> Node1
     * ——————————————————————————————————————————————
     *
     * @return The String representation as above or (Empty Graph) if empty
     */
    @Override
    public String toString() {
        if (nodes.size() == 0) return "(Empty Graph)";

        StringBuilder out = new StringBuilder("——————————————————————————————————————————————\n");
        out.append("NODES:\n");
        StringBuilder edgesStr = new StringBuilder("EDGES:\n");

        Enumeration<NodeType> nodeDatas = nodes.keys();
        while (nodeDatas.hasMoreElements()) {
            NodeType building = nodeDatas.nextElement();
            out.append(building.toString().trim()).append("\n");

            List<Edge> edges = nodes.get(building).edgesLeaving;
            for (Edge edge : edges)
                edgesStr.append(building.toString().trim())
                        .append(" ==[")
                        .append(edge.data)
                        .append("]=> ")
                        .append(edge.successor.data.toString().trim())
                        .append("\n");
        }

        out.append("——————————————————————————————————————————————\n")
           .append(edgesStr)
           .append("——————————————————————————————————————————————\n");
        return out.toString();
    }
}