import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nicholas Brigida
 * @version 5/2/23
 */
public class BackendDeveloperTests {

    /**
     * Tests LoadData method using containsBuilding and containsEdge methods
     */
    @Test
    public void test1()
    {
        BuildingGraphReaderInterface reader = new BuildingGraphReaderBD();
        AdvancedGraphInterface graph = new AdvancedGraphBD();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        backend.loadData(null);
        backend.containsBuilding("A");
        assertTrue(backend.containsBuilding("A"));
        assertTrue(backend.containsEdge("A", "B"));
        assertFalse(backend.containsBuilding("W"));
        assertFalse(backend.containsEdge("S", "G"));
    }

    /**
     * Test addBuildings and removeBuildings methods
     */
    @Test
    public void test2()
    {
        BuildingGraphReaderInterface reader = new BuildingGraphReaderBD();
        AdvancedGraphInterface graph = new AdvancedGraphBD();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        backend.loadData(null);
        List<BuildingInterface> buildingList = new ArrayList<>();
        BuildingBD Y = new BuildingBD("Y", "y");
        BuildingBD Z = new BuildingBD("Z", "z");
        buildingList.add(Y);
        buildingList.add(Z);
        backend.addBuildings(buildingList);
        assertTrue(backend.containsBuilding("Y"));
        assertTrue(backend.containsBuilding("Z"));
        List<String> removeList = new ArrayList<>();
        removeList.add("A");
        removeList.add("B");
        backend.removeBuildings(removeList);
        assertFalse(backend.containsBuilding("A"));
        assertFalse(backend.containsBuilding("B"));
    }

    /**
     * Tests generateMST and MST.toString
     */
    @Test
    public void test3()
    {
        BuildingGraphReaderInterface reader = new BuildingGraphReaderBD();
        AdvancedGraphInterface graph = new AdvancedGraphBD();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        backend.generateMST(null);
        assertEquals("A -> B -> C", backend.getMstString());
    }

    /**
     * Tests removeBuildings method
     */
    @Test
    public void test4()
    {
        BuildingGraphReaderInterface reader = new BuildingGraphReaderBD();
        AdvancedGraphInterface graph = new AdvancedGraphBD();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        backend.loadData(null);
        List<String> removeList = new ArrayList<>();
        removeList.add("A");
        removeList.add("B");
        backend.removeBuildings(removeList);
        assertFalse(backend.containsBuilding("A"));
        assertFalse(backend.containsBuilding("B"));
    }

    /**
     * Tests getPaths and generatePaths
     */
    @Test
    public void test5()
    {
        BuildingGraphReaderInterface reader = new BuildingGraphReaderBD();
        AdvancedGraphInterface graph = new AdvancedGraphBD();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        backend.generatePaths(new ArrayList<String>());
        assertEquals("[[A, B, C], [D, E, F], [G, H, I]]", backend.getPaths().toString());
    }


    /**
     * Tests if print buildings function works
     */
    @Test
    public void CodeReviewOfFrontend1()
    {
        TextUITester uiTester = new TextUITester("PP\nQ\n");
        Scanner s = new Scanner(System.in);
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackendFD();
        CampusNavigatorFrontendInterface frontend = new CampusNavigatorFrontend(s, backend);
        frontend.runCommandLoop();
        String systemOutput = uiTester.checkOutput();
        //"BuisnessLibrary" is a preset building in CampusNavigatorBackendFD and should be in print list
        assertTrue(systemOutput.contains("BuisnessLibrary"));
    }

    /**
     * Tests addBuilding with Frontend and BackendFD (No implementation so error should be printed)
     */
    @Test
    public void CodeReviewOfFrontend2()
    {
        TextUITester uiTester = new TextUITester("AB\nmyHouse\ndone\nQ\n");
        Scanner s = new Scanner(System.in);
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackendFD();
        CampusNavigatorFrontendInterface frontend = new CampusNavigatorFrontend(s, backend);
        frontend.runCommandLoop();
        String systemOutput = uiTester.checkOutput();
        System.out.println(systemOutput);
        //CampusNavigatorBackendFD has no functionality for addBuilding so an error should be printed
        assertTrue(systemOutput.contains("[ERROR] Building With Name \"myHouse\" Already Exists"));
    }


    /**
     * Generates an MST of the Medical Sciences building using the smallTest.dot file
     */
    @Test
    public void IntegrationTest1()
    {
        TextUITester uiTester = new TextUITester("LB\nsmallTest.dot\nGM\nMedical Sciences\nPM\nQ\n");
        Scanner s = new Scanner(System.in);
        BuildingGraphReaderInterface reader = new BuildingGraphReader();
        AdvancedGraphInterface graph = new AdvancedGraph();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        CampusNavigatorFrontendInterface frontend = new CampusNavigatorFrontend(s, backend);
        frontend.runCommandLoop();
        String systemOutput = uiTester.checkOutput();
        assertTrue(systemOutput.contains("Medical Sciences (1215 Linden Dr.) ==[0.0]=>" +
                " Service Memorial Institute (470 N. Charter St.)"));
        //Medical Sciences should include a path with distance of 0 to Service Memorial Institute in this format
    }


    /**
     * Tests GeneratePaths method
     */
    @Test
    public void IntegrationTest2()
    {
        TextUITester uiTester = new TextUITester("LB\nMainCampus.dot\nGP\nSellery Residence Hall\n" +
                "Social Sciences Building\nCollege Library\ndone\nPP\nQ\n");
        Scanner s = new Scanner(System.in);
        BuildingGraphReaderInterface reader = new BuildingGraphReader();
        AdvancedGraphInterface graph = new AdvancedGraph();
        CampusNavigatorBackendInterface backend = new CampusNavigatorBackend(graph, reader);
        CampusNavigatorFrontendInterface frontend = new CampusNavigatorFrontend(s, backend);
        frontend.runCommandLoop();
        String systemOutput = uiTester.checkOutput();
        //Tests if valid path is printed starting at Sellery Residence Hall
        assertTrue(systemOutput.contains("Sellery Residence Hall=>"));
    }
}
