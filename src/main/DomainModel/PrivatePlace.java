package main.DomainModel;

/*
* Class that represent the concrete object of the Private Places.
* It keeps all the infos (attributes) and actions (methods) related with the object and modifications that can be made on it.
*/
public class PrivatePlace extends Place {
    private final String name;
    PlaceType type;
    protected int ownerId;
    protected String ownerName;

    public PrivatePlace(int id, String city, String name, String address, int placeCapacity,
                        boolean indoor, PlaceType type, int ownerId, String ownerName) {
        super(id, city, address, placeCapacity, indoor);
        this.name = name;
        this.type = type;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
    }

    public String getOwnerName() { return ownerName; }
    public int getOwnerId() { return ownerId; }
    public String getName() {
        return name;
    }
    public PlaceType getType() {
        return type;
    }
}
