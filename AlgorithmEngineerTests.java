// --== CS400 File Header Information ==--
//            Name: Aryav Bharali
//           Email: bharali@wisc.edu
//  Group and Team: CM Blue
//        Group TA: Karan Grover
//        Lecturer: Florian Heimerl
// Notes to Grader: None

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A series of tests to verify the functionality of
 * the AdvancedGraph class for the AE role.
 */
public class AlgorithmEngineerTests {
    /**
     * Tests that the overridden insertEdge() inserts an undirected
     * edge and that the toString() produces the correct output.
     */
    @Test
    public void test1() {
        AdvancedGraph<Character, Double> graph = new AdvancedGraph<>();
        assertTrue(graph.insertNode('A'));
        assertTrue(graph.insertNode('B'));
        assertTrue(graph.insertEdge('A', 'B', 2.5));

        assertEquals(2, graph.getNodeCount());
        assertTrue(graph.toString().contains("A\n"));
        assertTrue(graph.toString().contains("B\n"));

        assertEquals(2, graph.getEdgeCount());
        assertTrue(graph.toString().contains("A ==[2.5]=> B"));
        assertTrue(graph.toString().contains("B ==[2.5]=> A"));
    }

    /**
     * Tests insertNodes() and removeNodes() with 4 Nodes.
     */
    @Test
    public void test2() {
        Character[] nodes = {'A', 'B', 'C', 'D'};
        List<Character> nodesList = Arrays.asList(nodes);
        AdvancedGraph<Character, Integer> graph = new AdvancedGraph<>();

        assertTrue(graph.insertNodes(nodesList));
        assertEquals(4, graph.getNodeCount());
        assertTrue(graph.toString().contains("A\n"));
        assertTrue(graph.toString().contains("B\n"));
        assertTrue(graph.toString().contains("C\n"));
        assertTrue(graph.toString().contains("D\n"));
        assertEquals(0, graph.getEdgeCount());

        assertTrue(graph.removeNodes(nodesList));
        assertEquals(0, graph.getNodeCount());
        assertEquals(0, graph.getEdgeCount());
    }

    /**
     * Tests insertEdge() and removeEdge() with 3 nodes and two edges.
     */
    @Test
    public void test3() {
        AdvancedGraph<Character, Double> graph = new AdvancedGraph<>();
        Character[] nodes = {'A', 'B', 'C'};
        List<Character> nodesList = Arrays.asList(nodes);
        assertTrue(graph.insertNodes(nodesList));

        assertTrue(graph.insertEdge('A', 'B', 1.5));
        assertTrue(graph.insertEdge('B', 'C', 2.5));
        assertEquals(4, graph.getEdgeCount());
        assertTrue(graph.toString().contains("A ==[1.5]=> B"));
        assertTrue(graph.toString().contains("B ==[1.5]=> A"));
        assertTrue(graph.toString().contains("B ==[2.5]=> C"));
        assertTrue(graph.toString().contains("C ==[2.5]=> B"));

        assertTrue(graph.removeEdge('C', 'B'));
        assertEquals(2, graph.getEdgeCount());
        assertTrue(graph.toString().contains("A ==[1.5]=> B"));
        assertTrue(graph.toString().contains("B ==[1.5]=> A"));

        assertTrue(graph.removeEdge('B', 'A'));
        assertEquals(0, graph.getEdgeCount());
    }

    /**
     * Tests shortestPathMultiple() twice on a medium-sized graph.
     */
    @Test
    public void test4() {
        AdvancedGraph<Integer, Integer> graph = new AdvancedGraph<>();
        Integer[] nodes = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        graph.insertNodes(Arrays.asList(nodes));

        assertTrue(graph.insertEdge(0, 1, 4));
        assertTrue(graph.insertEdge(0, 7, 8));
        assertTrue(graph.insertEdge(1, 2, 8));
        assertTrue(graph.insertEdge(1, 7, 11));
        assertTrue(graph.insertEdge(2, 8, 2));
        assertTrue(graph.insertEdge(2, 3, 7));
        assertTrue(graph.insertEdge(3, 4, 9));
        assertTrue(graph.insertEdge(3, 5, 14));
        assertTrue(graph.insertEdge(4, 5, 10));
        assertTrue(graph.insertEdge(5, 6, 2));
        assertTrue(graph.insertEdge(6, 8, 6));
        assertTrue(graph.insertEdge(6, 7, 1));
        assertTrue(graph.insertEdge(7, 8, 7));

        Integer[] stops = {0, 3, 8, 5, 0};
        assertEquals(
            "[[0, 1, 2, 3], [3, 2, 8], [8, 6, 5], [5, 6, 7, 0]]",
            graph.shortestPathMultiple(Arrays.asList(stops)).toString()
        );

        stops = new Integer[] {4, 1, 6, 4};
        assertEquals(
            "[[4, 5, 6, 7, 1], [1, 7, 6], [6, 5, 4]]",
            graph.shortestPathMultiple(Arrays.asList(stops)).toString()
        );
    }

    /**
     * Tests findMST() by checking Prim's algorithm on a medium-sized graph.
     */
    @Test
    public void test5() {
        AdvancedGraph<Integer, Integer> graph = new AdvancedGraph<>();
        Integer[] nodes = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        graph.insertNodes(Arrays.asList(nodes));

        assertTrue(graph.insertEdge(0, 1, 4));
        assertTrue(graph.insertEdge(0, 7, 8));
        assertTrue(graph.insertEdge(1, 2, 8));
        assertTrue(graph.insertEdge(1, 7, 11));
        assertTrue(graph.insertEdge(2, 8, 2));
        assertTrue(graph.insertEdge(2, 3, 7));
        assertTrue(graph.insertEdge(3, 4, 9));
        assertTrue(graph.insertEdge(3, 5, 14));
        assertTrue(graph.insertEdge(4, 5, 10));
        assertTrue(graph.insertEdge(5, 6, 2));
        assertTrue(graph.insertEdge(6, 8, 6));
        assertTrue(graph.insertEdge(6, 7, 1));
        assertTrue(graph.insertEdge(7, 8, 7));

        AdvancedGraphInterface<Integer, Integer> mst = graph.findMST(0);
        assertEquals(9, mst.getNodeCount());
        assertEquals(8 * 2, mst.getEdgeCount());
        assertTrue(mst.toString().contains("0\n"));
        assertTrue(mst.toString().contains("1\n"));
        assertTrue(mst.toString().contains("2\n"));
        assertTrue(mst.toString().contains("3\n"));
        assertTrue(mst.toString().contains("4\n"));
        assertTrue(mst.toString().contains("5\n"));
        assertTrue(mst.toString().contains("6\n"));
        assertTrue(mst.toString().contains("7\n"));
        assertTrue(mst.toString().contains("8\n"));
        assertTrue(mst.toString().contains("0 ==[4]=> 1"));
        assertTrue(mst.toString().contains("1 ==[8]=> 2"));
        assertTrue(mst.toString().contains("2 ==[7]=> 3"));
        assertTrue(mst.toString().contains("3 ==[9]=> 4"));
        assertTrue(mst.toString().contains("2 ==[2]=> 8"));
        assertTrue(mst.toString().contains("8 ==[6]=> 6"));
        assertTrue(mst.toString().contains("6 ==[2]=> 5"));
        assertTrue(mst.toString().contains("6 ==[1]=> 7"));
    }
}
