import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class FrontendDeveloperTests {
/**
 * This test checks if the Frontend is printing the right message when the whole app starts and when
 *  the user quits 
 */
  @Test
  void test1() {
    TextUITester uiTester = new TextUITester("Q");
    CampusNavigatorBackendFD backendTest = new CampusNavigatorBackendFD();

    Scanner sc = new Scanner(System.in);
    CampusNavigatorFrontend testFront = new CampusNavigatorFrontend(sc, backendTest);

    testFront.runCommandLoop();
    String systemOutput = uiTester.checkOutput();

    assertTrue(systemOutput.contains("Choose a command from the list below:"));
    assertTrue(systemOutput.contains("[AB]: Add Building"));
    assertTrue(systemOutput.contains("Thank you for using CM’s Campus Navigator!"));
    assertTrue(systemOutput.contains("[LB]: Load Building Data"));
    assertTrue(systemOutput.contains("Choose a command from the list below:"));
  }

/**
 * This tests if the Load buildings data is working properly and if it requsts the user for a file
 * name that is sent to the backend
 */
  @Test
  void test2() {
    TextUITester uiTester = new TextUITester("LB\na\nQ");
    CampusNavigatorBackendFD backendTest = new CampusNavigatorBackendFD();

    Scanner sc = new Scanner(System.in);
    CampusNavigatorFrontend testFront = new CampusNavigatorFrontend(sc, backendTest);

    testFront.runCommandLoop();
    String systemOutput = uiTester.checkOutput();

    assertTrue(systemOutput.contains("Enter the file to load:"));

  }

  /**
   * This tests the test output for generate paths and Print path methods and checks also if it is
   * using the right backend method and if the output is being presented properly
   */
  @Test
  void test3() {
    TextUITester uiTester = new TextUITester("GP\nmemorial\nsmith\ndone\nPP\nQ");
    CampusNavigatorBackendFD backendTest = new CampusNavigatorBackendFD();

    Scanner sc = new Scanner(System.in);
    CampusNavigatorFrontend testFront = new CampusNavigatorFrontend(sc, backendTest);

    testFront.runCommandLoop();
    String systemOutput = uiTester.checkOutput();

    assertTrue(systemOutput.contains("Enter the Building you want to add to your path:"));
    assertTrue(systemOutput.contains("The path has been generated"));
    assertTrue(systemOutput.contains("Smith=>BuisnessLibrary=>Memorial"));


  }

  /**
   * This tester is testing The Generates MST and checks if it is being generated without errors
   */
  @Test
  void test4() {
    TextUITester uiTester = new TextUITester("GM\nMemorial\nQ");
    CampusNavigatorBackendFD backendTest = new CampusNavigatorBackendFD();

    Scanner sc = new Scanner(System.in);
    CampusNavigatorFrontend testFront = new CampusNavigatorFrontend(sc, backendTest);

    testFront.runCommandLoop();
    String systemOutput = uiTester.checkOutput();

    assertTrue(systemOutput.contains("The MST has been generated"));



  }
  /**
   *this tests Add building and Remove Building method and takes input till done is inputed
   *and checks if it asks the user for the things it should being name and address
   */
  @Test
  void test5() {
    TextUITester uiTester = new TextUITester("AB\nMemorial\nlakeshore\n1\n2\ndone\nRB\ndone\nQ");
    CampusNavigatorBackendFD backendTest = new CampusNavigatorBackendFD();

    Scanner sc = new Scanner(System.in);
    CampusNavigatorFrontend testFront = new CampusNavigatorFrontend(sc, backendTest);

    testFront.runCommandLoop();
    String systemOutput = uiTester.checkOutput();

    assertTrue(systemOutput.contains(
        "Enter the Building and Address you want to add to the database and type done when done:"));
    assertTrue(systemOutput.contains("Enter the Building you want to remove from the Dataset:"));




  }

}

