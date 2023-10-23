// --== CS400 File Header Information ==--
// Name: Hunter Heller
// Email: hheller2@wisc.edu
// Group and Team: CM Blue
// Group TA: Karan Grover
// Lecturer: Florian
// Notes to Grader: None

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manages tests for the Data Wrangler
 * 
 */
public class DataWranglerTests {

	/**
	 * Tests the getters and setters of the Building class
	 */
	@Test
	public void test1() {

		Building testBuilding = new Building("Random Hall", "Circle Ave");
	
		assertEquals("Random Hall", testBuilding.getName());
		assertEquals("Circle Ave", testBuilding.getAddress());

		testBuilding.setName("Circle Hall");
		testBuilding.setAddress("Random Ave");

		assertEquals("Circle Hall", testBuilding.getName());
		assertEquals("Random Ave", testBuilding.getAddress());
	}


	/**
	 * Tests the toString() method of the Building class
	 */
	@Test
	public void test2() {

		Building testBuilding = new Building("Random", "Circle");

		assertEquals("Random (Circle)", testBuilding.toString());
	}


	/**
	 * Tests the equals() method of the Building class
	 */
	@Test
	public void test3() {

		Building testBuilding = new Building("Random", "Circle");

		//equal - same name, same address
		Building build1 = new Building("Random", "Circle");
		assertTrue(testBuilding.equals(build1));

		//equal - same name, different address
		Building build2 = new Building("Random", "Square");
		assertTrue(testBuilding.equals(build2));

		//not equal - different name
		Building build3 = new Building("Exact", "Circle");
		assertFalse(testBuilding.equals(build3));

		//not equal - not of type Building
		String s = "HELLO";
		assertFalse(testBuilding.equals(s));
	}

	/**
	 * Tests that buildings are read in correctly in the BuildingGraphReader class
	 */
	@Test
	public void test4() throws FileNotFoundException {
	
		BuildingGraphReader testReader = new BuildingGraphReader();
		BuildingGraphReaderInterface.FileData buildingsAndEdges = testReader.readDataFromFile("smallTest.dot");

		assertEquals(buildingsAndEdges.buildings().size(),5);
	
		//checks format 	
		assertEquals(buildingsAndEdges.buildings().get(0).getName(),"Medical Sciences");
		assertEquals(buildingsAndEdges.buildings().get(0).getAddress(),"1215 Linden Dr.");
	
		assertEquals(buildingsAndEdges.buildings().get(1).getName(),"Brogden Psychology Building");
                assertEquals(buildingsAndEdges.buildings().get(1).getAddress(),"1202 W Johnson St.");
	
		assertEquals(buildingsAndEdges.buildings().get(2).getName(),"Service Memorial Institute");
                assertEquals(buildingsAndEdges.buildings().get(2).getAddress(),"470 N. Charter St.");
        
		assertEquals(buildingsAndEdges.buildings().get(3).getName(),"Van Hise Hall");
                assertEquals(buildingsAndEdges.buildings().get(3).getAddress(),"1220 Linden Dr.");
	
		assertEquals(buildingsAndEdges.buildings().get(4).getName(),"Middleton Building");
		assertEquals(buildingsAndEdges.buildings().get(4).getAddress(),"1305 Linden Dr.");
	}

	/**
	 * Tests that edges are read in correctly in the BuildingGraphReader class
	 */
	@Test
	public void test5() throws FileNotFoundException {

		BuildingGraphReader testReader = new BuildingGraphReader();
                BuildingGraphReaderInterface.FileData buildingsAndEdges = testReader.readDataFromFile("smallTest.dot");

		assertEquals(buildingsAndEdges.edges().size(),4);

		assertEquals(buildingsAndEdges.edges().get(0).srcName(),"Medical Sciences");
		assertEquals(buildingsAndEdges.edges().get(0).destName(),"Brogden Psychology Building");
		assertEquals(buildingsAndEdges.edges().get(0).dist(),0.2);

		assertEquals(buildingsAndEdges.edges().get(1).srcName(),"Medical Sciences");
                assertEquals(buildingsAndEdges.edges().get(1).destName(),"Service Memorial Institute");
                assertEquals(buildingsAndEdges.edges().get(1).dist(),0.0);

		assertEquals(buildingsAndEdges.edges().get(2).srcName(),"Medical Sciences");
                assertEquals(buildingsAndEdges.edges().get(2).destName(),"Van Hise Hall");
                assertEquals(buildingsAndEdges.edges().get(2).dist(),0.1);

                assertEquals(buildingsAndEdges.edges().get(3).srcName(),"Medical Sciences");
                assertEquals(buildingsAndEdges.edges().get(3).destName(),"Middleton Building");
                assertEquals(buildingsAndEdges.edges().get(3).dist(),0.08);
	}

	/**
	 *Tests integration of DW and BD by ensuring that a file is correctly loaded as a result of the loadData method in BD which depends on the implementation of the DW 
	 */
	@Test
	public void IntegrationTest1() {
	
		BuildingGraphReader reader = new BuildingGraphReader();
                AdvancedGraph graph = new AdvancedGraph();
                CampusNavigatorBackend testBD = new CampusNavigatorBackend(graph,reader);
		
		testBD.loadData("smallTest.dot");

		assertTrue(testBD.containsBuilding("Medical Sciences"));
	}

	/**
	 *Tests integration of FD and BD by making use of the LB command in FD that depends on the loadData method in BD
	 *Ensures the correct message is displayed when file is successfully loaded
	 */
	@Test
	public void IntegrationTest2() {
	
		TextUITester uiTester = new TextUITester("LB\nsmallTest.dot\nQ");
		BuildingGraphReader reader = new BuildingGraphReader();
		AdvancedGraph graph = new AdvancedGraph();
                CampusNavigatorBackend testBD = new CampusNavigatorBackend(graph,reader);
                Scanner sc = new Scanner(System.in);
                CampusNavigatorFrontend testFD = new CampusNavigatorFrontend(sc, testBD);
                testFD.runCommandLoop();
                String systemOutput = uiTester.checkOutput();

		assertTrue(systemOutput.contains("Loaded Data!"));
	}

	/**
	 *Reviews the FD and ensures that the proper message is displayed when the LB command is entered
	 */
	@Test
	public void CodeReviewOfFrontendDeveloperTest1() {
	
		TextUITester uiTester = new TextUITester("LB\nQ\nQ");
		CampusNavigatorBackendFD testBD = new CampusNavigatorBackendFD();
		Scanner sc = new Scanner(System.in);
		CampusNavigatorFrontend testFD = new CampusNavigatorFrontend(sc, testBD);
		testFD.runCommandLoop();
		String systemOutput = uiTester.checkOutput();

		assertTrue(systemOutput.contains("File Name:"));
	}

	/**
	 *
	 */
	@Test
	public void CodeReviewOfFrontendDeveloperTest2() {
	
		TextUITester uiTester = new TextUITester("AB\ndone\nQ");
		CampusNavigatorBackendFD testBD = new CampusNavigatorBackendFD();
                Scanner sc = new Scanner(System.in);
                CampusNavigatorFrontend testFD = new CampusNavigatorFrontend(sc, testBD);
                testFD.runCommandLoop();
                String systemOutput = uiTester.checkOutput();

		assertTrue(systemOutput.contains("Add Buildings To Dataset (Type \"done\" When Finished)..."));
	}

}
