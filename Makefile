run:  CampusNavigatorFrontend.class
	java CampusNavigatorFrontend

runTests:
	make runAlgorithmEngineerTests
	make runBackendDeveloperTests
	make runDataWranglerTests
	make runFrontendDeveloperTests

runFrontendDeveloperTests: Compile.class

runBackendDeveloperTests: CampusNavigatorBackend.class CampusNavigatorTests.class
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

CampusNavigatorBackend.class: Interfaces.class
	javac CampusNavigatorBackend.java

Interfaces.class:
	javac GraphADT.java
	javac AdvancedGraphInterface.java
	javac BuildingGraphReaderInterface.java
	javac BuildingInterface.java
	javac CampusNavigatorBackendInterface.java


CampusNavigatorTests.class: Interfaces.class
	javac BuildingBD.java
	javac BuildingGraphReaderBD.java
	javac AdvancedGraphBD.java
	
runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java \
	-jar junit5.jar \
	-cp . \
	-c AlgorithmEngineerTests

AlgorithmEngineerTests.class: AdvancedGraph.class
	javac AlgorithmEngineerTests.java \
	-cp .:junit5.jar 

AdvancedGraph.class: AdvancedGraph.java DataWranglerTests.class
	javac AdvancedGraph.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --select-class=DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java Building.class BuildingGraphReader.class
	javac -cp .:junit5.jar DataWranglerTests.java

BuildingGraphReader.class: BuildingGraphReader.java BuildingGraphReaderInterface.java
	javac BuildingGraphReader.java BuildingGraphReaderInterface.java

Building.class: Building.java BuildingInterface.java
	javac Building.java BuildingInterface.java

CampusNavigatorFrontend.class:CampusNavigatorFrontend.java
	javac CampusNavigatorFrontend.java
Compile.class:
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar -cp . -c FrontendDeveloperTests
clean:
	rm *.class
