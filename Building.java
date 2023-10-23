// --== CS400 File Header Information ==--
// Name: Hunter Heller
// Email: hheller2@wisc.edu
// Group and Team: CM Blue
// Group TA: Karan Grover
// Lecturer: Florian
// Notes to Grader: None

/**
 * Building class which has mutable attributes name and address. It also overrides the 
 * Object class's equals() and toString().
 * 
 * @author Hunter Heller
 */
public class Building implements BuildingInterface {

	private String name;
	private String address;
	
	/**
	 * Constructor for Building object
	 * 
	 * @param name - name of building
	 * @param address - address of building
	 */
	public Building(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	/**
	 * Getter for the name of the building
	 * 
	 * @return name of the building
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for the address of the building
	 * 
	 * @return address of the building
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Setter for the name of the building
	 * 
	 * @param name - name to be changed to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for the address of the building
	 * 
	 * @param address - address to be changed to
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	
	/**
     * Determines whether an Object is a Building and has the
     * same name. Buildings with the SAME NAME but DIFFERENT
     * ADDRESSES are considered EQUAL.
     *
     * @param obj Any Object
     * @return true if obj is a Building and has the SAME NAME,
     *         otherwise false
     */
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof Building) {
			Building other = (Building) (obj);
			return this.name.equals(other.name);
		}
		
		return false;
	}

	/*
	 * Generates a hash code for the Building which
	 * is the hash code of it's name.
	 * 
	 * @return The Building's name's hash code
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * Converts a Building to a string and returns the string as "Name: .... - Address: ...."
	 * 
	 * @return string of Building
	 */
	@Override
	public String toString() {
		return String.format("%s (%s)", name, address);
	}
}

