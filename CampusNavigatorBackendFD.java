import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CampusNavigatorBackendFD implements  CampusNavigatorBackendInterface{
  

  @Override
  public boolean loadData(String fileName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addBuildings(List<BuildingInterface> buildingNames) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeBuildings(List<String> buildingNames) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void generatePaths(List<String> buildingNames) {
    // TODO Auto-generated method stub
    buildingNames.add(1, "Chemistry_Building");

  }

  @Override
  public List<List<BuildingInterface>> getPaths() {
    BuildingFD a = new BuildingFD("Smith","");
    BuildingFD b = new BuildingFD("BuisnessLibrary","");
    BuildingFD c =  new BuildingFD("Memorial Union","");
    List<List<BuildingInterface>> ans = new ArrayList<List<BuildingInterface>>();
    List<BuildingInterface> ans1 = new ArrayList<>();
    ans1.add(a);
    ans1.add(b);
    ans1.add(c);
    ans.add(ans1);
    ans.add(ans1);
    return ans;
  }

  @Override
  public boolean containsBuilding(String buildingName) {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean containsEdge(String srcName, String destName) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void generateMST(String buildingName) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getMstString() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean addEdge(String pred, String succ, double weight) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeEdge(String pred, String succ) {
    // TODO Auto-generated method stub
    return false;
  }

}
