/**
 * The BuildingInterface is a boilerplate for the Building class
 * which has mutable attributes name and address. It also
 * overrides the Object class's equals() and toString().
 */
public interface BuildingInterface {
//  public Building(String name, String address)


    public String getName();
    public String getAddress();


    public void setName(String name);
    public void setAddress(String address);


    /**
     * Determines whether an Object is a Building and has the
     * same name. Buildings with the SAME NAME but DIFFERENT
     * ADDRESSES are considered EQUAL.
     *
     * @param obj Any Object
     * @return true if obj is a Building and has the SAME NAME,
     *         otherwise false
     */
    public boolean equals(Object obj); // @Override
    public int hashCode();             // @Override
    public String toString();          // @Override
}
