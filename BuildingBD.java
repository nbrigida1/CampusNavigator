/**
 * Mock implementation of Building data type for backend Development
 * @author Nicholas Brigida
 * @version 4/22/22
 */
public class BuildingBD implements BuildingInterface{
    /**
     * Constructor for mock Building class that sets an initial name and address
     * @param name name of the building
     * @param address address of the building
     */
    public BuildingBD(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
    private String name;
    private String address;

    /**
     * returns the name of the building as a string
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns address of the building as a string
     * @return address
     */
    @Override
    public String getAddress() {
        return address;
    }

    /**
     * overwrites the name of the building to the parameter
     * @param name the new name of the building
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * overwrites the address of the building as the paramater
     * @param address the new address of the building
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
