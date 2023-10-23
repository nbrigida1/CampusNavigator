// --== CS400 File Header Information ==--
// Name: Hunter Heller
// Email: hheller2@wisc.edu
// Group and Team: CM Blue
// Group TA: Karan Grover
// Lecturer: Florian
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * Loads in a list of Buildings and their Edges from a file in a DOT format.
 * 
 * @author Hunter Heller
 */
public class BuildingGraphReader implements BuildingGraphReaderInterface {

	/**
	 * Default constructor for BuldingGraphReader
	 */
	public BuildingGraphReader() {}
	
	 /**
     * Reads in all Buildings and their Edges of the graph
     * from a DOT file at the provided file name.
     *
     * @param fileName The file name at which data should be read from
     * @return All Buildings and Edges from the file as a Record
     * @throws FileNotFoundException if the file does not exist
     */
	public FileData readDataFromFile(String filename) throws FileNotFoundException {
		
		List<String> refNames = new ArrayList<String>();
		List<BuildingInterface> buildings = new ArrayList<BuildingInterface>();
		List<Edge> edges = new ArrayList<Edge>();
		FileData buildingsAndEdges = new FileData(buildings, edges);
		Scanner in = new Scanner(new File(filename));
		
		
		
		while (in.hasNextLine()) {
			
			String line = in.nextLine();
			
			//if it's a building
			if(line.contains("address")) {
				
				String[] split = line.split("\"");
				buildings.add(new Building(split[1], split[3]));
				
				String[] split2 = line.split(" ");
				refNames.add(split2[0]);
			}
			//if it's an edge
			if(line.contains("--")) {
				
				String[] split = line.split(" ");
				String refSrcName = split[0];
				String refDestName = split[2];
				String srcName = "";
				String destName= "";
				
				//loop to get the names of each building in the edge
				for(int i = 0; i < refNames.size(); i++) {
					
					if(refSrcName.equals(refNames.get(i))) {
						srcName = buildings.get(i).getName();
					}
					if(refDestName.equals(refNames.get(i))) {
						destName = buildings.get(i).getName();
					}
				}
				
				String[] split2 = line.split("=");
				
				//distance is split as "0.08]" so use substring to get rid of "]"
				//ends up being "0.08"	
				String distance = split2[2].substring(0,split2[2].length() - 2);
				
				//convert string to double
				double dist = Double.valueOf(distance);
				
				edges.add(new Edge(srcName, destName, dist));
			}
		}
		
		return buildingsAndEdges;
	}

}

